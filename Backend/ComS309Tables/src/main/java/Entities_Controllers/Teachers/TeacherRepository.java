package Entities_Controllers.Teachers;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findById(int id);

    void deleteById(int id);

}
