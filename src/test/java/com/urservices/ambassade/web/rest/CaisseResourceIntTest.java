package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.Caisse;
import com.urservices.ambassade.repository.CaisseRepository;
import com.urservices.ambassade.service.CaisseService;
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

/**
 * Test class for the CaisseResource REST controller.
 *
 * @see CaisseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class CaisseResourceIntTest {

    private static final Long DEFAULT_REFERENCE = 1L;
    private static final Long UPDATED_REFERENCE = 2L;

    private static final ZonedDateTime DEFAULT_DATE_DU_JOUR = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DU_JOUR = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_ID = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_CONCERNE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_CONCERNE = "BBBBBBBBBB";

    private static final String DEFAULT_MONNAIE = "AAAAAAAAAA";
    private static final String UPDATED_MONNAIE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_DATE_RETOUR = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_RETOUR = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM = 1;
    private static final Integer UPDATED_NUM = 2;

    private static final String DEFAULT_PAIEMENT = "AAAAAAAAAA";
    private static final String UPDATED_PAIEMENT = "BBBBBBBBBB";

    @Autowired
    private CaisseRepository caisseRepository;

    @Autowired
    private CaisseService caisseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaisseMockMvc;

    private Caisse caisse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaisseResource caisseResource = new CaisseResource(caisseService);
        this.restCaisseMockMvc = MockMvcBuilders.standaloneSetup(caisseResource)
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
    public static Caisse createEntity(EntityManager em) {
        Caisse caisse = new Caisse()
            .reference(DEFAULT_REFERENCE)
            .dateDuJour(DEFAULT_DATE_DU_JOUR)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .typeID(DEFAULT_TYPE_ID)
            .numeroID(DEFAULT_NUMERO_ID)
            .serviceConcerne(DEFAULT_SERVICE_CONCERNE)
            .monnaie(DEFAULT_MONNAIE)
            .montant(DEFAULT_MONTANT)
            .dateRetour(DEFAULT_DATE_RETOUR)
            .telephone(DEFAULT_TELEPHONE)
            .num(DEFAULT_NUM)
            .paiement(DEFAULT_PAIEMENT);
        return caisse;
    }

    @Before
    public void initTest() {
        caisse = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaisse() throws Exception {
        int databaseSizeBeforeCreate = caisseRepository.findAll().size();

        // Create the Caisse
        restCaisseMockMvc.perform(post("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisse)))
            .andExpect(status().isCreated());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeCreate + 1);
        Caisse testCaisse = caisseList.get(caisseList.size() - 1);
        assertThat(testCaisse.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testCaisse.getDateDuJour()).isEqualTo(DEFAULT_DATE_DU_JOUR);
        assertThat(testCaisse.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testCaisse.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testCaisse.getTypeID()).isEqualTo(DEFAULT_TYPE_ID);
        assertThat(testCaisse.getNumeroID()).isEqualTo(DEFAULT_NUMERO_ID);
        assertThat(testCaisse.getServiceConcerne()).isEqualTo(DEFAULT_SERVICE_CONCERNE);
        assertThat(testCaisse.getMonnaie()).isEqualTo(DEFAULT_MONNAIE);
        assertThat(testCaisse.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testCaisse.getDateRetour()).isEqualTo(DEFAULT_DATE_RETOUR);
        assertThat(testCaisse.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testCaisse.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testCaisse.getPaiement()).isEqualTo(DEFAULT_PAIEMENT);
    }

    @Test
    @Transactional
    public void createCaisseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caisseRepository.findAll().size();

        // Create the Caisse with an existing ID
        caisse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaisseMockMvc.perform(post("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisse)))
            .andExpect(status().isBadRequest());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = caisseRepository.findAll().size();
        // set the field null
        caisse.setNum(null);

        // Create the Caisse, which fails.

        restCaisseMockMvc.perform(post("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisse)))
            .andExpect(status().isBadRequest());

        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCaisses() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        // Get all the caisseList
        restCaisseMockMvc.perform(get("/api/caisses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caisse.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.intValue())))
            .andExpect(jsonPath("$.[*].dateDuJour").value(hasItem(sameInstant(DEFAULT_DATE_DU_JOUR))))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].typeID").value(hasItem(DEFAULT_TYPE_ID.toString())))
            .andExpect(jsonPath("$.[*].numeroID").value(hasItem(DEFAULT_NUMERO_ID.toString())))
            .andExpect(jsonPath("$.[*].serviceConcerne").value(hasItem(DEFAULT_SERVICE_CONCERNE.toString())))
            .andExpect(jsonPath("$.[*].monnaie").value(hasItem(DEFAULT_MONNAIE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].dateRetour").value(hasItem(sameInstant(DEFAULT_DATE_RETOUR))))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].paiement").value(hasItem(DEFAULT_PAIEMENT.toString())));
    }

    @Test
    @Transactional
    public void getCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        // Get the caisse
        restCaisseMockMvc.perform(get("/api/caisses/{id}", caisse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caisse.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.intValue()))
            .andExpect(jsonPath("$.dateDuJour").value(sameInstant(DEFAULT_DATE_DU_JOUR)))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.typeID").value(DEFAULT_TYPE_ID.toString()))
            .andExpect(jsonPath("$.numeroID").value(DEFAULT_NUMERO_ID.toString()))
            .andExpect(jsonPath("$.serviceConcerne").value(DEFAULT_SERVICE_CONCERNE.toString()))
            .andExpect(jsonPath("$.monnaie").value(DEFAULT_MONNAIE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.dateRetour").value(sameInstant(DEFAULT_DATE_RETOUR)))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM))
            .andExpect(jsonPath("$.paiement").value(DEFAULT_PAIEMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCaisse() throws Exception {
        // Get the caisse
        restCaisseMockMvc.perform(get("/api/caisses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaisse() throws Exception {
        // Initialize the database
        caisseService.save(caisse);

        int databaseSizeBeforeUpdate = caisseRepository.findAll().size();

        // Update the caisse
        Caisse updatedCaisse = caisseRepository.findOne(caisse.getId());
        // Disconnect from session so that the updates on updatedCaisse are not directly saved in db
        em.detach(updatedCaisse);
        updatedCaisse
            .reference(UPDATED_REFERENCE)
            .dateDuJour(UPDATED_DATE_DU_JOUR)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .typeID(UPDATED_TYPE_ID)
            .numeroID(UPDATED_NUMERO_ID)
            .serviceConcerne(UPDATED_SERVICE_CONCERNE)
            .monnaie(UPDATED_MONNAIE)
            .montant(UPDATED_MONTANT)
            .dateRetour(UPDATED_DATE_RETOUR)
            .telephone(UPDATED_TELEPHONE)
            .num(UPDATED_NUM)
            .paiement(UPDATED_PAIEMENT);

        restCaisseMockMvc.perform(put("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaisse)))
            .andExpect(status().isOk());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeUpdate);
        Caisse testCaisse = caisseList.get(caisseList.size() - 1);
        assertThat(testCaisse.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testCaisse.getDateDuJour()).isEqualTo(UPDATED_DATE_DU_JOUR);
        assertThat(testCaisse.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testCaisse.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testCaisse.getTypeID()).isEqualTo(UPDATED_TYPE_ID);
        assertThat(testCaisse.getNumeroID()).isEqualTo(UPDATED_NUMERO_ID);
        assertThat(testCaisse.getServiceConcerne()).isEqualTo(UPDATED_SERVICE_CONCERNE);
        assertThat(testCaisse.getMonnaie()).isEqualTo(UPDATED_MONNAIE);
        assertThat(testCaisse.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testCaisse.getDateRetour()).isEqualTo(UPDATED_DATE_RETOUR);
        assertThat(testCaisse.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testCaisse.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testCaisse.getPaiement()).isEqualTo(UPDATED_PAIEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingCaisse() throws Exception {
        int databaseSizeBeforeUpdate = caisseRepository.findAll().size();

        // Create the Caisse

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCaisseMockMvc.perform(put("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisse)))
            .andExpect(status().isCreated());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCaisse() throws Exception {
        // Initialize the database
        caisseService.save(caisse);

        int databaseSizeBeforeDelete = caisseRepository.findAll().size();

        // Get the caisse
        restCaisseMockMvc.perform(delete("/api/caisses/{id}", caisse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Caisse.class);
        Caisse caisse1 = new Caisse();
        caisse1.setId(1L);
        Caisse caisse2 = new Caisse();
        caisse2.setId(caisse1.getId());
        assertThat(caisse1).isEqualTo(caisse2);
        caisse2.setId(2L);
        assertThat(caisse1).isNotEqualTo(caisse2);
        caisse1.setId(null);
        assertThat(caisse1).isNotEqualTo(caisse2);
    }
}
