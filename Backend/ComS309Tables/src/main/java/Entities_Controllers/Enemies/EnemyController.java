package Entities_Controllers.Enemies;

import Entities_Controllers.Flashcards.Flashcard;
import Entities_Controllers.Flashcards.FlashcardRepository;
import Entities_Controllers.Teachers.Teacher;
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
public class EnemyController {
    @Autowired
    EnemyRepository enemyRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    FlashcardRepository flashcardRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/enemies")
    List<Enemy> getAllEnemies(){
        return enemyRepository.findAll();
    }

    @GetMapping(path = "/enemies/{id}")
    Enemy getEnemyById(@PathVariable int id) {
        return enemyRepository.findById(id);
    }

    @PostMapping(path = "/enemies")
    <T> T createEnemy(@RequestBody Enemy enemy){
        if (enemy == null)
            return (T) failure;
        enemyRepository.save(enemy);
        return (T) enemy;
    }

    @PutMapping("/enemies/{id}")
    Enemy updateEnemy(@PathVariable int id, @RequestBody Enemy request){
        Enemy enemy = enemyRepository.findById(id);

        if(enemy == null) {
            throw new RuntimeException("enemy id does not exist");
        }
        else if (request.getId() != id){
            throw new RuntimeException("path variable id does not match enemy request id");
        }

        request.setId(enemy.getId());
        request.setFlashcard(enemy.getFlashcard());
        enemyRepository.save(request);
        return enemyRepository.findById(id);
    }

    @PutMapping("/enemies/{enemyId}/teacher/{teacherId}")
    String assignEnemyToClassroom(@PathVariable int enemyId, @PathVariable int teacherId) {
        Enemy enemy = enemyRepository.findById(enemyId);
        Teacher teacher = teacherRepository.findById(teacherId);
        if(enemy == null || teacher == null)
            return failure;

        enemy.setTeacher(teacher);
        teacher.addEnemy(enemy);

        enemyRepository.save(enemy);
        teacherRepository.save(teacher);
        return success;
    }

    @PutMapping("/enemies/{enemyId}/flashcards/{flashcardId}")
    String assignEnemyToFlashcard(@PathVariable int enemyId, @PathVariable int flashcardId) {
        Enemy enemy = enemyRepository.findById(enemyId);
        Flashcard flashcard = flashcardRepository.findById(flashcardId);
        if(enemy == null || flashcard == null)
            return failure;

        enemy.setFlashcard(flashcard);
        flashcard.addEnemy(enemy);

        enemyRepository.save(enemy);
        flashcardRepository.save(flashcard);
        return success;
    }

//    @PutMapping("/enemies/{enemyId}/laptops/{laptopId}")
//    String assignLaptopToEnemy(@PathVariable int enemyId,@PathVariable int laptopId){
//        Enemy enemy = enemyRepository.findById(enemyId);
//        Laptop laptop = laptopRepository.findById(laptopId);
//        if(enemy == null || laptop == null)
//            return failure;
//        laptop.setEnemy(enemy);
//        enemy.setLaptop(laptop);
//        enemyRepository.save(enemy);
//        return success;
//    }

    @DeleteMapping(path = "/enemies/{id}")
    String deleteTeacher(@PathVariable int id){
        if (enemyRepository.findById(id) == null) {
            return failure;
        } else {
            enemyRepository.deleteById(id);
            return success;
        }
    }
}
