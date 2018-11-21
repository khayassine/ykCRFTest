package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.FichePatientService;
import ma.kayas.cro.ykcrf.domain.FichePatient;
import ma.kayas.cro.ykcrf.repository.FichePatientRepository;
import ma.kayas.cro.ykcrf.service.dto.FichePatientDTO;
import ma.kayas.cro.ykcrf.service.mapper.FichePatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing FichePatient.
 */
@Service
@Transactional
public class FichePatientServiceImpl implements FichePatientService {

    private final Logger log = LoggerFactory.getLogger(FichePatientServiceImpl.class);

    private final FichePatientRepository fichePatientRepository;

    private final FichePatientMapper fichePatientMapper;

    public FichePatientServiceImpl(FichePatientRepository fichePatientRepository, FichePatientMapper fichePatientMapper) {
        this.fichePatientRepository = fichePatientRepository;
        this.fichePatientMapper = fichePatientMapper;
    }

    /**
     * Save a fichePatient.
     *
     * @param fichePatientDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FichePatientDTO save(FichePatientDTO fichePatientDTO) {
        log.debug("Request to save FichePatient : {}", fichePatientDTO);

        FichePatient fichePatient = fichePatientMapper.toEntity(fichePatientDTO);
        fichePatient = fichePatientRepository.save(fichePatient);
        return fichePatientMapper.toDto(fichePatient);
    }

    /**
     * Get all the fichePatients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FichePatientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FichePatients");
        return fichePatientRepository.findAll(pageable)
            .map(fichePatientMapper::toDto);
    }


    /**
     * Get one fichePatient by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FichePatientDTO> findOne(Long id) {
        log.debug("Request to get FichePatient : {}", id);
        return fichePatientRepository.findById(id)
            .map(fichePatientMapper::toDto);
    }

    /**
     * Delete the fichePatient by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FichePatient : {}", id);
        fichePatientRepository.deleteById(id);
    }
}
