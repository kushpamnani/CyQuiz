package onetoone.BanList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userName}")
    public ResponseEntity<BanList> getBanListByUserName(@PathVariable String userName) {
        Optional<BanList> banList = Optional.ofNullable(banListRepository.findByUserName(userName));
        return banList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
