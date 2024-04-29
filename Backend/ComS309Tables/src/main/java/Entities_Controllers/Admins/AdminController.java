package Entities_Controllers.Admins; // New package named Admin

import java.util.List;

import Entities_Controllers.Students.StudentRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author Dalton Clark
 *
 */
@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/admins")
    List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    @GetMapping(path = "/admins/{id}")
    Admin getAdminById( @PathVariable int id){
        return adminRepository.findById(id);
    }
    
    @PostMapping(path = "/admins")
    <T> T createAdmin(@RequestBody Admin admin){
        if (admin == null || (adminRepository.findByName(admin.getName()) != null) || (studentRepository.findByName(admin.getName()) != null) || (teacherRepository.findByName(admin.getName()) != null))
            return (T) failure;
        adminRepository.save(admin);
        return (T) admin;
    }
    
    @PutMapping("/admins/{id}")
    Admin updateAdmin(@PathVariable int id, @RequestBody Admin request){
        Admin admin = adminRepository.findById(id);

        if(admin == null) {
            throw new RuntimeException("classroom id does not exist");
        }
        if (request.getName() != null) {
            if ( adminRepository.findByName(request.getName()) != null && admin.getId() != adminRepository.findByName(request.getName()).getId() ) {
                throw new RuntimeException("request name already exists in admin database");
            }
            if ( studentRepository.findByName(request.getName()) != null ) {
                throw new RuntimeException("request name already exists in student database");
            }
            if ( teacherRepository.findByName(request.getName()) != null ) {
                throw new RuntimeException("request name already exists in teacher database");
            }
        }


        if (request.getId() == 0) {
            request.setId(admin.getId());
        }
        if (request.getName() == null) {
            request.setName(admin.getName());
        }
        if (request.getPassword() == null) {
            request.setPassword(admin.getPassword());
        }

        adminRepository.save(request);
        return adminRepository.findById(request.getId());
    }

    @DeleteMapping(path = "/admins/{id}")
    String deleteAdmin(@PathVariable int id){
        if (adminRepository.findById(id) == null) {
            return failure;
        } else {
            adminRepository.deleteById(id);
            return success;
        }
    }
}
