package sg.nus.edu.mystore.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.edu.mystore.entity.Cart;
import sg.nus.edu.mystore.entity.Product;
import sg.nus.edu.mystore.entity.User;
import sg.nus.edu.mystore.interfacemethods.CartInterface;
import sg.nus.edu.mystore.repository.CartRepository;
import sg.nus.edu.mystore.repository.ProductRepository;
import sg.nus.edu.mystore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartImplementation implements CartInterface {
    @Autowired
    private CartRepository CartRepo;
    @Autowired
    private UserRepository UserRepo;
    @Autowired
    private ProductRepository productRepo;

    @Transactional
    @Override
    public void addProductToCart(Integer userId, Integer productId, int quantity){
        Cart cart = CartRepo.findByUserIdAndProductId(userId, productId);

        if(cart != null) {
            cart.setQuantity(quantity + cart.getQuantity());
        }
        else {
            User user = UserRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id:" + userId));
            Product product  = productRepo.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id:" + productId));

            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(quantity);
        }
        CartRepo.save(cart);
    }

    @Transactional
    @Override
    public void removeProductFromCart(Integer userId, Integer productId) {
         CartRepo.deleteByUserIdAndProductId(userId, productId);
    }

    @Transactional
    @Override
    public void removeAllProductsFromCart(Integer user_id){
        CartRepo.deletaByUserId(user_id);
    }

    @Transactional
    @Override
    public List<Cart> viewCartList(Integer userId) {
        List<Cart> cartProductList = CartRepo.findByUserId(userId);
        return cartProductList;
    }

    @Transactional
    @Override
    public List<Cart> viewCartListByProductName(Integer userId, String productName) {
        List<Cart> cartProductList = CartRepo.findByUserIdAndProductName(userId, productName);
        return cartProductList;
    }

    @Transactional
    @Override
    public void updateQuantity(Integer userId, Integer productId, int quantity) {
        Cart cart = CartRepo.findByUserIdAndProductId(userId, productId);
        cart.setQuantity(quantity);
        CartRepo.save(cart);
    }

    @Transactional
    @Override
    public double calculateTotalPrice(Integer user_id){
        double totalPrice = CartRepo.findTotalPriceByUserId(user_id);
        return totalPrice;
    }

}
