package onetoone.Laptops;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import onetoone.Users.User;

/**
 *
 * @author Vivek Bengre
 */

@Entity
public class Flashcard {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Question;
    private String Answer;
    private String Option1;
    private String Option2;
    private String Option3;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * @JsonIgnore is to assure that there is no infinite loop while returning either user/laptop objects (laptop->user->laptop->...)
     */
    @OneToOne
    @JsonIgnore
    private Flashcard flashcard;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public onetoone.Laptops.Flashcard getFlashcard() {
        return flashcard;
    }

    public void setFlashcard(onetoone.Laptops.Flashcard flashcard) {
        this.flashcard = flashcard;
    }

    public Flashcard(String Question, String Answer, String Option1, String Option2, String Option3) {
        this.Question = Question;
        this.Answer = Answer;
        this.Option1 = Option1;
        this.Option2 = Option2;
        this.Option3 = Option3;
    }

    public Flashcard() {
    }



}
