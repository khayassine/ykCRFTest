package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.CentreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Centre and its DTO CentreDTO.
 */
@Mapper(componentModel = "spring", uses = {VilleMapper.class, RegionMapper.class, SousRegionMapper.class})
public interface CentreMapper extends EntityMapper<CentreDTO, Centre> {

    @Mapping(source = "ville.id", target = "villeId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "sousRegion.id", target = "sousRegionId")
    CentreDTO toDto(Centre centre);

    @Mapping(source = "villeId", target = "ville")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "sousRegionId", target = "sousRegion")
    Centre toEntity(CentreDTO centreDTO);

    default Centre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Centre centre = new Centre();
        centre.setId(id);
        return centre;
    }
}
