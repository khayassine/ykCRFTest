package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.FichePatient;
import ma.kayas.cro.ykcrf.repository.FichePatientRepository;
import ma.kayas.cro.ykcrf.service.FichePatientService;
import ma.kayas.cro.ykcrf.service.dto.FichePatientDTO;
import ma.kayas.cro.ykcrf.service.mapper.FichePatientMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static ma.kayas.cro.ykcrf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FichePatientResource REST controller.
 *
 * @see FichePatientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class FichePatientResourceIntTest {

    private static final String DEFAULT_CODE_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PATIENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_ONE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ONE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FichePatientRepository fichePatientRepository;

    @Autowired
    private FichePatientMapper fichePatientMapper;

    @Autowired
    private FichePatientService fichePatientService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFichePatientMockMvc;

    private FichePatient fichePatient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FichePatientResource fichePatientResource = new FichePatientResource(fichePatientService);
        this.restFichePatientMockMvc = MockMvcBuilders.standaloneSetup(fichePatientResource)
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
    public static FichePatient createEntity(EntityManager em) {
        FichePatient fichePatient = new FichePatient()
            .codePatient(DEFAULT_CODE_PATIENT)
            .oneDate(DEFAULT_ONE_DATE);
        return fichePatient;
    }

    @Before
    public void initTest() {
        fichePatient = createEntity(em);
    }

    @Test
    @Transactional
    public void createFichePatient() throws Exception {
        int databaseSizeBeforeCreate = fichePatientRepository.findAll().size();

        // Create the FichePatient
        FichePatientDTO fichePatientDTO = fichePatientMapper.toDto(fichePatient);
        restFichePatientMockMvc.perform(post("/api/fiche-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichePatientDTO)))
            .andExpect(status().isCreated());

        // Validate the FichePatient in the database
        List<FichePatient> fichePatientList = fichePatientRepository.findAll();
        assertThat(fichePatientList).hasSize(databaseSizeBeforeCreate + 1);
        FichePatient testFichePatient = fichePatientList.get(fichePatientList.size() - 1);
        assertThat(testFichePatient.getCodePatient()).isEqualTo(DEFAULT_CODE_PATIENT);
        assertThat(testFichePatient.getOneDate()).isEqualTo(DEFAULT_ONE_DATE);
    }

    @Test
    @Transactional
    public void createFichePatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fichePatientRepository.findAll().size();

        // Create the FichePatient with an existing ID
        fichePatient.setId(1L);
        FichePatientDTO fichePatientDTO = fichePatientMapper.toDto(fichePatient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFichePatientMockMvc.perform(post("/api/fiche-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichePatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichePatient in the database
        List<FichePatient> fichePatientList = fichePatientRepository.findAll();
        assertThat(fichePatientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFichePatients() throws Exception {
        // Initialize the database
        fichePatientRepository.saveAndFlush(fichePatient);

        // Get all the fichePatientList
        restFichePatientMockMvc.perform(get("/api/fiche-patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fichePatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].codePatient").value(hasItem(DEFAULT_CODE_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].oneDate").value(hasItem(DEFAULT_ONE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getFichePatient() throws Exception {
        // Initialize the database
        fichePatientRepository.saveAndFlush(fichePatient);

        // Get the fichePatient
        restFichePatientMockMvc.perform(get("/api/fiche-patients/{id}", fichePatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fichePatient.getId().intValue()))
            .andExpect(jsonPath("$.codePatient").value(DEFAULT_CODE_PATIENT.toString()))
            .andExpect(jsonPath("$.oneDate").value(DEFAULT_ONE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFichePatient() throws Exception {
        // Get the fichePatient
        restFichePatientMockMvc.perform(get("/api/fiche-patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFichePatient() throws Exception {
        // Initialize the database
        fichePatientRepository.saveAndFlush(fichePatient);

        int databaseSizeBeforeUpdate = fichePatientRepository.findAll().size();

        // Update the fichePatient
        FichePatient updatedFichePatient = fichePatientRepository.findById(fichePatient.getId()).get();
        // Disconnect from session so that the updates on updatedFichePatient are not directly saved in db
        em.detach(updatedFichePatient);
        updatedFichePatient
            .codePatient(UPDATED_CODE_PATIENT)
            .oneDate(UPDATED_ONE_DATE);
        FichePatientDTO fichePatientDTO = fichePatientMapper.toDto(updatedFichePatient);

        restFichePatientMockMvc.perform(put("/api/fiche-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichePatientDTO)))
            .andExpect(status().isOk());

        // Validate the FichePatient in the database
        List<FichePatient> fichePatientList = fichePatientRepository.findAll();
        assertThat(fichePatientList).hasSize(databaseSizeBeforeUpdate);
        FichePatient testFichePatient = fichePatientList.get(fichePatientList.size() - 1);
        assertThat(testFichePatient.getCodePatient()).isEqualTo(UPDATED_CODE_PATIENT);
        assertThat(testFichePatient.getOneDate()).isEqualTo(UPDATED_ONE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFichePatient() throws Exception {
        int databaseSizeBeforeUpdate = fichePatientRepository.findAll().size();

        // Create the FichePatient
        FichePatientDTO fichePatientDTO = fichePatientMapper.toDto(fichePatient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFichePatientMockMvc.perform(put("/api/fiche-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichePatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichePatient in the database
        List<FichePatient> fichePatientList = fichePatientRepository.findAll();
        assertThat(fichePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFichePatient() throws Exception {
        // Initialize the database
        fichePatientRepository.saveAndFlush(fichePatient);

        int databaseSizeBeforeDelete = fichePatientRepository.findAll().size();

        // Get the fichePatient
        restFichePatientMockMvc.perform(delete("/api/fiche-patients/{id}", fichePatient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FichePatient> fichePatientList = fichePatientRepository.findAll();
        assertThat(fichePatientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichePatient.class);
        FichePatient fichePatient1 = new FichePatient();
        fichePatient1.setId(1L);
        FichePatient fichePatient2 = new FichePatient();
        fichePatient2.setId(fichePatient1.getId());
        assertThat(fichePatient1).isEqualTo(fichePatient2);
        fichePatient2.setId(2L);
        assertThat(fichePatient1).isNotEqualTo(fichePatient2);
        fichePatient1.setId(null);
        assertThat(fichePatient1).isNotEqualTo(fichePatient2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichePatientDTO.class);
        FichePatientDTO fichePatientDTO1 = new FichePatientDTO();
        fichePatientDTO1.setId(1L);
        FichePatientDTO fichePatientDTO2 = new FichePatientDTO();
        assertThat(fichePatientDTO1).isNotEqualTo(fichePatientDTO2);
        fichePatientDTO2.setId(fichePatientDTO1.getId());
        assertThat(fichePatientDTO1).isEqualTo(fichePatientDTO2);
        fichePatientDTO2.setId(2L);
        assertThat(fichePatientDTO1).isNotEqualTo(fichePatientDTO2);
        fichePatientDTO1.setId(null);
        assertThat(fichePatientDTO1).isNotEqualTo(fichePatientDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fichePatientMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fichePatientMapper.fromId(null)).isNull();
    }
}
