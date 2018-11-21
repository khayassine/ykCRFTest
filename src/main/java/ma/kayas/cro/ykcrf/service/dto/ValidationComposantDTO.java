package ma.kayas.cro.ykcrf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ma.kayas.cro.ykcrf.domain.enumeration.NiveauValidation;

/**
 * A DTO for the ValidationComposant entity.
 */
public class ValidationComposantDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String titre;

    private String regexValidation;

    private String signeComparaison;

    private String valeurComparaison;

    private String message;

    private NiveauValidation niveauValidation;

    private Long composantTemplateId;

    private Long regleValidationId;

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

    public String getValeurComparaison() {
        return valeurComparaison;
    }

    public void setValeurComparaison(String valeurComparaison) {
        this.valeurComparaison = valeurComparaison;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NiveauValidation getNiveauValidation() {
        return niveauValidation;
    }

    public void setNiveauValidation(NiveauValidation niveauValidation) {
        this.niveauValidation = niveauValidation;
    }

    public Long getComposantTemplateId() {
        return composantTemplateId;
    }

    public void setComposantTemplateId(Long composantTemplateId) {
        this.composantTemplateId = composantTemplateId;
    }

    public Long getRegleValidationId() {
        return regleValidationId;
    }

    public void setRegleValidationId(Long regleValidationId) {
        this.regleValidationId = regleValidationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValidationComposantDTO validationComposantDTO = (ValidationComposantDTO) o;
        if (validationComposantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), validationComposantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ValidationComposantDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", regexValidation='" + getRegexValidation() + "'" +
            ", signeComparaison='" + getSigneComparaison() + "'" +
            ", valeurComparaison='" + getValeurComparaison() + "'" +
            ", message='" + getMessage() + "'" +
            ", niveauValidation='" + getNiveauValidation() + "'" +
            ", composantTemplate=" + getComposantTemplateId() +
            ", regleValidation=" + getRegleValidationId() +
            "}";
    }
}
