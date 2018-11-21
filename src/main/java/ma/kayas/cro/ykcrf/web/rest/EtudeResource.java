package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.EtudeService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.service.dto.EtudeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Etude.
 */
@RestController
@RequestMapping("/api")
public class EtudeResource {

    private final Logger log = LoggerFactory.getLogger(EtudeResource.class);

    private static final String ENTITY_NAME = "etude";

    private final EtudeService etudeService;

    public EtudeResource(EtudeService etudeService) {
        this.etudeService = etudeService;
    }

    /**
     * POST  /etudes : Create a new etude.
     *
     * @param etudeDTO the etudeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etudeDTO, or with status 400 (Bad Request) if the etude has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/etudes")
    @Timed
    public ResponseEntity<EtudeDTO> createEtude(@Valid @RequestBody EtudeDTO etudeDTO) throws URISyntaxException {
        log.debug("REST request to save Etude : {}", etudeDTO);
        if (etudeDTO.getId() != null) {
            throw new BadRequestAlertException("A new etude cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtudeDTO result = etudeService.save(etudeDTO);
        return ResponseEntity.created(new URI("/api/etudes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /etudes : Updates an existing etude.
     *
     * @param etudeDTO the etudeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etudeDTO,
     * or with status 400 (Bad Request) if the etudeDTO is not valid,
     * or with status 500 (Internal Server Error) if the etudeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/etudes")
    @Timed
    public ResponseEntity<EtudeDTO> updateEtude(@Valid @RequestBody EtudeDTO etudeDTO) throws URISyntaxException {
        log.debug("REST request to update Etude : {}", etudeDTO);
        if (etudeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtudeDTO result = etudeService.save(etudeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, etudeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /etudes : get all the etudes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of etudes in body
     */
    @GetMapping("/etudes")
    @Timed
    public List<EtudeDTO> getAllEtudes() {
        log.debug("REST request to get all Etudes");
        return etudeService.findAll();
    }

    /**
     * GET  /etudes/:id : get the "id" etude.
     *
     * @param id the id of the etudeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etudeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/etudes/{id}")
    @Timed
    public ResponseEntity<EtudeDTO> getEtude(@PathVariable Long id) {
        log.debug("REST request to get Etude : {}", id);
        Optional<EtudeDTO> etudeDTO = etudeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etudeDTO);
    }

    /**
     * DELETE  /etudes/:id : delete the "id" etude.
     *
     * @param id the id of the etudeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/etudes/{id}")
    @Timed
    public ResponseEntity<Void> deleteEtude(@PathVariable Long id) {
        log.debug("REST request to delete Etude : {}", id);
        etudeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
