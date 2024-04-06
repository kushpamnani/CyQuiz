package Entities_Controllers.Classrooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dalton Clark
 *
 */
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Classroom findById(int id);

    @Transactional
    void deleteById(int id);

    Classroom findByCode(int code);
}
