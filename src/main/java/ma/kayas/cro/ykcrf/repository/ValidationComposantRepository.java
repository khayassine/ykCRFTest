package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.ValidationComposant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ValidationComposant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ValidationComposantRepository extends JpaRepository<ValidationComposant, Long> {

}
