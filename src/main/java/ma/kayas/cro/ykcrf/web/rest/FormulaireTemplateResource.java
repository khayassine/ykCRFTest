package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.FormulaireTemplateService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.FormulaireTemplateDTO;
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
 * REST controller for managing FormulaireTemplate.
 */
@RestController
@RequestMapping("/api")
public class FormulaireTemplateResource {

    private final Logger log = LoggerFactory.getLogger(FormulaireTemplateResource.class);

    private static final String ENTITY_NAME = "formulaireTemplate";

    private final FormulaireTemplateService formulaireTemplateService;

    public FormulaireTemplateResource(FormulaireTemplateService formulaireTemplateService) {
        this.formulaireTemplateService = formulaireTemplateService;
    }

    /**
     * POST  /formulaire-templates : Create a new formulaireTemplate.
     *
     * @param formulaireTemplateDTO the formulaireTemplateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new formulaireTemplateDTO, or with status 400 (Bad Request) if the formulaireTemplate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/formulaire-templates")
    @Timed
    public ResponseEntity<FormulaireTemplateDTO> createFormulaireTemplate(@Valid @RequestBody FormulaireTemplateDTO formulaireTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save FormulaireTemplate : {}", formulaireTemplateDTO);
        if (formulaireTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new formulaireTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormulaireTemplateDTO result = formulaireTemplateService.save(formulaireTemplateDTO);
        return ResponseEntity.created(new URI("/api/formulaire-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /formulaire-templates : Updates an existing formulaireTemplate.
     *
     * @param formulaireTemplateDTO the formulaireTemplateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated formulaireTemplateDTO,
     * or with status 400 (Bad Request) if the formulaireTemplateDTO is not valid,
     * or with status 500 (Internal Server Error) if the formulaireTemplateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/formulaire-templates")
    @Timed
    public ResponseEntity<FormulaireTemplateDTO> updateFormulaireTemplate(@Valid @RequestBody FormulaireTemplateDTO formulaireTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update FormulaireTemplate : {}", formulaireTemplateDTO);
        if (formulaireTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormulaireTemplateDTO result = formulaireTemplateService.save(formulaireTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, formulaireTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /formulaire-templates : get all the formulaireTemplates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of formulaireTemplates in body
     */
    @GetMapping("/formulaire-templates")
    @Timed
    public ResponseEntity<List<FormulaireTemplateDTO>> getAllFormulaireTemplates(Pageable pageable) {
        log.debug("REST request to get a page of FormulaireTemplates");
        Page<FormulaireTemplateDTO> page = formulaireTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/formulaire-templates");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /formulaire-templates/:id : get the "id" formulaireTemplate.
     *
     * @param id the id of the formulaireTemplateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the formulaireTemplateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/formulaire-templates/{id}")
    @Timed
    public ResponseEntity<FormulaireTemplateDTO> getFormulaireTemplate(@PathVariable Long id) {
        log.debug("REST request to get FormulaireTemplate : {}", id);
        Optional<FormulaireTemplateDTO> formulaireTemplateDTO = formulaireTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formulaireTemplateDTO);
    }

    /**
     * DELETE  /formulaire-templates/:id : delete the "id" formulaireTemplate.
     *
     * @param id the id of the formulaireTemplateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/formulaire-templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteFormulaireTemplate(@PathVariable Long id) {
        log.debug("REST request to delete FormulaireTemplate : {}", id);
        formulaireTemplateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
