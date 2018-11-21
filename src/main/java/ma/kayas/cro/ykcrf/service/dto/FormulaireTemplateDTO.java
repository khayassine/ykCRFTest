package ma.kayas.cro.ykcrf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FormulaireTemplate entity.
 */
public class FormulaireTemplateDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String titre;

    private String version;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FormulaireTemplateDTO formulaireTemplateDTO = (FormulaireTemplateDTO) o;
        if (formulaireTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), formulaireTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FormulaireTemplateDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
