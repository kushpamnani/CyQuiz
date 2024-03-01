package onetoone.Flashcards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import onetoone.Users.User;
import onetoone.Users.UserRepository;

/**
 *
 * @author Vivek Bengre
 *
 */

@RestController
public class FlashcardController {

    @Autowired
    FlashcardRepository flashcardRepository;
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/flashcards")
    List<Flashcard> getAllFlashcards(){
        return flashcardRepository.findAll();
    }

    @GetMapping(path = "/flashcards/{id}")
    Flashcard getFlashcardById(@PathVariable int id){
        return flashcardRepository.findById(id);
    }

    @PostMapping(path = "/flashcards")
    String createFlashcard(Flashcard Flashcard){
        if (Flashcard == null)
            return failure;
        flashcardRepository.save(Flashcard);
        return success;
    }

    @PutMapping(path = "/flashcards/{id}")
    Flashcard updateFlashcard(@PathVariable int id, Flashcard request){
        Flashcard flashcard = flashcardRepository.findById(id);
        if(flashcard == null)
            return null;
        flashcardRepository.save(request);
        return flashcardRepository.findById(id);
    }

    @DeleteMapping(path = "/flashcards/{id}")
    String deleteFlashcard(@PathVariable int id){


        // delete the laptop if the changes have not been reflected by the above statement
        flashcardRepository.deleteById(id);
        return success;
    }
}
