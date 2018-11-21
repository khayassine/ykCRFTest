package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.TypeComposantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeComposant and its DTO TypeComposantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeComposantMapper extends EntityMapper<TypeComposantDTO, TypeComposant> {



    default TypeComposant fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeComposant typeComposant = new TypeComposant();
        typeComposant.setId(id);
        return typeComposant;
    }
}
