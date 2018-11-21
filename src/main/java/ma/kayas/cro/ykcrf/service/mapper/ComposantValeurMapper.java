package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.ComposantValeurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ComposantValeur and its DTO ComposantValeurDTO.
 */
@Mapper(componentModel = "spring", uses = {FormulairePatientMapper.class, ComposantTemplateMapper.class})
public interface ComposantValeurMapper extends EntityMapper<ComposantValeurDTO, ComposantValeur> {

    @Mapping(source = "formulairePatient.id", target = "formulairePatientId")
    @Mapping(source = "composantTemplate.id", target = "composantTemplateId")
    ComposantValeurDTO toDto(ComposantValeur composantValeur);

    @Mapping(source = "formulairePatientId", target = "formulairePatient")
    @Mapping(source = "composantTemplateId", target = "composantTemplate")
    ComposantValeur toEntity(ComposantValeurDTO composantValeurDTO);

    default ComposantValeur fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComposantValeur composantValeur = new ComposantValeur();
        composantValeur.setId(id);
        return composantValeur;
    }
}
