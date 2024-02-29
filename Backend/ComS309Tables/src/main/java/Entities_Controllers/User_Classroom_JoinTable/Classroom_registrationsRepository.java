package Entities_Controllers.User_Classroom_JoinTable;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface Classroom_registrationsRepository extends JpaRepository<Classroom_registrations, Long> {

    Classroom_registrations findById(int id);

    void deleteById(int id);

}
