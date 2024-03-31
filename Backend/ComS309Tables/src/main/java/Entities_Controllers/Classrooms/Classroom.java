package Entities_Controllers.Classrooms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import Entities_Controllers.Teachers.Teacher;
import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;

import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

/**
 *
 * @author Dalton Clark
 *
 */
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int code;
    @JsonIgnoreProperties({"classrooms", "password"})
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @JsonIgnoreProperties("classroom")
    @OneToMany(mappedBy="classroom", cascade = {MERGE, REMOVE, REFRESH, DETACH})
    private Set<Classroom_registrations> studentRegistrations;



    public Classroom(String name) {
        this.name = name;
    }

    public Classroom() {
    }



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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Classroom_registrations> getStudentRegistrations() {
        return studentRegistrations;
    }

    public void setStudentRegistrations(Set<Classroom_registrations> studentRegistrations) {
        this.studentRegistrations = studentRegistrations;
    }

    public void addStudentRegistration(Classroom_registrations classroom_registration) {
        this.studentRegistrations.add(classroom_registration);
    }
}
