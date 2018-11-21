package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Region.
 */
@Entity
@Table(name = "region")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
    private String titre;

    @ManyToOne
    @JsonIgnoreProperties("regions")
    private Ville ville;

    @OneToMany(mappedBy = "region")
    private Set<SousRegion> sousRegions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Region titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Ville getVille() {
        return ville;
    }

    public Region ville(Ville ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Set<SousRegion> getSousRegions() {
        return sousRegions;
    }

    public Region sousRegions(Set<SousRegion> sousRegions) {
        this.sousRegions = sousRegions;
        return this;
    }

    public Region addSousRegion(SousRegion sousRegion) {
        this.sousRegions.add(sousRegion);
        sousRegion.setRegion(this);
        return this;
    }

    public Region removeSousRegion(SousRegion sousRegion) {
        this.sousRegions.remove(sousRegion);
        sousRegion.setRegion(null);
        return this;
    }

    public void setSousRegions(Set<SousRegion> sousRegions) {
        this.sousRegions = sousRegions;
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
        Region region = (Region) o;
        if (region.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), region.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Region{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            "}";
    }
}
