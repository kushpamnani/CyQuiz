package Entities_Controllers.Students;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        studentRepository.save(request);
        return studentRepository.findById(id);
    }


//    @PutMapping("/students/{studentId}/role/{roleId}")
//    public ResponseEntity<?> assignRoleToStudent(@PathVariable Integer studentId, @PathVariable Integer roleId) {
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        //Optional<Role> roleOptional = roleRepository.findById(roleId);
////        if (!studentOptional.isPresent() || !roleOptional.isPresent()) {
////            return ResponseEntity.notFound().build();
////        }
//        Student student = studentOptional.get();
////        Role role = roleOptional.get();
////        student.setRole(role); // This assumes a setRole method in your Student entity
//        studentRepository.save(student);
//        return ResponseEntity.ok("Role assigned successfully");
    @PutMapping("/students/{studentId}/classrooms/{classroomId}")
    String assignStudentToClassroom(@PathVariable int studentId, @PathVariable int classroomId) {
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

    @PutMapping("/students/{studentId}/classrooms/{code}")
    String assignStudentToClassroomFromCode(@PathVariable int studentId, @PathVariable int code) {
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
    String deleteTeacher(@PathVariable int id){
        if (studentRepository.findById(id) == null) {
            return failure;
        } else {
            studentRepository.deleteById(id);
            return success;
        }
    }
}
