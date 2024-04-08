package Entities_Controllers.Events;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String condition1;
    private String condition2;
    private String hpChange;

    public Events() {
        // Default constructor for JPA
    }

    public Events(String title, String description, String condition1, String condition2, String hpChange) {
        this.title = title;
        this.description = description;
        this.condition1 = condition1;
        this.condition2 = condition2;
        this.hpChange = hpChange;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition1() {
        return condition1;
    }

    public void setCondition1(String condition1) {
        this.condition1 = condition1;
    }

    public String getCondition2() {
        return condition2;
    }

    public void setCondition2(String condition2) {
        this.condition2 = condition2;
    }

    public String getHpChange() {
        return hpChange;
    }

    public void setHpChange(String hpChange) {
        this.hpChange = hpChange;
    }
}
