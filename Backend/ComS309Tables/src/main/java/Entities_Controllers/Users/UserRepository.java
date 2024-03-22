package Entities_Controllers.Users;

import onetoone.Roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Dalton Clark
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findById(int id);

    @Transactional
    void deleteById(int id);

    User findByName(String name);

}
