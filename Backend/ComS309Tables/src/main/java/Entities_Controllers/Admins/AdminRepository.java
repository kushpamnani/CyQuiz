package Entities_Controllers.Admins; // New package named Admin

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Kush Pamnani
 *
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findById(int id);

    @Transactional
    void deleteById(int id);

    Admin findByName(String name);

}
