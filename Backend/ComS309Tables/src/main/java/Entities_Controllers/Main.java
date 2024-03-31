package Entities_Controllers;

import Entities_Controllers.Classrooms.ClassroomRepository;
import Entities_Controllers.Students.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author Dalton Clark
 * 
 */ 

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines
    /**
     * 
     * @param studentRepository repository for the User entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in Student.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        return args -> {

        };
    }

}
