package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.FormulairePatient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FormulairePatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormulairePatientRepository extends JpaRepository<FormulairePatient, Long> {

}
