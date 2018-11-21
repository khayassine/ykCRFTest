package ma.kayas.cro.ykcrf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RegleValidation entity.
 */
public class RegleValidationDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String titre;

    private String regexValidation;

    private String signeComparaison;

    private String message;

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

    public String getRegexValidation() {
        return regexValidation;
    }

    public void setRegexValidation(String regexValidation) {
        this.regexValidation = regexValidation;
    }

    public String getSigneComparaison() {
        return signeComparaison;
    }

    public void setSigneComparaison(String signeComparaison) {
        this.signeComparaison = signeComparaison;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegleValidationDTO regleValidationDTO = (RegleValidationDTO) o;
        if (regleValidationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), regleValidationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegleValidationDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", regexValidation='" + getRegexValidation() + "'" +
            ", signeComparaison='" + getSigneComparaison() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
