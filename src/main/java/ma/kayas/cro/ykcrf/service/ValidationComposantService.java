package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.ValidationComposantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ValidationComposant.
 */
public interface ValidationComposantService {

    /**
     * Save a validationComposant.
     *
     * @param validationComposantDTO the entity to save
     * @return the persisted entity
     */
    ValidationComposantDTO save(ValidationComposantDTO validationComposantDTO);

    /**
     * Get all the validationComposants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ValidationComposantDTO> findAll(Pageable pageable);


    /**
     * Get the "id" validationComposant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ValidationComposantDTO> findOne(Long id);

    /**
     * Delete the "id" validationComposant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
