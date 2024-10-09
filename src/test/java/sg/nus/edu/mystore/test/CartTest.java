//package sg.nus.edu.mystore.test;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import sg.nus.edu.mystore.entity.Cart;
//import sg.nus.edu.mystore.entity.Product;
//import sg.nus.edu.mystore.entity.User;
//import sg.nus.edu.mystore.repository.ProductRepository;
//import sg.nus.edu.mystore.repository.UserRepository;
//import sg.nus.edu.mystore.service.CartImplementation;
//
//import java.util.List;
//
//@SpringBootTest
//public class CartTest {
//    @Autowired
//    private CartImplementation cartImpl;
//
//    @Autowired
//    private ProductRepository productRepo;
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Test
//    void CreateUserTest() {
//        User user1 = new User("wyatt", "123456", "111", "18023123200", "wyattwei2001@gmail.com", 1, "头像路径", null);
//        userRepo.save(user1);
//    }
//
//    @Test
//    void CreateProductTest() {
//        Product product1 = new Product("牙刷", "用于牙齿清洁", 10, 1000, "生活用品");
//        Product product2 = new Product("显示器", "用于显示电脑", 500, 900, "电子产品");
//        Product product3 = new Product("葡萄酒", "用于喝酒", 600, 500, "酒精饮品");
//        productRepo.save(product1);
//        productRepo.save(product2);
//        productRepo.save(product3);
//    }
//
//    @Test
//    void AddToCartTest() {
//        User user1 = userRepo.findByUsername("wyatt");
//        Product product1 = productRepo.findByProductName("牙刷");
//        Product product2 = productRepo.findByProductName("显示器");
//        cartImpl.addProductToCart(user1.getId(), product1.getId(), 3);
//        cartImpl.addProductToCart(user1.getId(), product2.getId(), 1);
//    }
//
//    @Test
//    void RemoveFromCartTest() {
//        User user1 = userRepo.findByUsername("wyatt");
//        Product product1 = productRepo.findByProductName("牙刷");
//        cartImpl.removeProductFromCart(user1.getId(), product1.getId());
//    }
//
//    @Test
//    void RemoveAllFromCartTest() {
//        User user1 = userRepo.findByUsername("wyatt");
//        cartImpl.removeAllProductsFromCart(user1.getId());
//    }
//
//    @Test
//    void ViewCartTest() {
//        User user1 = userRepo.findByUsername("wyatt");
//        List<Cart> cartList = cartImpl.viewCartList(user1.getId());
//        for (Cart cart : cartList) {
//            System.out.println(cart);
//        }
//    }
//
//    @Test
//    void searchCartTest() {
//        User user1 = userRepo.findByUsername("wyatt");
//        List<Cart> cartList = cartImpl.viewCartListByProductName(user1.getId(), "牙");
//        for (Cart cart : cartList) {
//            System.out.println(cart);
//        }
//    }
//
//    @Test
//    void updateCartTest() {
//        User user1 = userRepo.findByUsername("wyatt");
//        Product product1 = productRepo.findByProductName("牙刷");
//        cartImpl.updateQuantity(user1.getId(), product1.getId(), 5);
//    }
//
//    @Test
//    void calculateTotalPriceTest() {
//        User user1 = userRepo.findByUsername("wyatt");
//        double totalPrice = cartImpl.calculateTotalPrice(user1.getId());
//        System.out.println(totalPrice);
//    }
//
//    // 边缘情况：尝试添加不存在的用户到购物车
////    @Test
////    void AddToCartWithNonExistentUserTest() {
////        assertThrows(IllegalArgumentException.class, () -> {
////            cartImpl.addProductToCart(-1L, 1L, 1); // 使用不存在的用户ID
////        });
////    }
////
////    // 边缘情况：尝试添加不存在的产品到购物车
////    @Test
////    void AddToCartWithNonExistentProductTest() {
////        User user1 = userRepo.findByUsername("wyatt");
////        assertThrows(IllegalArgumentException.class, () -> {
////            cartImpl.addProductToCart(user1.getId(), -1L, 1); // 使用不存在的产品ID
////        });
////    }
////
////    // 边缘情况：添加负数数量的产品到购物车
////    @Test
////    void AddNegativeQuantityToCartTest() {
////        User user1 = userRepo.findByUsername("wyatt");
////        Product product1 = productRepo.findByProductName("牙刷");
////        assertThrows(IllegalArgumentException.class, () -> {
////            cartImpl.addProductToCart(user1.getId(), product1.getId(), -1); // 尝试添加负数量
////        });
////    }
////
////    // 边缘情况：查看空购物车
////    @Test
////    void ViewEmptyCartTest() {
////        User user1 = userRepo.findByUsername("nonexistent_user"); // 确保这个用户不存在
////        var cart = cartImpl.viewCart(user1.getId());
////        assertNotNull(cart); // 购物车对象不应为空
////        assertTrue(cart.getProducts().isEmpty()); // 确保购物车是空的
////    }
//}
