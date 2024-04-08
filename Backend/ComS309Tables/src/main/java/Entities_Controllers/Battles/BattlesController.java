package Entities_Controllers.Battles;

import Entities_Controllers.Bosses.Boss;
import Entities_Controllers.Bosses.BossesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class BattlesController {

    @Autowired
    private BattlesRepository battlesRepository;

    @Autowired
    private BossesRepository bossesRepository;

    // Get all battles
    @GetMapping("/battles")
    public List<Battles> getAllBattles() {
        return battlesRepository.findAll();
    }

    // Get a specific battle by ID
    @GetMapping("/battles/{id}")
    public Battles getBattleById(@PathVariable int id) {
        return battlesRepository.findById(id).orElse(null);
    }

    // Create a new battle
    // Create a new battle
    @PostMapping("/battles")
    public ResponseEntity<Battles> createBattle(@RequestBody Battles battle) {
        // Check if the battle has an associated boss
        Boss boss = battle.getBoss();
        if (boss != null) {
            // Check if the boss entity is already saved or not
            if (boss.getId() == 0) {
                // If not saved, save the boss entity first
                boss = bossesRepository.save(boss);
            }
        }

        // Save the battle entity
        Battles createdBattle = battlesRepository.save(battle);
        return ResponseEntity.ok().body(createdBattle);
    }


    // Update an existing battle
    @PutMapping("/battles/{id}")
    public Battles updateBattle(@PathVariable int id, @RequestBody Battles updatedBattle) {
        Battles existingBattle = battlesRepository.findById(id).orElse(null);
        if (existingBattle != null) {
            existingBattle.setLargeEnemiesCount(updatedBattle.getLargeEnemiesCount());
            existingBattle.setSmallEnemiesCount(updatedBattle.getSmallEnemiesCount());

            // Check if the updatedBattle contains a non-null Boss object
            Boss updatedBoss = updatedBattle.getBoss();
            if (updatedBoss != null) {
                // Check if the Boss entity is already saved or not
                if (updatedBoss.getId() != 0 && bossesRepository.existsById(updatedBoss.getId())) {
                    existingBattle.setBoss(updatedBoss);
                } else {
                    // If not saved, save the Boss entity first
                    updatedBoss = bossesRepository.save(updatedBoss);
                    existingBattle.setBoss(updatedBoss);
                }
            }

            return battlesRepository.save(existingBattle);
        } else {
            return null; // Handle not found scenario
        }
    }

    // Delete a battle
    @DeleteMapping("/battles/{id}")
    public String deleteBattle(@PathVariable int id) {
        if (battlesRepository.existsById(id)) {
            battlesRepository.deleteById(id);
            return "Battle with ID " + id + " has been deleted.";
        } else {
            return "Battle with ID " + id + " not found.";
        }
    }
}

