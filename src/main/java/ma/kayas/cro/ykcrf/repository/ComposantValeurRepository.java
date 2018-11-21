package ma.kayas.cro.ykcrf.repository;

import ma.kayas.cro.ykcrf.domain.ComposantValeur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComposantValeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComposantValeurRepository extends JpaRepository<ComposantValeur, Long> {

}
