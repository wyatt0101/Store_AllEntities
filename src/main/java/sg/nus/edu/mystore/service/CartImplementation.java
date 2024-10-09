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
    public void addProductToCart(@Valid Cart cart){
        Cart existingCart = CartRepo.findByUserIdAndProductId(cart.getUser().getId(), cart.getProduct().getId());

        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            CartRepo.save(existingCart);
        } else {
            CartRepo.save(cart);
        }
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
        if (cartProductList.isEmpty()) {
            throw new EntityNotFoundException("No cart items found for userId: " + userId);
        }
        return cartProductList;
    }

    @Transactional
    @Override
    public List<Cart> viewCartListByProductName(Integer userId, String productName) {
        List<Cart> cartProductList = CartRepo.findByUserIdAndProductName(userId, productName);
        if (cartProductList.isEmpty()) {
            throw new EntityNotFoundException("No cart items found for " + productName);
        }
        return cartProductList;
    }

    /**
     * Update quantity of a product in the user's cart.
     */
    @Transactional
    @Override
    public void updateQuantity(Cart cart) {
        Cart existingCart = CartRepo.findByUserIdAndProductId(cart.getUser().getId(), cart.getProduct().getId());
        if (existingCart == null) {
            throw new EntityNotFoundException("Cart entry not found for userId: " + cart.getUser().getId() + " and productId: " + cart.getProduct().getId());
        }
        existingCart.setQuantity(cart.getQuantity());
        CartRepo.save(existingCart);
    }


    @Transactional
    @Override
    public double calculateTotalPrice(Integer user_id){
        Double totalPrice = CartRepo.findTotalPriceByUserId(user_id);
        if (totalPrice == null) {
            throw new EntityNotFoundException("No cart items found for userId: " + user_id);
        }
        return totalPrice;
    }

}
