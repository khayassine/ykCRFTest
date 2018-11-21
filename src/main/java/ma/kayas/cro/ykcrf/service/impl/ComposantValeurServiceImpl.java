package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.ComposantValeurService;
import ma.kayas.cro.ykcrf.domain.ComposantValeur;
import ma.kayas.cro.ykcrf.repository.ComposantValeurRepository;
import ma.kayas.cro.ykcrf.service.dto.ComposantValeurDTO;
import ma.kayas.cro.ykcrf.service.mapper.ComposantValeurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ComposantValeur.
 */
@Service
@Transactional
public class ComposantValeurServiceImpl implements ComposantValeurService {

    private final Logger log = LoggerFactory.getLogger(ComposantValeurServiceImpl.class);

    private final ComposantValeurRepository composantValeurRepository;

    private final ComposantValeurMapper composantValeurMapper;

    public ComposantValeurServiceImpl(ComposantValeurRepository composantValeurRepository, ComposantValeurMapper composantValeurMapper) {
        this.composantValeurRepository = composantValeurRepository;
        this.composantValeurMapper = composantValeurMapper;
    }

    /**
     * Save a composantValeur.
     *
     * @param composantValeurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComposantValeurDTO save(ComposantValeurDTO composantValeurDTO) {
        log.debug("Request to save ComposantValeur : {}", composantValeurDTO);

        ComposantValeur composantValeur = composantValeurMapper.toEntity(composantValeurDTO);
        composantValeur = composantValeurRepository.save(composantValeur);
        return composantValeurMapper.toDto(composantValeur);
    }

    /**
     * Get all the composantValeurs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComposantValeurDTO> findAll() {
        log.debug("Request to get all ComposantValeurs");
        return composantValeurRepository.findAll().stream()
            .map(composantValeurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one composantValeur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComposantValeurDTO> findOne(Long id) {
        log.debug("Request to get ComposantValeur : {}", id);
        return composantValeurRepository.findById(id)
            .map(composantValeurMapper::toDto);
    }

    /**
     * Delete the composantValeur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComposantValeur : {}", id);
        composantValeurRepository.deleteById(id);
    }
}
