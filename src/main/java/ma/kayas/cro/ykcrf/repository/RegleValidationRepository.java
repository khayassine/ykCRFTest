package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.RegleValidation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RegleValidation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegleValidationRepository extends JpaRepository<RegleValidation, Long> {

}
