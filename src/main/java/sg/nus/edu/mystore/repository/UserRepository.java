package sg.nus.edu.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.edu.mystore.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :name")
    User findByUsername(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findById(@Param("id") Integer id);
}
