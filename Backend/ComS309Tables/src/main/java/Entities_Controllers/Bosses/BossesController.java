package Entities_Controllers.Bosses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BossesController {

    @Autowired
    private BossesRepository bossesRepository;

    // Get all bosses
    @GetMapping("/bosses")
    public List<Boss> getAllBosses() {
        return bossesRepository.findAll();
    }

    // Get a specific boss by ID
    @GetMapping("/bosses/{id}")
    public Boss getBossById(@PathVariable int id) {
        return bossesRepository.findById(id).orElse(null);
    }

    // Create a new boss
    @PostMapping("/bosses")
    public Boss createBoss(@RequestBody Boss boss) {
        return bossesRepository.save(boss);
    }

    // Update an existing boss
    @PutMapping("/bosses/{id}")
    public Boss updateBoss(@PathVariable int id, @RequestBody Boss updatedBoss) {
        Boss existingBoss = bossesRepository.findById(id).orElse(null);
        if (existingBoss != null) {
            existingBoss.setName(updatedBoss.getName());
            existingBoss.setHealth(updatedBoss.getHealth());
            existingBoss.setAttack(updatedBoss.getAttack()); // Changed from getDamage
            existingBoss.setDefense(updatedBoss.getDefense()); // Changed from getDefenseStat
            existingBoss.setFlashcards(updatedBoss.getFlashcards());
            return bossesRepository.save(existingBoss);
        } else {
            return null; // Handle not found scenario
        }
    }

    // Delete a boss
    @DeleteMapping("/bosses/{id}")
    public String deleteBoss(@PathVariable int id) {
        if (bossesRepository.existsById(id)) {
            bossesRepository.deleteById(id);
            return "Boss with ID " + id + " has been deleted.";
        } else {
            return "Boss with ID " + id + " not found.";
        }
    }
}
