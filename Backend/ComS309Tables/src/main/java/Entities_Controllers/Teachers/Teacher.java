package Entities_Controllers.Teachers;

import Entities_Controllers.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Enemies.Enemy;

import java.util.Set;

/**
 *
 * @author Dalton Clark
 *
 */

@Entity
public class Teacher implements User {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    @JsonIgnoreProperties("teacher")
    @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL)
    private Set<Classroom> classrooms;
    @JsonIgnoreProperties("teacher") // might need to fix later
    @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL)
    private Set<Enemy> enemies;
    private boolean ifActive;

    public Teacher(String name, String password) {
        this.name = name;
        this.password = password;
        this.ifActive = true;
    }

    public Teacher() {
        this.ifActive = true;
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Set<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Set<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public void addClassroom(Classroom classroom) {
        this.classrooms.add(classroom);
    }

    public Set<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Set<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addEnemy(Enemy enemy) { this.enemies.add(enemy); }

    public boolean getIsActive(){
        return ifActive;
    }

    public void setIfActive(boolean ifActive){
        this.ifActive = ifActive;
    }
}
