package Entities_Controllers.Users;

import java.util.ArrayList;
import java.util.List;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Students.Student;
import Entities_Controllers.Students.StudentRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;
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
 * @author Dalton Clark
 *
 */

@RestController
public class UserController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    private String success = "{\"success\": true}";
    private String failure = "{\"success\": false}";

    @GetMapping(path = "/users") //                                 THIS IS A SECURITY RISK
    List<User> getAllUsers(){
        List<User> list = new ArrayList<>(teacherRepository.findAll());
        list.addAll(studentRepository.findAll());
        //admins added here
        return list;
    }

    @GetMapping(path = "/users/{name}/{password}")
    User getUserByNameAndPassword(@PathVariable String name, @PathVariable String password) {
        User user = studentRepository.findByName(name);
        User teacher = teacherRepository.findByName(name);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        else if (teacher != null && teacher.getPassword().equals(password)) {
            return teacher;
        }
        else {
            return null;
        }
    }

    @GetMapping(path = "/users")
    User getUserByNameAndPassword(@RequestBody Student u) {
        User user = studentRepository.findByName(u.getName());
        User user2 = teacherRepository.findByName(u.getName());
        if (user != null && user.getPassword().equals(u.getPassword())) {
            return user;
        }
        else if (user2 != null && user2.getPassword().equals(u.getPassword())) {
            return user2;
        }
        else {
            return null;
        }
    }

//    @PostMapping(path = "/users")
//    <T> T createUser(@RequestBody User user){
//        if (user == null || (userRepository.findByName(user.getName()) != null))
//            return (T) failure;
//        userRepository.save(user);
//        return (T) user;
//    }

//    @PutMapping("/users/{id}")
//    User updateUser(@PathVariable int id, @RequestBody User request){
//        User user = userRepository.findById(id);
//
//        if(user == null) {
//            throw new RuntimeException("user id does not exist");
//        }
//        else if (request.getId() != id){
//            throw new RuntimeException("path variable id does not match user request id");
//        }
//
//        userRepository.save(request);
//        return userRepository.findById(id);
//    }

//    @PutMapping("/users/{userId}/classrooms/{classroomId}")
//    String assignUserToClassroom(@PathVariable int userId, @PathVariable int classroomId) {
//        User user = userRepository.findById(userId);
//        Classroom classroom = classroomRepository.findById(classroomId);
//        if(user == null || classroom == null)
//            return failure;
//
//        Classroom_registrations cr = new Classroom_registrations(user, classroom);
//        user.addClassroomRegistration(cr);
//        classroom.addStudentRegistration(cr);
//
//        userRepository.save(user);
//        classroomRepository.save(classroom);
//        return success;
//    }

//    @PutMapping("/users/{userId}/classrooms/{code}")
//    String assignUserToClassroomFromCode(@PathVariable int userId, @PathVariable int code) {
//        User user = userRepository.findById(userId);
//        Classroom classroom = classroomRepository.findByCode(code);
//        if(user == null || classroom == null)
//            return failure;
//
//        Classroom_registrations cr = new Classroom_registrations(user, classroom);
//        user.addClassroomRegistration(cr);
//        classroom.addStudentRegistration(cr);
//
//        userRepository.save(user);
//        classroomRepository.save(classroom);
//        return success;
//    }

//    @DeleteMapping(path = "/users/{id}")
//    String deleteTeacher(@PathVariable int id){
//        if (userRepository.findById(id) == null) {
//            return failure;
//        } else {
//            userRepository.deleteById(id);
//            return success;
//        }
//    }
}
