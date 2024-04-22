package Entities_Controllers.Maps;

import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registration;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrationRepository;
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
    Classroom_registrationRepository classroom_registrationRepository;

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

        if (request.getId() == 0) {
            request.setId(map.getId());
        }
        if (request.getSeed() == null) {
            request.setSeed(map.getSeed());
        }
        request.setHealth(map.getHealth());
        if (request.getClassroomRegistration() == null) {
            request.setClassroomRegistration(map.getClassroomRegistration());
        }

        mapRepository.save(request);
        return mapRepository.findById(request.getId());
    }

    @PutMapping("/maps/{id}/health/{health}")
    Map updateMapHealth(@PathVariable int id, @PathVariable int health){
        Map map = mapRepository.findById(id);

        if(map == null) {
            throw new RuntimeException("map id does not exist");
        }

        map.setHealth(health);

        mapRepository.save(map);
        return mapRepository.findById(id);
    }

    @PutMapping("/maps/{mapId}/classroom_registrations/{classroomRegistrationsId}")
    String assignMapToClassroom(@PathVariable int mapId, @PathVariable int classroomRegistrationsId) {
        Map map = mapRepository.findById(mapId);
        Classroom_registration classroomRegistration = classroom_registrationRepository.findById(classroomRegistrationsId);
        if(map == null || classroomRegistration == null)
            return failure;

        map.setClassroomRegistration(classroomRegistration);
        classroomRegistration.setMap(map);

        mapRepository.save(map);
        classroom_registrationRepository.save(classroomRegistration);
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
