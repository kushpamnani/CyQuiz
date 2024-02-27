package onetoone.Laptops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import onetoone.Users.User;
import onetoone.Users.UserRepository;

/**
 *
 * @author Vivek Bengre
 *
 */

@RestController
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/roles")
    List<Laptop> getAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping(path = "/roles/{id}")
    Role getRoleById(@PathVariable int id){
        return roleRepository.findById(id);
    }

    @PostMapping(path = "/roles")
    String createRole(Role Role){
        if (Role == null)
            return failure;
        roleRepository.save(Role);
        return success;
    }

    @PutMapping(path = "/roles/{id}")
    Role updateRole(@PathVariable int id, @RequestBody Role request){
        Role role = roleRepository.findById(id);
        if(role == null)
            return null;
        roleRepository.save(request);
        return roleRepository.findById(id);
    }

    @DeleteMapping(path = "/roles/{id}")
    String deleteRole(@PathVariable int id){

        // Check if there is an object depending on user and then remove the dependency
        User user = userRepository.findByRoleId(id);
        user.setRole(null);
        userRepository.save(user);

        // delete the laptop if the changes have not been reflected by the above statement
        roleRepository.deleteById(id);
        return success;
    }
}
