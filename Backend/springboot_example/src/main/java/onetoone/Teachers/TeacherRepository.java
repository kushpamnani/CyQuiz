package onetoone.Teachers;

import onetoone.Teachers.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findById(int id);

    void deleteById(int id);

}
