package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import ma.kayas.cro.ykcrf.domain.enumeration.EtatValeur;

/**
 * A ComposantValeur.
 */
@Entity
@Table(name = "composant_valeur")
public class ComposantValeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valeur")
    private String valeur;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatValeur etat;

    @ManyToOne
    @JsonIgnoreProperties("composantValeurs")
    private FormulairePatient formulairePatient;

    @OneToOne    @JoinColumn(unique = true)
    private ComposantTemplate composantTemplate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public ComposantValeur valeur(String valeur) {
        this.valeur = valeur;
        return this;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public EtatValeur getEtat() {
        return etat;
    }

    public ComposantValeur etat(EtatValeur etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(EtatValeur etat) {
        this.etat = etat;
    }

    public FormulairePatient getFormulairePatient() {
        return formulairePatient;
    }

    public ComposantValeur formulairePatient(FormulairePatient formulairePatient) {
        this.formulairePatient = formulairePatient;
        return this;
    }

    public void setFormulairePatient(FormulairePatient formulairePatient) {
        this.formulairePatient = formulairePatient;
    }

    public ComposantTemplate getComposantTemplate() {
        return composantTemplate;
    }

    public ComposantValeur composantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplate = composantTemplate;
        return this;
    }

    public void setComposantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplate = composantTemplate;
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
        ComposantValeur composantValeur = (ComposantValeur) o;
        if (composantValeur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), composantValeur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComposantValeur{" +
            "id=" + getId() +
            ", valeur='" + getValeur() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
