package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.RegleValidationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RegleValidation and its DTO RegleValidationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegleValidationMapper extends EntityMapper<RegleValidationDTO, RegleValidation> {



    default RegleValidation fromId(Long id) {
        if (id == null) {
            return null;
        }
        RegleValidation regleValidation = new RegleValidation();
        regleValidation.setId(id);
        return regleValidation;
    }
}
