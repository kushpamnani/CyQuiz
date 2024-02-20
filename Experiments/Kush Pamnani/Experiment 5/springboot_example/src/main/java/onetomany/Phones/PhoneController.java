package onetomany.Phones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private PhoneService phoneService;

    @GetMapping(path = "/phones")
    public ResponseEntity<List<Phone>> getAllPhones(){
        List<Phone> phones = phoneRepository.findAll();
        return ResponseEntity.ok(phones);
    }

    @GetMapping(path = "/phones/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable int id){
        Optional<Phone> phone = phoneRepository.findById(id);
        return phone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/phones")
    public ResponseEntity<String> createPhone(@RequestBody Phone phone){
        if (phone == null) {
            return ResponseEntity.badRequest().body("{\"message\":\"failure\"}");
        }
        phoneRepository.save(phone);
        return ResponseEntity.ok("{\"message\":\"success\"}");
    }

    @PutMapping("/phones/{id}")
    public ResponseEntity<Phone> updatePhone(@PathVariable int id, @RequestBody Phone request){
        Phone updatedPhone = phoneService.updatePhone(id, request); // Assuming update logic is in the service
        if (updatedPhone == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPhone);
    }
}
