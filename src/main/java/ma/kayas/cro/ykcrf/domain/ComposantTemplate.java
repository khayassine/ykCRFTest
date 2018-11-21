package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ComposantTemplate.
 */
@Entity
@Table(name = "composant_template")
public class ComposantTemplate implements Serializable {

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

    @NotNull
    @Column(name = "ordre", nullable = false)
    private Integer ordre;

    @Column(name = "condition_affichage")
    private String conditionAffichage;

    @Column(name = "texte_droite")
    private String texteDroite;

    @Column(name = "css_style")
    private String cssStyle;

    @ManyToOne
    @JsonIgnoreProperties("composantTemplates")
    private FormulaireTemplate formulaireTemplate;

    @OneToOne    @JoinColumn(unique = true)
    private TypeComposant typeComposant;

    @OneToMany(mappedBy = "composantTemplate")
    private Set<ValidationComposant> validationComposants = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("sousComposants")
    private ComposantTemplate composantTemplate;

    @OneToMany(mappedBy = "composantTemplate")
    private Set<ComposantTemplate> sousComposants = new HashSet<>();
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

    public ComposantTemplate code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public ComposantTemplate titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public ComposantTemplate ordre(Integer ordre) {
        this.ordre = ordre;
        return this;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getConditionAffichage() {
        return conditionAffichage;
    }

    public ComposantTemplate conditionAffichage(String conditionAffichage) {
        this.conditionAffichage = conditionAffichage;
        return this;
    }

    public void setConditionAffichage(String conditionAffichage) {
        this.conditionAffichage = conditionAffichage;
    }

    public String getTexteDroite() {
        return texteDroite;
    }

    public ComposantTemplate texteDroite(String texteDroite) {
        this.texteDroite = texteDroite;
        return this;
    }

    public void setTexteDroite(String texteDroite) {
        this.texteDroite = texteDroite;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public ComposantTemplate cssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
        return this;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public FormulaireTemplate getFormulaireTemplate() {
        return formulaireTemplate;
    }

    public ComposantTemplate formulaireTemplate(FormulaireTemplate formulaireTemplate) {
        this.formulaireTemplate = formulaireTemplate;
        return this;
    }

    public void setFormulaireTemplate(FormulaireTemplate formulaireTemplate) {
        this.formulaireTemplate = formulaireTemplate;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public ComposantTemplate typeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
        return this;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public Set<ValidationComposant> getValidationComposants() {
        return validationComposants;
    }

    public ComposantTemplate validationComposants(Set<ValidationComposant> validationComposants) {
        this.validationComposants = validationComposants;
        return this;
    }

    public ComposantTemplate addValidationComposant(ValidationComposant validationComposant) {
        this.validationComposants.add(validationComposant);
        validationComposant.setComposantTemplate(this);
        return this;
    }

    public ComposantTemplate removeValidationComposant(ValidationComposant validationComposant) {
        this.validationComposants.remove(validationComposant);
        validationComposant.setComposantTemplate(null);
        return this;
    }

    public void setValidationComposants(Set<ValidationComposant> validationComposants) {
        this.validationComposants = validationComposants;
    }

    public ComposantTemplate getComposantTemplate() {
        return composantTemplate;
    }

    public ComposantTemplate composantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplate = composantTemplate;
        return this;
    }

    public void setComposantTemplate(ComposantTemplate composantTemplate) {
        this.composantTemplate = composantTemplate;
    }

    public Set<ComposantTemplate> getSousComposants() {
        return sousComposants;
    }

    public ComposantTemplate sousComposants(Set<ComposantTemplate> composantTemplates) {
        this.sousComposants = composantTemplates;
        return this;
    }

    public ComposantTemplate addSousComposant(ComposantTemplate composantTemplate) {
        this.sousComposants.add(composantTemplate);
        composantTemplate.setComposantTemplate(this);
        return this;
    }

    public ComposantTemplate removeSousComposant(ComposantTemplate composantTemplate) {
        this.sousComposants.remove(composantTemplate);
        composantTemplate.setComposantTemplate(null);
        return this;
    }

    public void setSousComposants(Set<ComposantTemplate> composantTemplates) {
        this.sousComposants = composantTemplates;
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
        ComposantTemplate composantTemplate = (ComposantTemplate) o;
        if (composantTemplate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), composantTemplate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComposantTemplate{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", ordre=" + getOrdre() +
            ", conditionAffichage='" + getConditionAffichage() + "'" +
            ", texteDroite='" + getTexteDroite() + "'" +
            ", cssStyle='" + getCssStyle() + "'" +
            "}";
    }
}
