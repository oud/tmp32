package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.ProduitAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Produit;
import com.mycompany.myapp.repository.ProduitRepository;
import com.mycompany.myapp.service.dto.ProduitDTO;
import com.mycompany.myapp.service.mapper.ProduitMapper;
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
 * Integration tests for the {@link ProduitResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProduitResourceIT {

    private static final String DEFAULT_CODE_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PRODUIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ADHESION_PRODUIT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ADHESION_PRODUIT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RADIATION_PRODUIT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RADIATION_PRODUIT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CODE_FORMULE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_FORMULE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_FAMILLE_RISQUE_FORMULE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_FAMILLE_RISQUE_FORMULE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE_FORMULE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_FORMULE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_FORMULE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FORMULE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ETAT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ETAT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/produits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitMapper produitMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProduitMockMvc;

    private Produit produit;

    private Produit insertedProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Produit createEntity() {
        return new Produit()
            .codeProduit(DEFAULT_CODE_PRODUIT)
            .dateAdhesionProduit(DEFAULT_DATE_ADHESION_PRODUIT)
            .dateRadiationProduit(DEFAULT_DATE_RADIATION_PRODUIT)
            .codeFormule(DEFAULT_CODE_FORMULE)
            .codeFamilleRisqueFormule(DEFAULT_CODE_FAMILLE_RISQUE_FORMULE)
            .titreFormule(DEFAULT_TITRE_FORMULE)
            .typeFormule(DEFAULT_TYPE_FORMULE)
            .codeEtat(DEFAULT_CODE_ETAT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Produit createUpdatedEntity() {
        return new Produit()
            .codeProduit(UPDATED_CODE_PRODUIT)
            .dateAdhesionProduit(UPDATED_DATE_ADHESION_PRODUIT)
            .dateRadiationProduit(UPDATED_DATE_RADIATION_PRODUIT)
            .codeFormule(UPDATED_CODE_FORMULE)
            .codeFamilleRisqueFormule(UPDATED_CODE_FAMILLE_RISQUE_FORMULE)
            .titreFormule(UPDATED_TITRE_FORMULE)
            .typeFormule(UPDATED_TYPE_FORMULE)
            .codeEtat(UPDATED_CODE_ETAT);
    }

    @BeforeEach
    void initTest() {
        produit = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedProduit != null) {
            produitRepository.delete(insertedProduit);
            insertedProduit = null;
        }
    }

    @Test
    @Transactional
    void createProduit() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);
        var returnedProduitDTO = om.readValue(
            restProduitMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProduitDTO.class
        );

        // Validate the Produit in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedProduit = produitMapper.toEntity(returnedProduitDTO);
        assertProduitUpdatableFieldsEquals(returnedProduit, getPersistedProduit(returnedProduit));

        insertedProduit = returnedProduit;
    }

    @Test
    @Transactional
    void createProduitWithExistingId() throws Exception {
        // Create the Produit with an existing ID
        produit.setId(1L);
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeProduitIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        produit.setCodeProduit(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateAdhesionProduitIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        produit.setDateAdhesionProduit(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeFormuleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        produit.setCodeFormule(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeFamilleRisqueFormuleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        produit.setCodeFamilleRisqueFormule(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitreFormuleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        produit.setTitreFormule(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeFormuleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        produit.setTypeFormule(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeEtatIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        produit.setCodeEtat(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProduits() throws Exception {
        // Initialize the database
        insertedProduit = produitRepository.saveAndFlush(produit);

        // Get all the produitList
        restProduitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produit.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeProduit").value(hasItem(DEFAULT_CODE_PRODUIT)))
            .andExpect(jsonPath("$.[*].dateAdhesionProduit").value(hasItem(DEFAULT_DATE_ADHESION_PRODUIT.toString())))
            .andExpect(jsonPath("$.[*].dateRadiationProduit").value(hasItem(DEFAULT_DATE_RADIATION_PRODUIT.toString())))
            .andExpect(jsonPath("$.[*].codeFormule").value(hasItem(DEFAULT_CODE_FORMULE)))
            .andExpect(jsonPath("$.[*].codeFamilleRisqueFormule").value(hasItem(DEFAULT_CODE_FAMILLE_RISQUE_FORMULE)))
            .andExpect(jsonPath("$.[*].titreFormule").value(hasItem(DEFAULT_TITRE_FORMULE)))
            .andExpect(jsonPath("$.[*].typeFormule").value(hasItem(DEFAULT_TYPE_FORMULE)))
            .andExpect(jsonPath("$.[*].codeEtat").value(hasItem(DEFAULT_CODE_ETAT)));
    }

    @Test
    @Transactional
    void getProduit() throws Exception {
        // Initialize the database
        insertedProduit = produitRepository.saveAndFlush(produit);

        // Get the produit
        restProduitMockMvc
            .perform(get(ENTITY_API_URL_ID, produit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(produit.getId().intValue()))
            .andExpect(jsonPath("$.codeProduit").value(DEFAULT_CODE_PRODUIT))
            .andExpect(jsonPath("$.dateAdhesionProduit").value(DEFAULT_DATE_ADHESION_PRODUIT.toString()))
            .andExpect(jsonPath("$.dateRadiationProduit").value(DEFAULT_DATE_RADIATION_PRODUIT.toString()))
            .andExpect(jsonPath("$.codeFormule").value(DEFAULT_CODE_FORMULE))
            .andExpect(jsonPath("$.codeFamilleRisqueFormule").value(DEFAULT_CODE_FAMILLE_RISQUE_FORMULE))
            .andExpect(jsonPath("$.titreFormule").value(DEFAULT_TITRE_FORMULE))
            .andExpect(jsonPath("$.typeFormule").value(DEFAULT_TYPE_FORMULE))
            .andExpect(jsonPath("$.codeEtat").value(DEFAULT_CODE_ETAT));
    }

    @Test
    @Transactional
    void getNonExistingProduit() throws Exception {
        // Get the produit
        restProduitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProduit() throws Exception {
        // Initialize the database
        insertedProduit = produitRepository.saveAndFlush(produit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the produit
        Produit updatedProduit = produitRepository.findById(produit.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProduit are not directly saved in db
        em.detach(updatedProduit);
        updatedProduit
            .codeProduit(UPDATED_CODE_PRODUIT)
            .dateAdhesionProduit(UPDATED_DATE_ADHESION_PRODUIT)
            .dateRadiationProduit(UPDATED_DATE_RADIATION_PRODUIT)
            .codeFormule(UPDATED_CODE_FORMULE)
            .codeFamilleRisqueFormule(UPDATED_CODE_FAMILLE_RISQUE_FORMULE)
            .titreFormule(UPDATED_TITRE_FORMULE)
            .typeFormule(UPDATED_TYPE_FORMULE)
            .codeEtat(UPDATED_CODE_ETAT);
        ProduitDTO produitDTO = produitMapper.toDto(updatedProduit);

        restProduitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, produitDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO))
            )
            .andExpect(status().isOk());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProduitToMatchAllProperties(updatedProduit);
    }

    @Test
    @Transactional
    void putNonExistingProduit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produit.setId(longCount.incrementAndGet());

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, produitDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProduit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produit.setId(longCount.incrementAndGet());

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(produitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProduit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produit.setId(longCount.incrementAndGet());

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProduitWithPatch() throws Exception {
        // Initialize the database
        insertedProduit = produitRepository.saveAndFlush(produit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the produit using partial update
        Produit partialUpdatedProduit = new Produit();
        partialUpdatedProduit.setId(produit.getId());

        partialUpdatedProduit
            .codeProduit(UPDATED_CODE_PRODUIT)
            .codeFamilleRisqueFormule(UPDATED_CODE_FAMILLE_RISQUE_FORMULE)
            .titreFormule(UPDATED_TITRE_FORMULE)
            .typeFormule(UPDATED_TYPE_FORMULE)
            .codeEtat(UPDATED_CODE_ETAT);

        restProduitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProduit))
            )
            .andExpect(status().isOk());

        // Validate the Produit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProduitUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedProduit, produit), getPersistedProduit(produit));
    }

    @Test
    @Transactional
    void fullUpdateProduitWithPatch() throws Exception {
        // Initialize the database
        insertedProduit = produitRepository.saveAndFlush(produit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the produit using partial update
        Produit partialUpdatedProduit = new Produit();
        partialUpdatedProduit.setId(produit.getId());

        partialUpdatedProduit
            .codeProduit(UPDATED_CODE_PRODUIT)
            .dateAdhesionProduit(UPDATED_DATE_ADHESION_PRODUIT)
            .dateRadiationProduit(UPDATED_DATE_RADIATION_PRODUIT)
            .codeFormule(UPDATED_CODE_FORMULE)
            .codeFamilleRisqueFormule(UPDATED_CODE_FAMILLE_RISQUE_FORMULE)
            .titreFormule(UPDATED_TITRE_FORMULE)
            .typeFormule(UPDATED_TYPE_FORMULE)
            .codeEtat(UPDATED_CODE_ETAT);

        restProduitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProduit))
            )
            .andExpect(status().isOk());

        // Validate the Produit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProduitUpdatableFieldsEquals(partialUpdatedProduit, getPersistedProduit(partialUpdatedProduit));
    }

    @Test
    @Transactional
    void patchNonExistingProduit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produit.setId(longCount.incrementAndGet());

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, produitDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(produitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProduit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produit.setId(longCount.incrementAndGet());

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(produitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProduit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produit.setId(longCount.incrementAndGet());

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(produitDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Produit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProduit() throws Exception {
        // Initialize the database
        insertedProduit = produitRepository.saveAndFlush(produit);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the produit
        restProduitMockMvc
            .perform(delete(ENTITY_API_URL_ID, produit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return produitRepository.count();
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

    protected Produit getPersistedProduit(Produit produit) {
        return produitRepository.findById(produit.getId()).orElseThrow();
    }

    protected void assertPersistedProduitToMatchAllProperties(Produit expectedProduit) {
        assertProduitAllPropertiesEquals(expectedProduit, getPersistedProduit(expectedProduit));
    }

    protected void assertPersistedProduitToMatchUpdatableProperties(Produit expectedProduit) {
        assertProduitAllUpdatablePropertiesEquals(expectedProduit, getPersistedProduit(expectedProduit));
    }
}
