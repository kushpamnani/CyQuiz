package Entities_Controllers.Classrooms;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Classroom findById(int id);

    void deleteById(int id);

}
