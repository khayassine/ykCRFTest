package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.RequetteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Requette and its DTO RequetteDTO.
 */
@Mapper(componentModel = "spring", uses = {ComposantValeurMapper.class, ValidationComposantMapper.class})
public interface RequetteMapper extends EntityMapper<RequetteDTO, Requette> {

    @Mapping(source = "composantValeur.id", target = "composantValeurId")
    @Mapping(source = "validationComposant.id", target = "validationComposantId")
    RequetteDTO toDto(Requette requette);

    @Mapping(source = "composantValeurId", target = "composantValeur")
    @Mapping(source = "validationComposantId", target = "validationComposant")
    @Mapping(target = "commentaireRequettes", ignore = true)
    Requette toEntity(RequetteDTO requetteDTO);

    default Requette fromId(Long id) {
        if (id == null) {
            return null;
        }
        Requette requette = new Requette();
        requette.setId(id);
        return requette;
    }
}
