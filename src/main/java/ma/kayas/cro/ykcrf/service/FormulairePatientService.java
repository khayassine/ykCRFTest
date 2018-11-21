package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.FormulairePatientDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FormulairePatient.
 */
public interface FormulairePatientService {

    /**
     * Save a formulairePatient.
     *
     * @param formulairePatientDTO the entity to save
     * @return the persisted entity
     */
    FormulairePatientDTO save(FormulairePatientDTO formulairePatientDTO);

    /**
     * Get all the formulairePatients.
     *
     * @return the list of entities
     */
    List<FormulairePatientDTO> findAll();


    /**
     * Get the "id" formulairePatient.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FormulairePatientDTO> findOne(Long id);

    /**
     * Delete the "id" formulairePatient.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
