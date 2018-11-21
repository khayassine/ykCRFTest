package ma.kayas.cro.ykcrf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Ville.
 */
@Entity
@Table(name = "ville")
public class Ville implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
    private String titre;

    @OneToMany(mappedBy = "ville")
    private Set<Region> regions = new HashSet<>();
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

    public Ville titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public Ville regions(Set<Region> regions) {
        this.regions = regions;
        return this;
    }

    public Ville addRegion(Region region) {
        this.regions.add(region);
        region.setVille(this);
        return this;
    }

    public Ville removeRegion(Region region) {
        this.regions.remove(region);
        region.setVille(null);
        return this;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
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
        Ville ville = (Ville) o;
        if (ville.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ville.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ville{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            "}";
    }
}
