package Entities_Controllers.Leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Kush Pamnani
 * 
 */

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
}
