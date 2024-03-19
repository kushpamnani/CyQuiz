package Entities_Controllers.Users;

import java.util.List;
import java.util.Optional;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Teachers.Teacher;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.User_Classroom_JoinTable.Classroom_registrations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Vivek Bengre
 * 
 */

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @PostMapping(path = "/users")
    <T> T createUser(@RequestBody User user){
        if (user == null || (userRepository.findByName(user.getName()) != null))
            return (T) failure;
        userRepository.save(user);
        return (T) user;
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);

        if(user == null) {
            throw new RuntimeException("user id does not exist");
        }
        else if (request.getId() != id){
            throw new RuntimeException("path variable id does not match user request id");
        }

        userRepository.save(request);
        return userRepository.findById(id);
    }


//    @PutMapping("/users/{userId}/role/{roleId}")
//    public ResponseEntity<?> assignRoleToUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        //Optional<Role> roleOptional = roleRepository.findById(roleId);
////        if (!userOptional.isPresent() || !roleOptional.isPresent()) {
////            return ResponseEntity.notFound().build();
////        }
//        User user = userOptional.get();
////        Role role = roleOptional.get();
////        user.setRole(role); // This assumes a setRole method in your User entity
//        userRepository.save(user);
//        return ResponseEntity.ok("Role assigned successfully");
    @PutMapping("/users/{userId}/classrooms/{classroomId}")
    String assignUserToClassroom(@PathVariable int userId, @PathVariable int classroomId) {
        User user = userRepository.findById(userId);
        Classroom classroom = classroomRepository.findById(classroomId);
        if(user == null || classroom == null)
            return failure;

        Classroom_registrations cr = new Classroom_registrations(user, classroom);
        user.addClassroomRegistration(cr);
        classroom.addStudentRegistration(cr);

        userRepository.save(user);
        classroomRepository.save(classroom);
        return success;
    }

    @PutMapping("/users/{userId}/classrooms/{code}")
    String assignUserToClassroomFromCode(@PathVariable int userId, @PathVariable int code) {
        User user = userRepository.findById(userId);
        Classroom classroom = classroomRepository.findByCode(code);
        if(user == null || classroom == null)
            return failure;

        Classroom_registrations cr = new Classroom_registrations(user, classroom);
        user.addClassroomRegistration(cr);
        classroom.addStudentRegistration(cr);

        userRepository.save(user);
        classroomRepository.save(classroom);
        return success;
    }

//    @PutMapping("/users/{userId}/laptops/{laptopId}")
//    String assignLaptopToUser(@PathVariable int userId,@PathVariable int laptopId){
//        User user = userRepository.findById(userId);
//        Laptop laptop = laptopRepository.findById(laptopId);
//        if(user == null || laptop == null)
//            return failure;
//        laptop.setUser(user);
//        user.setLaptop(laptop);
//        userRepository.save(user);
//        return success;
//    }

    @DeleteMapping(path = "/users/{id}")
    String deleteTeacher(@PathVariable int id){
        if (userRepository.findById(id) == null) {
            return failure;
        } else {
            userRepository.deleteById(id);
            return success;
        }
    }
}
