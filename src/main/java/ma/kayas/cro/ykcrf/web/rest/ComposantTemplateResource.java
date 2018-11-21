package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.ComposantTemplateService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.ComposantTemplateDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ComposantTemplate.
 */
@RestController
@RequestMapping("/api")
public class ComposantTemplateResource {

    private final Logger log = LoggerFactory.getLogger(ComposantTemplateResource.class);

    private static final String ENTITY_NAME = "composantTemplate";

    private final ComposantTemplateService composantTemplateService;

    public ComposantTemplateResource(ComposantTemplateService composantTemplateService) {
        this.composantTemplateService = composantTemplateService;
    }

    /**
     * POST  /composant-templates : Create a new composantTemplate.
     *
     * @param composantTemplateDTO the composantTemplateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new composantTemplateDTO, or with status 400 (Bad Request) if the composantTemplate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/composant-templates")
    @Timed
    public ResponseEntity<ComposantTemplateDTO> createComposantTemplate(@Valid @RequestBody ComposantTemplateDTO composantTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save ComposantTemplate : {}", composantTemplateDTO);
        if (composantTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new composantTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComposantTemplateDTO result = composantTemplateService.save(composantTemplateDTO);
        return ResponseEntity.created(new URI("/api/composant-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /composant-templates : Updates an existing composantTemplate.
     *
     * @param composantTemplateDTO the composantTemplateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated composantTemplateDTO,
     * or with status 400 (Bad Request) if the composantTemplateDTO is not valid,
     * or with status 500 (Internal Server Error) if the composantTemplateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/composant-templates")
    @Timed
    public ResponseEntity<ComposantTemplateDTO> updateComposantTemplate(@Valid @RequestBody ComposantTemplateDTO composantTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update ComposantTemplate : {}", composantTemplateDTO);
        if (composantTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComposantTemplateDTO result = composantTemplateService.save(composantTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, composantTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /composant-templates : get all the composantTemplates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of composantTemplates in body
     */
    @GetMapping("/composant-templates")
    @Timed
    public ResponseEntity<List<ComposantTemplateDTO>> getAllComposantTemplates(Pageable pageable) {
        log.debug("REST request to get a page of ComposantTemplates");
        Page<ComposantTemplateDTO> page = composantTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/composant-templates");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /composant-templates/:id : get the "id" composantTemplate.
     *
     * @param id the id of the composantTemplateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the composantTemplateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/composant-templates/{id}")
    @Timed
    public ResponseEntity<ComposantTemplateDTO> getComposantTemplate(@PathVariable Long id) {
        log.debug("REST request to get ComposantTemplate : {}", id);
        Optional<ComposantTemplateDTO> composantTemplateDTO = composantTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(composantTemplateDTO);
    }

    /**
     * DELETE  /composant-templates/:id : delete the "id" composantTemplate.
     *
     * @param id the id of the composantTemplateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/composant-templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteComposantTemplate(@PathVariable Long id) {
        log.debug("REST request to delete ComposantTemplate : {}", id);
        composantTemplateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
