package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.GroupeAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Groupe;
import com.mycompany.myapp.repository.GroupeRepository;
import com.mycompany.myapp.service.dto.GroupeDTO;
import com.mycompany.myapp.service.mapper.GroupeMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link GroupeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GroupeResourceIT {

    private static final String DEFAULT_CODE_GROUPE_ASSURES = "AAAAAAAAAA";
    private static final String UPDATED_CODE_GROUPE_ASSURES = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_GROUPE_POPULATION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_GROUPE_POPULATION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_GROUPE_ASSURES = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_GROUPE_ASSURES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEBUT_PERIODE_GROUPE_ASSURES = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT_PERIODE_GROUPE_ASSURES = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ETAT_GROUPE_ASSURES = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ETAT_GROUPE_ASSURES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/groupes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private GroupeMapper groupeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGroupeMockMvc;

    private Groupe groupe;

    private Groupe insertedGroupe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Groupe createEntity() {
        return new Groupe()
            .codeGroupeAssures(DEFAULT_CODE_GROUPE_ASSURES)
            .codeGroupePopulation(DEFAULT_CODE_GROUPE_POPULATION)
            .typeGroupeAssures(DEFAULT_TYPE_GROUPE_ASSURES)
            .dateDebutPeriodeGroupeAssures(DEFAULT_DATE_DEBUT_PERIODE_GROUPE_ASSURES)
            .libelleGroupeAssuresTypeAutre(DEFAULT_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE)
            .codeEtatGroupeAssures(DEFAULT_CODE_ETAT_GROUPE_ASSURES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Groupe createUpdatedEntity() {
        return new Groupe()
            .codeGroupeAssures(UPDATED_CODE_GROUPE_ASSURES)
            .codeGroupePopulation(UPDATED_CODE_GROUPE_POPULATION)
            .typeGroupeAssures(UPDATED_TYPE_GROUPE_ASSURES)
            .dateDebutPeriodeGroupeAssures(UPDATED_DATE_DEBUT_PERIODE_GROUPE_ASSURES)
            .libelleGroupeAssuresTypeAutre(UPDATED_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE)
            .codeEtatGroupeAssures(UPDATED_CODE_ETAT_GROUPE_ASSURES);
    }

    @BeforeEach
    void initTest() {
        groupe = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedGroupe != null) {
            groupeRepository.delete(insertedGroupe);
            insertedGroupe = null;
        }
    }

    @Test
    @Transactional
    void createGroupe() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);
        var returnedGroupeDTO = om.readValue(
            restGroupeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            GroupeDTO.class
        );

        // Validate the Groupe in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedGroupe = groupeMapper.toEntity(returnedGroupeDTO);
        assertGroupeUpdatableFieldsEquals(returnedGroupe, getPersistedGroupe(returnedGroupe));

        insertedGroupe = returnedGroupe;
    }

    @Test
    @Transactional
    void createGroupeWithExistingId() throws Exception {
        // Create the Groupe with an existing ID
        groupe.setId(1L);
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeGroupeAssuresIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        groupe.setCodeGroupeAssures(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeGroupePopulationIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        groupe.setCodeGroupePopulation(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeGroupeAssuresIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        groupe.setTypeGroupeAssures(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateDebutPeriodeGroupeAssuresIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        groupe.setDateDebutPeriodeGroupeAssures(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLibelleGroupeAssuresTypeAutreIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        groupe.setLibelleGroupeAssuresTypeAutre(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeEtatGroupeAssuresIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        groupe.setCodeEtatGroupeAssures(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGroupes() throws Exception {
        // Initialize the database
        insertedGroupe = groupeRepository.saveAndFlush(groupe);

        // Get all the groupeList
        restGroupeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupe.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeGroupeAssures").value(hasItem(DEFAULT_CODE_GROUPE_ASSURES)))
            .andExpect(jsonPath("$.[*].codeGroupePopulation").value(hasItem(DEFAULT_CODE_GROUPE_POPULATION)))
            .andExpect(jsonPath("$.[*].typeGroupeAssures").value(hasItem(DEFAULT_TYPE_GROUPE_ASSURES)))
            .andExpect(jsonPath("$.[*].dateDebutPeriodeGroupeAssures").value(hasItem(DEFAULT_DATE_DEBUT_PERIODE_GROUPE_ASSURES.toString())))
            .andExpect(jsonPath("$.[*].libelleGroupeAssuresTypeAutre").value(hasItem(DEFAULT_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE)))
            .andExpect(jsonPath("$.[*].codeEtatGroupeAssures").value(hasItem(DEFAULT_CODE_ETAT_GROUPE_ASSURES)));
    }

    @Test
    @Transactional
    void getGroupe() throws Exception {
        // Initialize the database
        insertedGroupe = groupeRepository.saveAndFlush(groupe);

        // Get the groupe
        restGroupeMockMvc
            .perform(get(ENTITY_API_URL_ID, groupe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupe.getId().intValue()))
            .andExpect(jsonPath("$.codeGroupeAssures").value(DEFAULT_CODE_GROUPE_ASSURES))
            .andExpect(jsonPath("$.codeGroupePopulation").value(DEFAULT_CODE_GROUPE_POPULATION))
            .andExpect(jsonPath("$.typeGroupeAssures").value(DEFAULT_TYPE_GROUPE_ASSURES))
            .andExpect(jsonPath("$.dateDebutPeriodeGroupeAssures").value(DEFAULT_DATE_DEBUT_PERIODE_GROUPE_ASSURES.toString()))
            .andExpect(jsonPath("$.libelleGroupeAssuresTypeAutre").value(DEFAULT_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE))
            .andExpect(jsonPath("$.codeEtatGroupeAssures").value(DEFAULT_CODE_ETAT_GROUPE_ASSURES));
    }

    @Test
    @Transactional
    void getNonExistingGroupe() throws Exception {
        // Get the groupe
        restGroupeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingGroupe() throws Exception {
        // Initialize the database
        insertedGroupe = groupeRepository.saveAndFlush(groupe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the groupe
        Groupe updatedGroupe = groupeRepository.findById(groupe.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedGroupe are not directly saved in db
        em.detach(updatedGroupe);
        updatedGroupe
            .codeGroupeAssures(UPDATED_CODE_GROUPE_ASSURES)
            .codeGroupePopulation(UPDATED_CODE_GROUPE_POPULATION)
            .typeGroupeAssures(UPDATED_TYPE_GROUPE_ASSURES)
            .dateDebutPeriodeGroupeAssures(UPDATED_DATE_DEBUT_PERIODE_GROUPE_ASSURES)
            .libelleGroupeAssuresTypeAutre(UPDATED_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE)
            .codeEtatGroupeAssures(UPDATED_CODE_ETAT_GROUPE_ASSURES);
        GroupeDTO groupeDTO = groupeMapper.toDto(updatedGroupe);

        restGroupeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, groupeDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedGroupeToMatchAllProperties(updatedGroupe);
    }

    @Test
    @Transactional
    void putNonExistingGroupe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        groupe.setId(longCount.incrementAndGet());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, groupeDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGroupe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        groupe.setId(longCount.incrementAndGet());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGroupe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        groupe.setId(longCount.incrementAndGet());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGroupeWithPatch() throws Exception {
        // Initialize the database
        insertedGroupe = groupeRepository.saveAndFlush(groupe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the groupe using partial update
        Groupe partialUpdatedGroupe = new Groupe();
        partialUpdatedGroupe.setId(groupe.getId());

        partialUpdatedGroupe.codeGroupeAssures(UPDATED_CODE_GROUPE_ASSURES).codeEtatGroupeAssures(UPDATED_CODE_ETAT_GROUPE_ASSURES);

        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGroupe.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGroupe))
            )
            .andExpect(status().isOk());

        // Validate the Groupe in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGroupeUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedGroupe, groupe), getPersistedGroupe(groupe));
    }

    @Test
    @Transactional
    void fullUpdateGroupeWithPatch() throws Exception {
        // Initialize the database
        insertedGroupe = groupeRepository.saveAndFlush(groupe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the groupe using partial update
        Groupe partialUpdatedGroupe = new Groupe();
        partialUpdatedGroupe.setId(groupe.getId());

        partialUpdatedGroupe
            .codeGroupeAssures(UPDATED_CODE_GROUPE_ASSURES)
            .codeGroupePopulation(UPDATED_CODE_GROUPE_POPULATION)
            .typeGroupeAssures(UPDATED_TYPE_GROUPE_ASSURES)
            .dateDebutPeriodeGroupeAssures(UPDATED_DATE_DEBUT_PERIODE_GROUPE_ASSURES)
            .libelleGroupeAssuresTypeAutre(UPDATED_LIBELLE_GROUPE_ASSURES_TYPE_AUTRE)
            .codeEtatGroupeAssures(UPDATED_CODE_ETAT_GROUPE_ASSURES);

        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGroupe.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGroupe))
            )
            .andExpect(status().isOk());

        // Validate the Groupe in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGroupeUpdatableFieldsEquals(partialUpdatedGroupe, getPersistedGroupe(partialUpdatedGroupe));
    }

    @Test
    @Transactional
    void patchNonExistingGroupe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        groupe.setId(longCount.incrementAndGet());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, groupeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGroupe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        groupe.setId(longCount.incrementAndGet());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGroupe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        groupe.setId(longCount.incrementAndGet());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(groupeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Groupe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGroupe() throws Exception {
        // Initialize the database
        insertedGroupe = groupeRepository.saveAndFlush(groupe);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the groupe
        restGroupeMockMvc
            .perform(delete(ENTITY_API_URL_ID, groupe.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return groupeRepository.count();
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

    protected Groupe getPersistedGroupe(Groupe groupe) {
        return groupeRepository.findById(groupe.getId()).orElseThrow();
    }

    protected void assertPersistedGroupeToMatchAllProperties(Groupe expectedGroupe) {
        assertGroupeAllPropertiesEquals(expectedGroupe, getPersistedGroupe(expectedGroupe));
    }

    protected void assertPersistedGroupeToMatchUpdatableProperties(Groupe expectedGroupe) {
        assertGroupeAllUpdatablePropertiesEquals(expectedGroupe, getPersistedGroupe(expectedGroupe));
    }
}
