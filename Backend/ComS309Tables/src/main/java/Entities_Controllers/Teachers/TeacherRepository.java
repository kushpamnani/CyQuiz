package Entities_Controllers.Teachers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dalton Clark
 *
 */

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findById(int id);

    @Transactional
    void deleteById(int id);

    Teacher findByName(String name);

}
