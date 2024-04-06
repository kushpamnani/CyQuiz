package Entities_Controllers.Admin; // New package named Admin

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/admins")
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping(path = "/admins/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return adminRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/admins")
    public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
        adminRepository.save(admin);
        return ResponseEntity.ok(success);
    }

    @PutMapping(path = "/admins/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin request) {
        Optional<Admin> updatedAdmin = adminRepository.findById(id)
                .map(admin -> {
                    admin.setUsername(request.getUsername());
                    admin.setPassword(request.getPassword());
                    return adminRepository.save(admin);
                });
        return updatedAdmin
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/admins/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        if (!adminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adminRepository.deleteById(id);
        return ResponseEntity.ok(success);
    }
}
