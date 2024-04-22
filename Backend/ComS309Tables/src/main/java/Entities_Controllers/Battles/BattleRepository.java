package Entities_Controllers.Battles;

import Entities_Controllers.Admins.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BattleRepository extends JpaRepository<Battle, Long> {
    Battle findById(int id);

    @Transactional
    void deleteById(int id);
}
