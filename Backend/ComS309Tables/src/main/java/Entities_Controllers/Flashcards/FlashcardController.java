package Entities_Controllers.Flashcards;

import java.util.List;

import Entities_Controllers.Admins.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

/**
 *
 * @author Dalton Clark
 *
 */
@RestController
public class FlashcardController {

    @Autowired
    private FlashcardRepository flashcardRepository;

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/flashcards")
    List<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    @GetMapping(path = "/flashcards/{id}")
    Flashcard getFlashcardById(@PathVariable int id) {
        return flashcardRepository.findById(id);
    }

    @PostMapping(path = "/flashcards")
    <T> T createFlashcard(@RequestBody Flashcard flashcard){
        if (flashcard == null) {
            return (T) failure;
        }
        flashcardRepository.save(flashcard);
        return (T) flashcard;
    }

    @PutMapping(path = "/flashcards/{id}")
    Flashcard updateFlashcard(@PathVariable int id, @RequestBody Flashcard request) {
        Flashcard flashcard = flashcardRepository.findById(id);

        if (flashcard == null) {
            throw new RuntimeException("flashcard id does not exist");
        }
        if (request.getId() == 0) {
            request.setId(flashcard.getId());
        }
        if (request.getQuestion() == null) {
            request.setQuestion(flashcard.getQuestion());
        }
        if (request.getAnswer() == null) {
            request.setAnswer(flashcard.getAnswer());
        }
        if (request.getOption1() == null) {
            request.setOption1(flashcard.getOption1());
        }
        if (request.getOption2() == null) {
            request.setOption2(flashcard.getOption2());
        }
        if (request.getOption3() == null) {
            request.setOption3(flashcard.getOption3());
        }
        if (request.getEnemies() == null) {
            request.setEnemies(flashcard.getEnemies());
        }

        flashcardRepository.save(request);
        return flashcardRepository.findById(request.getId());
    }


    @DeleteMapping(path = "/flashcards/{id}")
    String deleteFlashcard(@PathVariable int id) {
        if (flashcardRepository.findById(id) == null) {
            return failure;
        } else {
            flashcardRepository.deleteById(id);
            return success;
        }
    }
}
