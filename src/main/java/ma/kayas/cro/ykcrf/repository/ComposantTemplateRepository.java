package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.ComposantTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComposantTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComposantTemplateRepository extends JpaRepository<ComposantTemplate, Long> {

}
