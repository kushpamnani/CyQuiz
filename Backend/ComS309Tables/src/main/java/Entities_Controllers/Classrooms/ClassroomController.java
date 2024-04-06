package Entities_Controllers.Classrooms;

import java.util.List;
import java.util.Random;

import Entities_Controllers.Students.Student;
import Entities_Controllers.Students.StudentRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrationsRepository;
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
public class ClassroomController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    Classroom_registrationsRepository classroom_registrationsRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    private Random rand = new Random();

    @GetMapping(path = "/classrooms")
    List<Classroom> getAllClassrooms(){
        return classroomRepository.findAll();
    }

    @GetMapping(path = "/classrooms/{id}")
    Classroom getClassroomById( @PathVariable int id){
        return classroomRepository.findById(id);
    }

    @GetMapping(path = "/classrooms/code/{code}")
    Classroom getClassroomByCode(@PathVariable int code) { return classroomRepository.findByCode(code); }

    @PostMapping(path = "/classrooms")
    Classroom createClassroom(@RequestBody Classroom classroom){
        if (classroom == null)
            return null;
        int tempCode;
        do {
            tempCode = rand.nextInt(1000,10000);
        } while(getClassroomByCode(tempCode) != null);
        classroom.setCode(tempCode);
        classroomRepository.save(classroom);
        return classroom;
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

    @PutMapping("/classrooms/{classroomId}/students/{studentId}")
    String assignClassroomToStudent(@PathVariable int classroomId, @PathVariable int studentId) {
        Student student = studentRepository.findById(studentId);
        Classroom classroom = classroomRepository.findById(classroomId);
        if(student == null || classroom == null)
            return failure;

        Classroom_registrations cr = new Classroom_registrations(student, classroom);
        student.addClassroomRegistration(cr);
        classroom.addStudentRegistration(cr);

        studentRepository.save(student);
        classroomRepository.save(classroom);
        return success;
    }

    @PutMapping("/classrooms/{code}/students/{studentId}")
    String assignClassroomToStudentFromCode(@PathVariable int code, @PathVariable int studentId) {
        Student student = studentRepository.findById(studentId);
        Classroom classroom = classroomRepository.findByCode(code);
        if(student == null || classroom == null)
            return failure;

        Classroom_registrations cr = new Classroom_registrations(student, classroom);
        student.addClassroomRegistration(cr);
        classroom.addStudentRegistration(cr);

        studentRepository.save(student);
        classroomRepository.save(classroom);
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
