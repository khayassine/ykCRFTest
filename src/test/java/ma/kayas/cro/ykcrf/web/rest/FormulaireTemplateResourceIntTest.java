package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.FormulaireTemplate;
import ma.kayas.cro.ykcrf.repository.FormulaireTemplateRepository;
import ma.kayas.cro.ykcrf.service.FormulaireTemplateService;
import ma.kayas.cro.ykcrf.service.dto.FormulaireTemplateDTO;
import ma.kayas.cro.ykcrf.service.mapper.FormulaireTemplateMapper;
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
 * Test class for the FormulaireTemplateResource REST controller.
 *
 * @see FormulaireTemplateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class FormulaireTemplateResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private FormulaireTemplateRepository formulaireTemplateRepository;

    @Autowired
    private FormulaireTemplateMapper formulaireTemplateMapper;

    @Autowired
    private FormulaireTemplateService formulaireTemplateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFormulaireTemplateMockMvc;

    private FormulaireTemplate formulaireTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormulaireTemplateResource formulaireTemplateResource = new FormulaireTemplateResource(formulaireTemplateService);
        this.restFormulaireTemplateMockMvc = MockMvcBuilders.standaloneSetup(formulaireTemplateResource)
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
    public static FormulaireTemplate createEntity(EntityManager em) {
        FormulaireTemplate formulaireTemplate = new FormulaireTemplate()
            .code(DEFAULT_CODE)
            .titre(DEFAULT_TITRE)
            .version(DEFAULT_VERSION);
        return formulaireTemplate;
    }

    @Before
    public void initTest() {
        formulaireTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormulaireTemplate() throws Exception {
        int databaseSizeBeforeCreate = formulaireTemplateRepository.findAll().size();

        // Create the FormulaireTemplate
        FormulaireTemplateDTO formulaireTemplateDTO = formulaireTemplateMapper.toDto(formulaireTemplate);
        restFormulaireTemplateMockMvc.perform(post("/api/formulaire-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the FormulaireTemplate in the database
        List<FormulaireTemplate> formulaireTemplateList = formulaireTemplateRepository.findAll();
        assertThat(formulaireTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        FormulaireTemplate testFormulaireTemplate = formulaireTemplateList.get(formulaireTemplateList.size() - 1);
        assertThat(testFormulaireTemplate.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFormulaireTemplate.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testFormulaireTemplate.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createFormulaireTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formulaireTemplateRepository.findAll().size();

        // Create the FormulaireTemplate with an existing ID
        formulaireTemplate.setId(1L);
        FormulaireTemplateDTO formulaireTemplateDTO = formulaireTemplateMapper.toDto(formulaireTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormulaireTemplateMockMvc.perform(post("/api/formulaire-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaireTemplate in the database
        List<FormulaireTemplate> formulaireTemplateList = formulaireTemplateRepository.findAll();
        assertThat(formulaireTemplateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = formulaireTemplateRepository.findAll().size();
        // set the field null
        formulaireTemplate.setCode(null);

        // Create the FormulaireTemplate, which fails.
        FormulaireTemplateDTO formulaireTemplateDTO = formulaireTemplateMapper.toDto(formulaireTemplate);

        restFormulaireTemplateMockMvc.perform(post("/api/formulaire-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<FormulaireTemplate> formulaireTemplateList = formulaireTemplateRepository.findAll();
        assertThat(formulaireTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = formulaireTemplateRepository.findAll().size();
        // set the field null
        formulaireTemplate.setTitre(null);

        // Create the FormulaireTemplate, which fails.
        FormulaireTemplateDTO formulaireTemplateDTO = formulaireTemplateMapper.toDto(formulaireTemplate);

        restFormulaireTemplateMockMvc.perform(post("/api/formulaire-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<FormulaireTemplate> formulaireTemplateList = formulaireTemplateRepository.findAll();
        assertThat(formulaireTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFormulaireTemplates() throws Exception {
        // Initialize the database
        formulaireTemplateRepository.saveAndFlush(formulaireTemplate);

        // Get all the formulaireTemplateList
        restFormulaireTemplateMockMvc.perform(get("/api/formulaire-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulaireTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())));
    }
    
    @Test
    @Transactional
    public void getFormulaireTemplate() throws Exception {
        // Initialize the database
        formulaireTemplateRepository.saveAndFlush(formulaireTemplate);

        // Get the formulaireTemplate
        restFormulaireTemplateMockMvc.perform(get("/api/formulaire-templates/{id}", formulaireTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formulaireTemplate.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFormulaireTemplate() throws Exception {
        // Get the formulaireTemplate
        restFormulaireTemplateMockMvc.perform(get("/api/formulaire-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormulaireTemplate() throws Exception {
        // Initialize the database
        formulaireTemplateRepository.saveAndFlush(formulaireTemplate);

        int databaseSizeBeforeUpdate = formulaireTemplateRepository.findAll().size();

        // Update the formulaireTemplate
        FormulaireTemplate updatedFormulaireTemplate = formulaireTemplateRepository.findById(formulaireTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedFormulaireTemplate are not directly saved in db
        em.detach(updatedFormulaireTemplate);
        updatedFormulaireTemplate
            .code(UPDATED_CODE)
            .titre(UPDATED_TITRE)
            .version(UPDATED_VERSION);
        FormulaireTemplateDTO formulaireTemplateDTO = formulaireTemplateMapper.toDto(updatedFormulaireTemplate);

        restFormulaireTemplateMockMvc.perform(put("/api/formulaire-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the FormulaireTemplate in the database
        List<FormulaireTemplate> formulaireTemplateList = formulaireTemplateRepository.findAll();
        assertThat(formulaireTemplateList).hasSize(databaseSizeBeforeUpdate);
        FormulaireTemplate testFormulaireTemplate = formulaireTemplateList.get(formulaireTemplateList.size() - 1);
        assertThat(testFormulaireTemplate.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFormulaireTemplate.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testFormulaireTemplate.getVersion()).isEqualTo(UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void updateNonExistingFormulaireTemplate() throws Exception {
        int databaseSizeBeforeUpdate = formulaireTemplateRepository.findAll().size();

        // Create the FormulaireTemplate
        FormulaireTemplateDTO formulaireTemplateDTO = formulaireTemplateMapper.toDto(formulaireTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormulaireTemplateMockMvc.perform(put("/api/formulaire-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaireTemplate in the database
        List<FormulaireTemplate> formulaireTemplateList = formulaireTemplateRepository.findAll();
        assertThat(formulaireTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormulaireTemplate() throws Exception {
        // Initialize the database
        formulaireTemplateRepository.saveAndFlush(formulaireTemplate);

        int databaseSizeBeforeDelete = formulaireTemplateRepository.findAll().size();

        // Get the formulaireTemplate
        restFormulaireTemplateMockMvc.perform(delete("/api/formulaire-templates/{id}", formulaireTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FormulaireTemplate> formulaireTemplateList = formulaireTemplateRepository.findAll();
        assertThat(formulaireTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormulaireTemplate.class);
        FormulaireTemplate formulaireTemplate1 = new FormulaireTemplate();
        formulaireTemplate1.setId(1L);
        FormulaireTemplate formulaireTemplate2 = new FormulaireTemplate();
        formulaireTemplate2.setId(formulaireTemplate1.getId());
        assertThat(formulaireTemplate1).isEqualTo(formulaireTemplate2);
        formulaireTemplate2.setId(2L);
        assertThat(formulaireTemplate1).isNotEqualTo(formulaireTemplate2);
        formulaireTemplate1.setId(null);
        assertThat(formulaireTemplate1).isNotEqualTo(formulaireTemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormulaireTemplateDTO.class);
        FormulaireTemplateDTO formulaireTemplateDTO1 = new FormulaireTemplateDTO();
        formulaireTemplateDTO1.setId(1L);
        FormulaireTemplateDTO formulaireTemplateDTO2 = new FormulaireTemplateDTO();
        assertThat(formulaireTemplateDTO1).isNotEqualTo(formulaireTemplateDTO2);
        formulaireTemplateDTO2.setId(formulaireTemplateDTO1.getId());
        assertThat(formulaireTemplateDTO1).isEqualTo(formulaireTemplateDTO2);
        formulaireTemplateDTO2.setId(2L);
        assertThat(formulaireTemplateDTO1).isNotEqualTo(formulaireTemplateDTO2);
        formulaireTemplateDTO1.setId(null);
        assertThat(formulaireTemplateDTO1).isNotEqualTo(formulaireTemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(formulaireTemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(formulaireTemplateMapper.fromId(null)).isNull();
    }
}
