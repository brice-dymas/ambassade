package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.Rapatriement;
import com.urservices.ambassade.repository.RapatriementRepository;
import com.urservices.ambassade.service.RapatriementService;
import com.urservices.ambassade.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.urservices.ambassade.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.urservices.ambassade.domain.enumeration.Sexe;
/**
 * Test class for the RapatriementResource REST controller.
 *
 * @see RapatriementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class RapatriementResourceIntTest {

    private static final Integer DEFAULT_REFERENCE = 1;
    private static final Integer UPDATED_REFERENCE = 2;

    private static final String DEFAULT_NUMERO_DOSSIER = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_DOSSIER = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOCUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_ID = "BBBBBBBBBB";

    private static final Sexe DEFAULT_SEXE = Sexe.MASCULIN;
    private static final Sexe UPDATED_SEXE = Sexe.FEMININ;

    private static final String DEFAULT_MOTIF = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_RAPATRIEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RAPATRIEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FRONTIERE = "AAAAAAAAAA";
    private static final String UPDATED_FRONTIERE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DOCUMENT_SCANNE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOCUMENT_SCANNE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_DOCUMENT_SCANNE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOCUMENT_SCANNE_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_CREATED_BY_PHP_RUNNER = 1;
    private static final Integer UPDATED_CREATED_BY_PHP_RUNNER = 2;

    @Autowired
    private RapatriementRepository rapatriementRepository;

    @Autowired
    private RapatriementService rapatriementService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRapatriementMockMvc;

    private Rapatriement rapatriement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RapatriementResource rapatriementResource = new RapatriementResource(rapatriementService);
        this.restRapatriementMockMvc = MockMvcBuilders.standaloneSetup(rapatriementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rapatriement createEntity(EntityManager em) {
        Rapatriement rapatriement = new Rapatriement()
            .reference(DEFAULT_REFERENCE)
            .numeroDossier(DEFAULT_NUMERO_DOSSIER)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .documentID(DEFAULT_DOCUMENT_ID)
            .sexe(DEFAULT_SEXE)
            .motif(DEFAULT_MOTIF)
            .dateRapatriement(DEFAULT_DATE_RAPATRIEMENT)
            .frontiere(DEFAULT_FRONTIERE)
            .documentScanne(DEFAULT_DOCUMENT_SCANNE)
            .documentScanneContentType(DEFAULT_DOCUMENT_SCANNE_CONTENT_TYPE)
            .createdByPHPRunner(DEFAULT_CREATED_BY_PHP_RUNNER);
        return rapatriement;
    }

    @Before
    public void initTest() {
        rapatriement = createEntity(em);
    }

    @Test
    @Transactional
    public void createRapatriement() throws Exception {
        int databaseSizeBeforeCreate = rapatriementRepository.findAll().size();

        // Create the Rapatriement
        restRapatriementMockMvc.perform(post("/api/rapatriements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rapatriement)))
            .andExpect(status().isCreated());

        // Validate the Rapatriement in the database
        List<Rapatriement> rapatriementList = rapatriementRepository.findAll();
        assertThat(rapatriementList).hasSize(databaseSizeBeforeCreate + 1);
        Rapatriement testRapatriement = rapatriementList.get(rapatriementList.size() - 1);
        assertThat(testRapatriement.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testRapatriement.getNumeroDossier()).isEqualTo(DEFAULT_NUMERO_DOSSIER);
        assertThat(testRapatriement.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testRapatriement.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testRapatriement.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testRapatriement.getDocumentID()).isEqualTo(DEFAULT_DOCUMENT_ID);
        assertThat(testRapatriement.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testRapatriement.getMotif()).isEqualTo(DEFAULT_MOTIF);
        assertThat(testRapatriement.getDateRapatriement()).isEqualTo(DEFAULT_DATE_RAPATRIEMENT);
        assertThat(testRapatriement.getFrontiere()).isEqualTo(DEFAULT_FRONTIERE);
        assertThat(testRapatriement.getDocumentScanne()).isEqualTo(DEFAULT_DOCUMENT_SCANNE);
        assertThat(testRapatriement.getDocumentScanneContentType()).isEqualTo(DEFAULT_DOCUMENT_SCANNE_CONTENT_TYPE);
        assertThat(testRapatriement.getCreatedByPHPRunner()).isEqualTo(DEFAULT_CREATED_BY_PHP_RUNNER);
    }

    @Test
    @Transactional
    public void createRapatriementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rapatriementRepository.findAll().size();

        // Create the Rapatriement with an existing ID
        rapatriement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRapatriementMockMvc.perform(post("/api/rapatriements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rapatriement)))
            .andExpect(status().isBadRequest());

        // Validate the Rapatriement in the database
        List<Rapatriement> rapatriementList = rapatriementRepository.findAll();
        assertThat(rapatriementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = rapatriementRepository.findAll().size();
        // set the field null
        rapatriement.setReference(null);

        // Create the Rapatriement, which fails.

        restRapatriementMockMvc.perform(post("/api/rapatriements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rapatriement)))
            .andExpect(status().isBadRequest());

        List<Rapatriement> rapatriementList = rapatriementRepository.findAll();
        assertThat(rapatriementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRapatriements() throws Exception {
        // Initialize the database
        rapatriementRepository.saveAndFlush(rapatriement);

        // Get all the rapatriementList
        restRapatriementMockMvc.perform(get("/api/rapatriements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rapatriement.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].numeroDossier").value(hasItem(DEFAULT_NUMERO_DOSSIER.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].documentID").value(hasItem(DEFAULT_DOCUMENT_ID.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF.toString())))
            .andExpect(jsonPath("$.[*].dateRapatriement").value(hasItem(DEFAULT_DATE_RAPATRIEMENT.toString())))
            .andExpect(jsonPath("$.[*].frontiere").value(hasItem(DEFAULT_FRONTIERE.toString())))
            .andExpect(jsonPath("$.[*].documentScanneContentType").value(hasItem(DEFAULT_DOCUMENT_SCANNE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentScanne").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENT_SCANNE))))
            .andExpect(jsonPath("$.[*].createdByPHPRunner").value(hasItem(DEFAULT_CREATED_BY_PHP_RUNNER)));
    }

    @Test
    @Transactional
    public void searchAllRapatriements() throws Exception {
        // Initialize the database
        rapatriementRepository.saveAndFlush(rapatriement);

        // Get all the rapatriementList
        restRapatriementMockMvc.perform(get("/api/rapatriements?reference=111&numeroDossier=AAAAAAAAAA"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rapatriement.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].numeroDossier").value(hasItem(DEFAULT_NUMERO_DOSSIER.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].documentID").value(hasItem(DEFAULT_DOCUMENT_ID.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF.toString())))
            .andExpect(jsonPath("$.[*].dateRapatriement").value(hasItem(DEFAULT_DATE_RAPATRIEMENT.toString())))
            .andExpect(jsonPath("$.[*].frontiere").value(hasItem(DEFAULT_FRONTIERE.toString())))
            .andExpect(jsonPath("$.[*].documentScanneContentType").value(hasItem(DEFAULT_DOCUMENT_SCANNE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentScanne").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENT_SCANNE))))
            .andExpect(jsonPath("$.[*].createdByPHPRunner").value(hasItem(DEFAULT_CREATED_BY_PHP_RUNNER)));
    }

    @Test
    @Transactional
    public void getRapatriement() throws Exception {
        // Initialize the database
        rapatriementRepository.saveAndFlush(rapatriement);

        // Get the rapatriement
        restRapatriementMockMvc.perform(get("/api/rapatriements/{id}", rapatriement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rapatriement.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.numeroDossier").value(DEFAULT_NUMERO_DOSSIER.toString()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.documentID").value(DEFAULT_DOCUMENT_ID.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.motif").value(DEFAULT_MOTIF.toString()))
            .andExpect(jsonPath("$.dateRapatriement").value(DEFAULT_DATE_RAPATRIEMENT.toString()))
            .andExpect(jsonPath("$.frontiere").value(DEFAULT_FRONTIERE.toString()))
            .andExpect(jsonPath("$.documentScanneContentType").value(DEFAULT_DOCUMENT_SCANNE_CONTENT_TYPE))
            .andExpect(jsonPath("$.documentScanne").value(Base64Utils.encodeToString(DEFAULT_DOCUMENT_SCANNE)))
            .andExpect(jsonPath("$.createdByPHPRunner").value(DEFAULT_CREATED_BY_PHP_RUNNER));
    }

    @Test
    @Transactional
    public void getNonExistingRapatriement() throws Exception {
        // Get the rapatriement
        restRapatriementMockMvc.perform(get("/api/rapatriements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRapatriement() throws Exception {
        // Initialize the database
        rapatriementService.save(rapatriement);

        int databaseSizeBeforeUpdate = rapatriementRepository.findAll().size();

        // Update the rapatriement
        Rapatriement updatedRapatriement = rapatriementRepository.findOne(rapatriement.getId());
        // Disconnect from session so that the updates on updatedRapatriement are not directly saved in db
        em.detach(updatedRapatriement);
        updatedRapatriement
            .reference(UPDATED_REFERENCE)
            .numeroDossier(UPDATED_NUMERO_DOSSIER)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .documentID(UPDATED_DOCUMENT_ID)
            .sexe(UPDATED_SEXE)
            .motif(UPDATED_MOTIF)
            .dateRapatriement(UPDATED_DATE_RAPATRIEMENT)
            .frontiere(UPDATED_FRONTIERE)
            .documentScanne(UPDATED_DOCUMENT_SCANNE)
            .documentScanneContentType(UPDATED_DOCUMENT_SCANNE_CONTENT_TYPE)
            .createdByPHPRunner(UPDATED_CREATED_BY_PHP_RUNNER);

        restRapatriementMockMvc.perform(put("/api/rapatriements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRapatriement)))
            .andExpect(status().isOk());

        // Validate the Rapatriement in the database
        List<Rapatriement> rapatriementList = rapatriementRepository.findAll();
        assertThat(rapatriementList).hasSize(databaseSizeBeforeUpdate);
        Rapatriement testRapatriement = rapatriementList.get(rapatriementList.size() - 1);
        assertThat(testRapatriement.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testRapatriement.getNumeroDossier()).isEqualTo(UPDATED_NUMERO_DOSSIER);
        assertThat(testRapatriement.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testRapatriement.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testRapatriement.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testRapatriement.getDocumentID()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testRapatriement.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testRapatriement.getMotif()).isEqualTo(UPDATED_MOTIF);
        assertThat(testRapatriement.getDateRapatriement()).isEqualTo(UPDATED_DATE_RAPATRIEMENT);
        assertThat(testRapatriement.getFrontiere()).isEqualTo(UPDATED_FRONTIERE);
        assertThat(testRapatriement.getDocumentScanne()).isEqualTo(UPDATED_DOCUMENT_SCANNE);
        assertThat(testRapatriement.getDocumentScanneContentType()).isEqualTo(UPDATED_DOCUMENT_SCANNE_CONTENT_TYPE);
        assertThat(testRapatriement.getCreatedByPHPRunner()).isEqualTo(UPDATED_CREATED_BY_PHP_RUNNER);
    }

    @Test
    @Transactional
    public void updateNonExistingRapatriement() throws Exception {
        int databaseSizeBeforeUpdate = rapatriementRepository.findAll().size();

        // Create the Rapatriement

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRapatriementMockMvc.perform(put("/api/rapatriements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rapatriement)))
            .andExpect(status().isCreated());

        // Validate the Rapatriement in the database
        List<Rapatriement> rapatriementList = rapatriementRepository.findAll();
        assertThat(rapatriementList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRapatriement() throws Exception {
        // Initialize the database
        rapatriementService.save(rapatriement);

        int databaseSizeBeforeDelete = rapatriementRepository.findAll().size();

        // Get the rapatriement
        restRapatriementMockMvc.perform(delete("/api/rapatriements/{id}", rapatriement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Rapatriement> rapatriementList = rapatriementRepository.findAll();
        assertThat(rapatriementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rapatriement.class);
        Rapatriement rapatriement1 = new Rapatriement();
        rapatriement1.setId(1L);
        Rapatriement rapatriement2 = new Rapatriement();
        rapatriement2.setId(rapatriement1.getId());
        assertThat(rapatriement1).isEqualTo(rapatriement2);
        rapatriement2.setId(2L);
        assertThat(rapatriement1).isNotEqualTo(rapatriement2);
        rapatriement1.setId(null);
        assertThat(rapatriement1).isNotEqualTo(rapatriement2);
    }
}
