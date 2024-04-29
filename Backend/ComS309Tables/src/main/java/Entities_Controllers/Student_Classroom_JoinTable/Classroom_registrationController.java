package Entities_Controllers.Student_Classroom_JoinTable;

import java.util.List;

import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.Students.StudentRepository;
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
public class Classroom_registrationController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    Classroom_registrationRepository classroom_registrationRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/classroom_registrations")
    List<Classroom_registration> getAllClassroom_registrations(){
        return classroom_registrationRepository.findAll();
    }

    @GetMapping(path = "/classroom_registrations/{id}")
    Classroom_registration getClassroom_registrationById(@PathVariable int id){
        return classroom_registrationRepository.findById(id);
    }

    @PostMapping(path = "/classroom_registrations")
    Classroom_registration createClassroom_registration(@RequestBody Classroom_registration classroom_registration){
        if (classroom_registration == null)
            return null;
        classroom_registrationRepository.save(classroom_registration);
        return classroom_registration;
    }

    /* not safe to update */
//    @PutMapping("/students/{id}")
//    Student updateStudent(@PathVariable int id, @RequestBody Student request){
//        Student student = studentRepository.findById(id);
//        if(student == null)
//            return null;
//        studentRepository.save(request);
//        return studentRepository.findById(id);
//    }

    @PutMapping("/classroom_registrations/{id}")
    Classroom_registration updateClassroom_registration(@PathVariable int id, @RequestBody Classroom_registration request){
        Classroom_registration classroom_registration = classroom_registrationRepository.findById(id);

        if(classroom_registration == null) {
            throw new RuntimeException("classroom id does not exist");
        }

        if (request.getId() == 0) {
            request.setId(classroom_registration.getId());
        }
        if (request.getStudent() == null) {
            request.setStudent(classroom_registration.getStudent());
        }
        if (request.getClassroom() == null) {
            request.setClassroom(classroom_registration.getClassroom());
        }
        if (request.getMap() == null) {
            request.setMap(classroom_registration.getMap());
        }

        classroom_registrationRepository.save(request);
        return classroom_registrationRepository.findById(request.getId());
    }

    @DeleteMapping(path = "/classroom_registrations/{id}")
    String deleteClassroom_registration(@PathVariable int id){
        if (classroom_registrationRepository.findById(id) == null) {
            return failure;
        } else {
            classroom_registrationRepository.deleteById(id);
            return success;
        }
    }
}
