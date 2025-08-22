package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.DemandeXRMAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DemandeXRM;
import com.mycompany.myapp.domain.enumeration.Status;
import com.mycompany.myapp.domain.enumeration.Status;
import com.mycompany.myapp.repository.DemandeXRMRepository;
import com.mycompany.myapp.service.dto.DemandeXRMDTO;
import com.mycompany.myapp.service.mapper.DemandeXRMMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DemandeXRMResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DemandeXRMResourceIT {

    private static final LocalTime DEFAULT_DATE_DEMANDE = LocalTime.NOON;
    private static final LocalTime UPDATED_DATE_DEMANDE = LocalTime.MAX.withNano(0);

    private static final LocalTime DEFAULT_DATE_ENVOI_AI = LocalTime.NOON;
    private static final LocalTime UPDATED_DATE_ENVOI_AI = LocalTime.MAX.withNano(0);

    private static final LocalTime DEFAULT_DATE_ENVOI_IVS = LocalTime.NOON;
    private static final LocalTime UPDATED_DATE_ENVOI_IVS = LocalTime.MAX.withNano(0);

    private static final Status DEFAULT_A_I = Status.RECU;
    private static final Status UPDATED_A_I = Status.TRAITE;

    private static final Status DEFAULT_I_VS = Status.RECU;
    private static final Status UPDATED_I_VS = Status.TRAITE;

    private static final String ENTITY_API_URL = "/api/demande-xrms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DemandeXRMRepository demandeXRMRepository;

    @Autowired
    private DemandeXRMMapper demandeXRMMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandeXRMMockMvc;

    private DemandeXRM demandeXRM;

    private DemandeXRM insertedDemandeXRM;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeXRM createEntity() {
        return new DemandeXRM()
            .dateDemande(DEFAULT_DATE_DEMANDE)
            .dateEnvoiAI(DEFAULT_DATE_ENVOI_AI)
            .dateEnvoiIVS(DEFAULT_DATE_ENVOI_IVS)
            .aI(DEFAULT_A_I)
            .iVS(DEFAULT_I_VS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeXRM createUpdatedEntity() {
        return new DemandeXRM()
            .dateDemande(UPDATED_DATE_DEMANDE)
            .dateEnvoiAI(UPDATED_DATE_ENVOI_AI)
            .dateEnvoiIVS(UPDATED_DATE_ENVOI_IVS)
            .aI(UPDATED_A_I)
            .iVS(UPDATED_I_VS);
    }

    @BeforeEach
    void initTest() {
        demandeXRM = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDemandeXRM != null) {
            demandeXRMRepository.delete(insertedDemandeXRM);
            insertedDemandeXRM = null;
        }
    }

    @Test
    @Transactional
    void createDemandeXRM() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DemandeXRM
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);
        var returnedDemandeXRMDTO = om.readValue(
            restDemandeXRMMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandeXRMDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DemandeXRMDTO.class
        );

        // Validate the DemandeXRM in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDemandeXRM = demandeXRMMapper.toEntity(returnedDemandeXRMDTO);
        assertDemandeXRMUpdatableFieldsEquals(returnedDemandeXRM, getPersistedDemandeXRM(returnedDemandeXRM));

        insertedDemandeXRM = returnedDemandeXRM;
    }

    @Test
    @Transactional
    void createDemandeXRMWithExistingId() throws Exception {
        // Create the DemandeXRM with an existing ID
        demandeXRM.setId(1L);
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeXRMMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandeXRMDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDemandeXRMS() throws Exception {
        // Initialize the database
        insertedDemandeXRM = demandeXRMRepository.saveAndFlush(demandeXRM);

        // Get all the demandeXRMList
        restDemandeXRMMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeXRM.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDemande").value(hasItem(DEFAULT_DATE_DEMANDE.toString())))
            .andExpect(jsonPath("$.[*].dateEnvoiAI").value(hasItem(DEFAULT_DATE_ENVOI_AI.toString())))
            .andExpect(jsonPath("$.[*].dateEnvoiIVS").value(hasItem(DEFAULT_DATE_ENVOI_IVS.toString())))
            .andExpect(jsonPath("$.[*].aI").value(hasItem(DEFAULT_A_I.toString())))
            .andExpect(jsonPath("$.[*].iVS").value(hasItem(DEFAULT_I_VS.toString())));
    }

    @Test
    @Transactional
    void getDemandeXRM() throws Exception {
        // Initialize the database
        insertedDemandeXRM = demandeXRMRepository.saveAndFlush(demandeXRM);

        // Get the demandeXRM
        restDemandeXRMMockMvc
            .perform(get(ENTITY_API_URL_ID, demandeXRM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demandeXRM.getId().intValue()))
            .andExpect(jsonPath("$.dateDemande").value(DEFAULT_DATE_DEMANDE.toString()))
            .andExpect(jsonPath("$.dateEnvoiAI").value(DEFAULT_DATE_ENVOI_AI.toString()))
            .andExpect(jsonPath("$.dateEnvoiIVS").value(DEFAULT_DATE_ENVOI_IVS.toString()))
            .andExpect(jsonPath("$.aI").value(DEFAULT_A_I.toString()))
            .andExpect(jsonPath("$.iVS").value(DEFAULT_I_VS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDemandeXRM() throws Exception {
        // Get the demandeXRM
        restDemandeXRMMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDemandeXRM() throws Exception {
        // Initialize the database
        insertedDemandeXRM = demandeXRMRepository.saveAndFlush(demandeXRM);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the demandeXRM
        DemandeXRM updatedDemandeXRM = demandeXRMRepository.findById(demandeXRM.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDemandeXRM are not directly saved in db
        em.detach(updatedDemandeXRM);
        updatedDemandeXRM
            .dateDemande(UPDATED_DATE_DEMANDE)
            .dateEnvoiAI(UPDATED_DATE_ENVOI_AI)
            .dateEnvoiIVS(UPDATED_DATE_ENVOI_IVS)
            .aI(UPDATED_A_I)
            .iVS(UPDATED_I_VS);
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(updatedDemandeXRM);

        restDemandeXRMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandeXRMDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(demandeXRMDTO))
            )
            .andExpect(status().isOk());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDemandeXRMToMatchAllProperties(updatedDemandeXRM);
    }

    @Test
    @Transactional
    void putNonExistingDemandeXRM() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        demandeXRM.setId(longCount.incrementAndGet());

        // Create the DemandeXRM
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeXRMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandeXRMDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(demandeXRMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDemandeXRM() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        demandeXRM.setId(longCount.incrementAndGet());

        // Create the DemandeXRM
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeXRMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(demandeXRMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDemandeXRM() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        demandeXRM.setId(longCount.incrementAndGet());

        // Create the DemandeXRM
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeXRMMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(demandeXRMDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDemandeXRMWithPatch() throws Exception {
        // Initialize the database
        insertedDemandeXRM = demandeXRMRepository.saveAndFlush(demandeXRM);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the demandeXRM using partial update
        DemandeXRM partialUpdatedDemandeXRM = new DemandeXRM();
        partialUpdatedDemandeXRM.setId(demandeXRM.getId());

        partialUpdatedDemandeXRM.dateDemande(UPDATED_DATE_DEMANDE).dateEnvoiIVS(UPDATED_DATE_ENVOI_IVS).aI(UPDATED_A_I).iVS(UPDATED_I_VS);

        restDemandeXRMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandeXRM.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDemandeXRM))
            )
            .andExpect(status().isOk());

        // Validate the DemandeXRM in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDemandeXRMUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDemandeXRM, demandeXRM),
            getPersistedDemandeXRM(demandeXRM)
        );
    }

    @Test
    @Transactional
    void fullUpdateDemandeXRMWithPatch() throws Exception {
        // Initialize the database
        insertedDemandeXRM = demandeXRMRepository.saveAndFlush(demandeXRM);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the demandeXRM using partial update
        DemandeXRM partialUpdatedDemandeXRM = new DemandeXRM();
        partialUpdatedDemandeXRM.setId(demandeXRM.getId());

        partialUpdatedDemandeXRM
            .dateDemande(UPDATED_DATE_DEMANDE)
            .dateEnvoiAI(UPDATED_DATE_ENVOI_AI)
            .dateEnvoiIVS(UPDATED_DATE_ENVOI_IVS)
            .aI(UPDATED_A_I)
            .iVS(UPDATED_I_VS);

        restDemandeXRMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandeXRM.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDemandeXRM))
            )
            .andExpect(status().isOk());

        // Validate the DemandeXRM in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDemandeXRMUpdatableFieldsEquals(partialUpdatedDemandeXRM, getPersistedDemandeXRM(partialUpdatedDemandeXRM));
    }

    @Test
    @Transactional
    void patchNonExistingDemandeXRM() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        demandeXRM.setId(longCount.incrementAndGet());

        // Create the DemandeXRM
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeXRMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, demandeXRMDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(demandeXRMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDemandeXRM() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        demandeXRM.setId(longCount.incrementAndGet());

        // Create the DemandeXRM
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeXRMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(demandeXRMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDemandeXRM() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        demandeXRM.setId(longCount.incrementAndGet());

        // Create the DemandeXRM
        DemandeXRMDTO demandeXRMDTO = demandeXRMMapper.toDto(demandeXRM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeXRMMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(demandeXRMDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DemandeXRM in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDemandeXRM() throws Exception {
        // Initialize the database
        insertedDemandeXRM = demandeXRMRepository.saveAndFlush(demandeXRM);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the demandeXRM
        restDemandeXRMMockMvc
            .perform(delete(ENTITY_API_URL_ID, demandeXRM.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return demandeXRMRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected DemandeXRM getPersistedDemandeXRM(DemandeXRM demandeXRM) {
        return demandeXRMRepository.findById(demandeXRM.getId()).orElseThrow();
    }

    protected void assertPersistedDemandeXRMToMatchAllProperties(DemandeXRM expectedDemandeXRM) {
        assertDemandeXRMAllPropertiesEquals(expectedDemandeXRM, getPersistedDemandeXRM(expectedDemandeXRM));
    }

    protected void assertPersistedDemandeXRMToMatchUpdatableProperties(DemandeXRM expectedDemandeXRM) {
        assertDemandeXRMAllUpdatablePropertiesEquals(expectedDemandeXRM, getPersistedDemandeXRM(expectedDemandeXRM));
    }
}
