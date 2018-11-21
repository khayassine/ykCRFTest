package ma.kayas.cro.ykcrf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ComposantTemplate entity.
 */
public class ComposantTemplateDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String titre;

    @NotNull
    private Integer ordre;

    private String conditionAffichage;

    private String texteDroite;

    private String cssStyle;

    private Long formulaireTemplateId;

    private Long typeComposantId;

    private Long composantTemplateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getConditionAffichage() {
        return conditionAffichage;
    }

    public void setConditionAffichage(String conditionAffichage) {
        this.conditionAffichage = conditionAffichage;
    }

    public String getTexteDroite() {
        return texteDroite;
    }

    public void setTexteDroite(String texteDroite) {
        this.texteDroite = texteDroite;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public Long getFormulaireTemplateId() {
        return formulaireTemplateId;
    }

    public void setFormulaireTemplateId(Long formulaireTemplateId) {
        this.formulaireTemplateId = formulaireTemplateId;
    }

    public Long getTypeComposantId() {
        return typeComposantId;
    }

    public void setTypeComposantId(Long typeComposantId) {
        this.typeComposantId = typeComposantId;
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

        ComposantTemplateDTO composantTemplateDTO = (ComposantTemplateDTO) o;
        if (composantTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), composantTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComposantTemplateDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", ordre=" + getOrdre() +
            ", conditionAffichage='" + getConditionAffichage() + "'" +
            ", texteDroite='" + getTexteDroite() + "'" +
            ", cssStyle='" + getCssStyle() + "'" +
            ", formulaireTemplate=" + getFormulaireTemplateId() +
            ", typeComposant=" + getTypeComposantId() +
            ", composantTemplate=" + getComposantTemplateId() +
            "}";
    }
}
