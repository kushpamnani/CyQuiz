package Entities_Controllers.Enemies;

import Entities_Controllers.Battles.Battle;
import Entities_Controllers.Battles.BattleRepository;
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

    @Autowired
    BattleRepository battleRepository;

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

        if (request.getId() == 0) {
            request.setId(enemy.getId());
        }
        if (request.getName() == null) {
            request.setName(enemy.getName());
        }
        if (request.getFlashcard() == null) {
            request.setFlashcard(enemy.getFlashcard());
        }
        request.setAttack(enemy.getAttack());
        request.setDefense(enemy.getDefense());
        request.setHealth(enemy.getHealth());
        if (request.getTeacher() == null) {
            request.setTeacher(enemy.getTeacher());
        }
        if (request.getBattles() == null) {
            request.setBattles(enemy.getBattles());
        }

        enemyRepository.save(request);
        return enemyRepository.findById(id);
    }

    @PutMapping("/enemies/{id}/attack/{attack}")
    Enemy updateEnemyAttack(@PathVariable int id, @PathVariable int attack){
        Enemy enemy = enemyRepository.findById(id);

        if(enemy == null) {
            throw new RuntimeException("enemy id does not exist");
        }

        enemy.setAttack(attack);

        enemyRepository.save(enemy);
        return enemyRepository.findById(id);
    }

    @PutMapping("/enemies/{id}/defense/{defense}")
    Enemy updateEnemyDefense(@PathVariable int id, @PathVariable int defense){
        Enemy enemy = enemyRepository.findById(id);

        if(enemy == null) {
            throw new RuntimeException("enemy id does not exist");
        }

        enemy.setDefense(defense);

        enemyRepository.save(enemy);
        return enemyRepository.findById(id);
    }

    @PutMapping("/enemies/{id}/health/{health}")
    Enemy updateEnemyHealth(@PathVariable int id, @PathVariable int health){
        Enemy enemy = enemyRepository.findById(id);

        if(enemy == null) {
            throw new RuntimeException("enemy id does not exist");
        }

        enemy.setHealth(health);

        enemyRepository.save(enemy);
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

    @PutMapping("/enemies/{enemyId}/battles/{battleId}")
    String assignEnemyToBattle(@PathVariable int enemyId, @PathVariable int battleId) {
        Enemy enemy = enemyRepository.findById(enemyId);
        Battle battle = battleRepository.findById(battleId);
        if(enemy == null || battle == null)
            return failure;

        enemy.addBattle(battle);
        battle.setBoss(enemy);

        enemyRepository.save(enemy);
        battleRepository.save(battle);
        return success;
    }

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
