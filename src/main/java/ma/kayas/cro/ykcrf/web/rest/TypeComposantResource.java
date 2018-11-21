package ma.kayas.cro.ykcrf.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.kayas.cro.ykcrf.service.TypeComposantService;
import ma.kayas.cro.ykcrf.web.rest.errors.BadRequestAlertException;
import ma.kayas.cro.ykcrf.web.rest.util.HeaderUtil;
import ma.kayas.cro.ykcrf.web.rest.util.PaginationUtil;
import ma.kayas.cro.ykcrf.service.dto.TypeComposantDTO;
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
 * REST controller for managing TypeComposant.
 */
@RestController
@RequestMapping("/api")
public class TypeComposantResource {

    private final Logger log = LoggerFactory.getLogger(TypeComposantResource.class);

    private static final String ENTITY_NAME = "typeComposant";

    private final TypeComposantService typeComposantService;

    public TypeComposantResource(TypeComposantService typeComposantService) {
        this.typeComposantService = typeComposantService;
    }

    /**
     * POST  /type-composants : Create a new typeComposant.
     *
     * @param typeComposantDTO the typeComposantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeComposantDTO, or with status 400 (Bad Request) if the typeComposant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-composants")
    @Timed
    public ResponseEntity<TypeComposantDTO> createTypeComposant(@Valid @RequestBody TypeComposantDTO typeComposantDTO) throws URISyntaxException {
        log.debug("REST request to save TypeComposant : {}", typeComposantDTO);
        if (typeComposantDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeComposant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeComposantDTO result = typeComposantService.save(typeComposantDTO);
        return ResponseEntity.created(new URI("/api/type-composants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-composants : Updates an existing typeComposant.
     *
     * @param typeComposantDTO the typeComposantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeComposantDTO,
     * or with status 400 (Bad Request) if the typeComposantDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeComposantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-composants")
    @Timed
    public ResponseEntity<TypeComposantDTO> updateTypeComposant(@Valid @RequestBody TypeComposantDTO typeComposantDTO) throws URISyntaxException {
        log.debug("REST request to update TypeComposant : {}", typeComposantDTO);
        if (typeComposantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeComposantDTO result = typeComposantService.save(typeComposantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeComposantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-composants : get all the typeComposants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of typeComposants in body
     */
    @GetMapping("/type-composants")
    @Timed
    public ResponseEntity<List<TypeComposantDTO>> getAllTypeComposants(Pageable pageable) {
        log.debug("REST request to get a page of TypeComposants");
        Page<TypeComposantDTO> page = typeComposantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/type-composants");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /type-composants/:id : get the "id" typeComposant.
     *
     * @param id the id of the typeComposantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeComposantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-composants/{id}")
    @Timed
    public ResponseEntity<TypeComposantDTO> getTypeComposant(@PathVariable Long id) {
        log.debug("REST request to get TypeComposant : {}", id);
        Optional<TypeComposantDTO> typeComposantDTO = typeComposantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeComposantDTO);
    }

    /**
     * DELETE  /type-composants/:id : delete the "id" typeComposant.
     *
     * @param id the id of the typeComposantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-composants/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeComposant(@PathVariable Long id) {
        log.debug("REST request to delete TypeComposant : {}", id);
        typeComposantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
