package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.repository.PasseportRepository;
import com.urservices.ambassade.service.PasseportService;
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

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.urservices.ambassade.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.urservices.ambassade.domain.enumeration.Statut;
/**
 * Test class for the PasseportResource REST controller.
 *
 * @see PasseportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class PasseportResourceIntTest {

    private static final Long DEFAULT_NUMERO_FORMULAIRE = 0L;
    private static final Long UPDATED_NUMERO_FORMULAIRE = 1L;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_PASSEPORT = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PASSEPORT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NE_LE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NE_LE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIEU_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_NAISSANCE = "BBBBBBBBBB";

    private static final Statut DEFAULT_ETAT_CIVIL = Statut.CELIBATAIRE;
    private static final Statut UPDATED_ETAT_CIVIL = Statut.MARIE;

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_NIF = "AAAAAAAAAA";
    private static final String UPDATED_NIF = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS_EMETTEUR = "AAAAAAAAAA";
    private static final String UPDATED_PAYS_EMETTEUR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SOUMIS_LE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SOUMIS_LE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DELIVRE_LE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVRE_LE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(0);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(1);

    private static final String DEFAULT_REMARQUES = "AAAAAAAAAA";
    private static final String UPDATED_REMARQUES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_EMISSION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EMISSION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_EXPIRATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EXPIRATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARQUES_R = "AAAAAAAAAA";
    private static final String UPDATED_REMARQUES_R = "BBBBBBBBBB";

    private static final String DEFAULT_SMS = "AAAAAAAAAA";
    private static final String UPDATED_SMS = "BBBBBBBBBB";

    private static final String DEFAULT_SMS_2 = "AAAAAAAAAA";
    private static final String UPDATED_SMS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENTS = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTS = "BBBBBBBBBB";

    @Autowired
    private PasseportRepository passeportRepository;

    @Autowired
    private PasseportService passeportService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPasseportMockMvc;

    private Passeport passeport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PasseportResource passeportResource = new PasseportResource(passeportService);
        this.restPasseportMockMvc = MockMvcBuilders.standaloneSetup(passeportResource)
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
    public static Passeport createEntity(EntityManager em) {
        Passeport passeport = new Passeport()
            .numeroFormulaire(DEFAULT_NUMERO_FORMULAIRE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .numeroPasseport(DEFAULT_NUMERO_PASSEPORT)
            .neLe(DEFAULT_NE_LE)
            .lieuNaissance(DEFAULT_LIEU_NAISSANCE)
            .etatCivil(DEFAULT_ETAT_CIVIL)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .nif(DEFAULT_NIF)
            .paysEmetteur(DEFAULT_PAYS_EMETTEUR)
            .soumisLe(DEFAULT_SOUMIS_LE)
            .delivreLe(DEFAULT_DELIVRE_LE)
            .montant(DEFAULT_MONTANT)
            .remarques(DEFAULT_REMARQUES)
            .dateEmission(DEFAULT_DATE_EMISSION)
            .dateExpiration(DEFAULT_DATE_EXPIRATION)
            .remarquesR(DEFAULT_REMARQUES_R)
            .sms(DEFAULT_SMS)
            .sms2(DEFAULT_SMS_2)
            .documents(DEFAULT_DOCUMENTS);
        return passeport;
    }

    @Before
    public void initTest() {
        passeport = createEntity(em);
    }

    @Test
    @Transactional
    public void createPasseport() throws Exception {
        int databaseSizeBeforeCreate = passeportRepository.findAll().size();

        // Create the Passeport
        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isCreated());

        // Validate the Passeport in the database
        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeCreate + 1);
        Passeport testPasseport = passeportList.get(passeportList.size() - 1);
        assertThat(testPasseport.getNumeroFormulaire()).isEqualTo(DEFAULT_NUMERO_FORMULAIRE);
        assertThat(testPasseport.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPasseport.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testPasseport.getNumeroPasseport()).isEqualTo(DEFAULT_NUMERO_PASSEPORT);
        assertThat(testPasseport.getNeLe()).isEqualTo(DEFAULT_NE_LE);
        assertThat(testPasseport.getLieuNaissance()).isEqualTo(DEFAULT_LIEU_NAISSANCE);
        assertThat(testPasseport.getEtatCivil()).isEqualTo(DEFAULT_ETAT_CIVIL);
        assertThat(testPasseport.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testPasseport.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testPasseport.getNif()).isEqualTo(DEFAULT_NIF);
        assertThat(testPasseport.getPaysEmetteur()).isEqualTo(DEFAULT_PAYS_EMETTEUR);
        assertThat(testPasseport.getSoumisLe()).isEqualTo(DEFAULT_SOUMIS_LE);
        assertThat(testPasseport.getDelivreLe()).isEqualTo(DEFAULT_DELIVRE_LE);
        assertThat(testPasseport.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testPasseport.getRemarques()).isEqualTo(DEFAULT_REMARQUES);
        assertThat(testPasseport.getDateEmission()).isEqualTo(DEFAULT_DATE_EMISSION);
        assertThat(testPasseport.getDateExpiration()).isEqualTo(DEFAULT_DATE_EXPIRATION);
        assertThat(testPasseport.getRemarquesR()).isEqualTo(DEFAULT_REMARQUES_R);
        assertThat(testPasseport.getSms()).isEqualTo(DEFAULT_SMS);
        assertThat(testPasseport.getSms2()).isEqualTo(DEFAULT_SMS_2);
        assertThat(testPasseport.getDocuments()).isEqualTo(DEFAULT_DOCUMENTS);
    }

    @Test
    @Transactional
    public void createPasseportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = passeportRepository.findAll().size();

        // Create the Passeport with an existing ID
        passeport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        // Validate the Passeport in the database
        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroFormulaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = passeportRepository.findAll().size();
        // set the field null
        passeport.setNumeroFormulaire(null);

        // Create the Passeport, which fails.

        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = passeportRepository.findAll().size();
        // set the field null
        passeport.setNom(null);

        // Create the Passeport, which fails.

        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = passeportRepository.findAll().size();
        // set the field null
        passeport.setPrenom(null);

        // Create the Passeport, which fails.

        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNeLeIsRequired() throws Exception {
        int databaseSizeBeforeTest = passeportRepository.findAll().size();
        // set the field null
        passeport.setNeLe(null);

        // Create the Passeport, which fails.

        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLieuNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = passeportRepository.findAll().size();
        // set the field null
        passeport.setLieuNaissance(null);

        // Create the Passeport, which fails.

        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSoumisLeIsRequired() throws Exception {
        int databaseSizeBeforeTest = passeportRepository.findAll().size();
        // set the field null
        passeport.setSoumisLe(null);

        // Create the Passeport, which fails.

        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = passeportRepository.findAll().size();
        // set the field null
        passeport.setMontant(null);

        // Create the Passeport, which fails.

        restPasseportMockMvc.perform(post("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isBadRequest());

        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPasseports() throws Exception {
        // Initialize the database
        passeportRepository.saveAndFlush(passeport);

        // Get all the passeportList
        restPasseportMockMvc.perform(get("/api/passeports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(passeport.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroFormulaire").value(hasItem(DEFAULT_NUMERO_FORMULAIRE.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].numeroPasseport").value(hasItem(DEFAULT_NUMERO_PASSEPORT.toString())))
            .andExpect(jsonPath("$.[*].neLe").value(hasItem(DEFAULT_NE_LE.toString())))
            .andExpect(jsonPath("$.[*].lieuNaissance").value(hasItem(DEFAULT_LIEU_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].etatCivil").value(hasItem(DEFAULT_ETAT_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].nif").value(hasItem(DEFAULT_NIF.toString())))
            .andExpect(jsonPath("$.[*].paysEmetteur").value(hasItem(DEFAULT_PAYS_EMETTEUR.toString())))
            .andExpect(jsonPath("$.[*].soumisLe").value(hasItem(DEFAULT_SOUMIS_LE.toString())))
            .andExpect(jsonPath("$.[*].delivreLe").value(hasItem(DEFAULT_DELIVRE_LE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].remarques").value(hasItem(DEFAULT_REMARQUES.toString())))
            .andExpect(jsonPath("$.[*].dateEmission").value(hasItem(DEFAULT_DATE_EMISSION.toString())))
            .andExpect(jsonPath("$.[*].dateExpiration").value(hasItem(DEFAULT_DATE_EXPIRATION.toString())))
            .andExpect(jsonPath("$.[*].remarquesR").value(hasItem(DEFAULT_REMARQUES_R.toString())))
            .andExpect(jsonPath("$.[*].sms").value(hasItem(DEFAULT_SMS.toString())))
            .andExpect(jsonPath("$.[*].sms2").value(hasItem(DEFAULT_SMS_2.toString())))
            .andExpect(jsonPath("$.[*].documents").value(hasItem(DEFAULT_DOCUMENTS.toString())));
    }

    @Test
    @Transactional
    public void getPasseport() throws Exception {
        // Initialize the database
        passeportRepository.saveAndFlush(passeport);

        // Get the passeport
        restPasseportMockMvc.perform(get("/api/passeports/{id}", passeport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(passeport.getId().intValue()))
            .andExpect(jsonPath("$.numeroFormulaire").value(DEFAULT_NUMERO_FORMULAIRE.intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.numeroPasseport").value(DEFAULT_NUMERO_PASSEPORT.toString()))
            .andExpect(jsonPath("$.neLe").value(DEFAULT_NE_LE.toString()))
            .andExpect(jsonPath("$.lieuNaissance").value(DEFAULT_LIEU_NAISSANCE.toString()))
            .andExpect(jsonPath("$.etatCivil").value(DEFAULT_ETAT_CIVIL.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.nif").value(DEFAULT_NIF.toString()))
            .andExpect(jsonPath("$.paysEmetteur").value(DEFAULT_PAYS_EMETTEUR.toString()))
            .andExpect(jsonPath("$.soumisLe").value(DEFAULT_SOUMIS_LE.toString()))
            .andExpect(jsonPath("$.delivreLe").value(DEFAULT_DELIVRE_LE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.remarques").value(DEFAULT_REMARQUES.toString()))
            .andExpect(jsonPath("$.dateEmission").value(DEFAULT_DATE_EMISSION.toString()))
            .andExpect(jsonPath("$.dateExpiration").value(DEFAULT_DATE_EXPIRATION.toString()))
            .andExpect(jsonPath("$.remarquesR").value(DEFAULT_REMARQUES_R.toString()))
            .andExpect(jsonPath("$.sms").value(DEFAULT_SMS.toString()))
            .andExpect(jsonPath("$.sms2").value(DEFAULT_SMS_2.toString()))
            .andExpect(jsonPath("$.documents").value(DEFAULT_DOCUMENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPasseport() throws Exception {
        // Get the passeport
        restPasseportMockMvc.perform(get("/api/passeports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePasseport() throws Exception {
        // Initialize the database
        passeportService.save(passeport);

        int databaseSizeBeforeUpdate = passeportRepository.findAll().size();

        // Update the passeport
        Passeport updatedPasseport = passeportRepository.findOne(passeport.getId());
        // Disconnect from session so that the updates on updatedPasseport are not directly saved in db
        em.detach(updatedPasseport);
        updatedPasseport
            .numeroFormulaire(UPDATED_NUMERO_FORMULAIRE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .numeroPasseport(UPDATED_NUMERO_PASSEPORT)
            .neLe(UPDATED_NE_LE)
            .lieuNaissance(UPDATED_LIEU_NAISSANCE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .nif(UPDATED_NIF)
            .paysEmetteur(UPDATED_PAYS_EMETTEUR)
            .soumisLe(UPDATED_SOUMIS_LE)
            .delivreLe(UPDATED_DELIVRE_LE)
            .montant(UPDATED_MONTANT)
            .remarques(UPDATED_REMARQUES)
            .dateEmission(UPDATED_DATE_EMISSION)
            .dateExpiration(UPDATED_DATE_EXPIRATION)
            .remarquesR(UPDATED_REMARQUES_R)
            .sms(UPDATED_SMS)
            .sms2(UPDATED_SMS_2)
            .documents(UPDATED_DOCUMENTS);

        restPasseportMockMvc.perform(put("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPasseport)))
            .andExpect(status().isOk());

        // Validate the Passeport in the database
        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeUpdate);
        Passeport testPasseport = passeportList.get(passeportList.size() - 1);
        assertThat(testPasseport.getNumeroFormulaire()).isEqualTo(UPDATED_NUMERO_FORMULAIRE);
        assertThat(testPasseport.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPasseport.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testPasseport.getNumeroPasseport()).isEqualTo(UPDATED_NUMERO_PASSEPORT);
        assertThat(testPasseport.getNeLe()).isEqualTo(UPDATED_NE_LE);
        assertThat(testPasseport.getLieuNaissance()).isEqualTo(UPDATED_LIEU_NAISSANCE);
        assertThat(testPasseport.getEtatCivil()).isEqualTo(UPDATED_ETAT_CIVIL);
        assertThat(testPasseport.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testPasseport.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testPasseport.getNif()).isEqualTo(UPDATED_NIF);
        assertThat(testPasseport.getPaysEmetteur()).isEqualTo(UPDATED_PAYS_EMETTEUR);
        assertThat(testPasseport.getSoumisLe()).isEqualTo(UPDATED_SOUMIS_LE);
        assertThat(testPasseport.getDelivreLe()).isEqualTo(UPDATED_DELIVRE_LE);
        assertThat(testPasseport.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testPasseport.getRemarques()).isEqualTo(UPDATED_REMARQUES);
        assertThat(testPasseport.getDateEmission()).isEqualTo(UPDATED_DATE_EMISSION);
        assertThat(testPasseport.getDateExpiration()).isEqualTo(UPDATED_DATE_EXPIRATION);
        assertThat(testPasseport.getRemarquesR()).isEqualTo(UPDATED_REMARQUES_R);
        assertThat(testPasseport.getSms()).isEqualTo(UPDATED_SMS);
        assertThat(testPasseport.getSms2()).isEqualTo(UPDATED_SMS_2);
        assertThat(testPasseport.getDocuments()).isEqualTo(UPDATED_DOCUMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingPasseport() throws Exception {
        int databaseSizeBeforeUpdate = passeportRepository.findAll().size();

        // Create the Passeport

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPasseportMockMvc.perform(put("/api/passeports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passeport)))
            .andExpect(status().isCreated());

        // Validate the Passeport in the database
        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePasseport() throws Exception {
        // Initialize the database
        passeportService.save(passeport);

        int databaseSizeBeforeDelete = passeportRepository.findAll().size();

        // Get the passeport
        restPasseportMockMvc.perform(delete("/api/passeports/{id}", passeport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Passeport> passeportList = passeportRepository.findAll();
        assertThat(passeportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Passeport.class);
        Passeport passeport1 = new Passeport();
        passeport1.setId(1L);
        Passeport passeport2 = new Passeport();
        passeport2.setId(passeport1.getId());
        assertThat(passeport1).isEqualTo(passeport2);
        passeport2.setId(2L);
        assertThat(passeport1).isNotEqualTo(passeport2);
        passeport1.setId(null);
        assertThat(passeport1).isNotEqualTo(passeport2);
    }
}
