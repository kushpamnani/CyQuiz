package Entities_Controllers.Leaderboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 
 * @author Kush Pamnani
 * 
 */

@RestController
public class LeaderboardController {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @GetMapping(path = "/leaderboard")
    public List<Leaderboard> getAllEntries() {
        return leaderboardRepository.findAll();
    }

    @GetMapping(path = "/leaderboard/{id}")
    public ResponseEntity<Leaderboard> getEntryById(@PathVariable Long id) {
        return leaderboardRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/leaderboard")
    public Leaderboard createEntry(@RequestBody Leaderboard entry) {
        return leaderboardRepository.save(entry);
    }

    @PutMapping(path = "/leaderboard/{id}")
    public ResponseEntity<Leaderboard> updateEntry(@PathVariable Long id, @RequestBody Leaderboard updatedEntry) {
        return leaderboardRepository.findById(id)
            .map(existingEntry -> {
                existingEntry.setPlayerName(updatedEntry.getPlayerName());
                existingEntry.setScore(updatedEntry.getScore());
                return ResponseEntity.ok(leaderboardRepository.save(existingEntry));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/leaderboard/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        return leaderboardRepository.findById(id)
            .map(entry -> {
                leaderboardRepository.delete(entry);
                return ResponseEntity.ok("{\"message\":\"Deleted successfully\"}");
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
