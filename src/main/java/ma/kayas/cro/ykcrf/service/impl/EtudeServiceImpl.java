package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.EtudeService;
import ma.kayas.cro.ykcrf.domain.Etude;
import ma.kayas.cro.ykcrf.repository.EtudeRepository;
import ma.kayas.cro.ykcrf.service.dto.EtudeDTO;
import ma.kayas.cro.ykcrf.service.mapper.EtudeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Etude.
 */
@Service
@Transactional
public class EtudeServiceImpl implements EtudeService {

    private final Logger log = LoggerFactory.getLogger(EtudeServiceImpl.class);

    private final EtudeRepository etudeRepository;

    private final EtudeMapper etudeMapper;

    public EtudeServiceImpl(EtudeRepository etudeRepository, EtudeMapper etudeMapper) {
        this.etudeRepository = etudeRepository;
        this.etudeMapper = etudeMapper;
    }

    /**
     * Save a etude.
     *
     * @param etudeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EtudeDTO save(EtudeDTO etudeDTO) {
        log.debug("Request to save Etude : {}", etudeDTO);

        Etude etude = etudeMapper.toEntity(etudeDTO);
        etude = etudeRepository.save(etude);
        return etudeMapper.toDto(etude);
    }

    /**
     * Get all the etudes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtudeDTO> findAll() {
        log.debug("Request to get all Etudes");
        return etudeRepository.findAll().stream()
            .map(etudeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one etude by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtudeDTO> findOne(Long id) {
        log.debug("Request to get Etude : {}", id);
        return etudeRepository.findById(id)
            .map(etudeMapper::toDto);
    }

    /**
     * Delete the etude by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Etude : {}", id);
        etudeRepository.deleteById(id);
    }
}
