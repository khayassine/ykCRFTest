package ma.kayas.cro.ykcrf.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ma.kayas.cro.ykcrf.domain.enumeration.EtatFormulaire;

/**
 * A DTO for the FormulairePatient entity.
 */
public class FormulairePatientDTO implements Serializable {

    private Long id;

    private EtatFormulaire etat;

    private Long fichePatientId;

    private Long formulaireTemplateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatFormulaire getEtat() {
        return etat;
    }

    public void setEtat(EtatFormulaire etat) {
        this.etat = etat;
    }

    public Long getFichePatientId() {
        return fichePatientId;
    }

    public void setFichePatientId(Long fichePatientId) {
        this.fichePatientId = fichePatientId;
    }

    public Long getFormulaireTemplateId() {
        return formulaireTemplateId;
    }

    public void setFormulaireTemplateId(Long formulaireTemplateId) {
        this.formulaireTemplateId = formulaireTemplateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FormulairePatientDTO formulairePatientDTO = (FormulairePatientDTO) o;
        if (formulairePatientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), formulairePatientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FormulairePatientDTO{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            ", fichePatient=" + getFichePatientId() +
            ", formulaireTemplate=" + getFormulaireTemplateId() +
            "}";
    }
}
