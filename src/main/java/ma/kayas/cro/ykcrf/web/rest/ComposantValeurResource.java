package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.ComposantValeurService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.service.dto.ComposantValeurDTO;
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
 * REST controller for managing ComposantValeur.
 */
@RestController
@RequestMapping("/api")
public class ComposantValeurResource {

    private final Logger log = LoggerFactory.getLogger(ComposantValeurResource.class);

    private static final String ENTITY_NAME = "composantValeur";

    private final ComposantValeurService composantValeurService;

    public ComposantValeurResource(ComposantValeurService composantValeurService) {
        this.composantValeurService = composantValeurService;
    }

    /**
     * POST  /composant-valeurs : Create a new composantValeur.
     *
     * @param composantValeurDTO the composantValeurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new composantValeurDTO, or with status 400 (Bad Request) if the composantValeur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/composant-valeurs")
    @Timed
    public ResponseEntity<ComposantValeurDTO> createComposantValeur(@RequestBody ComposantValeurDTO composantValeurDTO) throws URISyntaxException {
        log.debug("REST request to save ComposantValeur : {}", composantValeurDTO);
        if (composantValeurDTO.getId() != null) {
            throw new BadRequestAlertException("A new composantValeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComposantValeurDTO result = composantValeurService.save(composantValeurDTO);
        return ResponseEntity.created(new URI("/api/composant-valeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /composant-valeurs : Updates an existing composantValeur.
     *
     * @param composantValeurDTO the composantValeurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated composantValeurDTO,
     * or with status 400 (Bad Request) if the composantValeurDTO is not valid,
     * or with status 500 (Internal Server Error) if the composantValeurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/composant-valeurs")
    @Timed
    public ResponseEntity<ComposantValeurDTO> updateComposantValeur(@RequestBody ComposantValeurDTO composantValeurDTO) throws URISyntaxException {
        log.debug("REST request to update ComposantValeur : {}", composantValeurDTO);
        if (composantValeurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComposantValeurDTO result = composantValeurService.save(composantValeurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, composantValeurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /composant-valeurs : get all the composantValeurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of composantValeurs in body
     */
    @GetMapping("/composant-valeurs")
    @Timed
    public List<ComposantValeurDTO> getAllComposantValeurs() {
        log.debug("REST request to get all ComposantValeurs");
        return composantValeurService.findAll();
    }

    /**
     * GET  /composant-valeurs/:id : get the "id" composantValeur.
     *
     * @param id the id of the composantValeurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the composantValeurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/composant-valeurs/{id}")
    @Timed
    public ResponseEntity<ComposantValeurDTO> getComposantValeur(@PathVariable Long id) {
        log.debug("REST request to get ComposantValeur : {}", id);
        Optional<ComposantValeurDTO> composantValeurDTO = composantValeurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(composantValeurDTO);
    }

    /**
     * DELETE  /composant-valeurs/:id : delete the "id" composantValeur.
     *
     * @param id the id of the composantValeurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/composant-valeurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteComposantValeur(@PathVariable Long id) {
        log.debug("REST request to delete ComposantValeur : {}", id);
        composantValeurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
