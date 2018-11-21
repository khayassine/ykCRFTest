package ma.kayas.cro.ykcrf.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RegleValidation.
 */
@Entity
@Table(name = "regle_validation")
public class RegleValidation implements Serializable {

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

    @Column(name = "message")
    private String message;

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

    public RegleValidation code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public RegleValidation titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRegexValidation() {
        return regexValidation;
    }

    public RegleValidation regexValidation(String regexValidation) {
        this.regexValidation = regexValidation;
        return this;
    }

    public void setRegexValidation(String regexValidation) {
        this.regexValidation = regexValidation;
    }

    public String getSigneComparaison() {
        return signeComparaison;
    }

    public RegleValidation signeComparaison(String signeComparaison) {
        this.signeComparaison = signeComparaison;
        return this;
    }

    public void setSigneComparaison(String signeComparaison) {
        this.signeComparaison = signeComparaison;
    }

    public String getMessage() {
        return message;
    }

    public RegleValidation message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
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
        RegleValidation regleValidation = (RegleValidation) o;
        if (regleValidation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), regleValidation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegleValidation{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", regexValidation='" + getRegexValidation() + "'" +
            ", signeComparaison='" + getSigneComparaison() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
