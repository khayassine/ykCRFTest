package ma.kayas.cro.ykcrf.service.impl;

import ma.kayas.cro.ykcrf.service.FormulaireTemplateService;
import ma.kayas.cro.ykcrf.domain.FormulaireTemplate;
import ma.kayas.cro.ykcrf.repository.FormulaireTemplateRepository;
import ma.kayas.cro.ykcrf.service.dto.FormulaireTemplateDTO;
import ma.kayas.cro.ykcrf.service.mapper.FormulaireTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing FormulaireTemplate.
 */
@Service
@Transactional
public class FormulaireTemplateServiceImpl implements FormulaireTemplateService {

    private final Logger log = LoggerFactory.getLogger(FormulaireTemplateServiceImpl.class);

    private final FormulaireTemplateRepository formulaireTemplateRepository;

    private final FormulaireTemplateMapper formulaireTemplateMapper;

    public FormulaireTemplateServiceImpl(FormulaireTemplateRepository formulaireTemplateRepository, FormulaireTemplateMapper formulaireTemplateMapper) {
        this.formulaireTemplateRepository = formulaireTemplateRepository;
        this.formulaireTemplateMapper = formulaireTemplateMapper;
    }

    /**
     * Save a formulaireTemplate.
     *
     * @param formulaireTemplateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FormulaireTemplateDTO save(FormulaireTemplateDTO formulaireTemplateDTO) {
        log.debug("Request to save FormulaireTemplate : {}", formulaireTemplateDTO);

        FormulaireTemplate formulaireTemplate = formulaireTemplateMapper.toEntity(formulaireTemplateDTO);
        formulaireTemplate = formulaireTemplateRepository.save(formulaireTemplate);
        return formulaireTemplateMapper.toDto(formulaireTemplate);
    }

    /**
     * Get all the formulaireTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FormulaireTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FormulaireTemplates");
        return formulaireTemplateRepository.findAll(pageable)
            .map(formulaireTemplateMapper::toDto);
    }


    /**
     * Get one formulaireTemplate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormulaireTemplateDTO> findOne(Long id) {
        log.debug("Request to get FormulaireTemplate : {}", id);
        return formulaireTemplateRepository.findById(id)
            .map(formulaireTemplateMapper::toDto);
    }

    /**
     * Delete the formulaireTemplate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormulaireTemplate : {}", id);
        formulaireTemplateRepository.deleteById(id);
    }
}
