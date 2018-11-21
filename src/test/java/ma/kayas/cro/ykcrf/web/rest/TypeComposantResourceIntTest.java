package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.TypeComposant;
import ma.kayas.cro.ykcrf.repository.TypeComposantRepository;
import ma.kayas.cro.ykcrf.service.TypeComposantService;
import ma.kayas.cro.ykcrf.service.dto.TypeComposantDTO;
import ma.kayas.cro.ykcrf.service.mapper.TypeComposantMapper;
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
 * Test class for the TypeComposantResource REST controller.
 *
 * @see TypeComposantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class TypeComposantResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_CSS_STYLE = "AAAAAAAAAA";
    private static final String UPDATED_CSS_STYLE = "BBBBBBBBBB";

    @Autowired
    private TypeComposantRepository typeComposantRepository;

    @Autowired
    private TypeComposantMapper typeComposantMapper;

    @Autowired
    private TypeComposantService typeComposantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeComposantMockMvc;

    private TypeComposant typeComposant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeComposantResource typeComposantResource = new TypeComposantResource(typeComposantService);
        this.restTypeComposantMockMvc = MockMvcBuilders.standaloneSetup(typeComposantResource)
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
    public static TypeComposant createEntity(EntityManager em) {
        TypeComposant typeComposant = new TypeComposant()
            .code(DEFAULT_CODE)
            .titre(DEFAULT_TITRE)
            .cssStyle(DEFAULT_CSS_STYLE);
        return typeComposant;
    }

    @Before
    public void initTest() {
        typeComposant = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeComposant() throws Exception {
        int databaseSizeBeforeCreate = typeComposantRepository.findAll().size();

        // Create the TypeComposant
        TypeComposantDTO typeComposantDTO = typeComposantMapper.toDto(typeComposant);
        restTypeComposantMockMvc.perform(post("/api/type-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeComposantDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeComposant in the database
        List<TypeComposant> typeComposantList = typeComposantRepository.findAll();
        assertThat(typeComposantList).hasSize(databaseSizeBeforeCreate + 1);
        TypeComposant testTypeComposant = typeComposantList.get(typeComposantList.size() - 1);
        assertThat(testTypeComposant.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTypeComposant.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testTypeComposant.getCssStyle()).isEqualTo(DEFAULT_CSS_STYLE);
    }

    @Test
    @Transactional
    public void createTypeComposantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeComposantRepository.findAll().size();

        // Create the TypeComposant with an existing ID
        typeComposant.setId(1L);
        TypeComposantDTO typeComposantDTO = typeComposantMapper.toDto(typeComposant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeComposantMockMvc.perform(post("/api/type-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeComposantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeComposant in the database
        List<TypeComposant> typeComposantList = typeComposantRepository.findAll();
        assertThat(typeComposantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeComposantRepository.findAll().size();
        // set the field null
        typeComposant.setCode(null);

        // Create the TypeComposant, which fails.
        TypeComposantDTO typeComposantDTO = typeComposantMapper.toDto(typeComposant);

        restTypeComposantMockMvc.perform(post("/api/type-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeComposantDTO)))
            .andExpect(status().isBadRequest());

        List<TypeComposant> typeComposantList = typeComposantRepository.findAll();
        assertThat(typeComposantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeComposantRepository.findAll().size();
        // set the field null
        typeComposant.setTitre(null);

        // Create the TypeComposant, which fails.
        TypeComposantDTO typeComposantDTO = typeComposantMapper.toDto(typeComposant);

        restTypeComposantMockMvc.perform(post("/api/type-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeComposantDTO)))
            .andExpect(status().isBadRequest());

        List<TypeComposant> typeComposantList = typeComposantRepository.findAll();
        assertThat(typeComposantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeComposants() throws Exception {
        // Initialize the database
        typeComposantRepository.saveAndFlush(typeComposant);

        // Get all the typeComposantList
        restTypeComposantMockMvc.perform(get("/api/type-composants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeComposant.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE.toString())))
            .andExpect(jsonPath("$.[*].cssStyle").value(hasItem(DEFAULT_CSS_STYLE.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeComposant() throws Exception {
        // Initialize the database
        typeComposantRepository.saveAndFlush(typeComposant);

        // Get the typeComposant
        restTypeComposantMockMvc.perform(get("/api/type-composants/{id}", typeComposant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeComposant.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE.toString()))
            .andExpect(jsonPath("$.cssStyle").value(DEFAULT_CSS_STYLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeComposant() throws Exception {
        // Get the typeComposant
        restTypeComposantMockMvc.perform(get("/api/type-composants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeComposant() throws Exception {
        // Initialize the database
        typeComposantRepository.saveAndFlush(typeComposant);

        int databaseSizeBeforeUpdate = typeComposantRepository.findAll().size();

        // Update the typeComposant
        TypeComposant updatedTypeComposant = typeComposantRepository.findById(typeComposant.getId()).get();
        // Disconnect from session so that the updates on updatedTypeComposant are not directly saved in db
        em.detach(updatedTypeComposant);
        updatedTypeComposant
            .code(UPDATED_CODE)
            .titre(UPDATED_TITRE)
            .cssStyle(UPDATED_CSS_STYLE);
        TypeComposantDTO typeComposantDTO = typeComposantMapper.toDto(updatedTypeComposant);

        restTypeComposantMockMvc.perform(put("/api/type-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeComposantDTO)))
            .andExpect(status().isOk());

        // Validate the TypeComposant in the database
        List<TypeComposant> typeComposantList = typeComposantRepository.findAll();
        assertThat(typeComposantList).hasSize(databaseSizeBeforeUpdate);
        TypeComposant testTypeComposant = typeComposantList.get(typeComposantList.size() - 1);
        assertThat(testTypeComposant.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTypeComposant.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testTypeComposant.getCssStyle()).isEqualTo(UPDATED_CSS_STYLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeComposant() throws Exception {
        int databaseSizeBeforeUpdate = typeComposantRepository.findAll().size();

        // Create the TypeComposant
        TypeComposantDTO typeComposantDTO = typeComposantMapper.toDto(typeComposant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeComposantMockMvc.perform(put("/api/type-composants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeComposantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeComposant in the database
        List<TypeComposant> typeComposantList = typeComposantRepository.findAll();
        assertThat(typeComposantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeComposant() throws Exception {
        // Initialize the database
        typeComposantRepository.saveAndFlush(typeComposant);

        int databaseSizeBeforeDelete = typeComposantRepository.findAll().size();

        // Get the typeComposant
        restTypeComposantMockMvc.perform(delete("/api/type-composants/{id}", typeComposant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeComposant> typeComposantList = typeComposantRepository.findAll();
        assertThat(typeComposantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeComposant.class);
        TypeComposant typeComposant1 = new TypeComposant();
        typeComposant1.setId(1L);
        TypeComposant typeComposant2 = new TypeComposant();
        typeComposant2.setId(typeComposant1.getId());
        assertThat(typeComposant1).isEqualTo(typeComposant2);
        typeComposant2.setId(2L);
        assertThat(typeComposant1).isNotEqualTo(typeComposant2);
        typeComposant1.setId(null);
        assertThat(typeComposant1).isNotEqualTo(typeComposant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeComposantDTO.class);
        TypeComposantDTO typeComposantDTO1 = new TypeComposantDTO();
        typeComposantDTO1.setId(1L);
        TypeComposantDTO typeComposantDTO2 = new TypeComposantDTO();
        assertThat(typeComposantDTO1).isNotEqualTo(typeComposantDTO2);
        typeComposantDTO2.setId(typeComposantDTO1.getId());
        assertThat(typeComposantDTO1).isEqualTo(typeComposantDTO2);
        typeComposantDTO2.setId(2L);
        assertThat(typeComposantDTO1).isNotEqualTo(typeComposantDTO2);
        typeComposantDTO1.setId(null);
        assertThat(typeComposantDTO1).isNotEqualTo(typeComposantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeComposantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeComposantMapper.fromId(null)).isNull();
    }
}
