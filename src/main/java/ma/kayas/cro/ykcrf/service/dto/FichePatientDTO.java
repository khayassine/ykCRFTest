package ma.kayas.cro.ykcrf.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FichePatient entity.
 */
public class FichePatientDTO implements Serializable {

    private Long id;

    private String codePatient;

    private Instant oneDate;

    private Long centreId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(String codePatient) {
        this.codePatient = codePatient;
    }

    public Instant getOneDate() {
        return oneDate;
    }

    public void setOneDate(Instant oneDate) {
        this.oneDate = oneDate;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FichePatientDTO fichePatientDTO = (FichePatientDTO) o;
        if (fichePatientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fichePatientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FichePatientDTO{" +
            "id=" + getId() +
            ", codePatient='" + getCodePatient() + "'" +
            ", oneDate='" + getOneDate() + "'" +
            ", centre=" + getCentreId() +
            "}";
    }
}
