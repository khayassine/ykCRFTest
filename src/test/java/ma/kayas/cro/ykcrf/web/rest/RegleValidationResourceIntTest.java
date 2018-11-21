package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.RegleValidation;
import ma.kayas.cro.ykcrf.repository.RegleValidationRepository;
import ma.kayas.cro.ykcrf.service.RegleValidationService;
import ma.kayas.cro.ykcrf.service.dto.RegleValidationDTO;
import ma.kayas.cro.ykcrf.service.mapper.RegleValidationMapper;
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
 * Test class for the RegleValidationResource REST controller.
 *
 * @see RegleValidationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class RegleValidationResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_REGEX_VALIDATION = "AAAAAAAAAA";
    private static final String UPDATED_REGEX_VALIDATION = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNE_COMPARAISON = "AAAAAAAAAA";
    private static final String UPDATED_SIGNE_COMPARAISON = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private RegleValidationRepository regleValidationRepository;

    @Autowired
    private RegleValidationMapper regleValidationMapper;

    @Autowired
    private RegleValidationService regleValidationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRegleValidationMockMvc;

    private RegleValidation regleValidation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RegleValidationResource regleValidationResource = new RegleValidationResource(regleValidationService);
        this.restRegleValidationMockMvc = MockMvcBuilders.standaloneSetup(regleValidationResource)
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
    public static RegleValidation createEntity(EntityManager em) {
        RegleValidation regleValidation = new RegleValidation()
            .code(DEFAULT_CODE)
            .titre(DEFAULT_TITRE)
            .regexValidation(DEFAULT_REGEX_VALIDATION)
            .signeComparaison(DEFAULT_SIGNE_COMPARAISON)
            .message(DEFAULT_MESSAGE);
        return regleValidation;
    }

    @Before
    public void initTest() {
        regleValidation = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegleValidation() throws Exception {
        int databaseSizeBeforeCreate = regleValidationRepository.findAll().size();

        // Create the RegleValidation
        RegleValidationDTO regleValidationDTO = regleValidationMapper.toDto(regleValidation);
        restRegleValidationMockMvc.perform(post("/api/regle-validations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regleValidationDTO)))
            .andExpect(status().isCreated());

        // Validate the RegleValidation in the database
        List<RegleValidation> regleValidationList = regleValidationRepository.findAll();
        assertThat(regleValidationList).hasSize(databaseSizeBeforeCreate + 1);
        RegleValidation testRegleValidation = regleValidationList.get(regleValidationList.size() - 1);
        assertThat(testRegleValidation.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRegleValidation.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testRegleValidation.getRegexValidation()).isEqualTo(DEFAULT_REGEX_VALIDATION);
        assertThat(testRegleValidation.getSigneComparaison()).isEqualTo(DEFAULT_SIGNE_COMPARAISON);
        assertThat(testRegleValidation.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createRegleValidationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = regleValidationRepository.findAll().size();

        // Create the RegleValidation with an existing ID
        regleValidation.setId(1L);
        RegleValidationDTO regleValidationDTO = regleValidationMapper.toDto(regleValidation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegleValidationMockMvc.perform(post("/api/regle-validations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regleValidationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegleValidation in the database
        List<RegleValidation> regleValidationList = regleValidationRepository.findAll();
        assertThat(regleValidationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = regleValidationRepository.findAll().size();
        // set the field null
        regleValidation.setCode(null);

        // Create the RegleValidation, which fails.
        RegleValidationDTO regleValidationDTO = regleValidationMapper.toDto(regleValidation);

        restRegleValidationMockMvc.perform(post("/api/regle-validations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regleValidationDTO)))
            .andExpect(status().isBadRequest());

        List<RegleValidation> regleValidationList = regleValidationRepository.findAll();
        assertThat(regleValidationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = regleValidationRepository.findAll().size();
        // set the field null
        regleValidation.setTitre(null);

        // Create the RegleValidation, which fails.
        RegleValidationDTO regleValidationDTO = regleValidationMapper.toDto(regleValidation);

        restRegleValidationMockMvc.perform(post("/api/regle-validations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regleValidationDTO)))
            .andExpect(status().isBadRequest());

        List<RegleValidation> regleValidationList = regleValidationRepository.findAll();
        assertThat(regleValidationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRegleValidations() throws Exception {
        // Initialize the database
        regleValidationRepository.saveAndFlush(regleValidation);

        // Get all the regleValidationList
        restRegleValidationMockMvc.perform(get("/api/regle-validations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(regleValidation.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].regexValidation").value(hasItem(DEFAULT_REGEX_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].signeComparaison").value(hasItem(DEFAULT_SIGNE_COMPARAISON.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getRegleValidation() throws Exception {
        // Initialize the database
        regleValidationRepository.saveAndFlush(regleValidation);

        // Get the regleValidation
        restRegleValidationMockMvc.perform(get("/api/regle-validations/{id}", regleValidation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(regleValidation.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.regexValidation").value(DEFAULT_REGEX_VALIDATION.toString()))
            .andExpect(jsonPath("$.signeComparaison").value(DEFAULT_SIGNE_COMPARAISON.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRegleValidation() throws Exception {
        // Get the regleValidation
        restRegleValidationMockMvc.perform(get("/api/regle-validations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegleValidation() throws Exception {
        // Initialize the database
        regleValidationRepository.saveAndFlush(regleValidation);

        int databaseSizeBeforeUpdate = regleValidationRepository.findAll().size();

        // Update the regleValidation
        RegleValidation updatedRegleValidation = regleValidationRepository.findById(regleValidation.getId()).get();
        // Disconnect from session so that the updates on updatedRegleValidation are not directly saved in db
        em.detach(updatedRegleValidation);
        updatedRegleValidation
            .code(UPDATED_CODE)
            .titre(UPDATED_TITRE)
            .regexValidation(UPDATED_REGEX_VALIDATION)
            .signeComparaison(UPDATED_SIGNE_COMPARAISON)
            .message(UPDATED_MESSAGE);
        RegleValidationDTO regleValidationDTO = regleValidationMapper.toDto(updatedRegleValidation);

        restRegleValidationMockMvc.perform(put("/api/regle-validations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regleValidationDTO)))
            .andExpect(status().isOk());

        // Validate the RegleValidation in the database
        List<RegleValidation> regleValidationList = regleValidationRepository.findAll();
        assertThat(regleValidationList).hasSize(databaseSizeBeforeUpdate);
        RegleValidation testRegleValidation = regleValidationList.get(regleValidationList.size() - 1);
        assertThat(testRegleValidation.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRegleValidation.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testRegleValidation.getRegexValidation()).isEqualTo(UPDATED_REGEX_VALIDATION);
        assertThat(testRegleValidation.getSigneComparaison()).isEqualTo(UPDATED_SIGNE_COMPARAISON);
        assertThat(testRegleValidation.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingRegleValidation() throws Exception {
        int databaseSizeBeforeUpdate = regleValidationRepository.findAll().size();

        // Create the RegleValidation
        RegleValidationDTO regleValidationDTO = regleValidationMapper.toDto(regleValidation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegleValidationMockMvc.perform(put("/api/regle-validations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regleValidationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegleValidation in the database
        List<RegleValidation> regleValidationList = regleValidationRepository.findAll();
        assertThat(regleValidationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegleValidation() throws Exception {
        // Initialize the database
        regleValidationRepository.saveAndFlush(regleValidation);

        int databaseSizeBeforeDelete = regleValidationRepository.findAll().size();

        // Get the regleValidation
        restRegleValidationMockMvc.perform(delete("/api/regle-validations/{id}", regleValidation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RegleValidation> regleValidationList = regleValidationRepository.findAll();
        assertThat(regleValidationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegleValidation.class);
        RegleValidation regleValidation1 = new RegleValidation();
        regleValidation1.setId(1L);
        RegleValidation regleValidation2 = new RegleValidation();
        regleValidation2.setId(regleValidation1.getId());
        assertThat(regleValidation1).isEqualTo(regleValidation2);
        regleValidation2.setId(2L);
        assertThat(regleValidation1).isNotEqualTo(regleValidation2);
        regleValidation1.setId(null);
        assertThat(regleValidation1).isNotEqualTo(regleValidation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegleValidationDTO.class);
        RegleValidationDTO regleValidationDTO1 = new RegleValidationDTO();
        regleValidationDTO1.setId(1L);
        RegleValidationDTO regleValidationDTO2 = new RegleValidationDTO();
        assertThat(regleValidationDTO1).isNotEqualTo(regleValidationDTO2);
        regleValidationDTO2.setId(regleValidationDTO1.getId());
        assertThat(regleValidationDTO1).isEqualTo(regleValidationDTO2);
        regleValidationDTO2.setId(2L);
        assertThat(regleValidationDTO1).isNotEqualTo(regleValidationDTO2);
        regleValidationDTO1.setId(null);
        assertThat(regleValidationDTO1).isNotEqualTo(regleValidationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(regleValidationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(regleValidationMapper.fromId(null)).isNull();
    }
}
