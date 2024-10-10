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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartImplementation implements CartInterface {
    @Autowired
    private CartRepository CartRepo;
    @Autowired
    private UserRepository UserRepo;
    @Autowired
    private ProductRepository productRepo;

    /**
     * Add product to cart or update existing product quantity.
     */
    @Transactional
    @Override
    public void addProductToCart(Integer userId, Integer productId, Integer quantity){
        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        // Check if the product is already in the cart
        Cart cart = CartRepo.findByUserIdAndProductId(userId, productId);

        if (cart != null) {
            // Update quantity if product is already in cart
            cart.setQuantity(cart.getQuantity() + quantity);
        } else {
            // Find user and product entities
            User user = UserRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

            // Create new cart entry
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(quantity);
        }

        // Save the cart entry (new or updated)
        CartRepo.save(cart);
    }

    /**
     * Remove products from the user's cart.
     */
    @Transactional
    @Override
    public void removeProductFromCart(Integer userId, Integer productId) {
        Cart cart = CartRepo.findByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new EntityNotFoundException("Cart entry not found for userId: " + userId + " and productId: " + productId);
        }
         CartRepo.deleteByUserIdAndProductId(userId, productId);
    }

    @Transactional
    @Override
    public void removeAllProductsFromCart(Integer user_id){
        CartRepo.deletaByUserId(user_id);
    }

    /**
     * View products in the user's cart.
     */
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

    /**
     * Update quantity of a product in the user's cart.
     */
    @Transactional
    @Override
    public void updateQuantity(Integer userId, Integer productId, Integer quantity) {
        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        // Check if the cart entry exists
        Cart cart = CartRepo.findByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new EntityNotFoundException("Cart entry not found! ");
        }
        // Update quantity
        cart.setQuantity(quantity);
        CartRepo.save(cart);
    }

    @Transactional
    @Override
    public double calculateTotalPrice(Integer user_id){
        Double totalPrice = CartRepo.findTotalPriceByUserId(user_id);
        if (totalPrice == null) {
            return 0.0;
        }
        return totalPrice;
    }

}
