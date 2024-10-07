package sg.nus.edu.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.nus.edu.mystore.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
