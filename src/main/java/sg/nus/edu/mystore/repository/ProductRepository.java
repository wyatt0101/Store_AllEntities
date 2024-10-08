package sg.nus.edu.mystore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.edu.mystore.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name =:name")
    Product findByProductName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.id =:id")
    Optional<Product> findById(@Param("id") Integer id);
}
