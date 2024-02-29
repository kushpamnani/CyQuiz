package Entities_Controllers.Classrooms;

import java.util.List;

import Entities_Controllers.Teachers.Teacher;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.User_Classroom_JoinTable.Classroom_registrations;
import Entities_Controllers.User_Classroom_JoinTable.Classroom_registrationsRepository;
import Entities_Controllers.Users.User;
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
public class ClassroomController {

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

    @PutMapping("/classrooms/{classroomId}/users/{userId}")
    String assignClassroomToUser(@PathVariable int classroomId, @PathVariable int userId){
        User user = userRepository.findById(userId);
        Classroom classroom = classroomRepository.findById(classroomId);
        if(user == null || classroom == null)
            return failure;
        Classroom_registrations cr = user.addClassroomRegistration(classroom);
        classroom_registrationsRepository.save(cr);
        return success;
    }

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
