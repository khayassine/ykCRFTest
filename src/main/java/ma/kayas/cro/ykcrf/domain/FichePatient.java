package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FichePatient.
 */
@Entity
@Table(name = "fiche_patient")
public class FichePatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_patient")
    private String codePatient;

    @Column(name = "one_date")
    private Instant oneDate;

    @OneToOne    @JoinColumn(unique = true)
    private Centre centre;

    @OneToMany(mappedBy = "fichePatient")
    private Set<FormulairePatient> formulairePatients = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public FichePatient codePatient(String codePatient) {
        this.codePatient = codePatient;
        return this;
    }

    public void setCodePatient(String codePatient) {
        this.codePatient = codePatient;
    }

    public Instant getOneDate() {
        return oneDate;
    }

    public FichePatient oneDate(Instant oneDate) {
        this.oneDate = oneDate;
        return this;
    }

    public void setOneDate(Instant oneDate) {
        this.oneDate = oneDate;
    }

    public Centre getCentre() {
        return centre;
    }

    public FichePatient centre(Centre centre) {
        this.centre = centre;
        return this;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Set<FormulairePatient> getFormulairePatients() {
        return formulairePatients;
    }

    public FichePatient formulairePatients(Set<FormulairePatient> formulairePatients) {
        this.formulairePatients = formulairePatients;
        return this;
    }

    public FichePatient addFormulairePatient(FormulairePatient formulairePatient) {
        this.formulairePatients.add(formulairePatient);
        formulairePatient.setFichePatient(this);
        return this;
    }

    public FichePatient removeFormulairePatient(FormulairePatient formulairePatient) {
        this.formulairePatients.remove(formulairePatient);
        formulairePatient.setFichePatient(null);
        return this;
    }

    public void setFormulairePatients(Set<FormulairePatient> formulairePatients) {
        this.formulairePatients = formulairePatients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FichePatient fichePatient = (FichePatient) o;
        if (fichePatient.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fichePatient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FichePatient{" +
            "id=" + getId() +
            ", codePatient='" + getCodePatient() + "'" +
            ", oneDate='" + getOneDate() + "'" +
            "}";
    }
}
