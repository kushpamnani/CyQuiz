package Entities_Controllers;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Enemies.Enemy;
import Entities_Controllers.Flashcards.Flashcard;
import Entities_Controllers.Students.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DaltonSystemTest {

    @LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAndGetStudent() throws Exception {
        Student s = new Student("studentexample","passwordexample");
        Student stud = this.restTemplate.postForObject("http://localhost:" + port + "/students",s,Student.class);

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/" + stud.getId(),
                Student.class).getName()).isEqualTo("studentexample");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/" + stud.getId(),
                Student.class).getPassword()).isEqualTo("passwordexample");
    }

    @Test
    void createAndGetClassroom() throws Exception {
        Classroom c = new Classroom("classroomexample");
        Classroom classroom = this.restTemplate.postForObject("http://localhost:" + port + "/classrooms",c,Classroom.class);

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/classrooms/" + classroom.getId(),
                Classroom.class).getName()).isEqualTo("classroomexample");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/classrooms/" + classroom.getId(),
                Classroom.class).getCode()).isGreaterThan(999).isLessThan(10000);
    }

    @Test
    void createAndGetEnemy() throws Exception {
        Enemy e = new Enemy("enemyexample",1,2,3);
        Enemy enemy = this.restTemplate.postForObject("http://localhost:" + port + "/enemies",e,Enemy.class);

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/enemies/" + enemy.getId(),
                Enemy.class).getName()).isEqualTo("enemyexample");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/enemies/" + enemy.getId(),
                Enemy.class).getHealth()).isEqualTo(1);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/enemies/" + enemy.getId(),
                Enemy.class).getAttack()).isEqualTo(2);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/enemies/" + enemy.getId(),
                Enemy.class).getDefense()).isEqualTo(3);
    }

    @Test
    void createAndGetFlashcard() throws Exception {
        Flashcard f = new Flashcard("question","answer","option1","option2","option3");
        Flashcard flashcard = this.restTemplate.postForObject("http://localhost:" + port + "/flashcards",f,Flashcard.class);

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/flashcards/" + flashcard.getId(),
                Flashcard.class).getQuestion()).isEqualTo("question");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/flashcards/" + flashcard.getId(),
                Flashcard.class).getAnswer()).isEqualTo("answer");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/flashcards/" + flashcard.getId(),
                Flashcard.class).getOption1()).isEqualTo("option1");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/flashcards/" + flashcard.getId(),
                Flashcard.class).getOption2()).isEqualTo("option2");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/flashcards/" + flashcard.getId(),
                Flashcard.class).getOption3()).isEqualTo("option3");
    }
}
