package Entities_Controllers.Battles;

import Entities_Controllers.Enemies.Enemy;
import Entities_Controllers.Enemies.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class BattleController {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private EnemyRepository enemyRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    // Get all battles
    @GetMapping(path = "/battles")
    List<Battle> getAllBattles() {
        return battleRepository.findAll();
    }

    // Get a specific battle by ID
    @GetMapping(path = "/battles/{id}")
    Battle getBattleById(@PathVariable int id) {
        return battleRepository.findById(id);
    }

    // Create a new battle
    @PostMapping(path = "/battles")
    <T> T createBattle(@RequestBody Battle battle) {
        if (battle == null) {
            return (T) failure;
        }
        battleRepository.save(battle);
        return (T) battle;
    }


    // Update an existing battle
    @PutMapping(path = "/battles/{id}")
    Battle updateBattle(@PathVariable int id, @RequestBody Battle request) {
        Battle battle = battleRepository.findById(id);

        if (battle == null) {
            throw new RuntimeException("battle id does not exist");
        }

        if (request.getId() == 0) {
            request.setId(battle.getId());
        }
        request.setSmallEnemiesCount(battle.getSmallEnemiesCount());
        request.setLargeEnemiesCount(battle.getLargeEnemiesCount());
        if (request.getBoss() == null) {
            request.setBoss(battle.getBoss());
        }

        battleRepository.save(request);
        return battleRepository.findById(request.getId());
    }

    @PutMapping(path = "/battles/{id}/small_enemies/{count}")
    Battle updateBattleSmallEnemies(@PathVariable int id, @PathVariable int count) {
        Battle battle = battleRepository.findById(id);

        if (battle == null) {
            throw new RuntimeException("battle id does not exist");
        }

        battle.setSmallEnemiesCount(count);

        battleRepository.save(battle);
        return battleRepository.findById(id);
    }

    @PutMapping(path = "/battles/{id}/large_enemies/{count}")
    Battle updateBattleLargeEnemies(@PathVariable int id, @PathVariable int count) {
        Battle battle = battleRepository.findById(id);

        if (battle == null) {
            throw new RuntimeException("battle id does not exist");
        }

        battle.setLargeEnemiesCount(count);

        battleRepository.save(battle);
        return battleRepository.findById(id);
    }

    @PutMapping(path = "/battles/{battleId}/enemies/{enemyId}")
    String assignBattleToBoss(@PathVariable int battleId, @PathVariable int enemyId) {
        Battle battle = battleRepository.findById(battleId);
        Enemy enemy = enemyRepository.findById(enemyId);

        if (battle == null || enemy == null) {
            return failure;
        }

        battle.setBoss(enemy);
        enemy.addBattle(battle);

        battleRepository.save(battle);
        enemyRepository.save(enemy);
        return success;
    }

    // Delete a battle
    @DeleteMapping(path = "/battles/{id}")
    public String deleteBattle(@PathVariable int id) {
        if (battleRepository.findById(id) == null) {
            return failure;
        } else {
            battleRepository.deleteById(id);
            return success;
        }
    }
}

