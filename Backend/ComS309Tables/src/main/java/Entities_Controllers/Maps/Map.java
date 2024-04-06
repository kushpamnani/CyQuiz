package Entities_Controllers.Maps;

import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;
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
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seed;
    private int health;
    @JsonIgnoreProperties("map")
    @OneToOne(mappedBy="map")
    @JoinColumn(name = "classroom_registration_id")
    private Classroom_registrations classroomRegistration;


    public Map(int seed, int health) {
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

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Classroom_registrations getClassroomRegistration() {
        return classroomRegistration;
    }

    public void setClassroomRegistration(Classroom_registrations classroomRegistration) {
        this.classroomRegistration = classroomRegistration;
    }
}
