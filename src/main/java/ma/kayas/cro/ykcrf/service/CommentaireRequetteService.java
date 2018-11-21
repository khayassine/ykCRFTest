package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.CommentaireRequetteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CommentaireRequette.
 */
public interface CommentaireRequetteService {

    /**
     * Save a commentaireRequette.
     *
     * @param commentaireRequetteDTO the entity to save
     * @return the persisted entity
     */
    CommentaireRequetteDTO save(CommentaireRequetteDTO commentaireRequetteDTO);

    /**
     * Get all the commentaireRequettes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommentaireRequetteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commentaireRequette.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CommentaireRequetteDTO> findOne(Long id);

    /**
     * Delete the "id" commentaireRequette.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
