package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.RegleValidationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RegleValidation.
 */
public interface RegleValidationService {

    /**
     * Save a regleValidation.
     *
     * @param regleValidationDTO the entity to save
     * @return the persisted entity
     */
    RegleValidationDTO save(RegleValidationDTO regleValidationDTO);

    /**
     * Get all the regleValidations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RegleValidationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" regleValidation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RegleValidationDTO> findOne(Long id);

    /**
     * Delete the "id" regleValidation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
