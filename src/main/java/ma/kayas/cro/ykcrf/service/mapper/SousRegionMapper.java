package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.SousRegionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SousRegion and its DTO SousRegionDTO.
 */
@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface SousRegionMapper extends EntityMapper<SousRegionDTO, SousRegion> {

    @Mapping(source = "region.id", target = "regionId")
    SousRegionDTO toDto(SousRegion sousRegion);

    @Mapping(source = "regionId", target = "region")
    SousRegion toEntity(SousRegionDTO sousRegionDTO);

    default SousRegion fromId(Long id) {
        if (id == null) {
            return null;
        }
        SousRegion sousRegion = new SousRegion();
        sousRegion.setId(id);
        return sousRegion;
    }
}
