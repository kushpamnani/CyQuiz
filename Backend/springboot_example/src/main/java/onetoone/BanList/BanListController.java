package onetoone.BanList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import java.util.Optional;

@RestController
@RequestMapping("/banlists")
public class BanListController {

    private final BanListRepository banListRepository;

    @Autowired
    public BanListController(BanListRepository banListRepository) {
        this.banListRepository = banListRepository;
    }

    @PostMapping
    public ResponseEntity<BanList> createBanList(@RequestBody BanList banList) {
        BanList savedBanList = banListRepository.save(banList);
        return ResponseEntity.ok(savedBanList);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<String>> getAllBannedUsers() {
        List<BanList> allBanLists = banListRepository.findAll();
        Set<String> allBannedUsers = allBanLists.stream()
                .map(BanList::getBannedUsers) // Assuming getBannedUsers returns the comma-separated String
                .filter(Objects::nonNull)
                .flatMap(bannedUsers -> Arrays.stream(bannedUsers.split(",")))
                .collect(Collectors.toSet()); // Using a Set to avoid duplicates

        return ResponseEntity.ok(allBannedUsers);
    }

    @PutMapping("/add/{userName}")
    public ResponseEntity<BanList> addBannedUser(@PathVariable String userName, @RequestBody String userToBan) {
        BanList banList = banListRepository.findByUserName(userName);
        if (banList != null) {
            banList.addBannedUser(userToBan);
            banListRepository.save(banList);
            return ResponseEntity.ok(banList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/remove/{userName}")
    public ResponseEntity<?> removeBannedUser(@PathVariable String userName, @RequestBody String userToRemove) {
        BanList banList = banListRepository.findByUserName(userName);
        if (banList != null) {
            banList.removeBannedUser(userToRemove);
            banListRepository.save(banList);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
