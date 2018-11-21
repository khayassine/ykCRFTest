package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.Requette;
import ma.kayas.cro.ykcrf.repository.RequetteRepository;
import ma.kayas.cro.ykcrf.service.RequetteService;
import ma.kayas.cro.ykcrf.service.dto.RequetteDTO;
import ma.kayas.cro.ykcrf.service.mapper.RequetteMapper;
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

import ma.kayas.cro.ykcrf.domain.enumeration.EtatRequette;
/**
 * Test class for the RequetteResource REST controller.
 *
 * @see RequetteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class RequetteResourceIntTest {

    private static final EtatRequette DEFAULT_ETAT = EtatRequette.COMMENTED;
    private static final EtatRequette UPDATED_ETAT = EtatRequette.RESOLVED;

    @Autowired
    private RequetteRepository requetteRepository;

    @Autowired
    private RequetteMapper requetteMapper;

    @Autowired
    private RequetteService requetteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRequetteMockMvc;

    private Requette requette;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RequetteResource requetteResource = new RequetteResource(requetteService);
        this.restRequetteMockMvc = MockMvcBuilders.standaloneSetup(requetteResource)
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
    public static Requette createEntity(EntityManager em) {
        Requette requette = new Requette()
            .etat(DEFAULT_ETAT);
        return requette;
    }

    @Before
    public void initTest() {
        requette = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequette() throws Exception {
        int databaseSizeBeforeCreate = requetteRepository.findAll().size();

        // Create the Requette
        RequetteDTO requetteDTO = requetteMapper.toDto(requette);
        restRequetteMockMvc.perform(post("/api/requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requetteDTO)))
            .andExpect(status().isCreated());

        // Validate the Requette in the database
        List<Requette> requetteList = requetteRepository.findAll();
        assertThat(requetteList).hasSize(databaseSizeBeforeCreate + 1);
        Requette testRequette = requetteList.get(requetteList.size() - 1);
        assertThat(testRequette.getEtat()).isEqualTo(DEFAULT_ETAT);
    }

    @Test
    @Transactional
    public void createRequetteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requetteRepository.findAll().size();

        // Create the Requette with an existing ID
        requette.setId(1L);
        RequetteDTO requetteDTO = requetteMapper.toDto(requette);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequetteMockMvc.perform(post("/api/requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Requette in the database
        List<Requette> requetteList = requetteRepository.findAll();
        assertThat(requetteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRequettes() throws Exception {
        // Initialize the database
        requetteRepository.saveAndFlush(requette);

        // Get all the requetteList
        restRequetteMockMvc.perform(get("/api/requettes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requette.getId().intValue())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())));
    }
    
    @Test
    @Transactional
    public void getRequette() throws Exception {
        // Initialize the database
        requetteRepository.saveAndFlush(requette);

        // Get the requette
        restRequetteMockMvc.perform(get("/api/requettes/{id}", requette.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(requette.getId().intValue()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRequette() throws Exception {
        // Get the requette
        restRequetteMockMvc.perform(get("/api/requettes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequette() throws Exception {
        // Initialize the database
        requetteRepository.saveAndFlush(requette);

        int databaseSizeBeforeUpdate = requetteRepository.findAll().size();

        // Update the requette
        Requette updatedRequette = requetteRepository.findById(requette.getId()).get();
        // Disconnect from session so that the updates on updatedRequette are not directly saved in db
        em.detach(updatedRequette);
        updatedRequette
            .etat(UPDATED_ETAT);
        RequetteDTO requetteDTO = requetteMapper.toDto(updatedRequette);

        restRequetteMockMvc.perform(put("/api/requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requetteDTO)))
            .andExpect(status().isOk());

        // Validate the Requette in the database
        List<Requette> requetteList = requetteRepository.findAll();
        assertThat(requetteList).hasSize(databaseSizeBeforeUpdate);
        Requette testRequette = requetteList.get(requetteList.size() - 1);
        assertThat(testRequette.getEtat()).isEqualTo(UPDATED_ETAT);
    }

    @Test
    @Transactional
    public void updateNonExistingRequette() throws Exception {
        int databaseSizeBeforeUpdate = requetteRepository.findAll().size();

        // Create the Requette
        RequetteDTO requetteDTO = requetteMapper.toDto(requette);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequetteMockMvc.perform(put("/api/requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Requette in the database
        List<Requette> requetteList = requetteRepository.findAll();
        assertThat(requetteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequette() throws Exception {
        // Initialize the database
        requetteRepository.saveAndFlush(requette);

        int databaseSizeBeforeDelete = requetteRepository.findAll().size();

        // Get the requette
        restRequetteMockMvc.perform(delete("/api/requettes/{id}", requette.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Requette> requetteList = requetteRepository.findAll();
        assertThat(requetteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Requette.class);
        Requette requette1 = new Requette();
        requette1.setId(1L);
        Requette requette2 = new Requette();
        requette2.setId(requette1.getId());
        assertThat(requette1).isEqualTo(requette2);
        requette2.setId(2L);
        assertThat(requette1).isNotEqualTo(requette2);
        requette1.setId(null);
        assertThat(requette1).isNotEqualTo(requette2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequetteDTO.class);
        RequetteDTO requetteDTO1 = new RequetteDTO();
        requetteDTO1.setId(1L);
        RequetteDTO requetteDTO2 = new RequetteDTO();
        assertThat(requetteDTO1).isNotEqualTo(requetteDTO2);
        requetteDTO2.setId(requetteDTO1.getId());
        assertThat(requetteDTO1).isEqualTo(requetteDTO2);
        requetteDTO2.setId(2L);
        assertThat(requetteDTO1).isNotEqualTo(requetteDTO2);
        requetteDTO1.setId(null);
        assertThat(requetteDTO1).isNotEqualTo(requetteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(requetteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(requetteMapper.fromId(null)).isNull();
    }
}
