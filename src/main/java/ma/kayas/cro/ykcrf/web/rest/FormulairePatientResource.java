package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.FormulairePatientService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.service.dto.FormulairePatientDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FormulairePatient.
 */
@RestController
@RequestMapping("/api")
public class FormulairePatientResource {

    private final Logger log = LoggerFactory.getLogger(FormulairePatientResource.class);

    private static final String ENTITY_NAME = "formulairePatient";

    private final FormulairePatientService formulairePatientService;

    public FormulairePatientResource(FormulairePatientService formulairePatientService) {
        this.formulairePatientService = formulairePatientService;
    }

    /**
     * POST  /formulaire-patients : Create a new formulairePatient.
     *
     * @param formulairePatientDTO the formulairePatientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new formulairePatientDTO, or with status 400 (Bad Request) if the formulairePatient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/formulaire-patients")
    @Timed
    public ResponseEntity<FormulairePatientDTO> createFormulairePatient(@RequestBody FormulairePatientDTO formulairePatientDTO) throws URISyntaxException {
        log.debug("REST request to save FormulairePatient : {}", formulairePatientDTO);
        if (formulairePatientDTO.getId() != null) {
            throw new BadRequestAlertException("A new formulairePatient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormulairePatientDTO result = formulairePatientService.save(formulairePatientDTO);
        return ResponseEntity.created(new URI("/api/formulaire-patients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /formulaire-patients : Updates an existing formulairePatient.
     *
     * @param formulairePatientDTO the formulairePatientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated formulairePatientDTO,
     * or with status 400 (Bad Request) if the formulairePatientDTO is not valid,
     * or with status 500 (Internal Server Error) if the formulairePatientDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/formulaire-patients")
    @Timed
    public ResponseEntity<FormulairePatientDTO> updateFormulairePatient(@RequestBody FormulairePatientDTO formulairePatientDTO) throws URISyntaxException {
        log.debug("REST request to update FormulairePatient : {}", formulairePatientDTO);
        if (formulairePatientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormulairePatientDTO result = formulairePatientService.save(formulairePatientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, formulairePatientDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /formulaire-patients : get all the formulairePatients.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of formulairePatients in body
     */
    @GetMapping("/formulaire-patients")
    @Timed
    public List<FormulairePatientDTO> getAllFormulairePatients() {
        log.debug("REST request to get all FormulairePatients");
        return formulairePatientService.findAll();
    }

    /**
     * GET  /formulaire-patients/:id : get the "id" formulairePatient.
     *
     * @param id the id of the formulairePatientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the formulairePatientDTO, or with status 404 (Not Found)
     */
    @GetMapping("/formulaire-patients/{id}")
    @Timed
    public ResponseEntity<FormulairePatientDTO> getFormulairePatient(@PathVariable Long id) {
        log.debug("REST request to get FormulairePatient : {}", id);
        Optional<FormulairePatientDTO> formulairePatientDTO = formulairePatientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formulairePatientDTO);
    }

    /**
     * DELETE  /formulaire-patients/:id : delete the "id" formulairePatient.
     *
     * @param id the id of the formulairePatientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/formulaire-patients/{id}")
    @Timed
    public ResponseEntity<Void> deleteFormulairePatient(@PathVariable Long id) {
        log.debug("REST request to delete FormulairePatient : {}", id);
        formulairePatientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
