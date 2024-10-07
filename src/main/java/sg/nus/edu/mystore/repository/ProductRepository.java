package sg.nus.edu.mystore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sg.nus.edu.mystore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
