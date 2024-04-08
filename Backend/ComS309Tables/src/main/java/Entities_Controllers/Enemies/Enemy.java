package Entities_Controllers.Enemies;

import Entities_Controllers.Flashcards.Flashcard;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;
import Entities_Controllers.Teachers.Teacher;
import Entities_Controllers.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

import static jakarta.persistence.CascadeType.*;

/**
 * 
 * @author Dalton Clark
 * 
 */
@Entity
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int health;
    private int attack;
    private int defense;
    @JsonIgnoreProperties("enemies")
    @ManyToOne
    @JoinColumn(name="flashcard_id")
    private Flashcard flashcard;
    @JsonIgnoreProperties({"classrooms", "password"}) // might need to change
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;


    public Enemy(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public Enemy() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Flashcard getFlashcard() {
        return flashcard;
    }

    public void setFlashcard(Flashcard flashcard) {
        this.flashcard = flashcard;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
