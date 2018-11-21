package ma.kayas.cro.ykcrf.service.mapper;

import ma.kayas.cro.ykcrf.domain.*;
import ma.kayas.cro.ykcrf.service.dto.CommentaireRequetteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CommentaireRequette and its DTO CommentaireRequetteDTO.
 */
@Mapper(componentModel = "spring", uses = {RequetteMapper.class})
public interface CommentaireRequetteMapper extends EntityMapper<CommentaireRequetteDTO, CommentaireRequette> {

    @Mapping(source = "requette.id", target = "requetteId")
    CommentaireRequetteDTO toDto(CommentaireRequette commentaireRequette);

    @Mapping(source = "requetteId", target = "requette")
    CommentaireRequette toEntity(CommentaireRequetteDTO commentaireRequetteDTO);

    default CommentaireRequette fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommentaireRequette commentaireRequette = new CommentaireRequette();
        commentaireRequette.setId(id);
        return commentaireRequette;
    }
}
