package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import ma.kayas.cro.ykcrf.domain.enumeration.EtatFormulaire;

/**
 * A FormulairePatient.
 */
@Entity
@Table(name = "formulaire_patient")
public class FormulairePatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatFormulaire etat;

    @ManyToOne
    @JsonIgnoreProperties("formulairePatients")
    private FichePatient fichePatient;

    @OneToOne    @JoinColumn(unique = true)
    private FormulaireTemplate formulaireTemplate;

    @OneToMany(mappedBy = "formulairePatient")
    private Set<ComposantValeur> composantValeurs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatFormulaire getEtat() {
        return etat;
    }

    public FormulairePatient etat(EtatFormulaire etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(EtatFormulaire etat) {
        this.etat = etat;
    }

    public FichePatient getFichePatient() {
        return fichePatient;
    }

    public FormulairePatient fichePatient(FichePatient fichePatient) {
        this.fichePatient = fichePatient;
        return this;
    }

    public void setFichePatient(FichePatient fichePatient) {
        this.fichePatient = fichePatient;
    }

    public FormulaireTemplate getFormulaireTemplate() {
        return formulaireTemplate;
    }

    public FormulairePatient formulaireTemplate(FormulaireTemplate formulaireTemplate) {
        this.formulaireTemplate = formulaireTemplate;
        return this;
    }

    public void setFormulaireTemplate(FormulaireTemplate formulaireTemplate) {
        this.formulaireTemplate = formulaireTemplate;
    }

    public Set<ComposantValeur> getComposantValeurs() {
        return composantValeurs;
    }

    public FormulairePatient composantValeurs(Set<ComposantValeur> composantValeurs) {
        this.composantValeurs = composantValeurs;
        return this;
    }

    public FormulairePatient addComposantValeur(ComposantValeur composantValeur) {
        this.composantValeurs.add(composantValeur);
        composantValeur.setFormulairePatient(this);
        return this;
    }

    public FormulairePatient removeComposantValeur(ComposantValeur composantValeur) {
        this.composantValeurs.remove(composantValeur);
        composantValeur.setFormulairePatient(null);
        return this;
    }

    public void setComposantValeurs(Set<ComposantValeur> composantValeurs) {
        this.composantValeurs = composantValeurs;
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
        FormulairePatient formulairePatient = (FormulairePatient) o;
        if (formulairePatient.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), formulairePatient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FormulairePatient{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
