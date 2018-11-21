package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.Etude;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Etude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtudeRepository extends JpaRepository<Etude, Long> {

}
