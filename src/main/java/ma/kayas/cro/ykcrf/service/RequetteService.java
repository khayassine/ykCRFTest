package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.RequetteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Requette.
 */
public interface RequetteService {

    /**
     * Save a requette.
     *
     * @param requetteDTO the entity to save
     * @return the persisted entity
     */
    RequetteDTO save(RequetteDTO requetteDTO);

    /**
     * Get all the requettes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RequetteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" requette.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RequetteDTO> findOne(Long id);

    /**
     * Delete the "id" requette.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
