package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.DonneesActe;
import com.urservices.ambassade.repository.DonneesActeRepository;
import com.urservices.ambassade.service.DonneesActeService;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.urservices.ambassade.web.rest.TestUtil.sameInstant;
import static com.urservices.ambassade.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.urservices.ambassade.domain.enumeration.Sexe;
import com.urservices.ambassade.domain.enumeration.Statut;
/**
 * Test class for the DonneesActeResource REST controller.
 *
 * @see DonneesActeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class DonneesActeResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_DU_JOUR_CHIFFRE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DU_JOUR_CHIFFRE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_REGISTRE_SPECIAL_RD = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRE_SPECIAL_RD = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ENFANT = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ENFANT = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNEE = 1900;
    private static final Integer UPDATED_ANNEE = 1901;

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_NAISSANCE_CHIFFRE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_NAISSANCE_CHIFFRE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NOM_PERE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PERE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_PERE = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_PERE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MERE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MERE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_MERE = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_MERE = "BBBBBBBBBB";

    private static final Sexe DEFAULT_SEXE = Sexe.MASCULIN;
    private static final Sexe UPDATED_SEXE = Sexe.FEMININ;

    private static final Statut DEFAULT_STATUT = Statut.LEGITIME;
    private static final Statut UPDATED_STATUT = Statut.NATUREL;

    private static final String DEFAULT_VILLE_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_PERE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_PERE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_MERE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_MERE = "BBBBBBBBBB";

    private static final String DEFAULT_TEMOINS_1 = "AAAAAAAAAA";
    private static final String UPDATED_TEMOINS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TEMOINS_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEMOINS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ID_PERE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PERE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_MERE = "AAAAAAAAAA";
    private static final String UPDATED_ID_MERE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_JURIDICTION = "AAAAAAAAAA";
    private static final String UPDATED_JURIDICTION = "BBBBBBBBBB";

    private static final String DEFAULT_LIVRE = "AAAAAAAAAA";
    private static final String UPDATED_LIVRE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_FEUILLE = "AAAAAAAAAA";
    private static final String UPDATED_FEUILLE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTE = "AAAAAAAAAA";
    private static final String UPDATED_ACTE = "BBBBBBBBBB";

    @Autowired
    private DonneesActeRepository donneesActeRepository;

    @Autowired
    private DonneesActeService donneesActeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDonneesActeMockMvc;

    private DonneesActe donneesActe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DonneesActeResource donneesActeResource = new DonneesActeResource(donneesActeService);
        this.restDonneesActeMockMvc = MockMvcBuilders.standaloneSetup(donneesActeResource)
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
    public static DonneesActe createEntity(EntityManager em) {
        DonneesActe donneesActe = new DonneesActe()
            .reference(DEFAULT_REFERENCE)
            .dateDuJourChiffre(DEFAULT_DATE_DU_JOUR_CHIFFRE)
            .registreSpecialRD(DEFAULT_REGISTRE_SPECIAL_RD)
            .nomEnfant(DEFAULT_NOM_ENFANT)
            .registre(DEFAULT_REGISTRE)
            .annee(DEFAULT_ANNEE)
            .numero(DEFAULT_NUMERO)
            .dateNaissanceChiffre(DEFAULT_DATE_NAISSANCE_CHIFFRE)
            .nomPere(DEFAULT_NOM_PERE)
            .prenomPere(DEFAULT_PRENOM_PERE)
            .nomMere(DEFAULT_NOM_MERE)
            .prenomMere(DEFAULT_PRENOM_MERE)
            .sexe(DEFAULT_SEXE)
            .statut(DEFAULT_STATUT)
            .villeNaissance(DEFAULT_VILLE_NAISSANCE)
            .adressePere(DEFAULT_ADRESSE_PERE)
            .adresseMere(DEFAULT_ADRESSE_MERE)
            .temoins1(DEFAULT_TEMOINS_1)
            .temoins2(DEFAULT_TEMOINS_2)
            .idPere(DEFAULT_ID_PERE)
            .idMere(DEFAULT_ID_MERE)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .juridiction(DEFAULT_JURIDICTION)
            .livre(DEFAULT_LIVRE)
            .notes(DEFAULT_NOTES)
            .feuille(DEFAULT_FEUILLE)
            .acte(DEFAULT_ACTE);
        return donneesActe;
    }

    @Before
    public void initTest() {
        donneesActe = createEntity(em);
    }

    @Test
    @Transactional
    public void createDonneesActe() throws Exception {
        int databaseSizeBeforeCreate = donneesActeRepository.findAll().size();

        // Create the DonneesActe
        restDonneesActeMockMvc.perform(post("/api/donnees-actes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donneesActe)))
            .andExpect(status().isCreated());

        // Validate the DonneesActe in the database
        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeCreate + 1);
        DonneesActe testDonneesActe = donneesActeList.get(donneesActeList.size() - 1);
        assertThat(testDonneesActe.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testDonneesActe.getDateDuJourChiffre()).isEqualTo(DEFAULT_DATE_DU_JOUR_CHIFFRE);
        assertThat(testDonneesActe.getRegistreSpecialRD()).isEqualTo(DEFAULT_REGISTRE_SPECIAL_RD);
        assertThat(testDonneesActe.getNomEnfant()).isEqualTo(DEFAULT_NOM_ENFANT);
        assertThat(testDonneesActe.getRegistre()).isEqualTo(DEFAULT_REGISTRE);
        assertThat(testDonneesActe.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testDonneesActe.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testDonneesActe.getDateNaissanceChiffre()).isEqualTo(DEFAULT_DATE_NAISSANCE_CHIFFRE);
        assertThat(testDonneesActe.getNomPere()).isEqualTo(DEFAULT_NOM_PERE);
        assertThat(testDonneesActe.getPrenomPere()).isEqualTo(DEFAULT_PRENOM_PERE);
        assertThat(testDonneesActe.getNomMere()).isEqualTo(DEFAULT_NOM_MERE);
        assertThat(testDonneesActe.getPrenomMere()).isEqualTo(DEFAULT_PRENOM_MERE);
        assertThat(testDonneesActe.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testDonneesActe.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testDonneesActe.getVilleNaissance()).isEqualTo(DEFAULT_VILLE_NAISSANCE);
        assertThat(testDonneesActe.getAdressePere()).isEqualTo(DEFAULT_ADRESSE_PERE);
        assertThat(testDonneesActe.getAdresseMere()).isEqualTo(DEFAULT_ADRESSE_MERE);
        assertThat(testDonneesActe.getTemoins1()).isEqualTo(DEFAULT_TEMOINS_1);
        assertThat(testDonneesActe.getTemoins2()).isEqualTo(DEFAULT_TEMOINS_2);
        assertThat(testDonneesActe.getIdPere()).isEqualTo(DEFAULT_ID_PERE);
        assertThat(testDonneesActe.getIdMere()).isEqualTo(DEFAULT_ID_MERE);
        assertThat(testDonneesActe.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testDonneesActe.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testDonneesActe.getJuridiction()).isEqualTo(DEFAULT_JURIDICTION);
        assertThat(testDonneesActe.getLivre()).isEqualTo(DEFAULT_LIVRE);
        assertThat(testDonneesActe.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testDonneesActe.getFeuille()).isEqualTo(DEFAULT_FEUILLE);
        assertThat(testDonneesActe.getActe()).isEqualTo(DEFAULT_ACTE);
    }

    @Test
    @Transactional
    public void createDonneesActeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donneesActeRepository.findAll().size();

        // Create the DonneesActe with an existing ID
        donneesActe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonneesActeMockMvc.perform(post("/api/donnees-actes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donneesActe)))
            .andExpect(status().isBadRequest());

        // Validate the DonneesActe in the database
        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = donneesActeRepository.findAll().size();
        // set the field null
        donneesActe.setReference(null);

        // Create the DonneesActe, which fails.

        restDonneesActeMockMvc.perform(post("/api/donnees-actes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donneesActe)))
            .andExpect(status().isBadRequest());

        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexeIsRequired() throws Exception {
        int databaseSizeBeforeTest = donneesActeRepository.findAll().size();
        // set the field null
        donneesActe.setSexe(null);

        // Create the DonneesActe, which fails.

        restDonneesActeMockMvc.perform(post("/api/donnees-actes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donneesActe)))
            .andExpect(status().isBadRequest());

        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatutIsRequired() throws Exception {
        int databaseSizeBeforeTest = donneesActeRepository.findAll().size();
        // set the field null
        donneesActe.setStatut(null);

        // Create the DonneesActe, which fails.

        restDonneesActeMockMvc.perform(post("/api/donnees-actes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donneesActe)))
            .andExpect(status().isBadRequest());

        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDonneesActes() throws Exception {
        // Initialize the database
        donneesActeRepository.saveAndFlush(donneesActe);

        // Get all the donneesActeList
        restDonneesActeMockMvc.perform(get("/api/donnees-actes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donneesActe.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].dateDuJourChiffre").value(hasItem(sameInstant(DEFAULT_DATE_DU_JOUR_CHIFFRE))))
            .andExpect(jsonPath("$.[*].registreSpecialRD").value(hasItem(DEFAULT_REGISTRE_SPECIAL_RD.toString())))
            .andExpect(jsonPath("$.[*].nomEnfant").value(hasItem(DEFAULT_NOM_ENFANT.toString())))
            .andExpect(jsonPath("$.[*].registre").value(hasItem(DEFAULT_REGISTRE.toString())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].dateNaissanceChiffre").value(hasItem(sameInstant(DEFAULT_DATE_NAISSANCE_CHIFFRE))))
            .andExpect(jsonPath("$.[*].nomPere").value(hasItem(DEFAULT_NOM_PERE.toString())))
            .andExpect(jsonPath("$.[*].prenomPere").value(hasItem(DEFAULT_PRENOM_PERE.toString())))
            .andExpect(jsonPath("$.[*].nomMere").value(hasItem(DEFAULT_NOM_MERE.toString())))
            .andExpect(jsonPath("$.[*].prenomMere").value(hasItem(DEFAULT_PRENOM_MERE.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].villeNaissance").value(hasItem(DEFAULT_VILLE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].adressePere").value(hasItem(DEFAULT_ADRESSE_PERE.toString())))
            .andExpect(jsonPath("$.[*].adresseMere").value(hasItem(DEFAULT_ADRESSE_MERE.toString())))
            .andExpect(jsonPath("$.[*].temoins1").value(hasItem(DEFAULT_TEMOINS_1.toString())))
            .andExpect(jsonPath("$.[*].temoins2").value(hasItem(DEFAULT_TEMOINS_2.toString())))
            .andExpect(jsonPath("$.[*].idPere").value(hasItem(DEFAULT_ID_PERE.toString())))
            .andExpect(jsonPath("$.[*].idMere").value(hasItem(DEFAULT_ID_MERE.toString())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].juridiction").value(hasItem(DEFAULT_JURIDICTION.toString())))
            .andExpect(jsonPath("$.[*].livre").value(hasItem(DEFAULT_LIVRE.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].feuille").value(hasItem(DEFAULT_FEUILLE.toString())))
            .andExpect(jsonPath("$.[*].acte").value(hasItem(DEFAULT_ACTE.toString())));
    }

    @Test
    @Transactional
    public void getDonneesActe() throws Exception {
        // Initialize the database
        donneesActeRepository.saveAndFlush(donneesActe);

        // Get the donneesActe
        restDonneesActeMockMvc.perform(get("/api/donnees-actes/{id}", donneesActe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(donneesActe.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.dateDuJourChiffre").value(sameInstant(DEFAULT_DATE_DU_JOUR_CHIFFRE)))
            .andExpect(jsonPath("$.registreSpecialRD").value(DEFAULT_REGISTRE_SPECIAL_RD.toString()))
            .andExpect(jsonPath("$.nomEnfant").value(DEFAULT_NOM_ENFANT.toString()))
            .andExpect(jsonPath("$.registre").value(DEFAULT_REGISTRE.toString()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.dateNaissanceChiffre").value(sameInstant(DEFAULT_DATE_NAISSANCE_CHIFFRE)))
            .andExpect(jsonPath("$.nomPere").value(DEFAULT_NOM_PERE.toString()))
            .andExpect(jsonPath("$.prenomPere").value(DEFAULT_PRENOM_PERE.toString()))
            .andExpect(jsonPath("$.nomMere").value(DEFAULT_NOM_MERE.toString()))
            .andExpect(jsonPath("$.prenomMere").value(DEFAULT_PRENOM_MERE.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.villeNaissance").value(DEFAULT_VILLE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.adressePere").value(DEFAULT_ADRESSE_PERE.toString()))
            .andExpect(jsonPath("$.adresseMere").value(DEFAULT_ADRESSE_MERE.toString()))
            .andExpect(jsonPath("$.temoins1").value(DEFAULT_TEMOINS_1.toString()))
            .andExpect(jsonPath("$.temoins2").value(DEFAULT_TEMOINS_2.toString()))
            .andExpect(jsonPath("$.idPere").value(DEFAULT_ID_PERE.toString()))
            .andExpect(jsonPath("$.idMere").value(DEFAULT_ID_MERE.toString()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.juridiction").value(DEFAULT_JURIDICTION.toString()))
            .andExpect(jsonPath("$.livre").value(DEFAULT_LIVRE.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.feuille").value(DEFAULT_FEUILLE.toString()))
            .andExpect(jsonPath("$.acte").value(DEFAULT_ACTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDonneesActe() throws Exception {
        // Get the donneesActe
        restDonneesActeMockMvc.perform(get("/api/donnees-actes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonneesActe() throws Exception {
        // Initialize the database
        donneesActeService.save(donneesActe);

        int databaseSizeBeforeUpdate = donneesActeRepository.findAll().size();

        // Update the donneesActe
        DonneesActe updatedDonneesActe = donneesActeRepository.findOne(donneesActe.getId());
        // Disconnect from session so that the updates on updatedDonneesActe are not directly saved in db
        em.detach(updatedDonneesActe);
        updatedDonneesActe
            .reference(UPDATED_REFERENCE)
            .dateDuJourChiffre(UPDATED_DATE_DU_JOUR_CHIFFRE)
            .registreSpecialRD(UPDATED_REGISTRE_SPECIAL_RD)
            .nomEnfant(UPDATED_NOM_ENFANT)
            .registre(UPDATED_REGISTRE)
            .annee(UPDATED_ANNEE)
            .numero(UPDATED_NUMERO)
            .dateNaissanceChiffre(UPDATED_DATE_NAISSANCE_CHIFFRE)
            .nomPere(UPDATED_NOM_PERE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .nomMere(UPDATED_NOM_MERE)
            .prenomMere(UPDATED_PRENOM_MERE)
            .sexe(UPDATED_SEXE)
            .statut(UPDATED_STATUT)
            .villeNaissance(UPDATED_VILLE_NAISSANCE)
            .adressePere(UPDATED_ADRESSE_PERE)
            .adresseMere(UPDATED_ADRESSE_MERE)
            .temoins1(UPDATED_TEMOINS_1)
            .temoins2(UPDATED_TEMOINS_2)
            .idPere(UPDATED_ID_PERE)
            .idMere(UPDATED_ID_MERE)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .juridiction(UPDATED_JURIDICTION)
            .livre(UPDATED_LIVRE)
            .notes(UPDATED_NOTES)
            .feuille(UPDATED_FEUILLE)
            .acte(UPDATED_ACTE);

        restDonneesActeMockMvc.perform(put("/api/donnees-actes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDonneesActe)))
            .andExpect(status().isOk());

        // Validate the DonneesActe in the database
        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeUpdate);
        DonneesActe testDonneesActe = donneesActeList.get(donneesActeList.size() - 1);
        assertThat(testDonneesActe.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testDonneesActe.getDateDuJourChiffre()).isEqualTo(UPDATED_DATE_DU_JOUR_CHIFFRE);
        assertThat(testDonneesActe.getRegistreSpecialRD()).isEqualTo(UPDATED_REGISTRE_SPECIAL_RD);
        assertThat(testDonneesActe.getNomEnfant()).isEqualTo(UPDATED_NOM_ENFANT);
        assertThat(testDonneesActe.getRegistre()).isEqualTo(UPDATED_REGISTRE);
        assertThat(testDonneesActe.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testDonneesActe.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testDonneesActe.getDateNaissanceChiffre()).isEqualTo(UPDATED_DATE_NAISSANCE_CHIFFRE);
        assertThat(testDonneesActe.getNomPere()).isEqualTo(UPDATED_NOM_PERE);
        assertThat(testDonneesActe.getPrenomPere()).isEqualTo(UPDATED_PRENOM_PERE);
        assertThat(testDonneesActe.getNomMere()).isEqualTo(UPDATED_NOM_MERE);
        assertThat(testDonneesActe.getPrenomMere()).isEqualTo(UPDATED_PRENOM_MERE);
        assertThat(testDonneesActe.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testDonneesActe.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testDonneesActe.getVilleNaissance()).isEqualTo(UPDATED_VILLE_NAISSANCE);
        assertThat(testDonneesActe.getAdressePere()).isEqualTo(UPDATED_ADRESSE_PERE);
        assertThat(testDonneesActe.getAdresseMere()).isEqualTo(UPDATED_ADRESSE_MERE);
        assertThat(testDonneesActe.getTemoins1()).isEqualTo(UPDATED_TEMOINS_1);
        assertThat(testDonneesActe.getTemoins2()).isEqualTo(UPDATED_TEMOINS_2);
        assertThat(testDonneesActe.getIdPere()).isEqualTo(UPDATED_ID_PERE);
        assertThat(testDonneesActe.getIdMere()).isEqualTo(UPDATED_ID_MERE);
        assertThat(testDonneesActe.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testDonneesActe.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testDonneesActe.getJuridiction()).isEqualTo(UPDATED_JURIDICTION);
        assertThat(testDonneesActe.getLivre()).isEqualTo(UPDATED_LIVRE);
        assertThat(testDonneesActe.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testDonneesActe.getFeuille()).isEqualTo(UPDATED_FEUILLE);
        assertThat(testDonneesActe.getActe()).isEqualTo(UPDATED_ACTE);
    }

    @Test
    @Transactional
    public void updateNonExistingDonneesActe() throws Exception {
        int databaseSizeBeforeUpdate = donneesActeRepository.findAll().size();

        // Create the DonneesActe

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDonneesActeMockMvc.perform(put("/api/donnees-actes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donneesActe)))
            .andExpect(status().isCreated());

        // Validate the DonneesActe in the database
        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDonneesActe() throws Exception {
        // Initialize the database
        donneesActeService.save(donneesActe);

        int databaseSizeBeforeDelete = donneesActeRepository.findAll().size();

        // Get the donneesActe
        restDonneesActeMockMvc.perform(delete("/api/donnees-actes/{id}", donneesActe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DonneesActe> donneesActeList = donneesActeRepository.findAll();
        assertThat(donneesActeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonneesActe.class);
        DonneesActe donneesActe1 = new DonneesActe();
        donneesActe1.setId(1L);
        DonneesActe donneesActe2 = new DonneesActe();
        donneesActe2.setId(donneesActe1.getId());
        assertThat(donneesActe1).isEqualTo(donneesActe2);
        donneesActe2.setId(2L);
        assertThat(donneesActe1).isNotEqualTo(donneesActe2);
        donneesActe1.setId(null);
        assertThat(donneesActe1).isNotEqualTo(donneesActe2);
    }
}
