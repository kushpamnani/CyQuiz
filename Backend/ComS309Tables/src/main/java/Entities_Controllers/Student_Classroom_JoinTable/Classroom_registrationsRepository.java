package Entities_Controllers.Student_Classroom_JoinTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dalton Clark
 *
 */

public interface Classroom_registrationsRepository extends JpaRepository<Classroom_registrations, Long> {

    Classroom_registrations findById(int id);

    @Transactional
    void deleteById(int id);

}
