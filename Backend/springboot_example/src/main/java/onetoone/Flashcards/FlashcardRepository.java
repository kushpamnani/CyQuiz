package onetoone.Flashcards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    Flashcard findById(int id);

    @Transactional
    void deleteById(int id);
}
