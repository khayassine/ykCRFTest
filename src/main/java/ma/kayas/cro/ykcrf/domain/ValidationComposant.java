package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import ma.kayas.cro.ykcrf.domain.enumeration.NiveauValidation;

/**
 * A ValidationComposant.
 */
@Entity
@Table(name = "validation_composant")
public class ValidationComposant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "regex_validation")
    private String regexValidation;

    @Column(name = "signe_comparaison")
    private String signeComparaison;

    @Column(name = "valeur_comparaison")
    private String valeurComparaison;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau_validation")
    private NiveauValidation niveauValidation;

    @ManyToOne
    @JsonIgnoreProperties("validationComposants")
    private ComposantTemplate composantTemplate;

    @OneToOne    @JoinColumn(unique = true)
    private RegleValidation regleValidation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ValidationComposant code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public ValidationComposant titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRegexValidation() {
        return regexValidation;
    }

    public ValidationComposant regexValidation(String regexValidation) {
        this.regexValidation = regexValidation;
        return this;
    }

    public void setRegexValidation(String regexValidation) {
        this.regexValidation = regexValidation;
    }

    public String getSigneComparaison() {
        return signeComparaison;
    }

    public ValidationComposant signeComparaison(String signeComparaison) {
        this.signeComparaison = signeComparaison;
        return this;
    }

    public void setSigneComparaison(String signeComparaison) {
        this.signeComparaison = signeComparaison;
    }

    public String getValeurComparaison() {
        return valeurComparaison;
    }

    public ValidationComposant valeurComparaison(String valeurComparaison) {
        this.valeurComparaison = valeurComparaison;
        return this;
    }

    public void setValeurComparaison(String valeurComparaison) {
        this.valeurComparaison = valeurComparaison;
    }

    public String getMessage() {
        return message;
    }

    public ValidationComposant message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NiveauValidation getNiveauValidation() {
        return niveauValidation;
    }

    public ValidationComposant niveauValidation(NiveauValidation niveauValidation) {
        this.niveauValidation = niveauValidation;
        return this;
    }

    public void setNiveauValidation(NiveauValidation niveauValidation) {
        this.niveauValidation = niveauValidation;
    }

    public ComposantTemplate getComposantTemplate() {
        return composantTemplate;
    }

    public ValidationComposant composantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplate = composantTemplate;
        return this;
    }

    public void setComposantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplate = composantTemplate;
    }

    public RegleValidation getRegleValidation() {
        return regleValidation;
    }

    public ValidationComposant regleValidation(RegleValidation regleValidation) {
        this.regleValidation = regleValidation;
        return this;
    }

    public void setRegleValidation(RegleValidation regleValidation) {
        this.regleValidation = regleValidation;
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
        ValidationComposant validationComposant = (ValidationComposant) o;
        if (validationComposant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), validationComposant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ValidationComposant{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", regexValidation='" + getRegexValidation() + "'" +
            ", signeComparaison='" + getSigneComparaison() + "'" +
            ", valeurComparaison='" + getValeurComparaison() + "'" +
            ", message='" + getMessage() + "'" +
            ", niveauValidation='" + getNiveauValidation() + "'" +
            "}";
    }
}
