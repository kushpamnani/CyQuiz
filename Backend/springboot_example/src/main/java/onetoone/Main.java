package onetoone;

import onetoone.Classrooms.Classroom;
import onetoone.Classrooms.ClassroomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import onetoone.Users.User;
import onetoone.Users.UserRepository;

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
     * @param laptopRepository repository for the Laptop entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, ClassroomRepository classroomRepository) {
        return args -> {
            User user1 = new User("John", "password 1");
            User user2 = new User("Jane", "password 2");
            User user3 = new User("Justin", "password 3");
            Classroom classroom1 = new Classroom("classroom1", "ASDF");
            Classroom classroom2 = new Classroom("classroom2", "QWER");
            Classroom classroom3 = new Classroom("classroom3", "ZXCV");
            classroomRepository.save(classroom1);
            classroomRepository.save(classroom2);
            classroomRepository.save(classroom3);
            user1.setClassroom(classroom1);
            user2.setClassroom(classroom2);
            user3.setClassroom(classroom3);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        };
    }

}
