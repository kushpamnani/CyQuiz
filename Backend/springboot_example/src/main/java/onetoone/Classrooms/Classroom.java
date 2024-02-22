package onetoone.Classrooms;

import jakarta.persistence.*;
import onetoone.Users.User;

import java.util.Set;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String code;
    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    private String teacher;
    private Set<User> studentList;
}
