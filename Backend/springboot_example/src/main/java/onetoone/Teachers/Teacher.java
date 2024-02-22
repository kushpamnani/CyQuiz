package onetoone.Teachers;

import jakarta.persistence.*;

import onetoone.Classrooms.Classroom;
import onetoone.Laptops.Laptop;

import java.util.Set;

/**
 *
 * @author Vivek Bengre
 *
 */

@Entity
public class Teacher {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL)
    private Set<Classroom> classrooms;
    private boolean ifActive;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    public Teacher(String name, String password) {
        this.name = name;
        this.password = password;
        this.ifActive = true;
    }

    public Teacher() {
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

    public Classroom getClassroom(){
        return classroom;
    }

    public void setClassroom(Classroom classroom) { this.classroom = classroom; }

    public boolean getIsActive(){
        return ifActive;
    }

    public void setIfActive(boolean ifActive){
        this.ifActive = ifActive;
    }
}
