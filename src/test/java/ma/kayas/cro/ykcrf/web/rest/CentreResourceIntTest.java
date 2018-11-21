package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.Centre;
import ma.kayas.cro.ykcrf.repository.CentreRepository;
import ma.kayas.cro.ykcrf.service.CentreService;
import ma.kayas.cro.ykcrf.service.dto.CentreDTO;
import ma.kayas.cro.ykcrf.service.mapper.CentreMapper;
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
 * Test class for the CentreResource REST controller.
 *
 * @see CentreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class CentreResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENT = "BBBBBBBBBB";

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private CentreMapper centreMapper;

    @Autowired
    private CentreService centreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCentreMockMvc;

    private Centre centre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CentreResource centreResource = new CentreResource(centreService);
        this.restCentreMockMvc = MockMvcBuilders.standaloneSetup(centreResource)
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
    public static Centre createEntity(EntityManager em) {
        Centre centre = new Centre()
            .code(DEFAULT_CODE)
            .titre(DEFAULT_TITRE)
            .complement(DEFAULT_COMPLEMENT);
        return centre;
    }

    @Before
    public void initTest() {
        centre = createEntity(em);
    }

    @Test
    @Transactional
    public void createCentre() throws Exception {
        int databaseSizeBeforeCreate = centreRepository.findAll().size();

        // Create the Centre
        CentreDTO centreDTO = centreMapper.toDto(centre);
        restCentreMockMvc.perform(post("/api/centres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isCreated());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeCreate + 1);
        Centre testCentre = centreList.get(centreList.size() - 1);
        assertThat(testCentre.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCentre.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testCentre.getComplement()).isEqualTo(DEFAULT_COMPLEMENT);
    }

    @Test
    @Transactional
    public void createCentreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = centreRepository.findAll().size();

        // Create the Centre with an existing ID
        centre.setId(1L);
        CentreDTO centreDTO = centreMapper.toDto(centre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCentreMockMvc.perform(post("/api/centres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCentres() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        // Get all the centreList
        restCentreMockMvc.perform(get("/api/centres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(centre.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].complement").value(hasItem(DEFAULT_COMPLEMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getCentre() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        // Get the centre
        restCentreMockMvc.perform(get("/api/centres/{id}", centre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(centre.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.complement").value(DEFAULT_COMPLEMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCentre() throws Exception {
        // Get the centre
        restCentreMockMvc.perform(get("/api/centres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCentre() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        int databaseSizeBeforeUpdate = centreRepository.findAll().size();

        // Update the centre
        Centre updatedCentre = centreRepository.findById(centre.getId()).get();
        // Disconnect from session so that the updates on updatedCentre are not directly saved in db
        em.detach(updatedCentre);
        updatedCentre
            .code(UPDATED_CODE)
            .titre(UPDATED_TITRE)
            .complement(UPDATED_COMPLEMENT);
        CentreDTO centreDTO = centreMapper.toDto(updatedCentre);

        restCentreMockMvc.perform(put("/api/centres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isOk());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeUpdate);
        Centre testCentre = centreList.get(centreList.size() - 1);
        assertThat(testCentre.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCentre.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testCentre.getComplement()).isEqualTo(UPDATED_COMPLEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingCentre() throws Exception {
        int databaseSizeBeforeUpdate = centreRepository.findAll().size();

        // Create the Centre
        CentreDTO centreDTO = centreMapper.toDto(centre);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCentreMockMvc.perform(put("/api/centres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCentre() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        int databaseSizeBeforeDelete = centreRepository.findAll().size();

        // Get the centre
        restCentreMockMvc.perform(delete("/api/centres/{id}", centre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Centre.class);
        Centre centre1 = new Centre();
        centre1.setId(1L);
        Centre centre2 = new Centre();
        centre2.setId(centre1.getId());
        assertThat(centre1).isEqualTo(centre2);
        centre2.setId(2L);
        assertThat(centre1).isNotEqualTo(centre2);
        centre1.setId(null);
        assertThat(centre1).isNotEqualTo(centre2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentreDTO.class);
        CentreDTO centreDTO1 = new CentreDTO();
        centreDTO1.setId(1L);
        CentreDTO centreDTO2 = new CentreDTO();
        assertThat(centreDTO1).isNotEqualTo(centreDTO2);
        centreDTO2.setId(centreDTO1.getId());
        assertThat(centreDTO1).isEqualTo(centreDTO2);
        centreDTO2.setId(2L);
        assertThat(centreDTO1).isNotEqualTo(centreDTO2);
        centreDTO1.setId(null);
        assertThat(centreDTO1).isNotEqualTo(centreDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(centreMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(centreMapper.fromId(null)).isNull();
    }
}
