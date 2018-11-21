package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.Centre;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Centre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {

}
