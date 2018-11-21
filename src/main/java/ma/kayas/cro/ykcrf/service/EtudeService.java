package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.EtudeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Etude.
 */
public interface EtudeService {

    /**
     * Save a etude.
     *
     * @param etudeDTO the entity to save
     * @return the persisted entity
     */
    EtudeDTO save(EtudeDTO etudeDTO);

    /**
     * Get all the etudes.
     *
     * @return the list of entities
     */
    List<EtudeDTO> findAll();


    /**
     * Get the "id" etude.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EtudeDTO> findOne(Long id);

    /**
     * Delete the "id" etude.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
