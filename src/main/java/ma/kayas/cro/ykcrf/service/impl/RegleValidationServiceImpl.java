package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.RegleValidationService;
import ma.kayas.cro.ykcrf.domain.RegleValidation;
import ma.kayas.cro.ykcrf.repository.RegleValidationRepository;
import ma.kayas.cro.ykcrf.service.dto.RegleValidationDTO;
import ma.kayas.cro.ykcrf.service.mapper.RegleValidationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RegleValidation.
 */
@Service
@Transactional
public class RegleValidationServiceImpl implements RegleValidationService {

    private final Logger log = LoggerFactory.getLogger(RegleValidationServiceImpl.class);

    private final RegleValidationRepository regleValidationRepository;

    private final RegleValidationMapper regleValidationMapper;

    public RegleValidationServiceImpl(RegleValidationRepository regleValidationRepository, RegleValidationMapper regleValidationMapper) {
        this.regleValidationRepository = regleValidationRepository;
        this.regleValidationMapper = regleValidationMapper;
    }

    /**
     * Save a regleValidation.
     *
     * @param regleValidationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RegleValidationDTO save(RegleValidationDTO regleValidationDTO) {
        log.debug("Request to save RegleValidation : {}", regleValidationDTO);

        RegleValidation regleValidation = regleValidationMapper.toEntity(regleValidationDTO);
        regleValidation = regleValidationRepository.save(regleValidation);
        return regleValidationMapper.toDto(regleValidation);
    }

    /**
     * Get all the regleValidations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RegleValidationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RegleValidations");
        return regleValidationRepository.findAll(pageable)
            .map(regleValidationMapper::toDto);
    }


    /**
     * Get one regleValidation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RegleValidationDTO> findOne(Long id) {
        log.debug("Request to get RegleValidation : {}", id);
        return regleValidationRepository.findById(id)
            .map(regleValidationMapper::toDto);
    }

    /**
     * Delete the regleValidation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RegleValidation : {}", id);
        regleValidationRepository.deleteById(id);
    }
}
