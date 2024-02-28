package onetoone.Classrooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import onetoone.Teachers.Teacher;
import onetoone.User_Classroom_JoinTable.Classroom_registrations;
import onetoone.Users.User;

import java.util.Set;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String code;
    @JsonIgnoreProperties("classrooms")
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy="classroom")
    @JsonIgnore
    private Set<Classroom_registrations> studentRegistrations;


    public Classroom(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
}
