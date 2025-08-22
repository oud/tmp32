package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.GarantieAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Garantie;
import com.mycompany.myapp.repository.GarantieRepository;
import com.mycompany.myapp.service.dto.GarantieDTO;
import com.mycompany.myapp.service.mapper.GarantieMapper;
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
 * Integration tests for the {@link GarantieResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GarantieResourceIT {

    private static final String DEFAULT_CODE_GARANTIE_TECHNIQUE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_GARANTIE_TECHNIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ETAT_GARANTIE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ETAT_GARANTIE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ADHESION_GARANTIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ADHESION_GARANTIE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RADIATION_GARANTIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RADIATION_GARANTIE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CODE_ASSUREUR = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ASSUREUR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_FORMULE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_FORMULE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_PACK = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PACK = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_PACK = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_PACK = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE_PACK = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_PACK = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_SOUS_PACK = "AAAAAAAAAA";
    private static final String UPDATED_CODE_SOUS_PACK = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_SOUS_PACK = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_SOUS_PACK = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE_SOUS_PACK = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_SOUS_PACK = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_TYPE_PRESTATIONS = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TYPE_PRESTATIONS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/garanties";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private GarantieRepository garantieRepository;

    @Autowired
    private GarantieMapper garantieMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGarantieMockMvc;

    private Garantie garantie;

    private Garantie insertedGarantie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Garantie createEntity() {
        return new Garantie()
            .codeGarantieTechnique(DEFAULT_CODE_GARANTIE_TECHNIQUE)
            .codeEtatGarantie(DEFAULT_CODE_ETAT_GARANTIE)
            .dateAdhesionGarantie(DEFAULT_DATE_ADHESION_GARANTIE)
            .dateRadiationGarantie(DEFAULT_DATE_RADIATION_GARANTIE)
            .codeAssureur(DEFAULT_CODE_ASSUREUR)
            .codeFormule(DEFAULT_CODE_FORMULE)
            .codePack(DEFAULT_CODE_PACK)
            .typePack(DEFAULT_TYPE_PACK)
            .titrePack(DEFAULT_TITRE_PACK)
            .codeSousPack(DEFAULT_CODE_SOUS_PACK)
            .typeSousPack(DEFAULT_TYPE_SOUS_PACK)
            .titreSousPack(DEFAULT_TITRE_SOUS_PACK)
            .codeTypePrestations(DEFAULT_CODE_TYPE_PRESTATIONS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Garantie createUpdatedEntity() {
        return new Garantie()
            .codeGarantieTechnique(UPDATED_CODE_GARANTIE_TECHNIQUE)
            .codeEtatGarantie(UPDATED_CODE_ETAT_GARANTIE)
            .dateAdhesionGarantie(UPDATED_DATE_ADHESION_GARANTIE)
            .dateRadiationGarantie(UPDATED_DATE_RADIATION_GARANTIE)
            .codeAssureur(UPDATED_CODE_ASSUREUR)
            .codeFormule(UPDATED_CODE_FORMULE)
            .codePack(UPDATED_CODE_PACK)
            .typePack(UPDATED_TYPE_PACK)
            .titrePack(UPDATED_TITRE_PACK)
            .codeSousPack(UPDATED_CODE_SOUS_PACK)
            .typeSousPack(UPDATED_TYPE_SOUS_PACK)
            .titreSousPack(UPDATED_TITRE_SOUS_PACK)
            .codeTypePrestations(UPDATED_CODE_TYPE_PRESTATIONS);
    }

    @BeforeEach
    void initTest() {
        garantie = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedGarantie != null) {
            garantieRepository.delete(insertedGarantie);
            insertedGarantie = null;
        }
    }

    @Test
    @Transactional
    void createGarantie() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);
        var returnedGarantieDTO = om.readValue(
            restGarantieMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            GarantieDTO.class
        );

        // Validate the Garantie in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedGarantie = garantieMapper.toEntity(returnedGarantieDTO);
        assertGarantieUpdatableFieldsEquals(returnedGarantie, getPersistedGarantie(returnedGarantie));

        insertedGarantie = returnedGarantie;
    }

    @Test
    @Transactional
    void createGarantieWithExistingId() throws Exception {
        // Create the Garantie with an existing ID
        garantie.setId(1L);
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeGarantieTechniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setCodeGarantieTechnique(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeEtatGarantieIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setCodeEtatGarantie(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateAdhesionGarantieIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setDateAdhesionGarantie(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateRadiationGarantieIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setDateRadiationGarantie(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeAssureurIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setCodeAssureur(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeFormuleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setCodeFormule(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodePackIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setCodePack(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypePackIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setTypePack(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitrePackIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setTitrePack(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeSousPackIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setCodeSousPack(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeSousPackIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setTypeSousPack(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitreSousPackIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setTitreSousPack(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeTypePrestationsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        garantie.setCodeTypePrestations(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGaranties() throws Exception {
        // Initialize the database
        insertedGarantie = garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList
        restGarantieMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(garantie.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeGarantieTechnique").value(hasItem(DEFAULT_CODE_GARANTIE_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].codeEtatGarantie").value(hasItem(DEFAULT_CODE_ETAT_GARANTIE)))
            .andExpect(jsonPath("$.[*].dateAdhesionGarantie").value(hasItem(DEFAULT_DATE_ADHESION_GARANTIE.toString())))
            .andExpect(jsonPath("$.[*].dateRadiationGarantie").value(hasItem(DEFAULT_DATE_RADIATION_GARANTIE.toString())))
            .andExpect(jsonPath("$.[*].codeAssureur").value(hasItem(DEFAULT_CODE_ASSUREUR)))
            .andExpect(jsonPath("$.[*].codeFormule").value(hasItem(DEFAULT_CODE_FORMULE)))
            .andExpect(jsonPath("$.[*].codePack").value(hasItem(DEFAULT_CODE_PACK)))
            .andExpect(jsonPath("$.[*].typePack").value(hasItem(DEFAULT_TYPE_PACK)))
            .andExpect(jsonPath("$.[*].titrePack").value(hasItem(DEFAULT_TITRE_PACK)))
            .andExpect(jsonPath("$.[*].codeSousPack").value(hasItem(DEFAULT_CODE_SOUS_PACK)))
            .andExpect(jsonPath("$.[*].typeSousPack").value(hasItem(DEFAULT_TYPE_SOUS_PACK)))
            .andExpect(jsonPath("$.[*].titreSousPack").value(hasItem(DEFAULT_TITRE_SOUS_PACK)))
            .andExpect(jsonPath("$.[*].codeTypePrestations").value(hasItem(DEFAULT_CODE_TYPE_PRESTATIONS)));
    }

    @Test
    @Transactional
    void getGarantie() throws Exception {
        // Initialize the database
        insertedGarantie = garantieRepository.saveAndFlush(garantie);

        // Get the garantie
        restGarantieMockMvc
            .perform(get(ENTITY_API_URL_ID, garantie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(garantie.getId().intValue()))
            .andExpect(jsonPath("$.codeGarantieTechnique").value(DEFAULT_CODE_GARANTIE_TECHNIQUE))
            .andExpect(jsonPath("$.codeEtatGarantie").value(DEFAULT_CODE_ETAT_GARANTIE))
            .andExpect(jsonPath("$.dateAdhesionGarantie").value(DEFAULT_DATE_ADHESION_GARANTIE.toString()))
            .andExpect(jsonPath("$.dateRadiationGarantie").value(DEFAULT_DATE_RADIATION_GARANTIE.toString()))
            .andExpect(jsonPath("$.codeAssureur").value(DEFAULT_CODE_ASSUREUR))
            .andExpect(jsonPath("$.codeFormule").value(DEFAULT_CODE_FORMULE))
            .andExpect(jsonPath("$.codePack").value(DEFAULT_CODE_PACK))
            .andExpect(jsonPath("$.typePack").value(DEFAULT_TYPE_PACK))
            .andExpect(jsonPath("$.titrePack").value(DEFAULT_TITRE_PACK))
            .andExpect(jsonPath("$.codeSousPack").value(DEFAULT_CODE_SOUS_PACK))
            .andExpect(jsonPath("$.typeSousPack").value(DEFAULT_TYPE_SOUS_PACK))
            .andExpect(jsonPath("$.titreSousPack").value(DEFAULT_TITRE_SOUS_PACK))
            .andExpect(jsonPath("$.codeTypePrestations").value(DEFAULT_CODE_TYPE_PRESTATIONS));
    }

    @Test
    @Transactional
    void getNonExistingGarantie() throws Exception {
        // Get the garantie
        restGarantieMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingGarantie() throws Exception {
        // Initialize the database
        insertedGarantie = garantieRepository.saveAndFlush(garantie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the garantie
        Garantie updatedGarantie = garantieRepository.findById(garantie.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedGarantie are not directly saved in db
        em.detach(updatedGarantie);
        updatedGarantie
            .codeGarantieTechnique(UPDATED_CODE_GARANTIE_TECHNIQUE)
            .codeEtatGarantie(UPDATED_CODE_ETAT_GARANTIE)
            .dateAdhesionGarantie(UPDATED_DATE_ADHESION_GARANTIE)
            .dateRadiationGarantie(UPDATED_DATE_RADIATION_GARANTIE)
            .codeAssureur(UPDATED_CODE_ASSUREUR)
            .codeFormule(UPDATED_CODE_FORMULE)
            .codePack(UPDATED_CODE_PACK)
            .typePack(UPDATED_TYPE_PACK)
            .titrePack(UPDATED_TITRE_PACK)
            .codeSousPack(UPDATED_CODE_SOUS_PACK)
            .typeSousPack(UPDATED_TYPE_SOUS_PACK)
            .titreSousPack(UPDATED_TITRE_SOUS_PACK)
            .codeTypePrestations(UPDATED_CODE_TYPE_PRESTATIONS);
        GarantieDTO garantieDTO = garantieMapper.toDto(updatedGarantie);

        restGarantieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, garantieDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(garantieDTO))
            )
            .andExpect(status().isOk());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedGarantieToMatchAllProperties(updatedGarantie);
    }

    @Test
    @Transactional
    void putNonExistingGarantie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        garantie.setId(longCount.incrementAndGet());

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGarantieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, garantieDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(garantieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGarantie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        garantie.setId(longCount.incrementAndGet());

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGarantieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(garantieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGarantie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        garantie.setId(longCount.incrementAndGet());

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGarantieMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGarantieWithPatch() throws Exception {
        // Initialize the database
        insertedGarantie = garantieRepository.saveAndFlush(garantie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the garantie using partial update
        Garantie partialUpdatedGarantie = new Garantie();
        partialUpdatedGarantie.setId(garantie.getId());

        partialUpdatedGarantie
            .codeGarantieTechnique(UPDATED_CODE_GARANTIE_TECHNIQUE)
            .codeEtatGarantie(UPDATED_CODE_ETAT_GARANTIE)
            .dateAdhesionGarantie(UPDATED_DATE_ADHESION_GARANTIE)
            .dateRadiationGarantie(UPDATED_DATE_RADIATION_GARANTIE)
            .codeAssureur(UPDATED_CODE_ASSUREUR)
            .codeFormule(UPDATED_CODE_FORMULE)
            .typeSousPack(UPDATED_TYPE_SOUS_PACK);

        restGarantieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGarantie.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGarantie))
            )
            .andExpect(status().isOk());

        // Validate the Garantie in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGarantieUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedGarantie, garantie), getPersistedGarantie(garantie));
    }

    @Test
    @Transactional
    void fullUpdateGarantieWithPatch() throws Exception {
        // Initialize the database
        insertedGarantie = garantieRepository.saveAndFlush(garantie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the garantie using partial update
        Garantie partialUpdatedGarantie = new Garantie();
        partialUpdatedGarantie.setId(garantie.getId());

        partialUpdatedGarantie
            .codeGarantieTechnique(UPDATED_CODE_GARANTIE_TECHNIQUE)
            .codeEtatGarantie(UPDATED_CODE_ETAT_GARANTIE)
            .dateAdhesionGarantie(UPDATED_DATE_ADHESION_GARANTIE)
            .dateRadiationGarantie(UPDATED_DATE_RADIATION_GARANTIE)
            .codeAssureur(UPDATED_CODE_ASSUREUR)
            .codeFormule(UPDATED_CODE_FORMULE)
            .codePack(UPDATED_CODE_PACK)
            .typePack(UPDATED_TYPE_PACK)
            .titrePack(UPDATED_TITRE_PACK)
            .codeSousPack(UPDATED_CODE_SOUS_PACK)
            .typeSousPack(UPDATED_TYPE_SOUS_PACK)
            .titreSousPack(UPDATED_TITRE_SOUS_PACK)
            .codeTypePrestations(UPDATED_CODE_TYPE_PRESTATIONS);

        restGarantieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGarantie.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGarantie))
            )
            .andExpect(status().isOk());

        // Validate the Garantie in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGarantieUpdatableFieldsEquals(partialUpdatedGarantie, getPersistedGarantie(partialUpdatedGarantie));
    }

    @Test
    @Transactional
    void patchNonExistingGarantie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        garantie.setId(longCount.incrementAndGet());

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGarantieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, garantieDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(garantieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGarantie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        garantie.setId(longCount.incrementAndGet());

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGarantieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(garantieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGarantie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        garantie.setId(longCount.incrementAndGet());

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGarantieMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(garantieDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Garantie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGarantie() throws Exception {
        // Initialize the database
        insertedGarantie = garantieRepository.saveAndFlush(garantie);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the garantie
        restGarantieMockMvc
            .perform(delete(ENTITY_API_URL_ID, garantie.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return garantieRepository.count();
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

    protected Garantie getPersistedGarantie(Garantie garantie) {
        return garantieRepository.findById(garantie.getId()).orElseThrow();
    }

    protected void assertPersistedGarantieToMatchAllProperties(Garantie expectedGarantie) {
        assertGarantieAllPropertiesEquals(expectedGarantie, getPersistedGarantie(expectedGarantie));
    }

    protected void assertPersistedGarantieToMatchUpdatableProperties(Garantie expectedGarantie) {
        assertGarantieAllUpdatablePropertiesEquals(expectedGarantie, getPersistedGarantie(expectedGarantie));
    }
}
