package ma.kayas.cro.ykcrf.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SousRegion entity.
 */
public class SousRegionDTO implements Serializable {

    private Long id;

    private String titre;

    private Long regionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SousRegionDTO sousRegionDTO = (SousRegionDTO) o;
        if (sousRegionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sousRegionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SousRegionDTO{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", region=" + getRegionId() +
            "}";
    }
}
