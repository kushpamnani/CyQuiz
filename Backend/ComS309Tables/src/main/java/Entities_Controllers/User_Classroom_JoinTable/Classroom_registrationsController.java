package Entities_Controllers.User_Classroom_JoinTable;

import java.util.List;

import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Classroom_registrationsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    Classroom_registrationsRepository classroom_registrationsRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/classroom_registrations")
    List<Classroom_registrations> getAllClassroom_registrations(){
        return classroom_registrationsRepository.findAll();
    }

    @GetMapping(path = "/classroom_registrations/{id}")
    Classroom_registrations getClassroom_registrationById( @PathVariable int id){
        return classroom_registrationsRepository.findById(id);
    }

    @PostMapping(path = "/classroom_registrations")
    String createClassroom_registration(@RequestBody Classroom_registrations classroom_registration){
        if (classroom_registration == null)
            return failure;
        classroom_registrationsRepository.save(classroom_registration);
        return success;
    }

    /* not safe to update */
//    @PutMapping("/users/{id}")
//    User updateUser(@PathVariable int id, @RequestBody User request){
//        User user = userRepository.findById(id);
//        if(user == null)
//            return null;
//        userRepository.save(request);
//        return userRepository.findById(id);
//    }

    @PutMapping("/classroom_registrations/{id}")
    Classroom_registrations updateClassroom_registration(@PathVariable int id, @RequestBody Classroom_registrations request){
        Classroom_registrations classroom_registration = classroom_registrationsRepository.findById(id);

        if(classroom_registration == null) {
            throw new RuntimeException("classroom id does not exist");
        }
        else if (request.getId() != id){
            throw new RuntimeException("path variable id does not match classroom request id");
        }

        classroom_registrationsRepository.save(request);
        return classroom_registrationsRepository.findById(id);
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

    @DeleteMapping(path = "/classroom_registrations/{id}")
    String deleteClassroom_registration(@PathVariable int id){
        if (classroom_registrationsRepository.findById(id) == null) {
            return failure;
        } else {
            classroom_registrationsRepository.deleteById(id);
            return success;
        }
    }
}
