package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.ValidationComposant;
import ma.kayas.cro.ykcrf.repository.ValidationComposantRepository;
import ma.kayas.cro.ykcrf.service.ValidationComposantService;
import ma.kayas.cro.ykcrf.service.dto.ValidationComposantDTO;
import ma.kayas.cro.ykcrf.service.mapper.ValidationComposantMapper;
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

import ma.kayas.cro.ykcrf.domain.enumeration.NiveauValidation;
/**
 * Test class for the ValidationComposantResource REST controller.
 *
 * @see ValidationComposantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class ValidationComposantResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_REGEX_VALIDATION = "AAAAAAAAAA";
    private static final String UPDATED_REGEX_VALIDATION = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNE_COMPARAISON = "AAAAAAAAAA";
    private static final String UPDATED_SIGNE_COMPARAISON = "BBBBBBBBBB";

    private static final String DEFAULT_VALEUR_COMPARAISON = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR_COMPARAISON = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final NiveauValidation DEFAULT_NIVEAU_VALIDATION = NiveauValidation.WARNING;
    private static final NiveauValidation UPDATED_NIVEAU_VALIDATION = NiveauValidation.INFO;

    @Autowired
    private ValidationComposantRepository validationComposantRepository;

    @Autowired
    private ValidationComposantMapper validationComposantMapper;

    @Autowired
    private ValidationComposantService validationComposantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restValidationComposantMockMvc;

    private ValidationComposant validationComposant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ValidationComposantResource validationComposantResource = new ValidationComposantResource(validationComposantService);
        this.restValidationComposantMockMvc = MockMvcBuilders.standaloneSetup(validationComposantResource)
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
    public static ValidationComposant createEntity(EntityManager em) {
        ValidationComposant validationComposant = new ValidationComposant()
            .code(DEFAULT_CODE)
            .titre(DEFAULT_TITRE)
            .regexValidation(DEFAULT_REGEX_VALIDATION)
            .signeComparaison(DEFAULT_SIGNE_COMPARAISON)
            .valeurComparaison(DEFAULT_VALEUR_COMPARAISON)
            .message(DEFAULT_MESSAGE)
            .niveauValidation(DEFAULT_NIVEAU_VALIDATION);
        return validationComposant;
    }

    @Before
    public void initTest() {
        validationComposant = createEntity(em);
    }

    @Test
    @Transactional
    public void createValidationComposant() throws Exception {
        int databaseSizeBeforeCreate = validationComposantRepository.findAll().size();

        // Create the ValidationComposant
        ValidationComposantDTO validationComposantDTO = validationComposantMapper.toDto(validationComposant);
        restValidationComposantMockMvc.perform(post("/api/validation-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validationComposantDTO)))
            .andExpect(status().isCreated());

        // Validate the ValidationComposant in the database
        List<ValidationComposant> validationComposantList = validationComposantRepository.findAll();
        assertThat(validationComposantList).hasSize(databaseSizeBeforeCreate + 1);
        ValidationComposant testValidationComposant = validationComposantList.get(validationComposantList.size() - 1);
        assertThat(testValidationComposant.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testValidationComposant.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testValidationComposant.getRegexValidation()).isEqualTo(DEFAULT_REGEX_VALIDATION);
        assertThat(testValidationComposant.getSigneComparaison()).isEqualTo(DEFAULT_SIGNE_COMPARAISON);
        assertThat(testValidationComposant.getValeurComparaison()).isEqualTo(DEFAULT_VALEUR_COMPARAISON);
        assertThat(testValidationComposant.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testValidationComposant.getNiveauValidation()).isEqualTo(DEFAULT_NIVEAU_VALIDATION);
    }

    @Test
    @Transactional
    public void createValidationComposantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = validationComposantRepository.findAll().size();

        // Create the ValidationComposant with an existing ID
        validationComposant.setId(1L);
        ValidationComposantDTO validationComposantDTO = validationComposantMapper.toDto(validationComposant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValidationComposantMockMvc.perform(post("/api/validation-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validationComposantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ValidationComposant in the database
        List<ValidationComposant> validationComposantList = validationComposantRepository.findAll();
        assertThat(validationComposantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = validationComposantRepository.findAll().size();
        // set the field null
        validationComposant.setCode(null);

        // Create the ValidationComposant, which fails.
        ValidationComposantDTO validationComposantDTO = validationComposantMapper.toDto(validationComposant);

        restValidationComposantMockMvc.perform(post("/api/validation-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validationComposantDTO)))
            .andExpect(status().isBadRequest());

        List<ValidationComposant> validationComposantList = validationComposantRepository.findAll();
        assertThat(validationComposantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = validationComposantRepository.findAll().size();
        // set the field null
        validationComposant.setTitre(null);

        // Create the ValidationComposant, which fails.
        ValidationComposantDTO validationComposantDTO = validationComposantMapper.toDto(validationComposant);

        restValidationComposantMockMvc.perform(post("/api/validation-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validationComposantDTO)))
            .andExpect(status().isBadRequest());

        List<ValidationComposant> validationComposantList = validationComposantRepository.findAll();
        assertThat(validationComposantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllValidationComposants() throws Exception {
        // Initialize the database
        validationComposantRepository.saveAndFlush(validationComposant);

        // Get all the validationComposantList
        restValidationComposantMockMvc.perform(get("/api/validation-composants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(validationComposant.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].regexValidation").value(hasItem(DEFAULT_REGEX_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].signeComparaison").value(hasItem(DEFAULT_SIGNE_COMPARAISON.toString())))
            .andExpect(jsonPath("$.[*].valeurComparaison").value(hasItem(DEFAULT_VALEUR_COMPARAISON.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].niveauValidation").value(hasItem(DEFAULT_NIVEAU_VALIDATION.toString())));
    }
    
    @Test
    @Transactional
    public void getValidationComposant() throws Exception {
        // Initialize the database
        validationComposantRepository.saveAndFlush(validationComposant);

        // Get the validationComposant
        restValidationComposantMockMvc.perform(get("/api/validation-composants/{id}", validationComposant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(validationComposant.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.regexValidation").value(DEFAULT_REGEX_VALIDATION.toString()))
            .andExpect(jsonPath("$.signeComparaison").value(DEFAULT_SIGNE_COMPARAISON.toString()))
            .andExpect(jsonPath("$.valeurComparaison").value(DEFAULT_VALEUR_COMPARAISON.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.niveauValidation").value(DEFAULT_NIVEAU_VALIDATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingValidationComposant() throws Exception {
        // Get the validationComposant
        restValidationComposantMockMvc.perform(get("/api/validation-composants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValidationComposant() throws Exception {
        // Initialize the database
        validationComposantRepository.saveAndFlush(validationComposant);

        int databaseSizeBeforeUpdate = validationComposantRepository.findAll().size();

        // Update the validationComposant
        ValidationComposant updatedValidationComposant = validationComposantRepository.findById(validationComposant.getId()).get();
        // Disconnect from session so that the updates on updatedValidationComposant are not directly saved in db
        em.detach(updatedValidationComposant);
        updatedValidationComposant
            .code(UPDATED_CODE)
            .titre(UPDATED_TITRE)
            .regexValidation(UPDATED_REGEX_VALIDATION)
            .signeComparaison(UPDATED_SIGNE_COMPARAISON)
            .valeurComparaison(UPDATED_VALEUR_COMPARAISON)
            .message(UPDATED_MESSAGE)
            .niveauValidation(UPDATED_NIVEAU_VALIDATION);
        ValidationComposantDTO validationComposantDTO = validationComposantMapper.toDto(updatedValidationComposant);

        restValidationComposantMockMvc.perform(put("/api/validation-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validationComposantDTO)))
            .andExpect(status().isOk());

        // Validate the ValidationComposant in the database
        List<ValidationComposant> validationComposantList = validationComposantRepository.findAll();
        assertThat(validationComposantList).hasSize(databaseSizeBeforeUpdate);
        ValidationComposant testValidationComposant = validationComposantList.get(validationComposantList.size() - 1);
        assertThat(testValidationComposant.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testValidationComposant.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testValidationComposant.getRegexValidation()).isEqualTo(UPDATED_REGEX_VALIDATION);
        assertThat(testValidationComposant.getSigneComparaison()).isEqualTo(UPDATED_SIGNE_COMPARAISON);
        assertThat(testValidationComposant.getValeurComparaison()).isEqualTo(UPDATED_VALEUR_COMPARAISON);
        assertThat(testValidationComposant.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testValidationComposant.getNiveauValidation()).isEqualTo(UPDATED_NIVEAU_VALIDATION);
    }

    @Test
    @Transactional
    public void updateNonExistingValidationComposant() throws Exception {
        int databaseSizeBeforeUpdate = validationComposantRepository.findAll().size();

        // Create the ValidationComposant
        ValidationComposantDTO validationComposantDTO = validationComposantMapper.toDto(validationComposant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidationComposantMockMvc.perform(put("/api/validation-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validationComposantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ValidationComposant in the database
        List<ValidationComposant> validationComposantList = validationComposantRepository.findAll();
        assertThat(validationComposantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteValidationComposant() throws Exception {
        // Initialize the database
        validationComposantRepository.saveAndFlush(validationComposant);

        int databaseSizeBeforeDelete = validationComposantRepository.findAll().size();

        // Get the validationComposant
        restValidationComposantMockMvc.perform(delete("/api/validation-composants/{id}", validationComposant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ValidationComposant> validationComposantList = validationComposantRepository.findAll();
        assertThat(validationComposantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidationComposant.class);
        ValidationComposant validationComposant1 = new ValidationComposant();
        validationComposant1.setId(1L);
        ValidationComposant validationComposant2 = new ValidationComposant();
        validationComposant2.setId(validationComposant1.getId());
        assertThat(validationComposant1).isEqualTo(validationComposant2);
        validationComposant2.setId(2L);
        assertThat(validationComposant1).isNotEqualTo(validationComposant2);
        validationComposant1.setId(null);
        assertThat(validationComposant1).isNotEqualTo(validationComposant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidationComposantDTO.class);
        ValidationComposantDTO validationComposantDTO1 = new ValidationComposantDTO();
        validationComposantDTO1.setId(1L);
        ValidationComposantDTO validationComposantDTO2 = new ValidationComposantDTO();
        assertThat(validationComposantDTO1).isNotEqualTo(validationComposantDTO2);
        validationComposantDTO2.setId(validationComposantDTO1.getId());
        assertThat(validationComposantDTO1).isEqualTo(validationComposantDTO2);
        validationComposantDTO2.setId(2L);
        assertThat(validationComposantDTO1).isNotEqualTo(validationComposantDTO2);
        validationComposantDTO1.setId(null);
        assertThat(validationComposantDTO1).isNotEqualTo(validationComposantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(validationComposantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(validationComposantMapper.fromId(null)).isNull();
    }
}
