package Entities_Controllers.Maps;

import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registration;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 *
 * @author Dalton Clark
 *
 */
@Entity
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seed;
    private int health;
    @JsonIgnoreProperties("map")
    @OneToOne
    @JoinColumn(name = "classroom_registration_id")
    private Classroom_registration classroomRegistration;


    public Map(String seed, int health) {
        this.seed = seed;
        this.health = health;
    }

    public Map() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Classroom_registration getClassroomRegistration() {
        return classroomRegistration;
    }

    public void setClassroomRegistration(Classroom_registration classroomRegistration) {
        this.classroomRegistration = classroomRegistration;
    }
}
