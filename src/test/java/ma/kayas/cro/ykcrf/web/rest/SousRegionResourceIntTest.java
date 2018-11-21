package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.SousRegion;
import ma.kayas.cro.ykcrf.repository.SousRegionRepository;
import ma.kayas.cro.ykcrf.service.SousRegionService;
import ma.kayas.cro.ykcrf.service.dto.SousRegionDTO;
import ma.kayas.cro.ykcrf.service.mapper.SousRegionMapper;
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

/**
 * Test class for the SousRegionResource REST controller.
 *
 * @see SousRegionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class SousRegionResourceIntTest {

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    @Autowired
    private SousRegionRepository sousRegionRepository;

    @Autowired
    private SousRegionMapper sousRegionMapper;

    @Autowired
    private SousRegionService sousRegionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSousRegionMockMvc;

    private SousRegion sousRegion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SousRegionResource sousRegionResource = new SousRegionResource(sousRegionService);
        this.restSousRegionMockMvc = MockMvcBuilders.standaloneSetup(sousRegionResource)
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
    public static SousRegion createEntity(EntityManager em) {
        SousRegion sousRegion = new SousRegion()
            .titre(DEFAULT_TITRE);
        return sousRegion;
    }

    @Before
    public void initTest() {
        sousRegion = createEntity(em);
    }

    @Test
    @Transactional
    public void createSousRegion() throws Exception {
        int databaseSizeBeforeCreate = sousRegionRepository.findAll().size();

        // Create the SousRegion
        SousRegionDTO sousRegionDTO = sousRegionMapper.toDto(sousRegion);
        restSousRegionMockMvc.perform(post("/api/sous-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sousRegionDTO)))
            .andExpect(status().isCreated());

        // Validate the SousRegion in the database
        List<SousRegion> sousRegionList = sousRegionRepository.findAll();
        assertThat(sousRegionList).hasSize(databaseSizeBeforeCreate + 1);
        SousRegion testSousRegion = sousRegionList.get(sousRegionList.size() - 1);
        assertThat(testSousRegion.getTitre()).isEqualTo(DEFAULT_TITRE);
    }

    @Test
    @Transactional
    public void createSousRegionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sousRegionRepository.findAll().size();

        // Create the SousRegion with an existing ID
        sousRegion.setId(1L);
        SousRegionDTO sousRegionDTO = sousRegionMapper.toDto(sousRegion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSousRegionMockMvc.perform(post("/api/sous-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sousRegionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SousRegion in the database
        List<SousRegion> sousRegionList = sousRegionRepository.findAll();
        assertThat(sousRegionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSousRegions() throws Exception {
        // Initialize the database
        sousRegionRepository.saveAndFlush(sousRegion);

        // Get all the sousRegionList
        restSousRegionMockMvc.perform(get("/api/sous-regions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sousRegion.getId().intValue())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())));
    }
    
    @Test
    @Transactional
    public void getSousRegion() throws Exception {
        // Initialize the database
        sousRegionRepository.saveAndFlush(sousRegion);

        // Get the sousRegion
        restSousRegionMockMvc.perform(get("/api/sous-regions/{id}", sousRegion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sousRegion.getId().intValue()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSousRegion() throws Exception {
        // Get the sousRegion
        restSousRegionMockMvc.perform(get("/api/sous-regions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSousRegion() throws Exception {
        // Initialize the database
        sousRegionRepository.saveAndFlush(sousRegion);

        int databaseSizeBeforeUpdate = sousRegionRepository.findAll().size();

        // Update the sousRegion
        SousRegion updatedSousRegion = sousRegionRepository.findById(sousRegion.getId()).get();
        // Disconnect from session so that the updates on updatedSousRegion are not directly saved in db
        em.detach(updatedSousRegion);
        updatedSousRegion
            .titre(UPDATED_TITRE);
        SousRegionDTO sousRegionDTO = sousRegionMapper.toDto(updatedSousRegion);

        restSousRegionMockMvc.perform(put("/api/sous-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sousRegionDTO)))
            .andExpect(status().isOk());

        // Validate the SousRegion in the database
        List<SousRegion> sousRegionList = sousRegionRepository.findAll();
        assertThat(sousRegionList).hasSize(databaseSizeBeforeUpdate);
        SousRegion testSousRegion = sousRegionList.get(sousRegionList.size() - 1);
        assertThat(testSousRegion.getTitre()).isEqualTo(UPDATED_TITRE);
    }

    @Test
    @Transactional
    public void updateNonExistingSousRegion() throws Exception {
        int databaseSizeBeforeUpdate = sousRegionRepository.findAll().size();

        // Create the SousRegion
        SousRegionDTO sousRegionDTO = sousRegionMapper.toDto(sousRegion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSousRegionMockMvc.perform(put("/api/sous-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sousRegionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SousRegion in the database
        List<SousRegion> sousRegionList = sousRegionRepository.findAll();
        assertThat(sousRegionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSousRegion() throws Exception {
        // Initialize the database
        sousRegionRepository.saveAndFlush(sousRegion);

        int databaseSizeBeforeDelete = sousRegionRepository.findAll().size();

        // Get the sousRegion
        restSousRegionMockMvc.perform(delete("/api/sous-regions/{id}", sousRegion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SousRegion> sousRegionList = sousRegionRepository.findAll();
        assertThat(sousRegionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SousRegion.class);
        SousRegion sousRegion1 = new SousRegion();
        sousRegion1.setId(1L);
        SousRegion sousRegion2 = new SousRegion();
        sousRegion2.setId(sousRegion1.getId());
        assertThat(sousRegion1).isEqualTo(sousRegion2);
        sousRegion2.setId(2L);
        assertThat(sousRegion1).isNotEqualTo(sousRegion2);
        sousRegion1.setId(null);
        assertThat(sousRegion1).isNotEqualTo(sousRegion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SousRegionDTO.class);
        SousRegionDTO sousRegionDTO1 = new SousRegionDTO();
        sousRegionDTO1.setId(1L);
        SousRegionDTO sousRegionDTO2 = new SousRegionDTO();
        assertThat(sousRegionDTO1).isNotEqualTo(sousRegionDTO2);
        sousRegionDTO2.setId(sousRegionDTO1.getId());
        assertThat(sousRegionDTO1).isEqualTo(sousRegionDTO2);
        sousRegionDTO2.setId(2L);
        assertThat(sousRegionDTO1).isNotEqualTo(sousRegionDTO2);
        sousRegionDTO1.setId(null);
        assertThat(sousRegionDTO1).isNotEqualTo(sousRegionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sousRegionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sousRegionMapper.fromId(null)).isNull();
    }
}
