package Entities_Controllers.Teams;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // Additional custom database queries can be defined here if necessary
}
