package ma.kayas.cro.ykcrf.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Centre.
 */
@Entity
@Table(name = "centre")
public class Centre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "titre")
    private String titre;

    @Column(name = "complement")
    private String complement;

    @OneToOne    @JoinColumn(unique = true)
    private Ville ville;

    @OneToOne    @JoinColumn(unique = true)
    private Region region;

    @OneToOne    @JoinColumn(unique = true)
    private SousRegion sousRegion;

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

    public Centre code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public Centre titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getComplement() {
        return complement;
    }

    public Centre complement(String complement) {
        this.complement = complement;
        return this;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Ville getVille() {
        return ville;
    }

    public Centre ville(Ville ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Region getRegion() {
        return region;
    }

    public Centre region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public SousRegion getSousRegion() {
        return sousRegion;
    }

    public Centre sousRegion(SousRegion sousRegion) {
        this.sousRegion = sousRegion;
        return this;
    }

    public void setSousRegion(SousRegion sousRegion) {
        this.sousRegion = sousRegion;
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
        Centre centre = (Centre) o;
        if (centre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), centre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Centre{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", complement='" + getComplement() + "'" +
            "}";
    }
}
