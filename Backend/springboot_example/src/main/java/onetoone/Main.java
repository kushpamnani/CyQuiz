package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import onetoone.Roles.Role;
import onetoone.Roles.RoleRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    CommandLineRunner initUser(UserRepository userRepository, RoleRepository roleRepository) {
//        return args -> {
//            // Create roles
//            Role role1 = new Role(1, "Admin");
//            Role role2 = new Role(2, "User");
//            Role role3 = new Role(3, "Guest");
//
//            // Save roles to the repository
//            roleRepository.save(role1);
//            roleRepository.save(role2);
//            roleRepository.save(role3);
//
//            // Create users and assign roles
//            User user1 = new User("John", "john@somemail.com");
//            //user1.setRole(role1);
//
//            User user2 = new User("Jane", "jane@somemail.com");
//            //user2.setRole(role2);
//
//            User user3 = new User("Justin", "justin@somemail.com");
//            //user3.setRole(role3);
//
//            // Save users to the repository
//            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.save(user3);
//        };
//    }
}
