package Entities_Controllers.Teachers;

import java.util.List;
import java.util.Random;

import Entities_Controllers.Admins.AdminRepository;
import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
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
public class TeacherController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AdminRepository adminRepository;

    private final Random rand = new Random();

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/teachers")
    List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    @GetMapping(path = "/teachers/{id}")
    Teacher getTeacherById( @PathVariable int id){
        return teacherRepository.findById(id);
    }



    @PostMapping(path = "/teachers")
    <T> T createTeacher(@RequestBody Teacher teacher){
        if (teacher == null || (teacherRepository.findByName(teacher.getName()) != null) || (studentRepository.findByName(teacher.getName()) != null) || (adminRepository.findByName(teacher.getName()) != null))
            return (T) failure;
        teacherRepository.save(teacher);
        return (T) teacher;
    }

    @PutMapping("/teachers/{id}/add_classroom")
    Teacher addClassroom(@PathVariable int id, @RequestBody Classroom classroom) {
        Teacher teacher = teacherRepository.findById(id);
        if (classroom == null) {
            throw new RuntimeException("no classroom sent in json body");
        }
        if(teacher == null) {
            throw new RuntimeException("teacher id does not exist");
        }

        int tempCode;
        do {
            tempCode = rand.nextInt(1000,10000);
        } while(classroomRepository.findByCode(tempCode) != null);
        classroom.setCode(tempCode);

        classroom.setTeacher(teacher);
        teacher.addClassroom(classroom);
        teacherRepository.save(teacher);
        return teacherRepository.findById(id);
    }

    @PutMapping("/teachers/{teacherId}/classrooms/{classroomId}")
    String assignClassroomToTeacher(@PathVariable int teacherId, @PathVariable int classroomId){
        Teacher teacher = teacherRepository.findById(teacherId);
        Classroom classroom = classroomRepository.findById(classroomId);
        if(teacher == null || classroom == null)
            return failure;
        classroom.setTeacher(teacher);
        teacher.addClassroom(classroom);
        teacherRepository.save(teacher);
        return success;
    }

    @PutMapping("/teachers/{id}")
    Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher request){
        Teacher teacher = teacherRepository.findById(id);

        if(teacher == null) {
            throw new RuntimeException("classroom id does not exist");
        }
        if (request.getName() != null) {
            if ( teacherRepository.findByName(request.getName()) != null && teacher.getId() != teacherRepository.findByName(request.getName()).getId() ) {
                throw new RuntimeException("request name already exists in teacher database");
            }
            if ( studentRepository.findByName(request.getName()) != null ) {
                throw new RuntimeException("request name already exists in student database");
            }
            if ( adminRepository.findByName(request.getName()) != null ) {
                throw new RuntimeException("request name already exists in admin database");
            }
        }


        if (request.getId() == 0) {
            request.setId(teacher.getId());
        }
        if (request.getName() == null) {
            request.setName(teacher.getName());
        }
        if (request.getPassword() == null) {
            request.setPassword(teacher.getPassword());
        }
        if (request.getClassrooms() == null) {
            request.setClassrooms(teacher.getClassrooms());
        }
        if (request.getEnemies() == null) {
            request.setEnemies(teacher.getEnemies());
        }

        teacherRepository.save(request);
        return teacherRepository.findById(request.getId());
    }

    @DeleteMapping(path = "/teachers/{id}")
    String deleteTeacher(@PathVariable int id){
        if (teacherRepository.findById(id) == null) {
            return failure;
        } else {
            teacherRepository.deleteById(id);
            return success;
        }
    }
}
