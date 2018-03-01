package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.Monnaie;
import com.urservices.ambassade.repository.MonnaieRepository;
import com.urservices.ambassade.service.MonnaieService;
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
import java.util.List;

import static com.urservices.ambassade.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MonnaieResource REST controller.
 *
 * @see MonnaieResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class MonnaieResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_MONTANT = 1L;
    private static final Long UPDATED_MONTANT = 2L;

    private static final String DEFAULT_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUIT = "BBBBBBBBBB";

    @Autowired
    private MonnaieRepository monnaieRepository;

    @Autowired
    private MonnaieService monnaieService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMonnaieMockMvc;

    private Monnaie monnaie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MonnaieResource monnaieResource = new MonnaieResource(monnaieService);
        this.restMonnaieMockMvc = MockMvcBuilders.standaloneSetup(monnaieResource)
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
    public static Monnaie createEntity(EntityManager em) {
        Monnaie monnaie = new Monnaie()
            .type(DEFAULT_TYPE)
            .montant(DEFAULT_MONTANT)
            .produit(DEFAULT_PRODUIT);
        return monnaie;
    }

    @Before
    public void initTest() {
        monnaie = createEntity(em);
    }

    @Test
    @Transactional
    public void createMonnaie() throws Exception {
        int databaseSizeBeforeCreate = monnaieRepository.findAll().size();

        // Create the Monnaie
        restMonnaieMockMvc.perform(post("/api/monnaies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monnaie)))
            .andExpect(status().isCreated());

        // Validate the Monnaie in the database
        List<Monnaie> monnaieList = monnaieRepository.findAll();
        assertThat(monnaieList).hasSize(databaseSizeBeforeCreate + 1);
        Monnaie testMonnaie = monnaieList.get(monnaieList.size() - 1);
        assertThat(testMonnaie.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMonnaie.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testMonnaie.getProduit()).isEqualTo(DEFAULT_PRODUIT);
    }

    @Test
    @Transactional
    public void createMonnaieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = monnaieRepository.findAll().size();

        // Create the Monnaie with an existing ID
        monnaie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMonnaieMockMvc.perform(post("/api/monnaies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monnaie)))
            .andExpect(status().isBadRequest());

        // Validate the Monnaie in the database
        List<Monnaie> monnaieList = monnaieRepository.findAll();
        assertThat(monnaieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMonnaies() throws Exception {
        // Initialize the database
        monnaieRepository.saveAndFlush(monnaie);

        // Get all the monnaieList
        restMonnaieMockMvc.perform(get("/api/monnaies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monnaie.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].produit").value(hasItem(DEFAULT_PRODUIT.toString())));
    }

    @Test
    @Transactional
    public void getMonnaie() throws Exception {
        // Initialize the database
        monnaieRepository.saveAndFlush(monnaie);

        // Get the monnaie
        restMonnaieMockMvc.perform(get("/api/monnaies/{id}", monnaie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(monnaie.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.produit").value(DEFAULT_PRODUIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMonnaie() throws Exception {
        // Get the monnaie
        restMonnaieMockMvc.perform(get("/api/monnaies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMonnaie() throws Exception {
        // Initialize the database
        monnaieService.save(monnaie);

        int databaseSizeBeforeUpdate = monnaieRepository.findAll().size();

        // Update the monnaie
        Monnaie updatedMonnaie = monnaieRepository.findOne(monnaie.getId());
        // Disconnect from session so that the updates on updatedMonnaie are not directly saved in db
        em.detach(updatedMonnaie);
        updatedMonnaie
            .type(UPDATED_TYPE)
            .montant(UPDATED_MONTANT)
            .produit(UPDATED_PRODUIT);

        restMonnaieMockMvc.perform(put("/api/monnaies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMonnaie)))
            .andExpect(status().isOk());

        // Validate the Monnaie in the database
        List<Monnaie> monnaieList = monnaieRepository.findAll();
        assertThat(monnaieList).hasSize(databaseSizeBeforeUpdate);
        Monnaie testMonnaie = monnaieList.get(monnaieList.size() - 1);
        assertThat(testMonnaie.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMonnaie.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testMonnaie.getProduit()).isEqualTo(UPDATED_PRODUIT);
    }

    @Test
    @Transactional
    public void updateNonExistingMonnaie() throws Exception {
        int databaseSizeBeforeUpdate = monnaieRepository.findAll().size();

        // Create the Monnaie

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMonnaieMockMvc.perform(put("/api/monnaies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monnaie)))
            .andExpect(status().isCreated());

        // Validate the Monnaie in the database
        List<Monnaie> monnaieList = monnaieRepository.findAll();
        assertThat(monnaieList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMonnaie() throws Exception {
        // Initialize the database
        monnaieService.save(monnaie);

        int databaseSizeBeforeDelete = monnaieRepository.findAll().size();

        // Get the monnaie
        restMonnaieMockMvc.perform(delete("/api/monnaies/{id}", monnaie.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Monnaie> monnaieList = monnaieRepository.findAll();
        assertThat(monnaieList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Monnaie.class);
        Monnaie monnaie1 = new Monnaie();
        monnaie1.setId(1L);
        Monnaie monnaie2 = new Monnaie();
        monnaie2.setId(monnaie1.getId());
        assertThat(monnaie1).isEqualTo(monnaie2);
        monnaie2.setId(2L);
        assertThat(monnaie1).isNotEqualTo(monnaie2);
        monnaie1.setId(null);
        assertThat(monnaie1).isNotEqualTo(monnaie2);
    }
}
