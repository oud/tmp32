package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.ContratAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.repository.ContratRepository;
import com.mycompany.myapp.service.dto.ContratDTO;
import com.mycompany.myapp.service.mapper.ContratMapper;
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
 * Integration tests for the {@link ContratResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContratResourceIT {

    private static final String DEFAULT_NUMERO_CONTRAT_COLLECTIF = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONTRAT_COLLECTIF = "BBBBBBBBBB";

    private static final String DEFAULT_MIGRE = "AAAAAAAAAA";
    private static final String UPDATED_MIGRE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ENTITE_RATTACHEMENT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ENTITE_RATTACHEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CENTRE_GESTION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CENTRE_GESTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_GROUPE_GESTION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_GROUPE_GESTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_RESEAU_DISTRIBUTION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_RESEAU_DISTRIBUTION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_CONTRAT_COLLECTIF = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CONTRAT_COLLECTIF = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT_CONTRAT = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_CONTRAT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_EFFET_PREMIERE_SOUSCRIPTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EFFET_PREMIERE_SOUSCRIPTION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_EFFET_DERNIERE_RESILIATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EFFET_DERNIERE_RESILIATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MOTIF_RESILIATION = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF_RESILIATION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_NATURE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_NATURE_COUVERTURE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_OFFRE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_OFFRE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_VERSION_OFFRE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_VERSION_OFFRE = "BBBBBBBBBB";

    private static final String DEFAULT_ECHEANCE_PRINCIPALE = "AAAAAAAAAA";
    private static final String UPDATED_ECHEANCE_PRINCIPALE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ORGANISME_PORTEUR_RISQUE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ORGANISME_PORTEUR_RISQUE = "BBBBBBBBBB";

    private static final String DEFAULT_INDICATEUR_PORTEUR_RISQUE = "AAAAAAAAAA";
    private static final String UPDATED_INDICATEUR_PORTEUR_RISQUE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ORGANISME_DELEGATAIRE_COTISATIONS = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ORGANISME_DELEGATAIRE_COTISATIONS = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_PREMIER_MOIS_COTISATION_AUTORISE = "AAAAAAAAAA";
    private static final String UPDATED_DATE_PREMIER_MOIS_COTISATION_AUTORISE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_OPERATION_NIVEAU_0 = 1;
    private static final Integer UPDATED_NUMERO_OPERATION_NIVEAU_0 = 2;

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contrats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ContratMapper contratMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContratMockMvc;

    private Contrat contrat;

    private Contrat insertedContrat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contrat createEntity() {
        return new Contrat()
            .numeroContratCollectif(DEFAULT_NUMERO_CONTRAT_COLLECTIF)
            .migre(DEFAULT_MIGRE)
            .codeEntiteRattachement(DEFAULT_CODE_ENTITE_RATTACHEMENT)
            .codeCentreGestion(DEFAULT_CODE_CENTRE_GESTION)
            .codeGroupeGestion(DEFAULT_CODE_GROUPE_GESTION)
            .codeReseauDistribution(DEFAULT_CODE_RESEAU_DISTRIBUTION)
            .typeContratCollectif(DEFAULT_TYPE_CONTRAT_COLLECTIF)
            .etatContrat(DEFAULT_ETAT_CONTRAT)
            .dateEffetPremiereSouscription(DEFAULT_DATE_EFFET_PREMIERE_SOUSCRIPTION)
            .dateEffetDerniereResiliation(DEFAULT_DATE_EFFET_DERNIERE_RESILIATION)
            .motifResiliation(DEFAULT_MOTIF_RESILIATION)
            .codeNatureCouverture(DEFAULT_CODE_NATURE_COUVERTURE)
            .codeOffre(DEFAULT_CODE_OFFRE)
            .numeroVersionOffre(DEFAULT_NUMERO_VERSION_OFFRE)
            .echeancePrincipale(DEFAULT_ECHEANCE_PRINCIPALE)
            .codeOrganismePorteurRisque(DEFAULT_CODE_ORGANISME_PORTEUR_RISQUE)
            .indicateurPorteurRisque(DEFAULT_INDICATEUR_PORTEUR_RISQUE)
            .codeOrganismeProducteurFicheDsn(DEFAULT_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN)
            .codeOrganismeDelegataireCotisations(DEFAULT_CODE_ORGANISME_DELEGATAIRE_COTISATIONS)
            .codeOrganismeDelegatairePrestations(DEFAULT_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS)
            .datePremierMoisCotisationAutorise(DEFAULT_DATE_PREMIER_MOIS_COTISATION_AUTORISE)
            .numeroOperationNiveau0(DEFAULT_NUMERO_OPERATION_NIVEAU_0)
            .statut(DEFAULT_STATUT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contrat createUpdatedEntity() {
        return new Contrat()
            .numeroContratCollectif(UPDATED_NUMERO_CONTRAT_COLLECTIF)
            .migre(UPDATED_MIGRE)
            .codeEntiteRattachement(UPDATED_CODE_ENTITE_RATTACHEMENT)
            .codeCentreGestion(UPDATED_CODE_CENTRE_GESTION)
            .codeGroupeGestion(UPDATED_CODE_GROUPE_GESTION)
            .codeReseauDistribution(UPDATED_CODE_RESEAU_DISTRIBUTION)
            .typeContratCollectif(UPDATED_TYPE_CONTRAT_COLLECTIF)
            .etatContrat(UPDATED_ETAT_CONTRAT)
            .dateEffetPremiereSouscription(UPDATED_DATE_EFFET_PREMIERE_SOUSCRIPTION)
            .dateEffetDerniereResiliation(UPDATED_DATE_EFFET_DERNIERE_RESILIATION)
            .motifResiliation(UPDATED_MOTIF_RESILIATION)
            .codeNatureCouverture(UPDATED_CODE_NATURE_COUVERTURE)
            .codeOffre(UPDATED_CODE_OFFRE)
            .numeroVersionOffre(UPDATED_NUMERO_VERSION_OFFRE)
            .echeancePrincipale(UPDATED_ECHEANCE_PRINCIPALE)
            .codeOrganismePorteurRisque(UPDATED_CODE_ORGANISME_PORTEUR_RISQUE)
            .indicateurPorteurRisque(UPDATED_INDICATEUR_PORTEUR_RISQUE)
            .codeOrganismeProducteurFicheDsn(UPDATED_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN)
            .codeOrganismeDelegataireCotisations(UPDATED_CODE_ORGANISME_DELEGATAIRE_COTISATIONS)
            .codeOrganismeDelegatairePrestations(UPDATED_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS)
            .datePremierMoisCotisationAutorise(UPDATED_DATE_PREMIER_MOIS_COTISATION_AUTORISE)
            .numeroOperationNiveau0(UPDATED_NUMERO_OPERATION_NIVEAU_0)
            .statut(UPDATED_STATUT);
    }

    @BeforeEach
    void initTest() {
        contrat = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedContrat != null) {
            contratRepository.delete(insertedContrat);
            insertedContrat = null;
        }
    }

    @Test
    @Transactional
    void createContrat() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);
        var returnedContratDTO = om.readValue(
            restContratMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ContratDTO.class
        );

        // Validate the Contrat in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedContrat = contratMapper.toEntity(returnedContratDTO);
        assertContratUpdatableFieldsEquals(returnedContrat, getPersistedContrat(returnedContrat));

        insertedContrat = returnedContrat;
    }

    @Test
    @Transactional
    void createContratWithExistingId() throws Exception {
        // Create the Contrat with an existing ID
        contrat.setId(1L);
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNumeroContratCollectifIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setNumeroContratCollectif(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMigreIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setMigre(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeEntiteRattachementIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeEntiteRattachement(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeCentreGestionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeCentreGestion(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeGroupeGestionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeGroupeGestion(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeReseauDistributionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeReseauDistribution(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeContratCollectifIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setTypeContratCollectif(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEtatContratIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setEtatContrat(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateEffetPremiereSouscriptionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setDateEffetPremiereSouscription(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeNatureCouvertureIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeNatureCouverture(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeOffreIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeOffre(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumeroVersionOffreIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setNumeroVersionOffre(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEcheancePrincipaleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setEcheancePrincipale(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeOrganismePorteurRisqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeOrganismePorteurRisque(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIndicateurPorteurRisqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setIndicateurPorteurRisque(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeOrganismeProducteurFicheDsnIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeOrganismeProducteurFicheDsn(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeOrganismeDelegataireCotisationsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeOrganismeDelegataireCotisations(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeOrganismeDelegatairePrestationsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setCodeOrganismeDelegatairePrestations(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDatePremierMoisCotisationAutoriseIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setDatePremierMoisCotisationAutorise(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumeroOperationNiveau0IsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setNumeroOperationNiveau0(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatutIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contrat.setStatut(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContrats() throws Exception {
        // Initialize the database
        insertedContrat = contratRepository.saveAndFlush(contrat);

        // Get all the contratList
        restContratMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contrat.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroContratCollectif").value(hasItem(DEFAULT_NUMERO_CONTRAT_COLLECTIF)))
            .andExpect(jsonPath("$.[*].migre").value(hasItem(DEFAULT_MIGRE)))
            .andExpect(jsonPath("$.[*].codeEntiteRattachement").value(hasItem(DEFAULT_CODE_ENTITE_RATTACHEMENT)))
            .andExpect(jsonPath("$.[*].codeCentreGestion").value(hasItem(DEFAULT_CODE_CENTRE_GESTION)))
            .andExpect(jsonPath("$.[*].codeGroupeGestion").value(hasItem(DEFAULT_CODE_GROUPE_GESTION)))
            .andExpect(jsonPath("$.[*].codeReseauDistribution").value(hasItem(DEFAULT_CODE_RESEAU_DISTRIBUTION)))
            .andExpect(jsonPath("$.[*].typeContratCollectif").value(hasItem(DEFAULT_TYPE_CONTRAT_COLLECTIF)))
            .andExpect(jsonPath("$.[*].etatContrat").value(hasItem(DEFAULT_ETAT_CONTRAT)))
            .andExpect(jsonPath("$.[*].dateEffetPremiereSouscription").value(hasItem(DEFAULT_DATE_EFFET_PREMIERE_SOUSCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateEffetDerniereResiliation").value(hasItem(DEFAULT_DATE_EFFET_DERNIERE_RESILIATION.toString())))
            .andExpect(jsonPath("$.[*].motifResiliation").value(hasItem(DEFAULT_MOTIF_RESILIATION)))
            .andExpect(jsonPath("$.[*].codeNatureCouverture").value(hasItem(DEFAULT_CODE_NATURE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].codeOffre").value(hasItem(DEFAULT_CODE_OFFRE)))
            .andExpect(jsonPath("$.[*].numeroVersionOffre").value(hasItem(DEFAULT_NUMERO_VERSION_OFFRE)))
            .andExpect(jsonPath("$.[*].echeancePrincipale").value(hasItem(DEFAULT_ECHEANCE_PRINCIPALE)))
            .andExpect(jsonPath("$.[*].codeOrganismePorteurRisque").value(hasItem(DEFAULT_CODE_ORGANISME_PORTEUR_RISQUE)))
            .andExpect(jsonPath("$.[*].indicateurPorteurRisque").value(hasItem(DEFAULT_INDICATEUR_PORTEUR_RISQUE)))
            .andExpect(jsonPath("$.[*].codeOrganismeProducteurFicheDsn").value(hasItem(DEFAULT_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN)))
            .andExpect(jsonPath("$.[*].codeOrganismeDelegataireCotisations").value(hasItem(DEFAULT_CODE_ORGANISME_DELEGATAIRE_COTISATIONS)))
            .andExpect(jsonPath("$.[*].codeOrganismeDelegatairePrestations").value(hasItem(DEFAULT_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS)))
            .andExpect(jsonPath("$.[*].datePremierMoisCotisationAutorise").value(hasItem(DEFAULT_DATE_PREMIER_MOIS_COTISATION_AUTORISE)))
            .andExpect(jsonPath("$.[*].numeroOperationNiveau0").value(hasItem(DEFAULT_NUMERO_OPERATION_NIVEAU_0)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)));
    }

    @Test
    @Transactional
    void getContrat() throws Exception {
        // Initialize the database
        insertedContrat = contratRepository.saveAndFlush(contrat);

        // Get the contrat
        restContratMockMvc
            .perform(get(ENTITY_API_URL_ID, contrat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contrat.getId().intValue()))
            .andExpect(jsonPath("$.numeroContratCollectif").value(DEFAULT_NUMERO_CONTRAT_COLLECTIF))
            .andExpect(jsonPath("$.migre").value(DEFAULT_MIGRE))
            .andExpect(jsonPath("$.codeEntiteRattachement").value(DEFAULT_CODE_ENTITE_RATTACHEMENT))
            .andExpect(jsonPath("$.codeCentreGestion").value(DEFAULT_CODE_CENTRE_GESTION))
            .andExpect(jsonPath("$.codeGroupeGestion").value(DEFAULT_CODE_GROUPE_GESTION))
            .andExpect(jsonPath("$.codeReseauDistribution").value(DEFAULT_CODE_RESEAU_DISTRIBUTION))
            .andExpect(jsonPath("$.typeContratCollectif").value(DEFAULT_TYPE_CONTRAT_COLLECTIF))
            .andExpect(jsonPath("$.etatContrat").value(DEFAULT_ETAT_CONTRAT))
            .andExpect(jsonPath("$.dateEffetPremiereSouscription").value(DEFAULT_DATE_EFFET_PREMIERE_SOUSCRIPTION.toString()))
            .andExpect(jsonPath("$.dateEffetDerniereResiliation").value(DEFAULT_DATE_EFFET_DERNIERE_RESILIATION.toString()))
            .andExpect(jsonPath("$.motifResiliation").value(DEFAULT_MOTIF_RESILIATION))
            .andExpect(jsonPath("$.codeNatureCouverture").value(DEFAULT_CODE_NATURE_COUVERTURE))
            .andExpect(jsonPath("$.codeOffre").value(DEFAULT_CODE_OFFRE))
            .andExpect(jsonPath("$.numeroVersionOffre").value(DEFAULT_NUMERO_VERSION_OFFRE))
            .andExpect(jsonPath("$.echeancePrincipale").value(DEFAULT_ECHEANCE_PRINCIPALE))
            .andExpect(jsonPath("$.codeOrganismePorteurRisque").value(DEFAULT_CODE_ORGANISME_PORTEUR_RISQUE))
            .andExpect(jsonPath("$.indicateurPorteurRisque").value(DEFAULT_INDICATEUR_PORTEUR_RISQUE))
            .andExpect(jsonPath("$.codeOrganismeProducteurFicheDsn").value(DEFAULT_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN))
            .andExpect(jsonPath("$.codeOrganismeDelegataireCotisations").value(DEFAULT_CODE_ORGANISME_DELEGATAIRE_COTISATIONS))
            .andExpect(jsonPath("$.codeOrganismeDelegatairePrestations").value(DEFAULT_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS))
            .andExpect(jsonPath("$.datePremierMoisCotisationAutorise").value(DEFAULT_DATE_PREMIER_MOIS_COTISATION_AUTORISE))
            .andExpect(jsonPath("$.numeroOperationNiveau0").value(DEFAULT_NUMERO_OPERATION_NIVEAU_0))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT));
    }

    @Test
    @Transactional
    void getNonExistingContrat() throws Exception {
        // Get the contrat
        restContratMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingContrat() throws Exception {
        // Initialize the database
        insertedContrat = contratRepository.saveAndFlush(contrat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contrat
        Contrat updatedContrat = contratRepository.findById(contrat.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedContrat are not directly saved in db
        em.detach(updatedContrat);
        updatedContrat
            .numeroContratCollectif(UPDATED_NUMERO_CONTRAT_COLLECTIF)
            .migre(UPDATED_MIGRE)
            .codeEntiteRattachement(UPDATED_CODE_ENTITE_RATTACHEMENT)
            .codeCentreGestion(UPDATED_CODE_CENTRE_GESTION)
            .codeGroupeGestion(UPDATED_CODE_GROUPE_GESTION)
            .codeReseauDistribution(UPDATED_CODE_RESEAU_DISTRIBUTION)
            .typeContratCollectif(UPDATED_TYPE_CONTRAT_COLLECTIF)
            .etatContrat(UPDATED_ETAT_CONTRAT)
            .dateEffetPremiereSouscription(UPDATED_DATE_EFFET_PREMIERE_SOUSCRIPTION)
            .dateEffetDerniereResiliation(UPDATED_DATE_EFFET_DERNIERE_RESILIATION)
            .motifResiliation(UPDATED_MOTIF_RESILIATION)
            .codeNatureCouverture(UPDATED_CODE_NATURE_COUVERTURE)
            .codeOffre(UPDATED_CODE_OFFRE)
            .numeroVersionOffre(UPDATED_NUMERO_VERSION_OFFRE)
            .echeancePrincipale(UPDATED_ECHEANCE_PRINCIPALE)
            .codeOrganismePorteurRisque(UPDATED_CODE_ORGANISME_PORTEUR_RISQUE)
            .indicateurPorteurRisque(UPDATED_INDICATEUR_PORTEUR_RISQUE)
            .codeOrganismeProducteurFicheDsn(UPDATED_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN)
            .codeOrganismeDelegataireCotisations(UPDATED_CODE_ORGANISME_DELEGATAIRE_COTISATIONS)
            .codeOrganismeDelegatairePrestations(UPDATED_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS)
            .datePremierMoisCotisationAutorise(UPDATED_DATE_PREMIER_MOIS_COTISATION_AUTORISE)
            .numeroOperationNiveau0(UPDATED_NUMERO_OPERATION_NIVEAU_0)
            .statut(UPDATED_STATUT);
        ContratDTO contratDTO = contratMapper.toDto(updatedContrat);

        restContratMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contratDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO))
            )
            .andExpect(status().isOk());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedContratToMatchAllProperties(updatedContrat);
    }

    @Test
    @Transactional
    void putNonExistingContrat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contrat.setId(longCount.incrementAndGet());

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContratMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contratDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContrat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contrat.setId(longCount.incrementAndGet());

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContratMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(contratDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContrat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contrat.setId(longCount.incrementAndGet());

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContratMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContratWithPatch() throws Exception {
        // Initialize the database
        insertedContrat = contratRepository.saveAndFlush(contrat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contrat using partial update
        Contrat partialUpdatedContrat = new Contrat();
        partialUpdatedContrat.setId(contrat.getId());

        partialUpdatedContrat
            .migre(UPDATED_MIGRE)
            .codeCentreGestion(UPDATED_CODE_CENTRE_GESTION)
            .codeReseauDistribution(UPDATED_CODE_RESEAU_DISTRIBUTION)
            .dateEffetPremiereSouscription(UPDATED_DATE_EFFET_PREMIERE_SOUSCRIPTION)
            .dateEffetDerniereResiliation(UPDATED_DATE_EFFET_DERNIERE_RESILIATION)
            .codeOffre(UPDATED_CODE_OFFRE)
            .numeroVersionOffre(UPDATED_NUMERO_VERSION_OFFRE)
            .echeancePrincipale(UPDATED_ECHEANCE_PRINCIPALE)
            .codeOrganismeProducteurFicheDsn(UPDATED_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN)
            .codeOrganismeDelegataireCotisations(UPDATED_CODE_ORGANISME_DELEGATAIRE_COTISATIONS)
            .codeOrganismeDelegatairePrestations(UPDATED_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS)
            .statut(UPDATED_STATUT);

        restContratMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContrat.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContrat))
            )
            .andExpect(status().isOk());

        // Validate the Contrat in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContratUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedContrat, contrat), getPersistedContrat(contrat));
    }

    @Test
    @Transactional
    void fullUpdateContratWithPatch() throws Exception {
        // Initialize the database
        insertedContrat = contratRepository.saveAndFlush(contrat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contrat using partial update
        Contrat partialUpdatedContrat = new Contrat();
        partialUpdatedContrat.setId(contrat.getId());

        partialUpdatedContrat
            .numeroContratCollectif(UPDATED_NUMERO_CONTRAT_COLLECTIF)
            .migre(UPDATED_MIGRE)
            .codeEntiteRattachement(UPDATED_CODE_ENTITE_RATTACHEMENT)
            .codeCentreGestion(UPDATED_CODE_CENTRE_GESTION)
            .codeGroupeGestion(UPDATED_CODE_GROUPE_GESTION)
            .codeReseauDistribution(UPDATED_CODE_RESEAU_DISTRIBUTION)
            .typeContratCollectif(UPDATED_TYPE_CONTRAT_COLLECTIF)
            .etatContrat(UPDATED_ETAT_CONTRAT)
            .dateEffetPremiereSouscription(UPDATED_DATE_EFFET_PREMIERE_SOUSCRIPTION)
            .dateEffetDerniereResiliation(UPDATED_DATE_EFFET_DERNIERE_RESILIATION)
            .motifResiliation(UPDATED_MOTIF_RESILIATION)
            .codeNatureCouverture(UPDATED_CODE_NATURE_COUVERTURE)
            .codeOffre(UPDATED_CODE_OFFRE)
            .numeroVersionOffre(UPDATED_NUMERO_VERSION_OFFRE)
            .echeancePrincipale(UPDATED_ECHEANCE_PRINCIPALE)
            .codeOrganismePorteurRisque(UPDATED_CODE_ORGANISME_PORTEUR_RISQUE)
            .indicateurPorteurRisque(UPDATED_INDICATEUR_PORTEUR_RISQUE)
            .codeOrganismeProducteurFicheDsn(UPDATED_CODE_ORGANISME_PRODUCTEUR_FICHE_DSN)
            .codeOrganismeDelegataireCotisations(UPDATED_CODE_ORGANISME_DELEGATAIRE_COTISATIONS)
            .codeOrganismeDelegatairePrestations(UPDATED_CODE_ORGANISME_DELEGATAIRE_PRESTATIONS)
            .datePremierMoisCotisationAutorise(UPDATED_DATE_PREMIER_MOIS_COTISATION_AUTORISE)
            .numeroOperationNiveau0(UPDATED_NUMERO_OPERATION_NIVEAU_0)
            .statut(UPDATED_STATUT);

        restContratMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContrat.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContrat))
            )
            .andExpect(status().isOk());

        // Validate the Contrat in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContratUpdatableFieldsEquals(partialUpdatedContrat, getPersistedContrat(partialUpdatedContrat));
    }

    @Test
    @Transactional
    void patchNonExistingContrat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contrat.setId(longCount.incrementAndGet());

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContratMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contratDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contratDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContrat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contrat.setId(longCount.incrementAndGet());

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContratMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contratDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContrat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contrat.setId(longCount.incrementAndGet());

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContratMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(contratDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contrat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContrat() throws Exception {
        // Initialize the database
        insertedContrat = contratRepository.saveAndFlush(contrat);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the contrat
        restContratMockMvc
            .perform(delete(ENTITY_API_URL_ID, contrat.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return contratRepository.count();
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

    protected Contrat getPersistedContrat(Contrat contrat) {
        return contratRepository.findById(contrat.getId()).orElseThrow();
    }

    protected void assertPersistedContratToMatchAllProperties(Contrat expectedContrat) {
        assertContratAllPropertiesEquals(expectedContrat, getPersistedContrat(expectedContrat));
    }

    protected void assertPersistedContratToMatchUpdatableProperties(Contrat expectedContrat) {
        assertContratAllUpdatablePropertiesEquals(expectedContrat, getPersistedContrat(expectedContrat));
    }
}
