package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.TypeService;
import com.urservices.ambassade.repository.TypeServiceRepository;
import com.urservices.ambassade.service.TypeServiceService;
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
 * Test class for the TypeServiceResource REST controller.
 *
 * @see TypeServiceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class TypeServiceResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Long DEFAULT_MONTANT = 0L;
    private static final Long UPDATED_MONTANT = 1L;

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    @Autowired
    private TypeServiceRepository typeServiceRepository;

    @Autowired
    private TypeServiceService typeServiceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeServiceMockMvc;

    private TypeService typeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeServiceResource typeServiceResource = new TypeServiceResource(typeServiceService);
        this.restTypeServiceMockMvc = MockMvcBuilders.standaloneSetup(typeServiceResource)
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
    public static TypeService createEntity(EntityManager em) {
        TypeService typeService = new TypeService()
            .nom(DEFAULT_NOM)
            .montant(DEFAULT_MONTANT)
            .deleted(DEFAULT_DELETED);
        return typeService;
    }

    @Before
    public void initTest() {
        typeService = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeService() throws Exception {
        int databaseSizeBeforeCreate = typeServiceRepository.findAll().size();

        // Create the TypeService
        restTypeServiceMockMvc.perform(post("/api/type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeService)))
            .andExpect(status().isCreated());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeCreate + 1);
        TypeService testTypeService = typeServiceList.get(typeServiceList.size() - 1);
        assertThat(testTypeService.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTypeService.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testTypeService.isDeleted()).isEqualTo(DEFAULT_DELETED);
    }

    @Test
    @Transactional
    public void createTypeServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeServiceRepository.findAll().size();

        // Create the TypeService with an existing ID
        typeService.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeServiceMockMvc.perform(post("/api/type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeService)))
            .andExpect(status().isBadRequest());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTypeServices() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList
        restTypeServiceMockMvc.perform(get("/api/type-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void getTypeService() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get the typeService
        restTypeServiceMockMvc.perform(get("/api/type-services/{id}", typeService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeService.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeService() throws Exception {
        // Get the typeService
        restTypeServiceMockMvc.perform(get("/api/type-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeService() throws Exception {
        // Initialize the database
        typeServiceService.save(typeService);

        int databaseSizeBeforeUpdate = typeServiceRepository.findAll().size();

        // Update the typeService
        TypeService updatedTypeService = typeServiceRepository.findOne(typeService.getId());
        // Disconnect from session so that the updates on updatedTypeService are not directly saved in db
        em.detach(updatedTypeService);
        updatedTypeService
            .nom(UPDATED_NOM)
            .montant(UPDATED_MONTANT)
            .deleted(UPDATED_DELETED);

        restTypeServiceMockMvc.perform(put("/api/type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeService)))
            .andExpect(status().isOk());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeUpdate);
        TypeService testTypeService = typeServiceList.get(typeServiceList.size() - 1);
        assertThat(testTypeService.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTypeService.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testTypeService.isDeleted()).isEqualTo(UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeService() throws Exception {
        int databaseSizeBeforeUpdate = typeServiceRepository.findAll().size();

        // Create the TypeService

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTypeServiceMockMvc.perform(put("/api/type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeService)))
            .andExpect(status().isCreated());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTypeService() throws Exception {
        // Initialize the database
        typeServiceService.save(typeService);

        int databaseSizeBeforeDelete = typeServiceRepository.findAll().size();

        // Get the typeService
        restTypeServiceMockMvc.perform(delete("/api/type-services/{id}", typeService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeService.class);
        TypeService typeService1 = new TypeService();
        typeService1.setId(1L);
        TypeService typeService2 = new TypeService();
        typeService2.setId(typeService1.getId());
        assertThat(typeService1).isEqualTo(typeService2);
        typeService2.setId(2L);
        assertThat(typeService1).isNotEqualTo(typeService2);
        typeService1.setId(null);
        assertThat(typeService1).isNotEqualTo(typeService2);
    }
}
