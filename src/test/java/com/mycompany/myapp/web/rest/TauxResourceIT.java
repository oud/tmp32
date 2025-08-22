package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.TauxAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Taux;
import com.mycompany.myapp.repository.TauxRepository;
import com.mycompany.myapp.service.dto.TauxDTO;
import com.mycompany.myapp.service.mapper.TauxMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link TauxResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TauxResourceIT {

    private static final String DEFAULT_CODE_VARIABLE_DECLARATIVE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_VARIABLE_DECLARATIVE = "BBBBBBBBBB";

    private static final String DEFAULT_UNITE_VARIABLE_DECLARATIVE = "AAAAAAAAAA";
    private static final String UPDATED_UNITE_VARIABLE_DECLARATIVE = "BBBBBBBBBB";

    private static final String DEFAULT_VALEUR_FACTEUR_MONTANT = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR_FACTEUR_MONTANT = "BBBBBBBBBB";

    private static final String DEFAULT_VALEUR_FACTEUR_TAUX = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR_FACTEUR_TAUX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tauxes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TauxRepository tauxRepository;

    @Autowired
    private TauxMapper tauxMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTauxMockMvc;

    private Taux taux;

    private Taux insertedTaux;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taux createEntity() {
        return new Taux()
            .codeVariableDeclarative(DEFAULT_CODE_VARIABLE_DECLARATIVE)
            .uniteVariableDeclarative(DEFAULT_UNITE_VARIABLE_DECLARATIVE)
            .valeurFacteurMontant(DEFAULT_VALEUR_FACTEUR_MONTANT)
            .valeurFacteurTaux(DEFAULT_VALEUR_FACTEUR_TAUX);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taux createUpdatedEntity() {
        return new Taux()
            .codeVariableDeclarative(UPDATED_CODE_VARIABLE_DECLARATIVE)
            .uniteVariableDeclarative(UPDATED_UNITE_VARIABLE_DECLARATIVE)
            .valeurFacteurMontant(UPDATED_VALEUR_FACTEUR_MONTANT)
            .valeurFacteurTaux(UPDATED_VALEUR_FACTEUR_TAUX);
    }

    @BeforeEach
    void initTest() {
        taux = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedTaux != null) {
            tauxRepository.delete(insertedTaux);
            insertedTaux = null;
        }
    }

    @Test
    @Transactional
    void createTaux() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Taux
        TauxDTO tauxDTO = tauxMapper.toDto(taux);
        var returnedTauxDTO = om.readValue(
            restTauxMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TauxDTO.class
        );

        // Validate the Taux in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaux = tauxMapper.toEntity(returnedTauxDTO);
        assertTauxUpdatableFieldsEquals(returnedTaux, getPersistedTaux(returnedTaux));

        insertedTaux = returnedTaux;
    }

    @Test
    @Transactional
    void createTauxWithExistingId() throws Exception {
        // Create the Taux with an existing ID
        taux.setId(1L);
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTauxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeVariableDeclarativeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        taux.setCodeVariableDeclarative(null);

        // Create the Taux, which fails.
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        restTauxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUniteVariableDeclarativeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        taux.setUniteVariableDeclarative(null);

        // Create the Taux, which fails.
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        restTauxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValeurFacteurTauxIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        taux.setValeurFacteurTaux(null);

        // Create the Taux, which fails.
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        restTauxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTauxes() throws Exception {
        // Initialize the database
        insertedTaux = tauxRepository.saveAndFlush(taux);

        // Get all the tauxList
        restTauxMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taux.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeVariableDeclarative").value(hasItem(DEFAULT_CODE_VARIABLE_DECLARATIVE)))
            .andExpect(jsonPath("$.[*].uniteVariableDeclarative").value(hasItem(DEFAULT_UNITE_VARIABLE_DECLARATIVE)))
            .andExpect(jsonPath("$.[*].valeurFacteurMontant").value(hasItem(DEFAULT_VALEUR_FACTEUR_MONTANT)))
            .andExpect(jsonPath("$.[*].valeurFacteurTaux").value(hasItem(DEFAULT_VALEUR_FACTEUR_TAUX)));
    }

    @Test
    @Transactional
    void getTaux() throws Exception {
        // Initialize the database
        insertedTaux = tauxRepository.saveAndFlush(taux);

        // Get the taux
        restTauxMockMvc
            .perform(get(ENTITY_API_URL_ID, taux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taux.getId().intValue()))
            .andExpect(jsonPath("$.codeVariableDeclarative").value(DEFAULT_CODE_VARIABLE_DECLARATIVE))
            .andExpect(jsonPath("$.uniteVariableDeclarative").value(DEFAULT_UNITE_VARIABLE_DECLARATIVE))
            .andExpect(jsonPath("$.valeurFacteurMontant").value(DEFAULT_VALEUR_FACTEUR_MONTANT))
            .andExpect(jsonPath("$.valeurFacteurTaux").value(DEFAULT_VALEUR_FACTEUR_TAUX));
    }

    @Test
    @Transactional
    void getNonExistingTaux() throws Exception {
        // Get the taux
        restTauxMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaux() throws Exception {
        // Initialize the database
        insertedTaux = tauxRepository.saveAndFlush(taux);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taux
        Taux updatedTaux = tauxRepository.findById(taux.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaux are not directly saved in db
        em.detach(updatedTaux);
        updatedTaux
            .codeVariableDeclarative(UPDATED_CODE_VARIABLE_DECLARATIVE)
            .uniteVariableDeclarative(UPDATED_UNITE_VARIABLE_DECLARATIVE)
            .valeurFacteurMontant(UPDATED_VALEUR_FACTEUR_MONTANT)
            .valeurFacteurTaux(UPDATED_VALEUR_FACTEUR_TAUX);
        TauxDTO tauxDTO = tauxMapper.toDto(updatedTaux);

        restTauxMockMvc
            .perform(put(ENTITY_API_URL_ID, tauxDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isOk());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTauxToMatchAllProperties(updatedTaux);
    }

    @Test
    @Transactional
    void putNonExistingTaux() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taux.setId(longCount.incrementAndGet());

        // Create the Taux
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTauxMockMvc
            .perform(put(ENTITY_API_URL_ID, tauxDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaux() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taux.setId(longCount.incrementAndGet());

        // Create the Taux
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTauxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tauxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaux() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taux.setId(longCount.incrementAndGet());

        // Create the Taux
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTauxMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTauxWithPatch() throws Exception {
        // Initialize the database
        insertedTaux = tauxRepository.saveAndFlush(taux);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taux using partial update
        Taux partialUpdatedTaux = new Taux();
        partialUpdatedTaux.setId(taux.getId());

        partialUpdatedTaux
            .codeVariableDeclarative(UPDATED_CODE_VARIABLE_DECLARATIVE)
            .uniteVariableDeclarative(UPDATED_UNITE_VARIABLE_DECLARATIVE)
            .valeurFacteurMontant(UPDATED_VALEUR_FACTEUR_MONTANT);

        restTauxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaux.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaux))
            )
            .andExpect(status().isOk());

        // Validate the Taux in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTauxUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedTaux, taux), getPersistedTaux(taux));
    }

    @Test
    @Transactional
    void fullUpdateTauxWithPatch() throws Exception {
        // Initialize the database
        insertedTaux = tauxRepository.saveAndFlush(taux);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taux using partial update
        Taux partialUpdatedTaux = new Taux();
        partialUpdatedTaux.setId(taux.getId());

        partialUpdatedTaux
            .codeVariableDeclarative(UPDATED_CODE_VARIABLE_DECLARATIVE)
            .uniteVariableDeclarative(UPDATED_UNITE_VARIABLE_DECLARATIVE)
            .valeurFacteurMontant(UPDATED_VALEUR_FACTEUR_MONTANT)
            .valeurFacteurTaux(UPDATED_VALEUR_FACTEUR_TAUX);

        restTauxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaux.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaux))
            )
            .andExpect(status().isOk());

        // Validate the Taux in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTauxUpdatableFieldsEquals(partialUpdatedTaux, getPersistedTaux(partialUpdatedTaux));
    }

    @Test
    @Transactional
    void patchNonExistingTaux() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taux.setId(longCount.incrementAndGet());

        // Create the Taux
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTauxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tauxDTO.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tauxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaux() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taux.setId(longCount.incrementAndGet());

        // Create the Taux
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTauxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tauxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaux() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taux.setId(longCount.incrementAndGet());

        // Create the Taux
        TauxDTO tauxDTO = tauxMapper.toDto(taux);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTauxMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tauxDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taux in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaux() throws Exception {
        // Initialize the database
        insertedTaux = tauxRepository.saveAndFlush(taux);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taux
        restTauxMockMvc
            .perform(delete(ENTITY_API_URL_ID, taux.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return tauxRepository.count();
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

    protected Taux getPersistedTaux(Taux taux) {
        return tauxRepository.findById(taux.getId()).orElseThrow();
    }

    protected void assertPersistedTauxToMatchAllProperties(Taux expectedTaux) {
        assertTauxAllPropertiesEquals(expectedTaux, getPersistedTaux(expectedTaux));
    }

    protected void assertPersistedTauxToMatchUpdatableProperties(Taux expectedTaux) {
        assertTauxAllUpdatablePropertiesEquals(expectedTaux, getPersistedTaux(expectedTaux));
    }
}
