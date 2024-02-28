package onetoone.User_Classroom_JoinTable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import onetoone.Classrooms.Classroom;
import onetoone.Teachers.Teacher;
import onetoone.Users.User;

import java.util.Set;

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
