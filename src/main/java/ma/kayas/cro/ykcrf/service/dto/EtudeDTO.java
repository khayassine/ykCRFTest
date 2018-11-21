package ma.kayas.cro.ykcrf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Etude entity.
 */
public class EtudeDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String titre;

    private String description;

    private String pageHtml;

    private String cssGlobal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageHtml() {
        return pageHtml;
    }

    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }

    public String getCssGlobal() {
        return cssGlobal;
    }

    public void setCssGlobal(String cssGlobal) {
        this.cssGlobal = cssGlobal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtudeDTO etudeDTO = (EtudeDTO) o;
        if (etudeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etudeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtudeDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", description='" + getDescription() + "'" +
            ", pageHtml='" + getPageHtml() + "'" +
            ", cssGlobal='" + getCssGlobal() + "'" +
            "}";
    }
}
