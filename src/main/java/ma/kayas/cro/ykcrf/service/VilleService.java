package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.VilleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Ville.
 */
public interface VilleService {

    /**
     * Save a ville.
     *
     * @param villeDTO the entity to save
     * @return the persisted entity
     */
    VilleDTO save(VilleDTO villeDTO);

    /**
     * Get all the villes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VilleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ville.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VilleDTO> findOne(Long id);

    /**
     * Delete the "id" ville.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
