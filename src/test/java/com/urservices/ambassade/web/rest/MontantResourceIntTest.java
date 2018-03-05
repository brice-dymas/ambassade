package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.Montant;
import com.urservices.ambassade.repository.MontantRepository;
import com.urservices.ambassade.service.MontantService;
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
 * Test class for the MontantResource REST controller.
 *
 * @see MontantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class MontantResourceIntTest {

    private static final String DEFAULT_MONNAIE = "AAAAAAAAAA";
    private static final String UPDATED_MONNAIE = "BBBBBBBBBB";

    private static final Long DEFAULT_MONTANT = 0L;
    private static final Long UPDATED_MONTANT = 1L;

    private static final String DEFAULT_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUIT = "BBBBBBBBBB";

    @Autowired
    private MontantRepository montantRepository;

    @Autowired
    private MontantService montantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMontantMockMvc;

    private Montant montant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MontantResource montantResource = new MontantResource(montantService);
        this.restMontantMockMvc = MockMvcBuilders.standaloneSetup(montantResource)
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
    public static Montant createEntity(EntityManager em) {
        Montant montant = new Montant()
            .monnaie(DEFAULT_MONNAIE)
            .montant(DEFAULT_MONTANT)
            .produit(DEFAULT_PRODUIT);
        return montant;
    }

    @Before
    public void initTest() {
        montant = createEntity(em);
    }

    @Test
    @Transactional
    public void createMontant() throws Exception {
        int databaseSizeBeforeCreate = montantRepository.findAll().size();

        // Create the Montant
        restMontantMockMvc.perform(post("/api/montants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montant)))
            .andExpect(status().isCreated());

        // Validate the Montant in the database
        List<Montant> montantList = montantRepository.findAll();
        assertThat(montantList).hasSize(databaseSizeBeforeCreate + 1);
        Montant testMontant = montantList.get(montantList.size() - 1);
        assertThat(testMontant.getMonnaie()).isEqualTo(DEFAULT_MONNAIE);
        assertThat(testMontant.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testMontant.getProduit()).isEqualTo(DEFAULT_PRODUIT);
    }

    @Test
    @Transactional
    public void createMontantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = montantRepository.findAll().size();

        // Create the Montant with an existing ID
        montant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMontantMockMvc.perform(post("/api/montants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montant)))
            .andExpect(status().isBadRequest());

        // Validate the Montant in the database
        List<Montant> montantList = montantRepository.findAll();
        assertThat(montantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMontants() throws Exception {
        // Initialize the database
        montantRepository.saveAndFlush(montant);

        // Get all the montantList
        restMontantMockMvc.perform(get("/api/montants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(montant.getId().intValue())))
            .andExpect(jsonPath("$.[*].monnaie").value(hasItem(DEFAULT_MONNAIE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].produit").value(hasItem(DEFAULT_PRODUIT.toString())));
    }

    @Test
    @Transactional
    public void searchWithMontants() throws Exception {
        // Initialize the database
        montantRepository.saveAndFlush(montant);

        // Get all the montantList
        restMontantMockMvc.perform(get("/api/montants?monnaie=AAAAAAAAAA&produit=AAAAAAAAAA&montant=0&sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(montant.getId().intValue())))
            .andExpect(jsonPath("$.[*].monnaie").value(hasItem(DEFAULT_MONNAIE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].produit").value(hasItem(DEFAULT_PRODUIT.toString())));
    }
    
    @Test
    @Transactional
    public void searchWithoutMontants() throws Exception {
        // Initialize the database
        montantRepository.saveAndFlush(montant);

        // Get all the montantList
        restMontantMockMvc.perform(get("/api/montants?monnaie=AAAAAAAAAA&produit=AAAAAAAAAA&sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(montant.getId().intValue())))
            .andExpect(jsonPath("$.[*].monnaie").value(hasItem(DEFAULT_MONNAIE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].produit").value(hasItem(DEFAULT_PRODUIT.toString())));
    }

    @Test
    @Transactional
    public void getMontant() throws Exception {
        // Initialize the database
        montantRepository.saveAndFlush(montant);

        // Get the montant
        restMontantMockMvc.perform(get("/api/montants/{id}", montant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(montant.getId().intValue()))
            .andExpect(jsonPath("$.monnaie").value(DEFAULT_MONNAIE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.produit").value(DEFAULT_PRODUIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMontant() throws Exception {
        // Get the montant
        restMontantMockMvc.perform(get("/api/montants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMontant() throws Exception {
        // Initialize the database
        montantService.save(montant);

        int databaseSizeBeforeUpdate = montantRepository.findAll().size();

        // Update the montant
        Montant updatedMontant = montantRepository.findOne(montant.getId());
        // Disconnect from session so that the updates on updatedMontant are not directly saved in db
        em.detach(updatedMontant);
        updatedMontant
            .monnaie(UPDATED_MONNAIE)
            .montant(UPDATED_MONTANT)
            .produit(UPDATED_PRODUIT);

        restMontantMockMvc.perform(put("/api/montants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMontant)))
            .andExpect(status().isOk());

        // Validate the Montant in the database
        List<Montant> montantList = montantRepository.findAll();
        assertThat(montantList).hasSize(databaseSizeBeforeUpdate);
        Montant testMontant = montantList.get(montantList.size() - 1);
        assertThat(testMontant.getMonnaie()).isEqualTo(UPDATED_MONNAIE);
        assertThat(testMontant.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testMontant.getProduit()).isEqualTo(UPDATED_PRODUIT);
    }

    @Test
    @Transactional
    public void updateNonExistingMontant() throws Exception {
        int databaseSizeBeforeUpdate = montantRepository.findAll().size();

        // Create the Montant

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMontantMockMvc.perform(put("/api/montants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montant)))
            .andExpect(status().isCreated());

        // Validate the Montant in the database
        List<Montant> montantList = montantRepository.findAll();
        assertThat(montantList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMontant() throws Exception {
        // Initialize the database
        montantService.save(montant);

        int databaseSizeBeforeDelete = montantRepository.findAll().size();

        // Get the montant
        restMontantMockMvc.perform(delete("/api/montants/{id}", montant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Montant> montantList = montantRepository.findAll();
        assertThat(montantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Montant.class);
        Montant montant1 = new Montant();
        montant1.setId(1L);
        Montant montant2 = new Montant();
        montant2.setId(montant1.getId());
        assertThat(montant1).isEqualTo(montant2);
        montant2.setId(2L);
        assertThat(montant1).isNotEqualTo(montant2);
        montant1.setId(null);
        assertThat(montant1).isNotEqualTo(montant2);
    }
}
