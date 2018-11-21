package ma.kayas.cro.ykcrf.service;

import ma.kayas.cro.ykcrf.service.dto.FormulaireTemplateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FormulaireTemplate.
 */
public interface FormulaireTemplateService {

    /**
     * Save a formulaireTemplate.
     *
     * @param formulaireTemplateDTO the entity to save
     * @return the persisted entity
     */
    FormulaireTemplateDTO save(FormulaireTemplateDTO formulaireTemplateDTO);

    /**
     * Get all the formulaireTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FormulaireTemplateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" formulaireTemplate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FormulaireTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" formulaireTemplate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
