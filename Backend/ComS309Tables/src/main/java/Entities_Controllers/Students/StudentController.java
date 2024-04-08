package Entities_Controllers.Students;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrationsRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.sql.Connection;
import java.util.List;

/**
 * 
 * @author Dalton Clark
 * 
 */

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    Classroom_registrationsRepository classroom_registrationsRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/students")
    List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/{id}")
    Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id);
    }

    @PostMapping(path = "/students")
    <T> T createStudent(@RequestBody Student student){
        if (student == null || (studentRepository.findByName(student.getName()) != null) || (teacherRepository.findByName(student.getName()) != null) )
            return (T) failure;
        studentRepository.save(student);
        return (T) student;
    }

    @PutMapping("/students/{id}")
    Student updateStudent(@PathVariable int id, @RequestBody Student request){
        Student student = studentRepository.findById(id);

        if(student == null) {
            throw new RuntimeException("student id does not exist");
        }
        else if (request.getId() != id){
            throw new RuntimeException("path variable id does not match student request id");
        }

        request.setId(student.getId());
        studentRepository.save(request);
        return studentRepository.findById(id);
    }


    @PutMapping("/students/{studentId}/classrooms/{classroomId}")
    Classroom_registrations assignStudentToClassroom(@PathVariable int studentId, @PathVariable int classroomId) {
        Student student = studentRepository.findById(studentId);
        Classroom classroom = classroomRepository.findById(classroomId);
        if(student == null || classroom == null)
            return null;

        Classroom_registrations cr = new Classroom_registrations(student, classroom);
        student.addClassroomRegistration(cr);
        classroom.addStudentRegistration(cr);

        studentRepository.save(student);
        classroomRepository.save(classroom);
        return cr;
    }

    @PutMapping("/students/{studentId}/code/{code}")
    Classroom_registrations assignStudentToClassroomFromCode(@PathVariable int studentId, @PathVariable int code) {
        Student student = studentRepository.findById(studentId);
        Classroom classroom = classroomRepository.findByCode(code);
        if(student == null || classroom == null)
            return null;

        Classroom_registrations cr = new Classroom_registrations(student, classroom);
        student.addClassroomRegistration(cr);
        classroom.addStudentRegistration(cr);

        studentRepository.save(student);
        classroomRepository.save(classroom);
        return cr;
    }

//    @PutMapping("/students/{studentId}/laptops/{laptopId}")
//    String assignLaptopToStudent(@PathVariable int studentId,@PathVariable int laptopId){
//        Student student = studentRepository.findById(studentId);
//        Laptop laptop = laptopRepository.findById(laptopId);
//        if(student == null || laptop == null)
//            return failure;
//        laptop.setStudent(student);
//        student.setLaptop(laptop);
//        studentRepository.save(student);
//        return success;
//    }

    @DeleteMapping(path = "/students/{id}")
    String deleteStudent(@PathVariable int id){
        if (studentRepository.findById(id) == null) {
            return failure;
        } else {
            studentRepository.deleteById(id);
            return success;
        }
    }
}
