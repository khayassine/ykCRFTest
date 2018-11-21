package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.ComposantTemplate;
import ma.kayas.cro.ykcrf.repository.ComposantTemplateRepository;
import ma.kayas.cro.ykcrf.service.ComposantTemplateService;
import ma.kayas.cro.ykcrf.service.dto.ComposantTemplateDTO;
import ma.kayas.cro.ykcrf.service.mapper.ComposantTemplateMapper;
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
 * Test class for the ComposantTemplateResource REST controller.
 *
 * @see ComposantTemplateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class ComposantTemplateResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDRE = 1;
    private static final Integer UPDATED_ORDRE = 2;

    private static final String DEFAULT_CONDITION_AFFICHAGE = "AAAAAAAAAA";
    private static final String UPDATED_CONDITION_AFFICHAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTE_DROITE = "AAAAAAAAAA";
    private static final String UPDATED_TEXTE_DROITE = "BBBBBBBBBB";

    private static final String DEFAULT_CSS_STYLE = "AAAAAAAAAA";
    private static final String UPDATED_CSS_STYLE = "BBBBBBBBBB";

    @Autowired
    private ComposantTemplateRepository composantTemplateRepository;

    @Autowired
    private ComposantTemplateMapper composantTemplateMapper;

    @Autowired
    private ComposantTemplateService composantTemplateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restComposantTemplateMockMvc;

    private ComposantTemplate composantTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComposantTemplateResource composantTemplateResource = new ComposantTemplateResource(composantTemplateService);
        this.restComposantTemplateMockMvc = MockMvcBuilders.standaloneSetup(composantTemplateResource)
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
    public static ComposantTemplate createEntity(EntityManager em) {
        ComposantTemplate composantTemplate = new ComposantTemplate()
            .code(DEFAULT_CODE)
            .titre(DEFAULT_TITRE)
            .ordre(DEFAULT_ORDRE)
            .conditionAffichage(DEFAULT_CONDITION_AFFICHAGE)
            .texteDroite(DEFAULT_TEXTE_DROITE)
            .cssStyle(DEFAULT_CSS_STYLE);
        return composantTemplate;
    }

    @Before
    public void initTest() {
        composantTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createComposantTemplate() throws Exception {
        int databaseSizeBeforeCreate = composantTemplateRepository.findAll().size();

        // Create the ComposantTemplate
        ComposantTemplateDTO composantTemplateDTO = composantTemplateMapper.toDto(composantTemplate);
        restComposantTemplateMockMvc.perform(post("/api/composant-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the ComposantTemplate in the database
        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        ComposantTemplate testComposantTemplate = composantTemplateList.get(composantTemplateList.size() - 1);
        assertThat(testComposantTemplate.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testComposantTemplate.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testComposantTemplate.getOrdre()).isEqualTo(DEFAULT_ORDRE);
        assertThat(testComposantTemplate.getConditionAffichage()).isEqualTo(DEFAULT_CONDITION_AFFICHAGE);
        assertThat(testComposantTemplate.getTexteDroite()).isEqualTo(DEFAULT_TEXTE_DROITE);
        assertThat(testComposantTemplate.getCssStyle()).isEqualTo(DEFAULT_CSS_STYLE);
    }

    @Test
    @Transactional
    public void createComposantTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = composantTemplateRepository.findAll().size();

        // Create the ComposantTemplate with an existing ID
        composantTemplate.setId(1L);
        ComposantTemplateDTO composantTemplateDTO = composantTemplateMapper.toDto(composantTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComposantTemplateMockMvc.perform(post("/api/composant-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComposantTemplate in the database
        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = composantTemplateRepository.findAll().size();
        // set the field null
        composantTemplate.setCode(null);

        // Create the ComposantTemplate, which fails.
        ComposantTemplateDTO composantTemplateDTO = composantTemplateMapper.toDto(composantTemplate);

        restComposantTemplateMockMvc.perform(post("/api/composant-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = composantTemplateRepository.findAll().size();
        // set the field null
        composantTemplate.setTitre(null);

        // Create the ComposantTemplate, which fails.
        ComposantTemplateDTO composantTemplateDTO = composantTemplateMapper.toDto(composantTemplate);

        restComposantTemplateMockMvc.perform(post("/api/composant-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdreIsRequired() throws Exception {
        int databaseSizeBeforeTest = composantTemplateRepository.findAll().size();
        // set the field null
        composantTemplate.setOrdre(null);

        // Create the ComposantTemplate, which fails.
        ComposantTemplateDTO composantTemplateDTO = composantTemplateMapper.toDto(composantTemplate);

        restComposantTemplateMockMvc.perform(post("/api/composant-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllComposantTemplates() throws Exception {
        // Initialize the database
        composantTemplateRepository.saveAndFlush(composantTemplate);

        // Get all the composantTemplateList
        restComposantTemplateMockMvc.perform(get("/api/composant-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(composantTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].ordre").value(hasItem(DEFAULT_ORDRE)))
            .andExpect(jsonPath("$.[*].conditionAffichage").value(hasItem(DEFAULT_CONDITION_AFFICHAGE.toString())))
            .andExpect(jsonPath("$.[*].texteDroite").value(hasItem(DEFAULT_TEXTE_DROITE.toString())))
            .andExpect(jsonPath("$.[*].cssStyle").value(hasItem(DEFAULT_CSS_STYLE.toString())));
    }
    
    @Test
    @Transactional
    public void getComposantTemplate() throws Exception {
        // Initialize the database
        composantTemplateRepository.saveAndFlush(composantTemplate);

        // Get the composantTemplate
        restComposantTemplateMockMvc.perform(get("/api/composant-templates/{id}", composantTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(composantTemplate.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.ordre").value(DEFAULT_ORDRE))
            .andExpect(jsonPath("$.conditionAffichage").value(DEFAULT_CONDITION_AFFICHAGE.toString()))
            .andExpect(jsonPath("$.texteDroite").value(DEFAULT_TEXTE_DROITE.toString()))
            .andExpect(jsonPath("$.cssStyle").value(DEFAULT_CSS_STYLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComposantTemplate() throws Exception {
        // Get the composantTemplate
        restComposantTemplateMockMvc.perform(get("/api/composant-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComposantTemplate() throws Exception {
        // Initialize the database
        composantTemplateRepository.saveAndFlush(composantTemplate);

        int databaseSizeBeforeUpdate = composantTemplateRepository.findAll().size();

        // Update the composantTemplate
        ComposantTemplate updatedComposantTemplate = composantTemplateRepository.findById(composantTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedComposantTemplate are not directly saved in db
        em.detach(updatedComposantTemplate);
        updatedComposantTemplate
            .code(UPDATED_CODE)
            .titre(UPDATED_TITRE)
            .ordre(UPDATED_ORDRE)
            .conditionAffichage(UPDATED_CONDITION_AFFICHAGE)
            .texteDroite(UPDATED_TEXTE_DROITE)
            .cssStyle(UPDATED_CSS_STYLE);
        ComposantTemplateDTO composantTemplateDTO = composantTemplateMapper.toDto(updatedComposantTemplate);

        restComposantTemplateMockMvc.perform(put("/api/composant-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the ComposantTemplate in the database
        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeUpdate);
        ComposantTemplate testComposantTemplate = composantTemplateList.get(composantTemplateList.size() - 1);
        assertThat(testComposantTemplate.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testComposantTemplate.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testComposantTemplate.getOrdre()).isEqualTo(UPDATED_ORDRE);
        assertThat(testComposantTemplate.getConditionAffichage()).isEqualTo(UPDATED_CONDITION_AFFICHAGE);
        assertThat(testComposantTemplate.getTexteDroite()).isEqualTo(UPDATED_TEXTE_DROITE);
        assertThat(testComposantTemplate.getCssStyle()).isEqualTo(UPDATED_CSS_STYLE);
    }

    @Test
    @Transactional
    public void updateNonExistingComposantTemplate() throws Exception {
        int databaseSizeBeforeUpdate = composantTemplateRepository.findAll().size();

        // Create the ComposantTemplate
        ComposantTemplateDTO composantTemplateDTO = composantTemplateMapper.toDto(composantTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComposantTemplateMockMvc.perform(put("/api/composant-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComposantTemplate in the database
        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComposantTemplate() throws Exception {
        // Initialize the database
        composantTemplateRepository.saveAndFlush(composantTemplate);

        int databaseSizeBeforeDelete = composantTemplateRepository.findAll().size();

        // Get the composantTemplate
        restComposantTemplateMockMvc.perform(delete("/api/composant-templates/{id}", composantTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComposantTemplate> composantTemplateList = composantTemplateRepository.findAll();
        assertThat(composantTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComposantTemplate.class);
        ComposantTemplate composantTemplate1 = new ComposantTemplate();
        composantTemplate1.setId(1L);
        ComposantTemplate composantTemplate2 = new ComposantTemplate();
        composantTemplate2.setId(composantTemplate1.getId());
        assertThat(composantTemplate1).isEqualTo(composantTemplate2);
        composantTemplate2.setId(2L);
        assertThat(composantTemplate1).isNotEqualTo(composantTemplate2);
        composantTemplate1.setId(null);
        assertThat(composantTemplate1).isNotEqualTo(composantTemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComposantTemplateDTO.class);
        ComposantTemplateDTO composantTemplateDTO1 = new ComposantTemplateDTO();
        composantTemplateDTO1.setId(1L);
        ComposantTemplateDTO composantTemplateDTO2 = new ComposantTemplateDTO();
        assertThat(composantTemplateDTO1).isNotEqualTo(composantTemplateDTO2);
        composantTemplateDTO2.setId(composantTemplateDTO1.getId());
        assertThat(composantTemplateDTO1).isEqualTo(composantTemplateDTO2);
        composantTemplateDTO2.setId(2L);
        assertThat(composantTemplateDTO1).isNotEqualTo(composantTemplateDTO2);
        composantTemplateDTO1.setId(null);
        assertThat(composantTemplateDTO1).isNotEqualTo(composantTemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(composantTemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(composantTemplateMapper.fromId(null)).isNull();
    }
}
