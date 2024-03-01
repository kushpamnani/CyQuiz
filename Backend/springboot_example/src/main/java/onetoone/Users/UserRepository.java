package onetoone.Users;

import onetoone.Roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Method to find users by role's ID
    //List<User> findByRole_Id(Integer roleId); // Assuming the attribute in User is named 'role'
}
