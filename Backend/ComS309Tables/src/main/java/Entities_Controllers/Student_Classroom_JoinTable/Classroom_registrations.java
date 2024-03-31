package Entities_Controllers.Student_Classroom_JoinTable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Students.Student;

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
    @JoinColumn(name = "student_id")
    Student student;
    @JsonIgnoreProperties("studentRegistrations")
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    Classroom classroom;


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
}
