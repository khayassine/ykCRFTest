package ma.kayas.cro.ykcrf.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CommentaireRequette entity.
 */
public class CommentaireRequetteDTO implements Serializable {

    private Long id;

    private String commentaire;

    private Long requetteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getRequetteId() {
        return requetteId;
    }

    public void setRequetteId(Long requetteId) {
        this.requetteId = requetteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommentaireRequetteDTO commentaireRequetteDTO = (CommentaireRequetteDTO) o;
        if (commentaireRequetteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentaireRequetteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentaireRequetteDTO{" +
            "id=" + getId() +
            ", commentaire='" + getCommentaire() + "'" +
            ", requette=" + getRequetteId() +
            "}";
    }
}
