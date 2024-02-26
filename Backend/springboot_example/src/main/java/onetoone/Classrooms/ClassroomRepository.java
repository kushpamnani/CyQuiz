package onetoone.Classrooms;

import onetoone.Classrooms.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Classroom findById(int id);

    void deleteById(int id);

}
