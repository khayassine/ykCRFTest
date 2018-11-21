package ma.kayas.cro.ykcrf.web.rest;

import ma.kayas.cro.ykcrf.YkCrfApp;

import ma.kayas.cro.ykcrf.domain.CommentaireRequette;
import ma.kayas.cro.ykcrf.repository.CommentaireRequetteRepository;
import ma.kayas.cro.ykcrf.service.CommentaireRequetteService;
import ma.kayas.cro.ykcrf.service.dto.CommentaireRequetteDTO;
import ma.kayas.cro.ykcrf.service.mapper.CommentaireRequetteMapper;
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
 * Test class for the CommentaireRequetteResource REST controller.
 *
 * @see CommentaireRequetteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkCrfApp.class)
public class CommentaireRequetteResourceIntTest {

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    @Autowired
    private CommentaireRequetteRepository commentaireRequetteRepository;

    @Autowired
    private CommentaireRequetteMapper commentaireRequetteMapper;

    @Autowired
    private CommentaireRequetteService commentaireRequetteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCommentaireRequetteMockMvc;

    private CommentaireRequette commentaireRequette;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommentaireRequetteResource commentaireRequetteResource = new CommentaireRequetteResource(commentaireRequetteService);
        this.restCommentaireRequetteMockMvc = MockMvcBuilders.standaloneSetup(commentaireRequetteResource)
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
    public static CommentaireRequette createEntity(EntityManager em) {
        CommentaireRequette commentaireRequette = new CommentaireRequette()
            .commentaire(DEFAULT_COMMENTAIRE);
        return commentaireRequette;
    }

    @Before
    public void initTest() {
        commentaireRequette = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommentaireRequette() throws Exception {
        int databaseSizeBeforeCreate = commentaireRequetteRepository.findAll().size();

        // Create the CommentaireRequette
        CommentaireRequetteDTO commentaireRequetteDTO = commentaireRequetteMapper.toDto(commentaireRequette);
        restCommentaireRequetteMockMvc.perform(post("/api/commentaire-requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commentaireRequetteDTO)))
            .andExpect(status().isCreated());

        // Validate the CommentaireRequette in the database
        List<CommentaireRequette> commentaireRequetteList = commentaireRequetteRepository.findAll();
        assertThat(commentaireRequetteList).hasSize(databaseSizeBeforeCreate + 1);
        CommentaireRequette testCommentaireRequette = commentaireRequetteList.get(commentaireRequetteList.size() - 1);
        assertThat(testCommentaireRequette.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void createCommentaireRequetteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commentaireRequetteRepository.findAll().size();

        // Create the CommentaireRequette with an existing ID
        commentaireRequette.setId(1L);
        CommentaireRequetteDTO commentaireRequetteDTO = commentaireRequetteMapper.toDto(commentaireRequette);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommentaireRequetteMockMvc.perform(post("/api/commentaire-requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commentaireRequetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentaireRequette in the database
        List<CommentaireRequette> commentaireRequetteList = commentaireRequetteRepository.findAll();
        assertThat(commentaireRequetteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCommentaireRequettes() throws Exception {
        // Initialize the database
        commentaireRequetteRepository.saveAndFlush(commentaireRequette);

        // Get all the commentaireRequetteList
        restCommentaireRequetteMockMvc.perform(get("/api/commentaire-requettes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commentaireRequette.getId().intValue())))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE.toString())));
    }
    
    @Test
    @Transactional
    public void getCommentaireRequette() throws Exception {
        // Initialize the database
        commentaireRequetteRepository.saveAndFlush(commentaireRequette);

        // Get the commentaireRequette
        restCommentaireRequetteMockMvc.perform(get("/api/commentaire-requettes/{id}", commentaireRequette.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commentaireRequette.getId().intValue()))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCommentaireRequette() throws Exception {
        // Get the commentaireRequette
        restCommentaireRequetteMockMvc.perform(get("/api/commentaire-requettes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommentaireRequette() throws Exception {
        // Initialize the database
        commentaireRequetteRepository.saveAndFlush(commentaireRequette);

        int databaseSizeBeforeUpdate = commentaireRequetteRepository.findAll().size();

        // Update the commentaireRequette
        CommentaireRequette updatedCommentaireRequette = commentaireRequetteRepository.findById(commentaireRequette.getId()).get();
        // Disconnect from session so that the updates on updatedCommentaireRequette are not directly saved in db
        em.detach(updatedCommentaireRequette);
        updatedCommentaireRequette
            .commentaire(UPDATED_COMMENTAIRE);
        CommentaireRequetteDTO commentaireRequetteDTO = commentaireRequetteMapper.toDto(updatedCommentaireRequette);

        restCommentaireRequetteMockMvc.perform(put("/api/commentaire-requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commentaireRequetteDTO)))
            .andExpect(status().isOk());

        // Validate the CommentaireRequette in the database
        List<CommentaireRequette> commentaireRequetteList = commentaireRequetteRepository.findAll();
        assertThat(commentaireRequetteList).hasSize(databaseSizeBeforeUpdate);
        CommentaireRequette testCommentaireRequette = commentaireRequetteList.get(commentaireRequetteList.size() - 1);
        assertThat(testCommentaireRequette.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingCommentaireRequette() throws Exception {
        int databaseSizeBeforeUpdate = commentaireRequetteRepository.findAll().size();

        // Create the CommentaireRequette
        CommentaireRequetteDTO commentaireRequetteDTO = commentaireRequetteMapper.toDto(commentaireRequette);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommentaireRequetteMockMvc.perform(put("/api/commentaire-requettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commentaireRequetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentaireRequette in the database
        List<CommentaireRequette> commentaireRequetteList = commentaireRequetteRepository.findAll();
        assertThat(commentaireRequetteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommentaireRequette() throws Exception {
        // Initialize the database
        commentaireRequetteRepository.saveAndFlush(commentaireRequette);

        int databaseSizeBeforeDelete = commentaireRequetteRepository.findAll().size();

        // Get the commentaireRequette
        restCommentaireRequetteMockMvc.perform(delete("/api/commentaire-requettes/{id}", commentaireRequette.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CommentaireRequette> commentaireRequetteList = commentaireRequetteRepository.findAll();
        assertThat(commentaireRequetteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommentaireRequette.class);
        CommentaireRequette commentaireRequette1 = new CommentaireRequette();
        commentaireRequette1.setId(1L);
        CommentaireRequette commentaireRequette2 = new CommentaireRequette();
        commentaireRequette2.setId(commentaireRequette1.getId());
        assertThat(commentaireRequette1).isEqualTo(commentaireRequette2);
        commentaireRequette2.setId(2L);
        assertThat(commentaireRequette1).isNotEqualTo(commentaireRequette2);
        commentaireRequette1.setId(null);
        assertThat(commentaireRequette1).isNotEqualTo(commentaireRequette2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommentaireRequetteDTO.class);
        CommentaireRequetteDTO commentaireRequetteDTO1 = new CommentaireRequetteDTO();
        commentaireRequetteDTO1.setId(1L);
        CommentaireRequetteDTO commentaireRequetteDTO2 = new CommentaireRequetteDTO();
        assertThat(commentaireRequetteDTO1).isNotEqualTo(commentaireRequetteDTO2);
        commentaireRequetteDTO2.setId(commentaireRequetteDTO1.getId());
        assertThat(commentaireRequetteDTO1).isEqualTo(commentaireRequetteDTO2);
        commentaireRequetteDTO2.setId(2L);
        assertThat(commentaireRequetteDTO1).isNotEqualTo(commentaireRequetteDTO2);
        commentaireRequetteDTO1.setId(null);
        assertThat(commentaireRequetteDTO1).isNotEqualTo(commentaireRequetteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(commentaireRequetteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(commentaireRequetteMapper.fromId(null)).isNull();
    }
}
