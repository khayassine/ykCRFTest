package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.FormulairePatientService;
import ma.kayas.cro.ykcrf.domain.FormulairePatient;
import ma.kayas.cro.ykcrf.repository.FormulairePatientRepository;
import ma.kayas.cro.ykcrf.service.dto.FormulairePatientDTO;
import ma.kayas.cro.ykcrf.service.mapper.FormulairePatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing FormulairePatient.
 */
@Service
@Transactional
public class FormulairePatientServiceImpl implements FormulairePatientService {

    private final Logger log = LoggerFactory.getLogger(FormulairePatientServiceImpl.class);

    private final FormulairePatientRepository formulairePatientRepository;

    private final FormulairePatientMapper formulairePatientMapper;

    public FormulairePatientServiceImpl(FormulairePatientRepository formulairePatientRepository, FormulairePatientMapper formulairePatientMapper) {
        this.formulairePatientRepository = formulairePatientRepository;
        this.formulairePatientMapper = formulairePatientMapper;
    }

    /**
     * Save a formulairePatient.
     *
     * @param formulairePatientDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FormulairePatientDTO save(FormulairePatientDTO formulairePatientDTO) {
        log.debug("Request to save FormulairePatient : {}", formulairePatientDTO);

        FormulairePatient formulairePatient = formulairePatientMapper.toEntity(formulairePatientDTO);
        formulairePatient = formulairePatientRepository.save(formulairePatient);
        return formulairePatientMapper.toDto(formulairePatient);
    }

    /**
     * Get all the formulairePatients.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormulairePatientDTO> findAll() {
        log.debug("Request to get all FormulairePatients");
        return formulairePatientRepository.findAll().stream()
            .map(formulairePatientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one formulairePatient by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormulairePatientDTO> findOne(Long id) {
        log.debug("Request to get FormulairePatient : {}", id);
        return formulairePatientRepository.findById(id)
            .map(formulairePatientMapper::toDto);
    }

    /**
     * Delete the formulairePatient by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormulairePatient : {}", id);
        formulairePatientRepository.deleteById(id);
    }
}
