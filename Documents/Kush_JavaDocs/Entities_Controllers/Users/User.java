package Entities_Controllers.Users;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.User_Classroom_JoinTable.Classroom_registrationsRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import Entities_Controllers.User_Classroom_JoinTable.Classroom_registrations;

import java.util.Set;

import static jakarta.persistence.CascadeType.*;

/**
 * 
 * @author Vivek Bengre
 * 
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    @JsonIgnoreProperties("student")
    @OneToMany(mappedBy="student", cascade = {MERGE, REMOVE, REFRESH, DETACH})
    private Set<Classroom_registrations> classroomRegistrations;
    private boolean ifActive;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */


    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.ifActive = true;
    }

    public User() {
        this.ifActive = true;
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

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Set<Classroom_registrations> getClassroomRegistrations() {
        return classroomRegistrations;
    }

    public void setClassroomRegistrations(Set<Classroom_registrations> classroomRegistrations) {
        this.classroomRegistrations = classroomRegistrations;
    }
    public void addClassroomRegistration(Classroom_registrations classroom_registration) {
        this.classroomRegistrations.add(classroom_registration);
    }

    public boolean getIsActive() {
        return ifActive;
    }

    public void setIfActive(boolean ifActive) {
        this.ifActive = ifActive;
    }
}