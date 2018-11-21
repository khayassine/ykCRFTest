package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.Etude;
import ma.kayas.cro.ykcrf.repository.EtudeRepository;
import ma.kayas.cro.ykcrf.service.EtudeService;
import ma.kayas.cro.ykcrf.service.dto.EtudeDTO;
import ma.kayas.cro.ykcrf.service.mapper.EtudeMapper;
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
 * Test class for the EtudeResource REST controller.
 *
 * @see EtudeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class EtudeResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PAGE_HTML = "AAAAAAAAAA";
    private static final String UPDATED_PAGE_HTML = "BBBBBBBBBB";

    private static final String DEFAULT_CSS_GLOBAL = "AAAAAAAAAA";
    private static final String UPDATED_CSS_GLOBAL = "BBBBBBBBBB";

    @Autowired
    private EtudeRepository etudeRepository;

    @Autowired
    private EtudeMapper etudeMapper;

    @Autowired
    private EtudeService etudeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEtudeMockMvc;

    private Etude etude;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtudeResource etudeResource = new EtudeResource(etudeService);
        this.restEtudeMockMvc = MockMvcBuilders.standaloneSetup(etudeResource)
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
    public static Etude createEntity(EntityManager em) {
        Etude etude = new Etude()
            .code(DEFAULT_CODE)
            .titre(DEFAULT_TITRE)
            .description(DEFAULT_DESCRIPTION)
            .pageHtml(DEFAULT_PAGE_HTML)
            .cssGlobal(DEFAULT_CSS_GLOBAL);
        return etude;
    }

    @Before
    public void initTest() {
        etude = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtude() throws Exception {
        int databaseSizeBeforeCreate = etudeRepository.findAll().size();

        // Create the Etude
        EtudeDTO etudeDTO = etudeMapper.toDto(etude);
        restEtudeMockMvc.perform(post("/api/etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeDTO)))
            .andExpect(status().isCreated());

        // Validate the Etude in the database
        List<Etude> etudeList = etudeRepository.findAll();
        assertThat(etudeList).hasSize(databaseSizeBeforeCreate + 1);
        Etude testEtude = etudeList.get(etudeList.size() - 1);
        assertThat(testEtude.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testEtude.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testEtude.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEtude.getPageHtml()).isEqualTo(DEFAULT_PAGE_HTML);
        assertThat(testEtude.getCssGlobal()).isEqualTo(DEFAULT_CSS_GLOBAL);
    }

    @Test
    @Transactional
    public void createEtudeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etudeRepository.findAll().size();

        // Create the Etude with an existing ID
        etude.setId(1L);
        EtudeDTO etudeDTO = etudeMapper.toDto(etude);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtudeMockMvc.perform(post("/api/etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etude in the database
        List<Etude> etudeList = etudeRepository.findAll();
        assertThat(etudeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = etudeRepository.findAll().size();
        // set the field null
        etude.setCode(null);

        // Create the Etude, which fails.
        EtudeDTO etudeDTO = etudeMapper.toDto(etude);

        restEtudeMockMvc.perform(post("/api/etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeDTO)))
            .andExpect(status().isBadRequest());

        List<Etude> etudeList = etudeRepository.findAll();
        assertThat(etudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = etudeRepository.findAll().size();
        // set the field null
        etude.setTitre(null);

        // Create the Etude, which fails.
        EtudeDTO etudeDTO = etudeMapper.toDto(etude);

        restEtudeMockMvc.perform(post("/api/etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeDTO)))
            .andExpect(status().isBadRequest());

        List<Etude> etudeList = etudeRepository.findAll();
        assertThat(etudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtudes() throws Exception {
        // Initialize the database
        etudeRepository.saveAndFlush(etude);

        // Get all the etudeList
        restEtudeMockMvc.perform(get("/api/etudes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etude.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].pageHtml").value(hasItem(DEFAULT_PAGE_HTML.toString())))
            .andExpect(jsonPath("$.[*].cssGlobal").value(hasItem(DEFAULT_CSS_GLOBAL.toString())));
    }
    
    @Test
    @Transactional
    public void getEtude() throws Exception {
        // Initialize the database
        etudeRepository.saveAndFlush(etude);

        // Get the etude
        restEtudeMockMvc.perform(get("/api/etudes/{id}", etude.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etude.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.pageHtml").value(DEFAULT_PAGE_HTML.toString()))
            .andExpect(jsonPath("$.cssGlobal").value(DEFAULT_CSS_GLOBAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEtude() throws Exception {
        // Get the etude
        restEtudeMockMvc.perform(get("/api/etudes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtude() throws Exception {
        // Initialize the database
        etudeRepository.saveAndFlush(etude);

        int databaseSizeBeforeUpdate = etudeRepository.findAll().size();

        // Update the etude
        Etude updatedEtude = etudeRepository.findById(etude.getId()).get();
        // Disconnect from session so that the updates on updatedEtude are not directly saved in db
        em.detach(updatedEtude);
        updatedEtude
            .code(UPDATED_CODE)
            .titre(UPDATED_TITRE)
            .description(UPDATED_DESCRIPTION)
            .pageHtml(UPDATED_PAGE_HTML)
            .cssGlobal(UPDATED_CSS_GLOBAL);
        EtudeDTO etudeDTO = etudeMapper.toDto(updatedEtude);

        restEtudeMockMvc.perform(put("/api/etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeDTO)))
            .andExpect(status().isOk());

        // Validate the Etude in the database
        List<Etude> etudeList = etudeRepository.findAll();
        assertThat(etudeList).hasSize(databaseSizeBeforeUpdate);
        Etude testEtude = etudeList.get(etudeList.size() - 1);
        assertThat(testEtude.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testEtude.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testEtude.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEtude.getPageHtml()).isEqualTo(UPDATED_PAGE_HTML);
        assertThat(testEtude.getCssGlobal()).isEqualTo(UPDATED_CSS_GLOBAL);
    }

    @Test
    @Transactional
    public void updateNonExistingEtude() throws Exception {
        int databaseSizeBeforeUpdate = etudeRepository.findAll().size();

        // Create the Etude
        EtudeDTO etudeDTO = etudeMapper.toDto(etude);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtudeMockMvc.perform(put("/api/etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etude in the database
        List<Etude> etudeList = etudeRepository.findAll();
        assertThat(etudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtude() throws Exception {
        // Initialize the database
        etudeRepository.saveAndFlush(etude);

        int databaseSizeBeforeDelete = etudeRepository.findAll().size();

        // Get the etude
        restEtudeMockMvc.perform(delete("/api/etudes/{id}", etude.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Etude> etudeList = etudeRepository.findAll();
        assertThat(etudeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Etude.class);
        Etude etude1 = new Etude();
        etude1.setId(1L);
        Etude etude2 = new Etude();
        etude2.setId(etude1.getId());
        assertThat(etude1).isEqualTo(etude2);
        etude2.setId(2L);
        assertThat(etude1).isNotEqualTo(etude2);
        etude1.setId(null);
        assertThat(etude1).isNotEqualTo(etude2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtudeDTO.class);
        EtudeDTO etudeDTO1 = new EtudeDTO();
        etudeDTO1.setId(1L);
        EtudeDTO etudeDTO2 = new EtudeDTO();
        assertThat(etudeDTO1).isNotEqualTo(etudeDTO2);
        etudeDTO2.setId(etudeDTO1.getId());
        assertThat(etudeDTO1).isEqualTo(etudeDTO2);
        etudeDTO2.setId(2L);
        assertThat(etudeDTO1).isNotEqualTo(etudeDTO2);
        etudeDTO1.setId(null);
        assertThat(etudeDTO1).isNotEqualTo(etudeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etudeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etudeMapper.fromId(null)).isNull();
    }
}
