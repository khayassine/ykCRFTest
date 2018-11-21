package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.ComposantValeur;
import ma.kayas.cro.ykcrf.repository.ComposantValeurRepository;
import ma.kayas.cro.ykcrf.service.ComposantValeurService;
import ma.kayas.cro.ykcrf.service.dto.ComposantValeurDTO;
import ma.kayas.cro.ykcrf.service.mapper.ComposantValeurMapper;
import ma.kayas.cro.ykcrf.web.rest.errors.ExceptionTranslator;

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


import static ma.kayas.cro.ykcrf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ma.kayas.cro.ykcrf.domain.enumeration.EtatValeur;
/**
 * Test class for the ComposantValeurResource REST controller.
 *
 * @see ComposantValeurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class ComposantValeurResourceIntTest {

    private static final String DEFAULT_VALEUR = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR = "BBBBBBBBBB";

    private static final EtatValeur DEFAULT_ETAT = EtatValeur.ND;
    private static final EtatValeur UPDATED_ETAT = EtatValeur.COMPLETED;

    @Autowired
    private ComposantValeurRepository composantValeurRepository;

    @Autowired
    private ComposantValeurMapper composantValeurMapper;

    @Autowired
    private ComposantValeurService composantValeurService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restComposantValeurMockMvc;

    private ComposantValeur composantValeur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComposantValeurResource composantValeurResource = new ComposantValeurResource(composantValeurService);
        this.restComposantValeurMockMvc = MockMvcBuilders.standaloneSetup(composantValeurResource)
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
    public static ComposantValeur createEntity(EntityManager em) {
        ComposantValeur composantValeur = new ComposantValeur()
            .valeur(DEFAULT_VALEUR)
            .etat(DEFAULT_ETAT);
        return composantValeur;
    }

    @Before
    public void initTest() {
        composantValeur = createEntity(em);
    }

    @Test
    @Transactional
    public void createComposantValeur() throws Exception {
        int databaseSizeBeforeCreate = composantValeurRepository.findAll().size();

        // Create the ComposantValeur
        ComposantValeurDTO composantValeurDTO = composantValeurMapper.toDto(composantValeur);
        restComposantValeurMockMvc.perform(post("/api/composant-valeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantValeurDTO)))
            .andExpect(status().isCreated());

        // Validate the ComposantValeur in the database
        List<ComposantValeur> composantValeurList = composantValeurRepository.findAll();
        assertThat(composantValeurList).hasSize(databaseSizeBeforeCreate + 1);
        ComposantValeur testComposantValeur = composantValeurList.get(composantValeurList.size() - 1);
        assertThat(testComposantValeur.getValeur()).isEqualTo(DEFAULT_VALEUR);
        assertThat(testComposantValeur.getEtat()).isEqualTo(DEFAULT_ETAT);
    }

    @Test
    @Transactional
    public void createComposantValeurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = composantValeurRepository.findAll().size();

        // Create the ComposantValeur with an existing ID
        composantValeur.setId(1L);
        ComposantValeurDTO composantValeurDTO = composantValeurMapper.toDto(composantValeur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComposantValeurMockMvc.perform(post("/api/composant-valeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantValeurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComposantValeur in the database
        List<ComposantValeur> composantValeurList = composantValeurRepository.findAll();
        assertThat(composantValeurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComposantValeurs() throws Exception {
        // Initialize the database
        composantValeurRepository.saveAndFlush(composantValeur);

        // Get all the composantValeurList
        restComposantValeurMockMvc.perform(get("/api/composant-valeurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(composantValeur.getId().intValue())))
            .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())));
    }
    
    @Test
    @Transactional
    public void getComposantValeur() throws Exception {
        // Initialize the database
        composantValeurRepository.saveAndFlush(composantValeur);

        // Get the composantValeur
        restComposantValeurMockMvc.perform(get("/api/composant-valeurs/{id}", composantValeur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(composantValeur.getId().intValue()))
            .andExpect(jsonPath("$.valeur").value(DEFAULT_VALEUR.toString()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComposantValeur() throws Exception {
        // Get the composantValeur
        restComposantValeurMockMvc.perform(get("/api/composant-valeurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComposantValeur() throws Exception {
        // Initialize the database
        composantValeurRepository.saveAndFlush(composantValeur);

        int databaseSizeBeforeUpdate = composantValeurRepository.findAll().size();

        // Update the composantValeur
        ComposantValeur updatedComposantValeur = composantValeurRepository.findById(composantValeur.getId()).get();
        // Disconnect from session so that the updates on updatedComposantValeur are not directly saved in db
        em.detach(updatedComposantValeur);
        updatedComposantValeur
            .valeur(UPDATED_VALEUR)
            .etat(UPDATED_ETAT);
        ComposantValeurDTO composantValeurDTO = composantValeurMapper.toDto(updatedComposantValeur);

        restComposantValeurMockMvc.perform(put("/api/composant-valeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantValeurDTO)))
            .andExpect(status().isOk());

        // Validate the ComposantValeur in the database
        List<ComposantValeur> composantValeurList = composantValeurRepository.findAll();
        assertThat(composantValeurList).hasSize(databaseSizeBeforeUpdate);
        ComposantValeur testComposantValeur = composantValeurList.get(composantValeurList.size() - 1);
        assertThat(testComposantValeur.getValeur()).isEqualTo(UPDATED_VALEUR);
        assertThat(testComposantValeur.getEtat()).isEqualTo(UPDATED_ETAT);
    }

    @Test
    @Transactional
    public void updateNonExistingComposantValeur() throws Exception {
        int databaseSizeBeforeUpdate = composantValeurRepository.findAll().size();

        // Create the ComposantValeur
        ComposantValeurDTO composantValeurDTO = composantValeurMapper.toDto(composantValeur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComposantValeurMockMvc.perform(put("/api/composant-valeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantValeurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComposantValeur in the database
        List<ComposantValeur> composantValeurList = composantValeurRepository.findAll();
        assertThat(composantValeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComposantValeur() throws Exception {
        // Initialize the database
        composantValeurRepository.saveAndFlush(composantValeur);

        int databaseSizeBeforeDelete = composantValeurRepository.findAll().size();

        // Get the composantValeur
        restComposantValeurMockMvc.perform(delete("/api/composant-valeurs/{id}", composantValeur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComposantValeur> composantValeurList = composantValeurRepository.findAll();
        assertThat(composantValeurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComposantValeur.class);
        ComposantValeur composantValeur1 = new ComposantValeur();
        composantValeur1.setId(1L);
        ComposantValeur composantValeur2 = new ComposantValeur();
        composantValeur2.setId(composantValeur1.getId());
        assertThat(composantValeur1).isEqualTo(composantValeur2);
        composantValeur2.setId(2L);
        assertThat(composantValeur1).isNotEqualTo(composantValeur2);
        composantValeur1.setId(null);
        assertThat(composantValeur1).isNotEqualTo(composantValeur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComposantValeurDTO.class);
        ComposantValeurDTO composantValeurDTO1 = new ComposantValeurDTO();
        composantValeurDTO1.setId(1L);
        ComposantValeurDTO composantValeurDTO2 = new ComposantValeurDTO();
        assertThat(composantValeurDTO1).isNotEqualTo(composantValeurDTO2);
        composantValeurDTO2.setId(composantValeurDTO1.getId());
        assertThat(composantValeurDTO1).isEqualTo(composantValeurDTO2);
        composantValeurDTO2.setId(2L);
        assertThat(composantValeurDTO1).isNotEqualTo(composantValeurDTO2);
        composantValeurDTO1.setId(null);
        assertThat(composantValeurDTO1).isNotEqualTo(composantValeurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(composantValeurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(composantValeurMapper.fromId(null)).isNull();
    }
}
