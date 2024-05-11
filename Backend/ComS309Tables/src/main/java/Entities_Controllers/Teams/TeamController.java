package Entities_Controllers.Teams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        return teamRepository.findById(id)
                .map(team -> {
                    team.setName(updatedTeam.getName());
                    team.setDescription(updatedTeam.getDescription());
                    return teamRepository.save(team);
                }).orElseGet(() -> {
                    updatedTeam.setId(id);
                    return teamRepository.save(updatedTeam);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamRepository.deleteById(id);
    }
}
