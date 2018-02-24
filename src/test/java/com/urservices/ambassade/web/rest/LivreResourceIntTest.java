package com.urservices.ambassade.web.rest;

import com.urservices.ambassade.AmbassadeApp;

import com.urservices.ambassade.domain.Livre;
import com.urservices.ambassade.repository.LivreRepository;
import com.urservices.ambassade.service.LivreService;
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
 * Test class for the LivreResource REST controller.
 *
 * @see LivreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmbassadeApp.class)
public class LivreResourceIntTest {

    private static final String DEFAULT_CODE_ISBN = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ISBN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTEUR = "AAAAAAAAAA";
    private static final String UPDATED_AUTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_EDITION = "AAAAAAAAAA";
    private static final String UPDATED_EDITION = "BBBBBBBBBB";

    private static final String DEFAULT_ETAGERE = "AAAAAAAAAA";
    private static final String UPDATED_ETAGERE = "BBBBBBBBBB";

    private static final String DEFAULT_ANNEE = "AAAA";
    private static final String UPDATED_ANNEE = "BBBB";

    private static final String DEFAULT_CATEGORIE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIE = "BBBBBBBBBB";

    private static final String DEFAULT_RESUME = "AAAAAAAAAA";
    private static final String UPDATED_RESUME = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTITE = "AA";
    private static final String UPDATED_QUANTITE = "BB";

    private static final String DEFAULT_DISPONIBLE = "AAAAAAAAAA";
    private static final String UPDATED_DISPONIBLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREATED_BY_PHP_RUNNER = 1;
    private static final Integer UPDATED_CREATED_BY_PHP_RUNNER = 2;

    private static final String DEFAULT_PAGE = "AAAA";
    private static final String UPDATED_PAGE = "BBBB";

    private static final String DEFAULT_CONSULTATION = "AAAAAAAAAA";
    private static final String UPDATED_CONSULTATION = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINE = "BBBBBBBBBB";

    private static final String DEFAULT_SOUS_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_SOUS_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_COLLECTION = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMPRESSION = "AAAAAAAAAA";
    private static final String UPDATED_IMPRESSION = "BBBBBBBBBB";

    private static final String DEFAULT_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_FORMAT = "BBBBBBBBBB";

    private static final String DEFAULT_INDEX = "AAAAAAAAAA";
    private static final String UPDATED_INDEX = "BBBBBBBBBB";

    private static final String DEFAULT_BIBLIOGRAPHIE = "AAAAAAAAAA";
    private static final String UPDATED_BIBLIOGRAPHIE = "BBBBBBBBBB";

    private static final String DEFAULT_LIEU_EDITION = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_EDITION = "BBBBBBBBBB";

    private static final String DEFAULT_LIEU_IMPRESSION = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_IMPRESSION = "BBBBBBBBBB";

    private static final String DEFAULT_ILLUSTRATION = "AAAAAAAAAA";
    private static final String UPDATED_ILLUSTRATION = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_STATISTIQUE = "AAAAAAAAAA";
    private static final String UPDATED_STATISTIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_GLOSSAIRE = "AAAAAAAAAA";
    private static final String UPDATED_GLOSSAIRE = "BBBBBBBBBB";

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private LivreService livreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLivreMockMvc;

    private Livre livre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LivreResource livreResource = new LivreResource(livreService);
        this.restLivreMockMvc = MockMvcBuilders.standaloneSetup(livreResource)
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
    public static Livre createEntity(EntityManager em) {
        Livre livre = new Livre()
            .codeISBN(DEFAULT_CODE_ISBN)
            .auteur(DEFAULT_AUTEUR)
            .titre(DEFAULT_TITRE)
            .edition(DEFAULT_EDITION)
            .etagere(DEFAULT_ETAGERE)
            .annee(DEFAULT_ANNEE)
            .categorie(DEFAULT_CATEGORIE)
            .resume(DEFAULT_RESUME)
            .quantite(DEFAULT_QUANTITE)
            .disponible(DEFAULT_DISPONIBLE)
            .createdByPHPRunner(DEFAULT_CREATED_BY_PHP_RUNNER)
            .page(DEFAULT_PAGE)
            .consultation(DEFAULT_CONSULTATION)
            .origine(DEFAULT_ORIGINE)
            .sousTitre(DEFAULT_SOUS_TITRE)
            .collection(DEFAULT_COLLECTION)
            .impression(DEFAULT_IMPRESSION)
            .format(DEFAULT_FORMAT)
            .index(DEFAULT_INDEX)
            .bibliographie(DEFAULT_BIBLIOGRAPHIE)
            .lieuEdition(DEFAULT_LIEU_EDITION)
            .lieuImpression(DEFAULT_LIEU_IMPRESSION)
            .illustration(DEFAULT_ILLUSTRATION)
            .observation(DEFAULT_OBSERVATION)
            .prenom(DEFAULT_PRENOM)
            .statistique(DEFAULT_STATISTIQUE)
            .glossaire(DEFAULT_GLOSSAIRE);
        return livre;
    }

    @Before
    public void initTest() {
        livre = createEntity(em);
    }

    @Test
    @Transactional
    public void createLivre() throws Exception {
        int databaseSizeBeforeCreate = livreRepository.findAll().size();

        // Create the Livre
        restLivreMockMvc.perform(post("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livre)))
            .andExpect(status().isCreated());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeCreate + 1);
        Livre testLivre = livreList.get(livreList.size() - 1);
        assertThat(testLivre.getCodeISBN()).isEqualTo(DEFAULT_CODE_ISBN);
        assertThat(testLivre.getAuteur()).isEqualTo(DEFAULT_AUTEUR);
        assertThat(testLivre.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testLivre.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testLivre.getEtagere()).isEqualTo(DEFAULT_ETAGERE);
        assertThat(testLivre.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testLivre.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);
        assertThat(testLivre.getResume()).isEqualTo(DEFAULT_RESUME);
        assertThat(testLivre.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testLivre.getDisponible()).isEqualTo(DEFAULT_DISPONIBLE);
        assertThat(testLivre.getCreatedByPHPRunner()).isEqualTo(DEFAULT_CREATED_BY_PHP_RUNNER);
        assertThat(testLivre.getPage()).isEqualTo(DEFAULT_PAGE);
        assertThat(testLivre.getConsultation()).isEqualTo(DEFAULT_CONSULTATION);
        assertThat(testLivre.getOrigine()).isEqualTo(DEFAULT_ORIGINE);
        assertThat(testLivre.getSousTitre()).isEqualTo(DEFAULT_SOUS_TITRE);
        assertThat(testLivre.getCollection()).isEqualTo(DEFAULT_COLLECTION);
        assertThat(testLivre.getImpression()).isEqualTo(DEFAULT_IMPRESSION);
        assertThat(testLivre.getFormat()).isEqualTo(DEFAULT_FORMAT);
        assertThat(testLivre.getIndex()).isEqualTo(DEFAULT_INDEX);
        assertThat(testLivre.getBibliographie()).isEqualTo(DEFAULT_BIBLIOGRAPHIE);
        assertThat(testLivre.getLieuEdition()).isEqualTo(DEFAULT_LIEU_EDITION);
        assertThat(testLivre.getLieuImpression()).isEqualTo(DEFAULT_LIEU_IMPRESSION);
        assertThat(testLivre.getIllustration()).isEqualTo(DEFAULT_ILLUSTRATION);
        assertThat(testLivre.getObservation()).isEqualTo(DEFAULT_OBSERVATION);
        assertThat(testLivre.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testLivre.getStatistique()).isEqualTo(DEFAULT_STATISTIQUE);
        assertThat(testLivre.getGlossaire()).isEqualTo(DEFAULT_GLOSSAIRE);
    }

    @Test
    @Transactional
    public void createLivreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = livreRepository.findAll().size();

        // Create the Livre with an existing ID
        livre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivreMockMvc.perform(post("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livre)))
            .andExpect(status().isBadRequest());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeISBNIsRequired() throws Exception {
        int databaseSizeBeforeTest = livreRepository.findAll().size();
        // set the field null
        livre.setCodeISBN(null);

        // Create the Livre, which fails.

        restLivreMockMvc.perform(post("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livre)))
            .andExpect(status().isBadRequest());

        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLivres() throws Exception {
        // Initialize the database
        livreRepository.saveAndFlush(livre);

        // Get all the livreList
        restLivreMockMvc.perform(get("/api/livres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livre.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeISBN").value(hasItem(DEFAULT_CODE_ISBN.toString())))
            .andExpect(jsonPath("$.[*].auteur").value(hasItem(DEFAULT_AUTEUR.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].edition").value(hasItem(DEFAULT_EDITION.toString())))
            .andExpect(jsonPath("$.[*].etagere").value(hasItem(DEFAULT_ETAGERE.toString())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE.toString())))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE.toString())))
            .andExpect(jsonPath("$.[*].resume").value(hasItem(DEFAULT_RESUME.toString())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.toString())))
            .andExpect(jsonPath("$.[*].disponible").value(hasItem(DEFAULT_DISPONIBLE.toString())))
            .andExpect(jsonPath("$.[*].createdByPHPRunner").value(hasItem(DEFAULT_CREATED_BY_PHP_RUNNER)))
            .andExpect(jsonPath("$.[*].page").value(hasItem(DEFAULT_PAGE.toString())))
            .andExpect(jsonPath("$.[*].consultation").value(hasItem(DEFAULT_CONSULTATION.toString())))
            .andExpect(jsonPath("$.[*].origine").value(hasItem(DEFAULT_ORIGINE.toString())))
            .andExpect(jsonPath("$.[*].sousTitre").value(hasItem(DEFAULT_SOUS_TITRE.toString())))
            .andExpect(jsonPath("$.[*].collection").value(hasItem(DEFAULT_COLLECTION.toString())))
            .andExpect(jsonPath("$.[*].impression").value(hasItem(DEFAULT_IMPRESSION.toString())))
            .andExpect(jsonPath("$.[*].format").value(hasItem(DEFAULT_FORMAT.toString())))
            .andExpect(jsonPath("$.[*].index").value(hasItem(DEFAULT_INDEX.toString())))
            .andExpect(jsonPath("$.[*].bibliographie").value(hasItem(DEFAULT_BIBLIOGRAPHIE.toString())))
            .andExpect(jsonPath("$.[*].lieuEdition").value(hasItem(DEFAULT_LIEU_EDITION.toString())))
            .andExpect(jsonPath("$.[*].lieuImpression").value(hasItem(DEFAULT_LIEU_IMPRESSION.toString())))
            .andExpect(jsonPath("$.[*].illustration").value(hasItem(DEFAULT_ILLUSTRATION.toString())))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].statistique").value(hasItem(DEFAULT_STATISTIQUE.toString())))
            .andExpect(jsonPath("$.[*].glossaire").value(hasItem(DEFAULT_GLOSSAIRE.toString())));
    }

    @Test
    @Transactional
    public void getLivre() throws Exception {
        // Initialize the database
        livreRepository.saveAndFlush(livre);

        // Get the livre
        restLivreMockMvc.perform(get("/api/livres/{id}", livre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(livre.getId().intValue()))
            .andExpect(jsonPath("$.codeISBN").value(DEFAULT_CODE_ISBN.toString()))
            .andExpect(jsonPath("$.auteur").value(DEFAULT_AUTEUR.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.edition").value(DEFAULT_EDITION.toString()))
            .andExpect(jsonPath("$.etagere").value(DEFAULT_ETAGERE.toString()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE.toString()))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE.toString()))
            .andExpect(jsonPath("$.resume").value(DEFAULT_RESUME.toString()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE.toString()))
            .andExpect(jsonPath("$.disponible").value(DEFAULT_DISPONIBLE.toString()))
            .andExpect(jsonPath("$.createdByPHPRunner").value(DEFAULT_CREATED_BY_PHP_RUNNER))
            .andExpect(jsonPath("$.page").value(DEFAULT_PAGE.toString()))
            .andExpect(jsonPath("$.consultation").value(DEFAULT_CONSULTATION.toString()))
            .andExpect(jsonPath("$.origine").value(DEFAULT_ORIGINE.toString()))
            .andExpect(jsonPath("$.sousTitre").value(DEFAULT_SOUS_TITRE.toString()))
            .andExpect(jsonPath("$.collection").value(DEFAULT_COLLECTION.toString()))
            .andExpect(jsonPath("$.impression").value(DEFAULT_IMPRESSION.toString()))
            .andExpect(jsonPath("$.format").value(DEFAULT_FORMAT.toString()))
            .andExpect(jsonPath("$.index").value(DEFAULT_INDEX.toString()))
            .andExpect(jsonPath("$.bibliographie").value(DEFAULT_BIBLIOGRAPHIE.toString()))
            .andExpect(jsonPath("$.lieuEdition").value(DEFAULT_LIEU_EDITION.toString()))
            .andExpect(jsonPath("$.lieuImpression").value(DEFAULT_LIEU_IMPRESSION.toString()))
            .andExpect(jsonPath("$.illustration").value(DEFAULT_ILLUSTRATION.toString()))
            .andExpect(jsonPath("$.observation").value(DEFAULT_OBSERVATION.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.statistique").value(DEFAULT_STATISTIQUE.toString()))
            .andExpect(jsonPath("$.glossaire").value(DEFAULT_GLOSSAIRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLivre() throws Exception {
        // Get the livre
        restLivreMockMvc.perform(get("/api/livres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLivre() throws Exception {
        // Initialize the database
        livreService.save(livre);

        int databaseSizeBeforeUpdate = livreRepository.findAll().size();

        // Update the livre
        Livre updatedLivre = livreRepository.findOne(livre.getId());
        // Disconnect from session so that the updates on updatedLivre are not directly saved in db
        em.detach(updatedLivre);
        updatedLivre
            .codeISBN(UPDATED_CODE_ISBN)
            .auteur(UPDATED_AUTEUR)
            .titre(UPDATED_TITRE)
            .edition(UPDATED_EDITION)
            .etagere(UPDATED_ETAGERE)
            .annee(UPDATED_ANNEE)
            .categorie(UPDATED_CATEGORIE)
            .resume(UPDATED_RESUME)
            .quantite(UPDATED_QUANTITE)
            .disponible(UPDATED_DISPONIBLE)
            .createdByPHPRunner(UPDATED_CREATED_BY_PHP_RUNNER)
            .page(UPDATED_PAGE)
            .consultation(UPDATED_CONSULTATION)
            .origine(UPDATED_ORIGINE)
            .sousTitre(UPDATED_SOUS_TITRE)
            .collection(UPDATED_COLLECTION)
            .impression(UPDATED_IMPRESSION)
            .format(UPDATED_FORMAT)
            .index(UPDATED_INDEX)
            .bibliographie(UPDATED_BIBLIOGRAPHIE)
            .lieuEdition(UPDATED_LIEU_EDITION)
            .lieuImpression(UPDATED_LIEU_IMPRESSION)
            .illustration(UPDATED_ILLUSTRATION)
            .observation(UPDATED_OBSERVATION)
            .prenom(UPDATED_PRENOM)
            .statistique(UPDATED_STATISTIQUE)
            .glossaire(UPDATED_GLOSSAIRE);

        restLivreMockMvc.perform(put("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLivre)))
            .andExpect(status().isOk());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeUpdate);
        Livre testLivre = livreList.get(livreList.size() - 1);
        assertThat(testLivre.getCodeISBN()).isEqualTo(UPDATED_CODE_ISBN);
        assertThat(testLivre.getAuteur()).isEqualTo(UPDATED_AUTEUR);
        assertThat(testLivre.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testLivre.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testLivre.getEtagere()).isEqualTo(UPDATED_ETAGERE);
        assertThat(testLivre.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testLivre.getCategorie()).isEqualTo(UPDATED_CATEGORIE);
        assertThat(testLivre.getResume()).isEqualTo(UPDATED_RESUME);
        assertThat(testLivre.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testLivre.getDisponible()).isEqualTo(UPDATED_DISPONIBLE);
        assertThat(testLivre.getCreatedByPHPRunner()).isEqualTo(UPDATED_CREATED_BY_PHP_RUNNER);
        assertThat(testLivre.getPage()).isEqualTo(UPDATED_PAGE);
        assertThat(testLivre.getConsultation()).isEqualTo(UPDATED_CONSULTATION);
        assertThat(testLivre.getOrigine()).isEqualTo(UPDATED_ORIGINE);
        assertThat(testLivre.getSousTitre()).isEqualTo(UPDATED_SOUS_TITRE);
        assertThat(testLivre.getCollection()).isEqualTo(UPDATED_COLLECTION);
        assertThat(testLivre.getImpression()).isEqualTo(UPDATED_IMPRESSION);
        assertThat(testLivre.getFormat()).isEqualTo(UPDATED_FORMAT);
        assertThat(testLivre.getIndex()).isEqualTo(UPDATED_INDEX);
        assertThat(testLivre.getBibliographie()).isEqualTo(UPDATED_BIBLIOGRAPHIE);
        assertThat(testLivre.getLieuEdition()).isEqualTo(UPDATED_LIEU_EDITION);
        assertThat(testLivre.getLieuImpression()).isEqualTo(UPDATED_LIEU_IMPRESSION);
        assertThat(testLivre.getIllustration()).isEqualTo(UPDATED_ILLUSTRATION);
        assertThat(testLivre.getObservation()).isEqualTo(UPDATED_OBSERVATION);
        assertThat(testLivre.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testLivre.getStatistique()).isEqualTo(UPDATED_STATISTIQUE);
        assertThat(testLivre.getGlossaire()).isEqualTo(UPDATED_GLOSSAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingLivre() throws Exception {
        int databaseSizeBeforeUpdate = livreRepository.findAll().size();

        // Create the Livre

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLivreMockMvc.perform(put("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livre)))
            .andExpect(status().isCreated());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLivre() throws Exception {
        // Initialize the database
        livreService.save(livre);

        int databaseSizeBeforeDelete = livreRepository.findAll().size();

        // Get the livre
        restLivreMockMvc.perform(delete("/api/livres/{id}", livre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Livre.class);
        Livre livre1 = new Livre();
        livre1.setId(1L);
        Livre livre2 = new Livre();
        livre2.setId(livre1.getId());
        assertThat(livre1).isEqualTo(livre2);
        livre2.setId(2L);
        assertThat(livre1).isNotEqualTo(livre2);
        livre1.setId(null);
        assertThat(livre1).isNotEqualTo(livre2);
    }
}
