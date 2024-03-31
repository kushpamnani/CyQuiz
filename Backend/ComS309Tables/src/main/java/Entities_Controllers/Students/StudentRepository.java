package Entities_Controllers.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dalton Clark
 *
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Student findById(int id);

    @Transactional
    void deleteById(int id);

    Student findByName(String name);

}
