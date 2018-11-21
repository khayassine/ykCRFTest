package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.FormulaireTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FormulaireTemplate and its DTO FormulaireTemplateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FormulaireTemplateMapper extends EntityMapper<FormulaireTemplateDTO, FormulaireTemplate> {


    @Mapping(target = "composantTemplates", ignore = true)
    FormulaireTemplate toEntity(FormulaireTemplateDTO formulaireTemplateDTO);

    default FormulaireTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        FormulaireTemplate formulaireTemplate = new FormulaireTemplate();
        formulaireTemplate.setId(id);
        return formulaireTemplate;
    }
}
