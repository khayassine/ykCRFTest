package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.CentreService;
import ma.kayas.cro.ykcrf.domain.Centre;
import ma.kayas.cro.ykcrf.repository.CentreRepository;
import ma.kayas.cro.ykcrf.service.dto.CentreDTO;
import ma.kayas.cro.ykcrf.service.mapper.CentreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Centre.
 */
@Service
@Transactional
public class CentreServiceImpl implements CentreService {

    private final Logger log = LoggerFactory.getLogger(CentreServiceImpl.class);

    private final CentreRepository centreRepository;

    private final CentreMapper centreMapper;

    public CentreServiceImpl(CentreRepository centreRepository, CentreMapper centreMapper) {
        this.centreRepository = centreRepository;
        this.centreMapper = centreMapper;
    }

    /**
     * Save a centre.
     *
     * @param centreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CentreDTO save(CentreDTO centreDTO) {
        log.debug("Request to save Centre : {}", centreDTO);

        Centre centre = centreMapper.toEntity(centreDTO);
        centre = centreRepository.save(centre);
        return centreMapper.toDto(centre);
    }

    /**
     * Get all the centres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CentreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Centres");
        return centreRepository.findAll(pageable)
            .map(centreMapper::toDto);
    }


    /**
     * Get one centre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CentreDTO> findOne(Long id) {
        log.debug("Request to get Centre : {}", id);
        return centreRepository.findById(id)
            .map(centreMapper::toDto);
    }

    /**
     * Delete the centre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Centre : {}", id);
        centreRepository.deleteById(id);
    }
}
