package Entities_Controllers.Users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findById(int id);

    void deleteById(int id);

}
