package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.ValidationComposantService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.ValidationComposantDTO;
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
 * REST controller for managing ValidationComposant.
 */
@RestController
@RequestMapping("/api")
public class ValidationComposantResource {

    private final Logger log = LoggerFactory.getLogger(ValidationComposantResource.class);

    private static final String ENTITY_NAME = "validationComposant";

    private final ValidationComposantService validationComposantService;

    public ValidationComposantResource(ValidationComposantService validationComposantService) {
        this.validationComposantService = validationComposantService;
    }

    /**
     * POST  /validation-composants : Create a new validationComposant.
     *
     * @param validationComposantDTO the validationComposantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new validationComposantDTO, or with status 400 (Bad Request) if the validationComposant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/validation-composants")
    @Timed
    public ResponseEntity<ValidationComposantDTO> createValidationComposant(@Valid @RequestBody ValidationComposantDTO validationComposantDTO) throws URISyntaxException {
        log.debug("REST request to save ValidationComposant : {}", validationComposantDTO);
        if (validationComposantDTO.getId() != null) {
            throw new BadRequestAlertException("A new validationComposant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ValidationComposantDTO result = validationComposantService.save(validationComposantDTO);
        return ResponseEntity.created(new URI("/api/validation-composants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /validation-composants : Updates an existing validationComposant.
     *
     * @param validationComposantDTO the validationComposantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated validationComposantDTO,
     * or with status 400 (Bad Request) if the validationComposantDTO is not valid,
     * or with status 500 (Internal Server Error) if the validationComposantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/validation-composants")
    @Timed
    public ResponseEntity<ValidationComposantDTO> updateValidationComposant(@Valid @RequestBody ValidationComposantDTO validationComposantDTO) throws URISyntaxException {
        log.debug("REST request to update ValidationComposant : {}", validationComposantDTO);
        if (validationComposantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ValidationComposantDTO result = validationComposantService.save(validationComposantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, validationComposantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /validation-composants : get all the validationComposants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of validationComposants in body
     */
    @GetMapping("/validation-composants")
    @Timed
    public ResponseEntity<List<ValidationComposantDTO>> getAllValidationComposants(Pageable pageable) {
        log.debug("REST request to get a page of ValidationComposants");
        Page<ValidationComposantDTO> page = validationComposantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/validation-composants");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /validation-composants/:id : get the "id" validationComposant.
     *
     * @param id the id of the validationComposantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the validationComposantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/validation-composants/{id}")
    @Timed
    public ResponseEntity<ValidationComposantDTO> getValidationComposant(@PathVariable Long id) {
        log.debug("REST request to get ValidationComposant : {}", id);
        Optional<ValidationComposantDTO> validationComposantDTO = validationComposantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(validationComposantDTO);
    }

    /**
     * DELETE  /validation-composants/:id : delete the "id" validationComposant.
     *
     * @param id the id of the validationComposantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/validation-composants/{id}")
    @Timed
    public ResponseEntity<Void> deleteValidationComposant(@PathVariable Long id) {
        log.debug("REST request to delete ValidationComposant : {}", id);
        validationComposantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
