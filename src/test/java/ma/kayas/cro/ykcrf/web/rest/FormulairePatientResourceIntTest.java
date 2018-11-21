package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.FormulairePatient;
import ma.kayas.cro.ykcrf.repository.FormulairePatientRepository;
import ma.kayas.cro.ykcrf.service.FormulairePatientService;
import ma.kayas.cro.ykcrf.service.dto.FormulairePatientDTO;
import ma.kayas.cro.ykcrf.service.mapper.FormulairePatientMapper;
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

import ma.kayas.cro.ykcrf.domain.enumeration.EtatFormulaire;
/**
 * Test class for the FormulairePatientResource REST controller.
 *
 * @see FormulairePatientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class FormulairePatientResourceIntTest {

    private static final EtatFormulaire DEFAULT_ETAT = EtatFormulaire.ND;
    private static final EtatFormulaire UPDATED_ETAT = EtatFormulaire.COMPLETED;

    @Autowired
    private FormulairePatientRepository formulairePatientRepository;

    @Autowired
    private FormulairePatientMapper formulairePatientMapper;

    @Autowired
    private FormulairePatientService formulairePatientService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFormulairePatientMockMvc;

    private FormulairePatient formulairePatient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormulairePatientResource formulairePatientResource = new FormulairePatientResource(formulairePatientService);
        this.restFormulairePatientMockMvc = MockMvcBuilders.standaloneSetup(formulairePatientResource)
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
    public static FormulairePatient createEntity(EntityManager em) {
        FormulairePatient formulairePatient = new FormulairePatient()
            .etat(DEFAULT_ETAT);
        return formulairePatient;
    }

    @Before
    public void initTest() {
        formulairePatient = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormulairePatient() throws Exception {
        int databaseSizeBeforeCreate = formulairePatientRepository.findAll().size();

        // Create the FormulairePatient
        FormulairePatientDTO formulairePatientDTO = formulairePatientMapper.toDto(formulairePatient);
        restFormulairePatientMockMvc.perform(post("/api/formulaire-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulairePatientDTO)))
            .andExpect(status().isCreated());

        // Validate the FormulairePatient in the database
        List<FormulairePatient> formulairePatientList = formulairePatientRepository.findAll();
        assertThat(formulairePatientList).hasSize(databaseSizeBeforeCreate + 1);
        FormulairePatient testFormulairePatient = formulairePatientList.get(formulairePatientList.size() - 1);
        assertThat(testFormulairePatient.getEtat()).isEqualTo(DEFAULT_ETAT);
    }

    @Test
    @Transactional
    public void createFormulairePatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formulairePatientRepository.findAll().size();

        // Create the FormulairePatient with an existing ID
        formulairePatient.setId(1L);
        FormulairePatientDTO formulairePatientDTO = formulairePatientMapper.toDto(formulairePatient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormulairePatientMockMvc.perform(post("/api/formulaire-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulairePatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FormulairePatient in the database
        List<FormulairePatient> formulairePatientList = formulairePatientRepository.findAll();
        assertThat(formulairePatientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFormulairePatients() throws Exception {
        // Initialize the database
        formulairePatientRepository.saveAndFlush(formulairePatient);

        // Get all the formulairePatientList
        restFormulairePatientMockMvc.perform(get("/api/formulaire-patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulairePatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())));
    }
    
    @Test
    @Transactional
    public void getFormulairePatient() throws Exception {
        // Initialize the database
        formulairePatientRepository.saveAndFlush(formulairePatient);

        // Get the formulairePatient
        restFormulairePatientMockMvc.perform(get("/api/formulaire-patients/{id}", formulairePatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formulairePatient.getId().intValue()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFormulairePatient() throws Exception {
        // Get the formulairePatient
        restFormulairePatientMockMvc.perform(get("/api/formulaire-patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormulairePatient() throws Exception {
        // Initialize the database
        formulairePatientRepository.saveAndFlush(formulairePatient);

        int databaseSizeBeforeUpdate = formulairePatientRepository.findAll().size();

        // Update the formulairePatient
        FormulairePatient updatedFormulairePatient = formulairePatientRepository.findById(formulairePatient.getId()).get();
        // Disconnect from session so that the updates on updatedFormulairePatient are not directly saved in db
        em.detach(updatedFormulairePatient);
        updatedFormulairePatient
            .etat(UPDATED_ETAT);
        FormulairePatientDTO formulairePatientDTO = formulairePatientMapper.toDto(updatedFormulairePatient);

        restFormulairePatientMockMvc.perform(put("/api/formulaire-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulairePatientDTO)))
            .andExpect(status().isOk());

        // Validate the FormulairePatient in the database
        List<FormulairePatient> formulairePatientList = formulairePatientRepository.findAll();
        assertThat(formulairePatientList).hasSize(databaseSizeBeforeUpdate);
        FormulairePatient testFormulairePatient = formulairePatientList.get(formulairePatientList.size() - 1);
        assertThat(testFormulairePatient.getEtat()).isEqualTo(UPDATED_ETAT);
    }

    @Test
    @Transactional
    public void updateNonExistingFormulairePatient() throws Exception {
        int databaseSizeBeforeUpdate = formulairePatientRepository.findAll().size();

        // Create the FormulairePatient
        FormulairePatientDTO formulairePatientDTO = formulairePatientMapper.toDto(formulairePatient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormulairePatientMockMvc.perform(put("/api/formulaire-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulairePatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FormulairePatient in the database
        List<FormulairePatient> formulairePatientList = formulairePatientRepository.findAll();
        assertThat(formulairePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormulairePatient() throws Exception {
        // Initialize the database
        formulairePatientRepository.saveAndFlush(formulairePatient);

        int databaseSizeBeforeDelete = formulairePatientRepository.findAll().size();

        // Get the formulairePatient
        restFormulairePatientMockMvc.perform(delete("/api/formulaire-patients/{id}", formulairePatient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FormulairePatient> formulairePatientList = formulairePatientRepository.findAll();
        assertThat(formulairePatientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormulairePatient.class);
        FormulairePatient formulairePatient1 = new FormulairePatient();
        formulairePatient1.setId(1L);
        FormulairePatient formulairePatient2 = new FormulairePatient();
        formulairePatient2.setId(formulairePatient1.getId());
        assertThat(formulairePatient1).isEqualTo(formulairePatient2);
        formulairePatient2.setId(2L);
        assertThat(formulairePatient1).isNotEqualTo(formulairePatient2);
        formulairePatient1.setId(null);
        assertThat(formulairePatient1).isNotEqualTo(formulairePatient2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormulairePatientDTO.class);
        FormulairePatientDTO formulairePatientDTO1 = new FormulairePatientDTO();
        formulairePatientDTO1.setId(1L);
        FormulairePatientDTO formulairePatientDTO2 = new FormulairePatientDTO();
        assertThat(formulairePatientDTO1).isNotEqualTo(formulairePatientDTO2);
        formulairePatientDTO2.setId(formulairePatientDTO1.getId());
        assertThat(formulairePatientDTO1).isEqualTo(formulairePatientDTO2);
        formulairePatientDTO2.setId(2L);
        assertThat(formulairePatientDTO1).isNotEqualTo(formulairePatientDTO2);
        formulairePatientDTO1.setId(null);
        assertThat(formulairePatientDTO1).isNotEqualTo(formulairePatientDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(formulairePatientMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(formulairePatientMapper.fromId(null)).isNull();
    }
}
