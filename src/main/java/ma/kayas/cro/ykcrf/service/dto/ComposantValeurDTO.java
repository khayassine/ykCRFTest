package ma.kayas.cro.ykcrf.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ma.kayas.cro.ykcrf.domain.enumeration.EtatValeur;

/**
 * A DTO for the ComposantValeur entity.
 */
public class ComposantValeurDTO implements Serializable {

    private Long id;

    private String valeur;

    private EtatValeur etat;

    private Long formulairePatientId;

    private Long composantTemplateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public EtatValeur getEtat() {
        return etat;
    }

    public void setEtat(EtatValeur etat) {
        this.etat = etat;
    }

    public Long getFormulairePatientId() {
        return formulairePatientId;
    }

    public void setFormulairePatientId(Long formulairePatientId) {
        this.formulairePatientId = formulairePatientId;
    }

    public Long getComposantTemplateId() {
        return composantTemplateId;
    }

    public void setComposantTemplateId(Long composantTemplateId) {
        this.composantTemplateId = composantTemplateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComposantValeurDTO composantValeurDTO = (ComposantValeurDTO) o;
        if (composantValeurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), composantValeurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComposantValeurDTO{" +
            "id=" + getId() +
            ", valeur='" + getValeur() + "'" +
            ", etat='" + getEtat() + "'" +
            ", formulairePatient=" + getFormulairePatientId() +
            ", composantTemplate=" + getComposantTemplateId() +
            "}";
    }
}
