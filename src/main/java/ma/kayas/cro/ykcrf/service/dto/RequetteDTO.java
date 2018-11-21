package ma.kayas.cro.ykcrf.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ma.kayas.cro.ykcrf.domain.enumeration.EtatRequette;

/**
 * A DTO for the Requette entity.
 */
public class RequetteDTO implements Serializable {

    private Long id;

    private EtatRequette etat;

    private Long composantValeurId;

    private Long validationComposantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatRequette getEtat() {
        return etat;
    }

    public void setEtat(EtatRequette etat) {
        this.etat = etat;
    }

    public Long getComposantValeurId() {
        return composantValeurId;
    }

    public void setComposantValeurId(Long composantValeurId) {
        this.composantValeurId = composantValeurId;
    }

    public Long getValidationComposantId() {
        return validationComposantId;
    }

    public void setValidationComposantId(Long validationComposantId) {
        this.validationComposantId = validationComposantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequetteDTO requetteDTO = (RequetteDTO) o;
        if (requetteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requetteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequetteDTO{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            ", composantValeur=" + getComposantValeurId() +
            ", validationComposant=" + getValidationComposantId() +
            "}";
    }
}
