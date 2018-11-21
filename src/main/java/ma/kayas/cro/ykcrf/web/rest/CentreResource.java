package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.CentreService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.CentreDTO;
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
 * REST controller for managing Centre.
 */
@RestController
@RequestMapping("/api")
public class CentreResource {

    private final Logger log = LoggerFactory.getLogger(CentreResource.class);

    private static final String ENTITY_NAME = "centre";

    private final CentreService centreService;

    public CentreResource(CentreService centreService) {
        this.centreService = centreService;
    }

    /**
     * POST  /centres : Create a new centre.
     *
     * @param centreDTO the centreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new centreDTO, or with status 400 (Bad Request) if the centre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/centres")
    @Timed
    public ResponseEntity<CentreDTO> createCentre(@RequestBody CentreDTO centreDTO) throws URISyntaxException {
        log.debug("REST request to save Centre : {}", centreDTO);
        if (centreDTO.getId() != null) {
            throw new BadRequestAlertException("A new centre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CentreDTO result = centreService.save(centreDTO);
        return ResponseEntity.created(new URI("/api/centres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /centres : Updates an existing centre.
     *
     * @param centreDTO the centreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated centreDTO,
     * or with status 400 (Bad Request) if the centreDTO is not valid,
     * or with status 500 (Internal Server Error) if the centreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/centres")
    @Timed
    public ResponseEntity<CentreDTO> updateCentre(@RequestBody CentreDTO centreDTO) throws URISyntaxException {
        log.debug("REST request to update Centre : {}", centreDTO);
        if (centreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CentreDTO result = centreService.save(centreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, centreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /centres : get all the centres.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of centres in body
     */
    @GetMapping("/centres")
    @Timed
    public ResponseEntity<List<CentreDTO>> getAllCentres(Pageable pageable) {
        log.debug("REST request to get a page of Centres");
        Page<CentreDTO> page = centreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/centres");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /centres/:id : get the "id" centre.
     *
     * @param id the id of the centreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the centreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/centres/{id}")
    @Timed
    public ResponseEntity<CentreDTO> getCentre(@PathVariable Long id) {
        log.debug("REST request to get Centre : {}", id);
        Optional<CentreDTO> centreDTO = centreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centreDTO);
    }

    /**
     * DELETE  /centres/:id : delete the "id" centre.
     *
     * @param id the id of the centreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/centres/{id}")
    @Timed
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
        log.debug("REST request to delete Centre : {}", id);
        centreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
