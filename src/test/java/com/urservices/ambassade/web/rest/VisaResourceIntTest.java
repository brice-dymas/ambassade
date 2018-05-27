package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.Visa;
import com.urservices.ambassade.repository.VisaRepository;
import com.urservices.ambassade.service.VisaService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.urservices.ambassade.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.urservices.ambassade.domain.enumeration.State;
/**
 * Test class for the VisaResource REST controller.
 *
 * @see VisaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class VisaResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_PASSEPORT = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PASSEPORT = "BBBBBBBBBB";

    private static final String DEFAULT_CEDULA = "AAAAAAAAAA";
    private static final String UPDATED_CEDULA = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMERO_VISA = 1L;
    private static final Long UPDATED_NUMERO_VISA = 2L;

    private static final LocalDate DEFAULT_DATE_EMISSION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EMISSION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_EXPIRATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EXPIRATION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_VALIDE_POUR = 1;
    private static final Integer UPDATED_VALIDE_POUR = 2;

    private static final String DEFAULT_NOMBRE_ENTREE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ENTREE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAXES = 1;
    private static final Integer UPDATED_TAXES = 2;

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARQUES = "AAAAAAAAAA";
    private static final String UPDATED_REMARQUES = "BBBBBBBBBB";

    private static final State DEFAULT_STATE = State.NOUVEAU;
    private static final State UPDATED_STATE = State.PAYE;

    @Autowired
    private VisaRepository visaRepository;

    @Autowired
    private VisaService visaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVisaMockMvc;

    private Visa visa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VisaResource visaResource = new VisaResource(visaService);
        this.restVisaMockMvc = MockMvcBuilders.standaloneSetup(visaResource)
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
    public static Visa createEntity(EntityManager em) {
        Visa visa = new Visa()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .nationalite(DEFAULT_NATIONALITE)
            .numeroPasseport(DEFAULT_NUMERO_PASSEPORT)
            .cedula(DEFAULT_CEDULA)
            .numeroVisa(DEFAULT_NUMERO_VISA)
            .dateEmission(DEFAULT_DATE_EMISSION)
            .dateExpiration(DEFAULT_DATE_EXPIRATION)
            .validePour(DEFAULT_VALIDE_POUR)
            .nombreEntree(DEFAULT_NOMBRE_ENTREE)
            .type(DEFAULT_TYPE)
            .categorie(DEFAULT_CATEGORIE)
            .taxes(DEFAULT_TAXES)
            .adresse(DEFAULT_ADRESSE)
            .remarques(DEFAULT_REMARQUES)
            .state(DEFAULT_STATE);
        return visa;
    }

    @Before
    public void initTest() {
        visa = createEntity(em);
    }

    @Test
    @Transactional
    public void createVisa() throws Exception {
        int databaseSizeBeforeCreate = visaRepository.findAll().size();

        // Create the Visa
        restVisaMockMvc.perform(post("/api/visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(visa)))
            .andExpect(status().isCreated());

        // Validate the Visa in the database
        List<Visa> visaList = visaRepository.findAll();
        assertThat(visaList).hasSize(databaseSizeBeforeCreate + 1);
        Visa testVisa = visaList.get(visaList.size() - 1);
        assertThat(testVisa.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testVisa.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testVisa.getNationalite()).isEqualTo(DEFAULT_NATIONALITE);
        assertThat(testVisa.getNumeroPasseport()).isEqualTo(DEFAULT_NUMERO_PASSEPORT);
        assertThat(testVisa.getCedula()).isEqualTo(DEFAULT_CEDULA);
        assertThat(testVisa.getNumeroVisa()).isEqualTo(DEFAULT_NUMERO_VISA);
        assertThat(testVisa.getDateEmission()).isEqualTo(DEFAULT_DATE_EMISSION);
        assertThat(testVisa.getDateExpiration()).isEqualTo(DEFAULT_DATE_EXPIRATION);
        assertThat(testVisa.getValidePour()).isEqualTo(DEFAULT_VALIDE_POUR);
        assertThat(testVisa.getNombreEntree()).isEqualTo(DEFAULT_NOMBRE_ENTREE);
        assertThat(testVisa.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVisa.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);
        assertThat(testVisa.getTaxes()).isEqualTo(DEFAULT_TAXES);
        assertThat(testVisa.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testVisa.getRemarques()).isEqualTo(DEFAULT_REMARQUES);
        assertThat(testVisa.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    public void createVisaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = visaRepository.findAll().size();

        // Create the Visa with an existing ID
        visa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVisaMockMvc.perform(post("/api/visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(visa)))
            .andExpect(status().isBadRequest());

        // Validate the Visa in the database
        List<Visa> visaList = visaRepository.findAll();
        assertThat(visaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroVisaIsRequired() throws Exception {
        int databaseSizeBeforeTest = visaRepository.findAll().size();
        // set the field null
        visa.setNumeroVisa(null);

        // Create the Visa, which fails.

        restVisaMockMvc.perform(post("/api/visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(visa)))
            .andExpect(status().isBadRequest());

        List<Visa> visaList = visaRepository.findAll();
        assertThat(visaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVisas() throws Exception {
        // Initialize the database
        visaRepository.saveAndFlush(visa);

        // Get all the visaList
        restVisaMockMvc.perform(get("/api/visas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(visa.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].nationalite").value(hasItem(DEFAULT_NATIONALITE.toString())))
            .andExpect(jsonPath("$.[*].numeroPasseport").value(hasItem(DEFAULT_NUMERO_PASSEPORT.toString())))
            .andExpect(jsonPath("$.[*].cedula").value(hasItem(DEFAULT_CEDULA.toString())))
            .andExpect(jsonPath("$.[*].numeroVisa").value(hasItem(DEFAULT_NUMERO_VISA.intValue())))
            .andExpect(jsonPath("$.[*].dateEmission").value(hasItem(DEFAULT_DATE_EMISSION.toString())))
            .andExpect(jsonPath("$.[*].dateExpiration").value(hasItem(DEFAULT_DATE_EXPIRATION.toString())))
            .andExpect(jsonPath("$.[*].validePour").value(hasItem(DEFAULT_VALIDE_POUR)))
            .andExpect(jsonPath("$.[*].nombreEntree").value(hasItem(DEFAULT_NOMBRE_ENTREE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE.toString())))
            .andExpect(jsonPath("$.[*].taxes").value(hasItem(DEFAULT_TAXES)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].remarques").value(hasItem(DEFAULT_REMARQUES.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @Test
    @Transactional
    public void getVisa() throws Exception {
        // Initialize the database
        visaRepository.saveAndFlush(visa);

        // Get the visa
        restVisaMockMvc.perform(get("/api/visas/{id}", visa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(visa.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.nationalite").value(DEFAULT_NATIONALITE.toString()))
            .andExpect(jsonPath("$.numeroPasseport").value(DEFAULT_NUMERO_PASSEPORT.toString()))
            .andExpect(jsonPath("$.cedula").value(DEFAULT_CEDULA.toString()))
            .andExpect(jsonPath("$.numeroVisa").value(DEFAULT_NUMERO_VISA.intValue()))
            .andExpect(jsonPath("$.dateEmission").value(DEFAULT_DATE_EMISSION.toString()))
            .andExpect(jsonPath("$.dateExpiration").value(DEFAULT_DATE_EXPIRATION.toString()))
            .andExpect(jsonPath("$.validePour").value(DEFAULT_VALIDE_POUR))
            .andExpect(jsonPath("$.nombreEntree").value(DEFAULT_NOMBRE_ENTREE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE.toString()))
            .andExpect(jsonPath("$.taxes").value(DEFAULT_TAXES))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.remarques").value(DEFAULT_REMARQUES.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVisa() throws Exception {
        // Get the visa
        restVisaMockMvc.perform(get("/api/visas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVisa() throws Exception {
        // Initialize the database
        visaService.save(visa);

        int databaseSizeBeforeUpdate = visaRepository.findAll().size();

        // Update the visa
        Visa updatedVisa = visaRepository.findOne(visa.getId());
        // Disconnect from session so that the updates on updatedVisa are not directly saved in db
        em.detach(updatedVisa);
        updatedVisa
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .nationalite(UPDATED_NATIONALITE)
            .numeroPasseport(UPDATED_NUMERO_PASSEPORT)
            .cedula(UPDATED_CEDULA)
            .numeroVisa(UPDATED_NUMERO_VISA)
            .dateEmission(UPDATED_DATE_EMISSION)
            .dateExpiration(UPDATED_DATE_EXPIRATION)
            .validePour(UPDATED_VALIDE_POUR)
            .nombreEntree(UPDATED_NOMBRE_ENTREE)
            .type(UPDATED_TYPE)
            .categorie(UPDATED_CATEGORIE)
            .taxes(UPDATED_TAXES)
            .adresse(UPDATED_ADRESSE)
            .remarques(UPDATED_REMARQUES)
            .state(UPDATED_STATE);

        restVisaMockMvc.perform(put("/api/visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVisa)))
            .andExpect(status().isOk());

        // Validate the Visa in the database
        List<Visa> visaList = visaRepository.findAll();
        assertThat(visaList).hasSize(databaseSizeBeforeUpdate);
        Visa testVisa = visaList.get(visaList.size() - 1);
        assertThat(testVisa.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testVisa.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testVisa.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
        assertThat(testVisa.getNumeroPasseport()).isEqualTo(UPDATED_NUMERO_PASSEPORT);
        assertThat(testVisa.getCedula()).isEqualTo(UPDATED_CEDULA);
        assertThat(testVisa.getNumeroVisa()).isEqualTo(UPDATED_NUMERO_VISA);
        assertThat(testVisa.getDateEmission()).isEqualTo(UPDATED_DATE_EMISSION);
        assertThat(testVisa.getDateExpiration()).isEqualTo(UPDATED_DATE_EXPIRATION);
        assertThat(testVisa.getValidePour()).isEqualTo(UPDATED_VALIDE_POUR);
        assertThat(testVisa.getNombreEntree()).isEqualTo(UPDATED_NOMBRE_ENTREE);
        assertThat(testVisa.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVisa.getCategorie()).isEqualTo(UPDATED_CATEGORIE);
        assertThat(testVisa.getTaxes()).isEqualTo(UPDATED_TAXES);
        assertThat(testVisa.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testVisa.getRemarques()).isEqualTo(UPDATED_REMARQUES);
        assertThat(testVisa.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingVisa() throws Exception {
        int databaseSizeBeforeUpdate = visaRepository.findAll().size();

        // Create the Visa

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVisaMockMvc.perform(put("/api/visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(visa)))
            .andExpect(status().isCreated());

        // Validate the Visa in the database
        List<Visa> visaList = visaRepository.findAll();
        assertThat(visaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVisa() throws Exception {
        // Initialize the database
        visaService.save(visa);

        int databaseSizeBeforeDelete = visaRepository.findAll().size();

        // Get the visa
        restVisaMockMvc.perform(delete("/api/visas/{id}", visa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Visa> visaList = visaRepository.findAll();
        assertThat(visaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Visa.class);
        Visa visa1 = new Visa();
        visa1.setId(1L);
        Visa visa2 = new Visa();
        visa2.setId(visa1.getId());
        assertThat(visa1).isEqualTo(visa2);
        visa2.setId(2L);
        assertThat(visa1).isNotEqualTo(visa2);
        visa1.setId(null);
        assertThat(visa1).isNotEqualTo(visa2);
    }
}
