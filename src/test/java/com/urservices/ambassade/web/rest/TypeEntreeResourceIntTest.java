package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.TypeEntree;
import com.urservices.ambassade.repository.TypeEntreeRepository;
import com.urservices.ambassade.service.TypeEntreeService;
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
 * Test class for the TypeEntreeResource REST controller.
 *
 * @see TypeEntreeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class TypeEntreeResourceIntTest {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeEntreeRepository typeEntreeRepository;

    @Autowired
    private TypeEntreeService typeEntreeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeEntreeMockMvc;

    private TypeEntree typeEntree;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeEntreeResource typeEntreeResource = new TypeEntreeResource(typeEntreeService);
        this.restTypeEntreeMockMvc = MockMvcBuilders.standaloneSetup(typeEntreeResource)
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
    public static TypeEntree createEntity(EntityManager em) {
        TypeEntree typeEntree = new TypeEntree()
            .libelle(DEFAULT_LIBELLE);
        return typeEntree;
    }

    @Before
    public void initTest() {
        typeEntree = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeEntree() throws Exception {
        int databaseSizeBeforeCreate = typeEntreeRepository.findAll().size();

        // Create the TypeEntree
        restTypeEntreeMockMvc.perform(post("/api/type-entrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEntree)))
            .andExpect(status().isCreated());

        // Validate the TypeEntree in the database
        List<TypeEntree> typeEntreeList = typeEntreeRepository.findAll();
        assertThat(typeEntreeList).hasSize(databaseSizeBeforeCreate + 1);
        TypeEntree testTypeEntree = typeEntreeList.get(typeEntreeList.size() - 1);
        assertThat(testTypeEntree.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeEntreeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeEntreeRepository.findAll().size();

        // Create the TypeEntree with an existing ID
        typeEntree.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeEntreeMockMvc.perform(post("/api/type-entrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEntree)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEntree in the database
        List<TypeEntree> typeEntreeList = typeEntreeRepository.findAll();
        assertThat(typeEntreeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEntreeRepository.findAll().size();
        // set the field null
        typeEntree.setLibelle(null);

        // Create the TypeEntree, which fails.

        restTypeEntreeMockMvc.perform(post("/api/type-entrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEntree)))
            .andExpect(status().isBadRequest());

        List<TypeEntree> typeEntreeList = typeEntreeRepository.findAll();
        assertThat(typeEntreeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeEntrees() throws Exception {
        // Initialize the database
        typeEntreeRepository.saveAndFlush(typeEntree);

        // Get all the typeEntreeList
        restTypeEntreeMockMvc.perform(get("/api/type-entrees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeEntree.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }

    @Test
    @Transactional
    public void getTypeEntree() throws Exception {
        // Initialize the database
        typeEntreeRepository.saveAndFlush(typeEntree);

        // Get the typeEntree
        restTypeEntreeMockMvc.perform(get("/api/type-entrees/{id}", typeEntree.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeEntree.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeEntree() throws Exception {
        // Get the typeEntree
        restTypeEntreeMockMvc.perform(get("/api/type-entrees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeEntree() throws Exception {
        // Initialize the database
        typeEntreeService.save(typeEntree);

        int databaseSizeBeforeUpdate = typeEntreeRepository.findAll().size();

        // Update the typeEntree
        TypeEntree updatedTypeEntree = typeEntreeRepository.findOne(typeEntree.getId());
        // Disconnect from session so that the updates on updatedTypeEntree are not directly saved in db
        em.detach(updatedTypeEntree);
        updatedTypeEntree
            .libelle(UPDATED_LIBELLE);

        restTypeEntreeMockMvc.perform(put("/api/type-entrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeEntree)))
            .andExpect(status().isOk());

        // Validate the TypeEntree in the database
        List<TypeEntree> typeEntreeList = typeEntreeRepository.findAll();
        assertThat(typeEntreeList).hasSize(databaseSizeBeforeUpdate);
        TypeEntree testTypeEntree = typeEntreeList.get(typeEntreeList.size() - 1);
        assertThat(testTypeEntree.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeEntree() throws Exception {
        int databaseSizeBeforeUpdate = typeEntreeRepository.findAll().size();

        // Create the TypeEntree

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTypeEntreeMockMvc.perform(put("/api/type-entrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEntree)))
            .andExpect(status().isCreated());

        // Validate the TypeEntree in the database
        List<TypeEntree> typeEntreeList = typeEntreeRepository.findAll();
        assertThat(typeEntreeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTypeEntree() throws Exception {
        // Initialize the database
        typeEntreeService.save(typeEntree);

        int databaseSizeBeforeDelete = typeEntreeRepository.findAll().size();

        // Get the typeEntree
        restTypeEntreeMockMvc.perform(delete("/api/type-entrees/{id}", typeEntree.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeEntree> typeEntreeList = typeEntreeRepository.findAll();
        assertThat(typeEntreeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEntree.class);
        TypeEntree typeEntree1 = new TypeEntree();
        typeEntree1.setId(1L);
        TypeEntree typeEntree2 = new TypeEntree();
        typeEntree2.setId(typeEntree1.getId());
        assertThat(typeEntree1).isEqualTo(typeEntree2);
        typeEntree2.setId(2L);
        assertThat(typeEntree1).isNotEqualTo(typeEntree2);
        typeEntree1.setId(null);
        assertThat(typeEntree1).isNotEqualTo(typeEntree2);
    }
}
