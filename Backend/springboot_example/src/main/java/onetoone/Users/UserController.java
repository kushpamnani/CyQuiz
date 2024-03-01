package onetoone.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import onetoone.Roles.Role;
//import onetoone.Roles.RoleRepository;

@RestController
public class UserController {

    private final UserRepository userRepository;
    //private final RoleRepository roleRepository;

    // Constructor-based Dependency Injection
    @Autowired
    public UserController(UserRepository userRepository){//}, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        //this.roleRepository = roleRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        // Before saving, you might want to check if the user with the given emailId already exists
        // to avoid duplicates. This is just an example and depends on your application's requirements.

        // Assuming newUser contains all the necessary information, including handling the 'role' if needed
        User savedUser = userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok(users); // Return 200 OK with the list of users
    }



    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User request) {
        return userRepository.findById(id).map(existingUser -> {
            // Update the necessary fields from request to existingUser here
            existingUser.setName(request.getName());
            existingUser.setEmailId(request.getEmailId());

            // Assuming Role is a complex object and handled elsewhere or nullable
            existingUser.setRole(request.getRole()); // Directly setting to null or the provided value

            //existingUser.setActive(request.isActive());

            userRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/users/{userId}/role/{roleId}")
    public ResponseEntity<?> assignRoleToUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
        Optional<User> userOptional = userRepository.findById(userId);
        //Optional<Role> roleOptional = roleRepository.findById(roleId);
//        if (!userOptional.isPresent() || !roleOptional.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
        User user = userOptional.get();
//        Role role = roleOptional.get();
//        user.setRole(role); // This assumes a setRole method in your User entity
        userRepository.save(user);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
