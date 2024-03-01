package onetoone.Flashcards;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Optional;


@RestController
public class FlashcardController {

    @Autowired
    private FlashcardRepository flashcardRepository;

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/flashcards")
    public List<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    @GetMapping(path = "/flashcards/{id}")
    public ResponseEntity<Flashcard> getFlashcardById(@PathVariable Long id) {
        return flashcardRepository.findById(id)
                .map(ResponseEntity::ok) // If flashcard is found, return it with 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }

    @PostMapping(path = "/flashcards")
    public ResponseEntity<String> createFlashcard(@RequestBody Flashcard flashcard) {
        flashcardRepository.save(flashcard);
        return ResponseEntity.ok(success);
    }

    @PutMapping(path = "/flashcards/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long id, @RequestBody Flashcard request) {
        Optional<Flashcard> updatedFlashcard = flashcardRepository.findById(id)
                .map(flashcard -> {
                    flashcard.setQuestion(request.getQuestion()); // Updating the question
                    flashcard.setAnswer(request.getAnswer()); // Updating the answer
                    // Make sure to update other fields as needed
                    flashcard.setOption1(request.getOption1());
                    flashcard.setOption2(request.getOption2());
                    flashcard.setOption3(request.getOption3());
                    return flashcardRepository.save(flashcard); // Save the updated flashcard
                });
        return updatedFlashcard
                .map(ResponseEntity::ok) // If update is successful, return the updated flashcard
                .orElseGet(() -> ResponseEntity.notFound().build()); // If original flashcard not found, return 404
    }


    @DeleteMapping(path = "/flashcards/{id}")
    public ResponseEntity<String> deleteFlashcard(@PathVariable Long id) {
        if (!flashcardRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        flashcardRepository.deleteById(id);
        return ResponseEntity.ok(success);
    }
}
