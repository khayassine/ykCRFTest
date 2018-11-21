package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.SousRegionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SousRegion.
 */
public interface SousRegionService {

    /**
     * Save a sousRegion.
     *
     * @param sousRegionDTO the entity to save
     * @return the persisted entity
     */
    SousRegionDTO save(SousRegionDTO sousRegionDTO);

    /**
     * Get all the sousRegions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SousRegionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sousRegion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SousRegionDTO> findOne(Long id);

    /**
     * Delete the "id" sousRegion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
