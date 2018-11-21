package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.ValidationComposantService;
import ma.kayas.cro.ykcrf.domain.ValidationComposant;
import ma.kayas.cro.ykcrf.repository.ValidationComposantRepository;
import ma.kayas.cro.ykcrf.service.dto.ValidationComposantDTO;
import ma.kayas.cro.ykcrf.service.mapper.ValidationComposantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ValidationComposant.
 */
@Service
@Transactional
public class ValidationComposantServiceImpl implements ValidationComposantService {

    private final Logger log = LoggerFactory.getLogger(ValidationComposantServiceImpl.class);

    private final ValidationComposantRepository validationComposantRepository;

    private final ValidationComposantMapper validationComposantMapper;

    public ValidationComposantServiceImpl(ValidationComposantRepository validationComposantRepository, ValidationComposantMapper validationComposantMapper) {
        this.validationComposantRepository = validationComposantRepository;
        this.validationComposantMapper = validationComposantMapper;
    }

    /**
     * Save a validationComposant.
     *
     * @param validationComposantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ValidationComposantDTO save(ValidationComposantDTO validationComposantDTO) {
        log.debug("Request to save ValidationComposant : {}", validationComposantDTO);

        ValidationComposant validationComposant = validationComposantMapper.toEntity(validationComposantDTO);
        validationComposant = validationComposantRepository.save(validationComposant);
        return validationComposantMapper.toDto(validationComposant);
    }

    /**
     * Get all the validationComposants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ValidationComposantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ValidationComposants");
        return validationComposantRepository.findAll(pageable)
            .map(validationComposantMapper::toDto);
    }


    /**
     * Get one validationComposant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ValidationComposantDTO> findOne(Long id) {
        log.debug("Request to get ValidationComposant : {}", id);
        return validationComposantRepository.findById(id)
            .map(validationComposantMapper::toDto);
    }

    /**
     * Delete the validationComposant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ValidationComposant : {}", id);
        validationComposantRepository.deleteById(id);
    }
}
