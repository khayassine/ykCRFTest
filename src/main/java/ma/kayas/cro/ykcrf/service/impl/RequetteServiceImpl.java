package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.RequetteService;
import ma.kayas.cro.ykcrf.domain.Requette;
import ma.kayas.cro.ykcrf.repository.RequetteRepository;
import ma.kayas.cro.ykcrf.service.dto.RequetteDTO;
import ma.kayas.cro.ykcrf.service.mapper.RequetteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Requette.
 */
@Service
@Transactional
public class RequetteServiceImpl implements RequetteService {

    private final Logger log = LoggerFactory.getLogger(RequetteServiceImpl.class);

    private final RequetteRepository requetteRepository;

    private final RequetteMapper requetteMapper;

    public RequetteServiceImpl(RequetteRepository requetteRepository, RequetteMapper requetteMapper) {
        this.requetteRepository = requetteRepository;
        this.requetteMapper = requetteMapper;
    }

    /**
     * Save a requette.
     *
     * @param requetteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RequetteDTO save(RequetteDTO requetteDTO) {
        log.debug("Request to save Requette : {}", requetteDTO);

        Requette requette = requetteMapper.toEntity(requetteDTO);
        requette = requetteRepository.save(requette);
        return requetteMapper.toDto(requette);
    }

    /**
     * Get all the requettes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RequetteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Requettes");
        return requetteRepository.findAll(pageable)
            .map(requetteMapper::toDto);
    }


    /**
     * Get one requette by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RequetteDTO> findOne(Long id) {
        log.debug("Request to get Requette : {}", id);
        return requetteRepository.findById(id)
            .map(requetteMapper::toDto);
    }

    /**
     * Delete the requette by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Requette : {}", id);
        requetteRepository.deleteById(id);
    }
}
