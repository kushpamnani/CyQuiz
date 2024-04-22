package Entities_Controllers.Students;

import Entities_Controllers.Admins.AdminRepository;
import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrationRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registration;
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

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    Classroom_registrationRepository classroom_registrationRepository;

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
        if (student == null || (studentRepository.findByName(student.getName()) != null) || (teacherRepository.findByName(student.getName()) != null) || (adminRepository.findByName(student.getName()) != null) )
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
        if (request.getName() != null) {
            if ( studentRepository.findByName(request.getName()) != null && student.getId() != studentRepository.findByName(request.getName()).getId() ) {
                throw new RuntimeException("request name already exists in student database");
            }
            if ( teacherRepository.findByName(request.getName()) != null ) {
                throw new RuntimeException("request name already exists in teacher database");
            }
            if ( adminRepository.findByName(request.getName()) != null ) {
                throw new RuntimeException("request name already exists in admin database");
            }
        }

        if (request.getId() == 0) {
            request.setId(student.getId());
        }
        if (request.getName() == null) {
            request.setName(student.getName());
        }
        if (request.getPassword() == null) {
            request.setPassword(student.getPassword());
        }
        if (request.getClassroomRegistrations() == null) {
            request.setClassroomRegistrations(student.getClassroomRegistrations());
        }

        studentRepository.save(request);
        return studentRepository.findById(request.getId());
    }


    @PutMapping("/students/{studentId}/classrooms/{classroomId}")
    Classroom_registration assignStudentToClassroom(@PathVariable int studentId, @PathVariable int classroomId) {
        Student student = studentRepository.findById(studentId);
        Classroom classroom = classroomRepository.findById(classroomId);
        if(student == null || classroom == null)
            return null;

        Classroom_registration cr = new Classroom_registration(student, classroom);
        student.addClassroomRegistration(cr);
        classroom.addStudentRegistration(cr);

        studentRepository.save(student);
        classroomRepository.save(classroom);
        return cr;
    }

    @PutMapping("/students/{studentId}/code/{code}")
    Classroom_registration assignStudentToClassroomFromCode(@PathVariable int studentId, @PathVariable int code) {
        Student student = studentRepository.findById(studentId);
        Classroom classroom = classroomRepository.findByCode(code);
        if(student == null || classroom == null)
            return null;

        Classroom_registration cr = new Classroom_registration(student, classroom);
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
