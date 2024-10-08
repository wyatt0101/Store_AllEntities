package sg.nus.edu.mystore.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.nus.edu.mystore.entity.Product;
import sg.nus.edu.mystore.entity.User;
import sg.nus.edu.mystore.repository.ProductRepository;
import sg.nus.edu.mystore.repository.UserRepository;
import sg.nus.edu.mystore.service.CartImplementation;

@SpringBootTest
public class CartTest {
    @Autowired
    private CartImplementation cartImpl;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    void CreateUserTest() {
        User user1 = new User("wyatt", "123456", "111", "18023123200", "wyattwei2001@gmail.com", 1, "头像路径", null);
        userRepo.save(user1);
    }

    @Test
    void CreateProductTest() {
        Product product1 = new Product("牙刷", "用于牙齿清洁", 10, 1000, "生活用品");
        Product product2 = new Product("显示器", "用于显示电脑", 500, 900, "电子产品");
        Product product3 = new Product("葡萄酒", "用于喝酒", 600, 500, "酒精饮品");
        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);
    }

    @Test
    void AddToCartTest() {
        User user1 = userRepo.findByUsername("wyatt");
        Product product1 = productRepo.findByProductName("牙刷");
        Product product2 = productRepo.findByProductName("显示器");
        cartImpl.addProductToCart(user1.getId(), product1.getId(), 3);
        cartImpl.addProductToCart(user1.getId(), product2.getId(), 1);
    }

    @Test
    void ViewCartTest() {
        // TODO: Implement this test

    }
}
