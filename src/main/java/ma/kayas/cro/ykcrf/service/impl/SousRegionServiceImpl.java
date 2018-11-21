package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.SousRegionService;
import ma.kayas.cro.ykcrf.domain.SousRegion;
import ma.kayas.cro.ykcrf.repository.SousRegionRepository;
import ma.kayas.cro.ykcrf.service.dto.SousRegionDTO;
import ma.kayas.cro.ykcrf.service.mapper.SousRegionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SousRegion.
 */
@Service
@Transactional
public class SousRegionServiceImpl implements SousRegionService {

    private final Logger log = LoggerFactory.getLogger(SousRegionServiceImpl.class);

    private final SousRegionRepository sousRegionRepository;

    private final SousRegionMapper sousRegionMapper;

    public SousRegionServiceImpl(SousRegionRepository sousRegionRepository, SousRegionMapper sousRegionMapper) {
        this.sousRegionRepository = sousRegionRepository;
        this.sousRegionMapper = sousRegionMapper;
    }

    /**
     * Save a sousRegion.
     *
     * @param sousRegionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SousRegionDTO save(SousRegionDTO sousRegionDTO) {
        log.debug("Request to save SousRegion : {}", sousRegionDTO);

        SousRegion sousRegion = sousRegionMapper.toEntity(sousRegionDTO);
        sousRegion = sousRegionRepository.save(sousRegion);
        return sousRegionMapper.toDto(sousRegion);
    }

    /**
     * Get all the sousRegions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SousRegionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SousRegions");
        return sousRegionRepository.findAll(pageable)
            .map(sousRegionMapper::toDto);
    }


    /**
     * Get one sousRegion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SousRegionDTO> findOne(Long id) {
        log.debug("Request to get SousRegion : {}", id);
        return sousRegionRepository.findById(id)
            .map(sousRegionMapper::toDto);
    }

    /**
     * Delete the sousRegion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SousRegion : {}", id);
        sousRegionRepository.deleteById(id);
    }
}
