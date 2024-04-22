package Entities_Controllers.Events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findById(int id);

    @Transactional
    void deleteById(int id);
}
