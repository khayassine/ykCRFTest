package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.SousRegionService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.SousRegionDTO;
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
 * REST controller for managing SousRegion.
 */
@RestController
@RequestMapping("/api")
public class SousRegionResource {

    private final Logger log = LoggerFactory.getLogger(SousRegionResource.class);

    private static final String ENTITY_NAME = "sousRegion";

    private final SousRegionService sousRegionService;

    public SousRegionResource(SousRegionService sousRegionService) {
        this.sousRegionService = sousRegionService;
    }

    /**
     * POST  /sous-regions : Create a new sousRegion.
     *
     * @param sousRegionDTO the sousRegionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sousRegionDTO, or with status 400 (Bad Request) if the sousRegion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sous-regions")
    @Timed
    public ResponseEntity<SousRegionDTO> createSousRegion(@RequestBody SousRegionDTO sousRegionDTO) throws URISyntaxException {
        log.debug("REST request to save SousRegion : {}", sousRegionDTO);
        if (sousRegionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sousRegion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SousRegionDTO result = sousRegionService.save(sousRegionDTO);
        return ResponseEntity.created(new URI("/api/sous-regions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sous-regions : Updates an existing sousRegion.
     *
     * @param sousRegionDTO the sousRegionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sousRegionDTO,
     * or with status 400 (Bad Request) if the sousRegionDTO is not valid,
     * or with status 500 (Internal Server Error) if the sousRegionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sous-regions")
    @Timed
    public ResponseEntity<SousRegionDTO> updateSousRegion(@RequestBody SousRegionDTO sousRegionDTO) throws URISyntaxException {
        log.debug("REST request to update SousRegion : {}", sousRegionDTO);
        if (sousRegionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SousRegionDTO result = sousRegionService.save(sousRegionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sousRegionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sous-regions : get all the sousRegions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sousRegions in body
     */
    @GetMapping("/sous-regions")
    @Timed
    public ResponseEntity<List<SousRegionDTO>> getAllSousRegions(Pageable pageable) {
        log.debug("REST request to get a page of SousRegions");
        Page<SousRegionDTO> page = sousRegionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sous-regions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /sous-regions/:id : get the "id" sousRegion.
     *
     * @param id the id of the sousRegionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sousRegionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sous-regions/{id}")
    @Timed
    public ResponseEntity<SousRegionDTO> getSousRegion(@PathVariable Long id) {
        log.debug("REST request to get SousRegion : {}", id);
        Optional<SousRegionDTO> sousRegionDTO = sousRegionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sousRegionDTO);
    }

    /**
     * DELETE  /sous-regions/:id : delete the "id" sousRegion.
     *
     * @param id the id of the sousRegionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sous-regions/{id}")
    @Timed
    public ResponseEntity<Void> deleteSousRegion(@PathVariable Long id) {
        log.debug("REST request to delete SousRegion : {}", id);
        sousRegionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
