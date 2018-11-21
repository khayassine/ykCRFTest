package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.SousRegion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SousRegion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SousRegionRepository extends JpaRepository<SousRegion, Long> {

}
