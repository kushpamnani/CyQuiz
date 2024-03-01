package onetoone.Roles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import onetoone.Users.User;
import onetoone.Users.UserRepository;

@RestController
public class RoleController {

    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;

    // Using constructor injection for consistency and best practice
    @Autowired
    public RoleController(RoleRepository roleRepository){//}, UserRepository userRepository) {
        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
    }

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping(path = "/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/roles")
    public ResponseEntity<String> createRole(@RequestBody Role role) {
        roleRepository.save(role);
        return ResponseEntity.ok(success);
    }

    @PutMapping(path = "/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role request) {
        return roleRepository.findById(id).map(existingRole -> {
            // Example of updating a field, replace "name" with actual field names
            // existingRole.setName(request.getName());
            roleRepository.save(existingRole);
            return ResponseEntity.ok(existingRole);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @DeleteMapping(path = "/roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id) {
        if (!roleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

//        List<User> users = userRepository.findByRole_Id(id); // This method now returns a List<User>
//        users.forEach(user -> {
//            user.setRole(null); // Assuming setRole(null) effectively removes the role from the user
//            userRepository.save(user);
//        });
        roleRepository.deleteById(id);
        return ResponseEntity.ok(success);
    }

}
