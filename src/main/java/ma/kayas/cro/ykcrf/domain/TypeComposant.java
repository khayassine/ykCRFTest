package ma.kayas.cro.ykcrf.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TypeComposant.
 */
@Entity
@Table(name = "type_composant")
public class TypeComposant implements Serializable {

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

    @Column(name = "css_style")
    private String cssStyle;

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

    public TypeComposant code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public TypeComposant titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public TypeComposant cssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
        return this;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
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
        TypeComposant typeComposant = (TypeComposant) o;
        if (typeComposant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeComposant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeComposant{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", cssStyle='" + getCssStyle() + "'" +
            "}";
    }
}
