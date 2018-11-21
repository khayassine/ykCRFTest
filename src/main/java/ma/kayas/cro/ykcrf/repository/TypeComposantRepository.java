package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.TypeComposant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeComposant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeComposantRepository extends JpaRepository<TypeComposant, Long> {

}
