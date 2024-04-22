package Entities_Controllers.Enemies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dalton Clark
 *
 */
public interface EnemyRepository extends JpaRepository<Enemy, Long> {
    
    Enemy findById(int id);

    @Transactional
    void deleteById(int id);

}
