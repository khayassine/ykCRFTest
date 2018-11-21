package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.CommentaireRequetteService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.CommentaireRequetteDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CommentaireRequette.
 */
@RestController
@RequestMapping("/api")
public class CommentaireRequetteResource {

    private final Logger log = LoggerFactory.getLogger(CommentaireRequetteResource.class);

    private static final String ENTITY_NAME = "commentaireRequette";

    private final CommentaireRequetteService commentaireRequetteService;

    public CommentaireRequetteResource(CommentaireRequetteService commentaireRequetteService) {
        this.commentaireRequetteService = commentaireRequetteService;
    }

    /**
     * POST  /commentaire-requettes : Create a new commentaireRequette.
     *
     * @param commentaireRequetteDTO the commentaireRequetteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new commentaireRequetteDTO, or with status 400 (Bad Request) if the commentaireRequette has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/commentaire-requettes")
    @Timed
    public ResponseEntity<CommentaireRequetteDTO> createCommentaireRequette(@RequestBody CommentaireRequetteDTO commentaireRequetteDTO) throws URISyntaxException {
        log.debug("REST request to save CommentaireRequette : {}", commentaireRequetteDTO);
        if (commentaireRequetteDTO.getId() != null) {
            throw new BadRequestAlertException("A new commentaireRequette cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommentaireRequetteDTO result = commentaireRequetteService.save(commentaireRequetteDTO);
        return ResponseEntity.created(new URI("/api/commentaire-requettes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /commentaire-requettes : Updates an existing commentaireRequette.
     *
     * @param commentaireRequetteDTO the commentaireRequetteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated commentaireRequetteDTO,
     * or with status 400 (Bad Request) if the commentaireRequetteDTO is not valid,
     * or with status 500 (Internal Server Error) if the commentaireRequetteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/commentaire-requettes")
    @Timed
    public ResponseEntity<CommentaireRequetteDTO> updateCommentaireRequette(@RequestBody CommentaireRequetteDTO commentaireRequetteDTO) throws URISyntaxException {
        log.debug("REST request to update CommentaireRequette : {}", commentaireRequetteDTO);
        if (commentaireRequetteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommentaireRequetteDTO result = commentaireRequetteService.save(commentaireRequetteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, commentaireRequetteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /commentaire-requettes : get all the commentaireRequettes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of commentaireRequettes in body
     */
    @GetMapping("/commentaire-requettes")
    @Timed
    public ResponseEntity<List<CommentaireRequetteDTO>> getAllCommentaireRequettes(Pageable pageable) {
        log.debug("REST request to get a page of CommentaireRequettes");
        Page<CommentaireRequetteDTO> page = commentaireRequetteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/commentaire-requettes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /commentaire-requettes/:id : get the "id" commentaireRequette.
     *
     * @param id the id of the commentaireRequetteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commentaireRequetteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/commentaire-requettes/{id}")
    @Timed
    public ResponseEntity<CommentaireRequetteDTO> getCommentaireRequette(@PathVariable Long id) {
        log.debug("REST request to get CommentaireRequette : {}", id);
        Optional<CommentaireRequetteDTO> commentaireRequetteDTO = commentaireRequetteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commentaireRequetteDTO);
    }

    /**
     * DELETE  /commentaire-requettes/:id : delete the "id" commentaireRequette.
     *
     * @param id the id of the commentaireRequetteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/commentaire-requettes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCommentaireRequette(@PathVariable Long id) {
        log.debug("REST request to delete CommentaireRequette : {}", id);
        commentaireRequetteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
