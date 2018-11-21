package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FormulaireTemplate.
 */
@Entity
@Table(name = "formulaire_template")
public class FormulaireTemplate implements Serializable {

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

    @Column(name = "version")
    private String version;

    @OneToMany(mappedBy = "formulaireTemplate")
    private Set<ComposantTemplate> composantTemplates = new HashSet<>();
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

    public FormulaireTemplate code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public FormulaireTemplate titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVersion() {
        return version;
    }

    public FormulaireTemplate version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<ComposantTemplate> getComposantTemplates() {
        return composantTemplates;
    }

    public FormulaireTemplate composantTemplates(Set<ComposantTemplate> composantTemplates) {
        this.composantTemplates = composantTemplates;
        return this;
    }

    public FormulaireTemplate addComposantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplates.add(composantTemplate);
        composantTemplate.setFormulaireTemplate(this);
        return this;
    }

    public FormulaireTemplate removeComposantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplates.remove(composantTemplate);
        composantTemplate.setFormulaireTemplate(null);
        return this;
    }

    public void setComposantTemplates(Set<ComposantTemplate> composantTemplates) {
        this.composantTemplates = composantTemplates;
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
        FormulaireTemplate formulaireTemplate = (FormulaireTemplate) o;
        if (formulaireTemplate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), formulaireTemplate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FormulaireTemplate{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
