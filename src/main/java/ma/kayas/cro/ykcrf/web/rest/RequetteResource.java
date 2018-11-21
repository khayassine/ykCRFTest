package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.RequetteService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.RequetteDTO;
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
 * REST controller for managing Requette.
 */
@RestController
@RequestMapping("/api")
public class RequetteResource {

    private final Logger log = LoggerFactory.getLogger(RequetteResource.class);

    private static final String ENTITY_NAME = "requette";

    private final RequetteService requetteService;

    public RequetteResource(RequetteService requetteService) {
        this.requetteService = requetteService;
    }

    /**
     * POST  /requettes : Create a new requette.
     *
     * @param requetteDTO the requetteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requetteDTO, or with status 400 (Bad Request) if the requette has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/requettes")
    @Timed
    public ResponseEntity<RequetteDTO> createRequette(@RequestBody RequetteDTO requetteDTO) throws URISyntaxException {
        log.debug("REST request to save Requette : {}", requetteDTO);
        if (requetteDTO.getId() != null) {
            throw new BadRequestAlertException("A new requette cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequetteDTO result = requetteService.save(requetteDTO);
        return ResponseEntity.created(new URI("/api/requettes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /requettes : Updates an existing requette.
     *
     * @param requetteDTO the requetteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated requetteDTO,
     * or with status 400 (Bad Request) if the requetteDTO is not valid,
     * or with status 500 (Internal Server Error) if the requetteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/requettes")
    @Timed
    public ResponseEntity<RequetteDTO> updateRequette(@RequestBody RequetteDTO requetteDTO) throws URISyntaxException {
        log.debug("REST request to update Requette : {}", requetteDTO);
        if (requetteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequetteDTO result = requetteService.save(requetteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, requetteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /requettes : get all the requettes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of requettes in body
     */
    @GetMapping("/requettes")
    @Timed
    public ResponseEntity<List<RequetteDTO>> getAllRequettes(Pageable pageable) {
        log.debug("REST request to get a page of Requettes");
        Page<RequetteDTO> page = requetteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/requettes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /requettes/:id : get the "id" requette.
     *
     * @param id the id of the requetteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the requetteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/requettes/{id}")
    @Timed
    public ResponseEntity<RequetteDTO> getRequette(@PathVariable Long id) {
        log.debug("REST request to get Requette : {}", id);
        Optional<RequetteDTO> requetteDTO = requetteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requetteDTO);
    }

    /**
     * DELETE  /requettes/:id : delete the "id" requette.
     *
     * @param id the id of the requetteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/requettes/{id}")
    @Timed
    public ResponseEntity<Void> deleteRequette(@PathVariable Long id) {
        log.debug("REST request to delete Requette : {}", id);
        requetteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
