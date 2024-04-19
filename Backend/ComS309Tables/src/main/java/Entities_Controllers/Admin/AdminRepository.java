package Entities_Controllers.Admin; // New package named Admin

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findById(int id);

    @Transactional
    void deleteById(int id);

    Admin findByUsername(String name);

}
