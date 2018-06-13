package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.UniteOrganisationelle;
import com.urservices.ambassade.repository.UniteOrganisationelleRepository;
import com.urservices.ambassade.service.UniteOrganisationelleService;
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
 * Test class for the UniteOrganisationelleResource REST controller.
 *
 * @see UniteOrganisationelleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class UniteOrganisationelleResourceIntTest {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private UniteOrganisationelleRepository uniteOrganisationelleRepository;

    @Autowired
    private UniteOrganisationelleService uniteOrganisationelleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUniteOrganisationelleMockMvc;

    private UniteOrganisationelle uniteOrganisationelle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UniteOrganisationelleResource uniteOrganisationelleResource = new UniteOrganisationelleResource(uniteOrganisationelleService);
        this.restUniteOrganisationelleMockMvc = MockMvcBuilders.standaloneSetup(uniteOrganisationelleResource)
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
    public static UniteOrganisationelle createEntity(EntityManager em) {
        UniteOrganisationelle uniteOrganisationelle = new UniteOrganisationelle()
            .libelle(DEFAULT_LIBELLE);
        return uniteOrganisationelle;
    }

    @Before
    public void initTest() {
        uniteOrganisationelle = createEntity(em);
    }

    @Test
    @Transactional
    public void createUniteOrganisationelle() throws Exception {
        int databaseSizeBeforeCreate = uniteOrganisationelleRepository.findAll().size();

        // Create the UniteOrganisationelle
        restUniteOrganisationelleMockMvc.perform(post("/api/unite-organisationelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uniteOrganisationelle)))
            .andExpect(status().isCreated());

        // Validate the UniteOrganisationelle in the database
        List<UniteOrganisationelle> uniteOrganisationelleList = uniteOrganisationelleRepository.findAll();
        assertThat(uniteOrganisationelleList).hasSize(databaseSizeBeforeCreate + 1);
        UniteOrganisationelle testUniteOrganisationelle = uniteOrganisationelleList.get(uniteOrganisationelleList.size() - 1);
        assertThat(testUniteOrganisationelle.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createUniteOrganisationelleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uniteOrganisationelleRepository.findAll().size();

        // Create the UniteOrganisationelle with an existing ID
        uniteOrganisationelle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUniteOrganisationelleMockMvc.perform(post("/api/unite-organisationelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uniteOrganisationelle)))
            .andExpect(status().isBadRequest());

        // Validate the UniteOrganisationelle in the database
        List<UniteOrganisationelle> uniteOrganisationelleList = uniteOrganisationelleRepository.findAll();
        assertThat(uniteOrganisationelleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = uniteOrganisationelleRepository.findAll().size();
        // set the field null
        uniteOrganisationelle.setLibelle(null);

        // Create the UniteOrganisationelle, which fails.

        restUniteOrganisationelleMockMvc.perform(post("/api/unite-organisationelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uniteOrganisationelle)))
            .andExpect(status().isBadRequest());

        List<UniteOrganisationelle> uniteOrganisationelleList = uniteOrganisationelleRepository.findAll();
        assertThat(uniteOrganisationelleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUniteOrganisationelles() throws Exception {
        // Initialize the database
        uniteOrganisationelleRepository.saveAndFlush(uniteOrganisationelle);

        // Get all the uniteOrganisationelleList
        restUniteOrganisationelleMockMvc.perform(get("/api/unite-organisationelles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uniteOrganisationelle.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }

    @Test
    @Transactional
    public void getUniteOrganisationelle() throws Exception {
        // Initialize the database
        uniteOrganisationelleRepository.saveAndFlush(uniteOrganisationelle);

        // Get the uniteOrganisationelle
        restUniteOrganisationelleMockMvc.perform(get("/api/unite-organisationelles/{id}", uniteOrganisationelle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uniteOrganisationelle.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUniteOrganisationelle() throws Exception {
        // Get the uniteOrganisationelle
        restUniteOrganisationelleMockMvc.perform(get("/api/unite-organisationelles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUniteOrganisationelle() throws Exception {
        // Initialize the database
        uniteOrganisationelleService.save(uniteOrganisationelle);

        int databaseSizeBeforeUpdate = uniteOrganisationelleRepository.findAll().size();

        // Update the uniteOrganisationelle
        UniteOrganisationelle updatedUniteOrganisationelle = uniteOrganisationelleRepository.findOne(uniteOrganisationelle.getId());
        // Disconnect from session so that the updates on updatedUniteOrganisationelle are not directly saved in db
        em.detach(updatedUniteOrganisationelle);
        updatedUniteOrganisationelle
            .libelle(UPDATED_LIBELLE);

        restUniteOrganisationelleMockMvc.perform(put("/api/unite-organisationelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUniteOrganisationelle)))
            .andExpect(status().isOk());

        // Validate the UniteOrganisationelle in the database
        List<UniteOrganisationelle> uniteOrganisationelleList = uniteOrganisationelleRepository.findAll();
        assertThat(uniteOrganisationelleList).hasSize(databaseSizeBeforeUpdate);
        UniteOrganisationelle testUniteOrganisationelle = uniteOrganisationelleList.get(uniteOrganisationelleList.size() - 1);
        assertThat(testUniteOrganisationelle.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingUniteOrganisationelle() throws Exception {
        int databaseSizeBeforeUpdate = uniteOrganisationelleRepository.findAll().size();

        // Create the UniteOrganisationelle

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUniteOrganisationelleMockMvc.perform(put("/api/unite-organisationelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uniteOrganisationelle)))
            .andExpect(status().isCreated());

        // Validate the UniteOrganisationelle in the database
        List<UniteOrganisationelle> uniteOrganisationelleList = uniteOrganisationelleRepository.findAll();
        assertThat(uniteOrganisationelleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUniteOrganisationelle() throws Exception {
        // Initialize the database
        uniteOrganisationelleService.save(uniteOrganisationelle);

        int databaseSizeBeforeDelete = uniteOrganisationelleRepository.findAll().size();

        // Get the uniteOrganisationelle
        restUniteOrganisationelleMockMvc.perform(delete("/api/unite-organisationelles/{id}", uniteOrganisationelle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UniteOrganisationelle> uniteOrganisationelleList = uniteOrganisationelleRepository.findAll();
        assertThat(uniteOrganisationelleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UniteOrganisationelle.class);
        UniteOrganisationelle uniteOrganisationelle1 = new UniteOrganisationelle();
        uniteOrganisationelle1.setId(1L);
        UniteOrganisationelle uniteOrganisationelle2 = new UniteOrganisationelle();
        uniteOrganisationelle2.setId(uniteOrganisationelle1.getId());
        assertThat(uniteOrganisationelle1).isEqualTo(uniteOrganisationelle2);
        uniteOrganisationelle2.setId(2L);
        assertThat(uniteOrganisationelle1).isNotEqualTo(uniteOrganisationelle2);
        uniteOrganisationelle1.setId(null);
        assertThat(uniteOrganisationelle1).isNotEqualTo(uniteOrganisationelle2);
    }
}
