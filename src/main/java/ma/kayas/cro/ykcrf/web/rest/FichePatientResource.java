package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.FichePatientService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.FichePatientDTO;
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
 * REST controller for managing FichePatient.
 */
@RestController
@RequestMapping("/api")
public class FichePatientResource {

    private final Logger log = LoggerFactory.getLogger(FichePatientResource.class);

    private static final String ENTITY_NAME = "fichePatient";

    private final FichePatientService fichePatientService;

    public FichePatientResource(FichePatientService fichePatientService) {
        this.fichePatientService = fichePatientService;
    }

    /**
     * POST  /fiche-patients : Create a new fichePatient.
     *
     * @param fichePatientDTO the fichePatientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fichePatientDTO, or with status 400 (Bad Request) if the fichePatient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fiche-patients")
    @Timed
    public ResponseEntity<FichePatientDTO> createFichePatient(@RequestBody FichePatientDTO fichePatientDTO) throws URISyntaxException {
        log.debug("REST request to save FichePatient : {}", fichePatientDTO);
        if (fichePatientDTO.getId() != null) {
            throw new BadRequestAlertException("A new fichePatient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FichePatientDTO result = fichePatientService.save(fichePatientDTO);
        return ResponseEntity.created(new URI("/api/fiche-patients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fiche-patients : Updates an existing fichePatient.
     *
     * @param fichePatientDTO the fichePatientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fichePatientDTO,
     * or with status 400 (Bad Request) if the fichePatientDTO is not valid,
     * or with status 500 (Internal Server Error) if the fichePatientDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fiche-patients")
    @Timed
    public ResponseEntity<FichePatientDTO> updateFichePatient(@RequestBody FichePatientDTO fichePatientDTO) throws URISyntaxException {
        log.debug("REST request to update FichePatient : {}", fichePatientDTO);
        if (fichePatientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FichePatientDTO result = fichePatientService.save(fichePatientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fichePatientDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fiche-patients : get all the fichePatients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fichePatients in body
     */
    @GetMapping("/fiche-patients")
    @Timed
    public ResponseEntity<List<FichePatientDTO>> getAllFichePatients(Pageable pageable) {
        log.debug("REST request to get a page of FichePatients");
        Page<FichePatientDTO> page = fichePatientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fiche-patients");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fiche-patients/:id : get the "id" fichePatient.
     *
     * @param id the id of the fichePatientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fichePatientDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fiche-patients/{id}")
    @Timed
    public ResponseEntity<FichePatientDTO> getFichePatient(@PathVariable Long id) {
        log.debug("REST request to get FichePatient : {}", id);
        Optional<FichePatientDTO> fichePatientDTO = fichePatientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fichePatientDTO);
    }

    /**
     * DELETE  /fiche-patients/:id : delete the "id" fichePatient.
     *
     * @param id the id of the fichePatientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fiche-patients/{id}")
    @Timed
    public ResponseEntity<Void> deleteFichePatient(@PathVariable Long id) {
        log.debug("REST request to delete FichePatient : {}", id);
        fichePatientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
