package Entities_Controllers.Student_Classroom_JoinTable;

import Entities_Controllers.Maps.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Students.Student;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

/**
 *
 * @author Dalton Clark
 *
 */
@Entity
public class Classroom_registrations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonIgnoreProperties({"classroomRegistrations", "password"})
    @ManyToOne
    @JoinColumn
    Student student;
    @JsonIgnoreProperties("studentRegistrations")
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    Classroom classroom;
    @JsonIgnoreProperties("classroomRegistration")
    @OneToOne(mappedBy = "classroomRegistration", cascade = {MERGE, REMOVE, REFRESH, DETACH})
    @JoinColumn(name = "map_id")
    private Map map;


    public Classroom_registrations(Student student, Classroom classroom) {
        this.student = student;
        this.classroom = classroom;
    }

    public Classroom_registrations() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
