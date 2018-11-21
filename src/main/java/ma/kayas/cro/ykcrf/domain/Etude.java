package ma.kayas.cro.ykcrf.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Etude.
 */
@Entity
@Table(name = "etude")
public class Etude implements Serializable {

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

    @Column(name = "description")
    private String description;

    @Column(name = "page_html")
    private String pageHtml;

    @Column(name = "css_global")
    private String cssGlobal;

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

    public Etude code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public Etude titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public Etude description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageHtml() {
        return pageHtml;
    }

    public Etude pageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
        return this;
    }

    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }

    public String getCssGlobal() {
        return cssGlobal;
    }

    public Etude cssGlobal(String cssGlobal) {
        this.cssGlobal = cssGlobal;
        return this;
    }

    public void setCssGlobal(String cssGlobal) {
        this.cssGlobal = cssGlobal;
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
        Etude etude = (Etude) o;
        if (etude.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etude.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Etude{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", description='" + getDescription() + "'" +
            ", pageHtml='" + getPageHtml() + "'" +
            ", cssGlobal='" + getCssGlobal() + "'" +
            "}";
    }
}
