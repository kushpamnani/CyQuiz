package Entities_Controllers.Maps;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrationsRepository;
import Entities_Controllers.Teachers.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * @author Dalton Clark
 * 
 */

@RestController
public class MapController {
    @Autowired
    MapRepository mapRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    Classroom_registrationsRepository classroom_registrationsRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/maps")
    List<Map> getAllMaps(){
        return mapRepository.findAll();
    }

    @GetMapping(path = "/maps/{id}")
    Map getMapById(@PathVariable int id) {
        return mapRepository.findById(id);
    }

    @PostMapping(path = "/maps")
    <T> T createMap(@RequestBody Map map){
        if (map == null)
            return (T) failure;
        mapRepository.save(map);
        return (T) map;
    }

    @PutMapping("/maps/{id}")
    Map updateMap(@PathVariable int id, @RequestBody Map request){
        Map map = mapRepository.findById(id);

        if(map == null) {
            throw new RuntimeException("map id does not exist");
        }
        else if (request.getId() != id){
            throw new RuntimeException("path variable id does not match map request id");
        }

        mapRepository.save(request);
        return mapRepository.findById(id);
    }

    @PutMapping("/maps/{mapId}/classroom_registrations/{classroomRegistrationsId}")
    String assignMapToClassroom(@PathVariable int mapId, @PathVariable int classroomRegistrationsId) {
        Map map = mapRepository.findById(mapId);
        Classroom_registrations classroomRegistration = classroom_registrationsRepository.findById(classroomRegistrationsId);
        if(map == null || classroomRegistration == null)
            return failure;

        map.setClassroomRegistration(classroomRegistration);
        classroomRegistration.setMap(map);

        mapRepository.save(map);
        classroom_registrationsRepository.save(classroomRegistration);
        return success;
    }

    @DeleteMapping(path = "/maps/{id}")
    String deleteMap(@PathVariable int id){
        if (mapRepository.findById(id) == null) {
            return failure;
        } else {
            mapRepository.deleteById(id);
            return success;
        }
    }
}
