package ma.kayas.cro.ykcrf.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Centre entity.
 */
public class CentreDTO implements Serializable {

    private Long id;

    private String code;

    private String titre;

    private String complement;

    private Long villeId;

    private Long regionId;

    private Long sousRegionId;

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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Long getVilleId() {
        return villeId;
    }

    public void setVilleId(Long villeId) {
        this.villeId = villeId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getSousRegionId() {
        return sousRegionId;
    }

    public void setSousRegionId(Long sousRegionId) {
        this.sousRegionId = sousRegionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CentreDTO centreDTO = (CentreDTO) o;
        if (centreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), centreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CentreDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", titre='" + getTitre() + "'" +
            ", complement='" + getComplement() + "'" +
            ", ville=" + getVilleId() +
            ", region=" + getRegionId() +
            ", sousRegion=" + getSousRegionId() +
            "}";
    }
}
