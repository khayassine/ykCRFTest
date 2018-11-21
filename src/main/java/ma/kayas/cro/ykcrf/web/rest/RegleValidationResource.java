package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.RegleValidationService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.RegleValidationDTO;
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
 * REST controller for managing RegleValidation.
 */
@RestController
@RequestMapping("/api")
public class RegleValidationResource {

    private final Logger log = LoggerFactory.getLogger(RegleValidationResource.class);

    private static final String ENTITY_NAME = "regleValidation";

    private final RegleValidationService regleValidationService;

    public RegleValidationResource(RegleValidationService regleValidationService) {
        this.regleValidationService = regleValidationService;
    }

    /**
     * POST  /regle-validations : Create a new regleValidation.
     *
     * @param regleValidationDTO the regleValidationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new regleValidationDTO, or with status 400 (Bad Request) if the regleValidation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/regle-validations")
    @Timed
    public ResponseEntity<RegleValidationDTO> createRegleValidation(@Valid @RequestBody RegleValidationDTO regleValidationDTO) throws URISyntaxException {
        log.debug("REST request to save RegleValidation : {}", regleValidationDTO);
        if (regleValidationDTO.getId() != null) {
            throw new BadRequestAlertException("A new regleValidation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegleValidationDTO result = regleValidationService.save(regleValidationDTO);
        return ResponseEntity.created(new URI("/api/regle-validations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /regle-validations : Updates an existing regleValidation.
     *
     * @param regleValidationDTO the regleValidationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated regleValidationDTO,
     * or with status 400 (Bad Request) if the regleValidationDTO is not valid,
     * or with status 500 (Internal Server Error) if the regleValidationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/regle-validations")
    @Timed
    public ResponseEntity<RegleValidationDTO> updateRegleValidation(@Valid @RequestBody RegleValidationDTO regleValidationDTO) throws URISyntaxException {
        log.debug("REST request to update RegleValidation : {}", regleValidationDTO);
        if (regleValidationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RegleValidationDTO result = regleValidationService.save(regleValidationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, regleValidationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /regle-validations : get all the regleValidations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of regleValidations in body
     */
    @GetMapping("/regle-validations")
    @Timed
    public ResponseEntity<List<RegleValidationDTO>> getAllRegleValidations(Pageable pageable) {
        log.debug("REST request to get a page of RegleValidations");
        Page<RegleValidationDTO> page = regleValidationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/regle-validations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /regle-validations/:id : get the "id" regleValidation.
     *
     * @param id the id of the regleValidationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the regleValidationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/regle-validations/{id}")
    @Timed
    public ResponseEntity<RegleValidationDTO> getRegleValidation(@PathVariable Long id) {
        log.debug("REST request to get RegleValidation : {}", id);
        Optional<RegleValidationDTO> regleValidationDTO = regleValidationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(regleValidationDTO);
    }

    /**
     * DELETE  /regle-validations/:id : delete the "id" regleValidation.
     *
     * @param id the id of the regleValidationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/regle-validations/{id}")
    @Timed
    public ResponseEntity<Void> deleteRegleValidation(@PathVariable Long id) {
        log.debug("REST request to delete RegleValidation : {}", id);
        regleValidationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
