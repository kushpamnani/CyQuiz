package Entities_Controllers.User_Classroom_JoinTable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Users.User;

@Entity
public class Classroom_registrations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonIgnoreProperties("classroomRegistrations")
    @ManyToOne
    @JoinColumn(name = "student_id")
    User student;
    @JsonIgnoreProperties("studentRegistrations")
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    Classroom classroom;


    public Classroom_registrations(User student, Classroom classroom) {
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

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
