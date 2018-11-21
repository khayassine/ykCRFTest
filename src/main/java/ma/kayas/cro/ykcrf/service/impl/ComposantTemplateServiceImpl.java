package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.ComposantTemplateService;
import ma.kayas.cro.ykcrf.domain.ComposantTemplate;
import ma.kayas.cro.ykcrf.repository.ComposantTemplateRepository;
import ma.kayas.cro.ykcrf.service.dto.ComposantTemplateDTO;
import ma.kayas.cro.ykcrf.service.mapper.ComposantTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ComposantTemplate.
 */
@Service
@Transactional
public class ComposantTemplateServiceImpl implements ComposantTemplateService {

    private final Logger log = LoggerFactory.getLogger(ComposantTemplateServiceImpl.class);

    private final ComposantTemplateRepository composantTemplateRepository;

    private final ComposantTemplateMapper composantTemplateMapper;

    public ComposantTemplateServiceImpl(ComposantTemplateRepository composantTemplateRepository, ComposantTemplateMapper composantTemplateMapper) {
        this.composantTemplateRepository = composantTemplateRepository;
        this.composantTemplateMapper = composantTemplateMapper;
    }

    /**
     * Save a composantTemplate.
     *
     * @param composantTemplateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComposantTemplateDTO save(ComposantTemplateDTO composantTemplateDTO) {
        log.debug("Request to save ComposantTemplate : {}", composantTemplateDTO);

        ComposantTemplate composantTemplate = composantTemplateMapper.toEntity(composantTemplateDTO);
        composantTemplate = composantTemplateRepository.save(composantTemplate);
        return composantTemplateMapper.toDto(composantTemplate);
    }

    /**
     * Get all the composantTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComposantTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ComposantTemplates");
        return composantTemplateRepository.findAll(pageable)
            .map(composantTemplateMapper::toDto);
    }


    /**
     * Get one composantTemplate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComposantTemplateDTO> findOne(Long id) {
        log.debug("Request to get ComposantTemplate : {}", id);
        return composantTemplateRepository.findById(id)
            .map(composantTemplateMapper::toDto);
    }

    /**
     * Delete the composantTemplate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComposantTemplate : {}", id);
        composantTemplateRepository.deleteById(id);
    }
}
