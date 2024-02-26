package onetoone.Classrooms;

import java.util.List;

import onetoone.Teachers.TeacherRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
public class ClassroomController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/classrooms")
    List<Classroom> getAllClassrooms(){
        return classroomRepository.findAll();
    }

    @GetMapping(path = "/classrooms/{id}")
    Classroom getClassroomById( @PathVariable int id){
        return classroomRepository.findById(id);
    }

    @PostMapping(path = "/classrooms")
    String createClassroom(@RequestBody Classroom classroom){
        if (classroom == null)
            return failure;
        classroomRepository.save(classroom);
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

    @PutMapping("/classrooms/{id}")
    Classroom updateClassroom(@PathVariable int id, @RequestBody Classroom request){
        Classroom classroom = classroomRepository.findById(id);

        if(classroom == null) {
            throw new RuntimeException("classroom id does not exist");
        }
        else if (request.getId() != id){
            throw new RuntimeException("path variable id does not match classroom request id");
        }

        classroomRepository.save(request);
        return classroomRepository.findById(id);
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

    @DeleteMapping(path = "/classrooms/{id}")
    String deleteClassroom(@PathVariable int id){
        if (classroomRepository.findById(id) == null) {
            return failure;
        } else {
            classroomRepository.deleteById(id);
            return success;
        }
    }
}
