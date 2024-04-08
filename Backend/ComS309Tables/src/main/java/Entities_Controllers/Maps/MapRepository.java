package Entities_Controllers.Maps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dalton Clark
 *
 */
public interface MapRepository extends JpaRepository<Map, Long> {

    Map findById(int id);

    @Transactional
    void deleteById(int id);

}
