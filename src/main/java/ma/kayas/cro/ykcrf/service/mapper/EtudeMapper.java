package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.EtudeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Etude and its DTO EtudeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtudeMapper extends EntityMapper<EtudeDTO, Etude> {



    default Etude fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etude etude = new Etude();
        etude.setId(id);
        return etude;
    }
}
