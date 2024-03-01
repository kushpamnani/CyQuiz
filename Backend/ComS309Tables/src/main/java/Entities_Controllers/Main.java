package Entities_Controllers;

import Entities_Controllers.Classrooms.Classroom;
import Entities_Controllers.Classrooms.ClassroomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Entities_Controllers.Users.User;
import Entities_Controllers.Users.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Vivek Bengre
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
     * @param userRepository repository for the User entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, ClassroomRepository classroomRepository) {
        return args -> {

        };
    }

}
