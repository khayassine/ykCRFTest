package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.FichePatientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FichePatient.
 */
public interface FichePatientService {

    /**
     * Save a fichePatient.
     *
     * @param fichePatientDTO the entity to save
     * @return the persisted entity
     */
    FichePatientDTO save(FichePatientDTO fichePatientDTO);

    /**
     * Get all the fichePatients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FichePatientDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fichePatient.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FichePatientDTO> findOne(Long id);

    /**
     * Delete the "id" fichePatient.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
