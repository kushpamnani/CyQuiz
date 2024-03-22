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
 * @author Dalton Clark
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
