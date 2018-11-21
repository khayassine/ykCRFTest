package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.TypeComposantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TypeComposant.
 */
public interface TypeComposantService {

    /**
     * Save a typeComposant.
     *
     * @param typeComposantDTO the entity to save
     * @return the persisted entity
     */
    TypeComposantDTO save(TypeComposantDTO typeComposantDTO);

    /**
     * Get all the typeComposants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeComposantDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeComposant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeComposantDTO> findOne(Long id);

    /**
     * Delete the "id" typeComposant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
