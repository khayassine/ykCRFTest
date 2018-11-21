package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.ComposantTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ComposantTemplate and its DTO ComposantTemplateDTO.
 */
@Mapper(componentModel = "spring", uses = {FormulaireTemplateMapper.class, TypeComposantMapper.class})
public interface ComposantTemplateMapper extends EntityMapper<ComposantTemplateDTO, ComposantTemplate> {

    @Mapping(source = "formulaireTemplate.id", target = "formulaireTemplateId")
    @Mapping(source = "typeComposant.id", target = "typeComposantId")
    @Mapping(source = "composantTemplate.id", target = "composantTemplateId")
    ComposantTemplateDTO toDto(ComposantTemplate composantTemplate);

    @Mapping(source = "formulaireTemplateId", target = "formulaireTemplate")
    @Mapping(source = "typeComposantId", target = "typeComposant")
    @Mapping(target = "validationComposants", ignore = true)
    @Mapping(source = "composantTemplateId", target = "composantTemplate")
    @Mapping(target = "sousComposants", ignore = true)
    ComposantTemplate toEntity(ComposantTemplateDTO composantTemplateDTO);

    default ComposantTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComposantTemplate composantTemplate = new ComposantTemplate();
        composantTemplate.setId(id);
        return composantTemplate;
    }
}
