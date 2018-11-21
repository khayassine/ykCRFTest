package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.ComposantTemplateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ComposantTemplate.
 */
public interface ComposantTemplateService {

    /**
     * Save a composantTemplate.
     *
     * @param composantTemplateDTO the entity to save
     * @return the persisted entity
     */
    ComposantTemplateDTO save(ComposantTemplateDTO composantTemplateDTO);

    /**
     * Get all the composantTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ComposantTemplateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" composantTemplate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ComposantTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" composantTemplate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
