package sg.nus.edu.mystore.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.edu.mystore.entity.Cart;
import sg.nus.edu.mystore.entity.Product;
import sg.nus.edu.mystore.entity.User;
import sg.nus.edu.mystore.interfacemethods.CartInterface;
import sg.nus.edu.mystore.repository.CartProductRepository;
import sg.nus.edu.mystore.repository.ProductRepository;
import sg.nus.edu.mystore.repository.UserRepository;

import java.util.List;

@Service
public class CartImplementation implements CartInterface {
    @Autowired
    private CartProductRepository cartProductRepo;
    @Autowired
    private UserRepository UserRepo;
    @Autowired
    private ProductRepository productRepo;

    @Transactional
    @Override
    public void addProductToCart(Integer userId, Integer productId, int quantity){
        Cart cartProduct = cartProductRepo.findCartProductByCartIdAndProductId(userId, productId);

        if(cartProduct != null) {
            cartProduct.setQuantity(quantity + cartProduct.getQuantity());
        }
        else {
            User user = UserRepo.findById(userId).get();
            Product product = productRepo.findById(userId).get();

            cartProduct = new Cart();
            cartProduct.setUser(user);
            cartProduct.setProduct(product);
            cartProduct.setQuantity(quantity);

            cartProductRepo.save(cartProduct);
        }
    }

    @Transactional
    @Override
    public void removeProductFromCart(Integer userId, Integer productId) {
         cartProductRepo.deleteCartProductByCartIdAndProductId(userId, productId);
    }

    @Transactional
    @Override
    public void removeAllProductsFromCart(Integer user_id){
        cartProductRepo.deletaAllCartProductByUserId(user_id);
    }

    @Transactional
    @Override
    public List<Cart> viewCartList(Integer userId) {
        List<Cart> cartProductList = cartProductRepo.findCartProductByUserId(userId);
        return cartProductList;
    }

    @Transactional
    @Override
    public void updateQuantity(Integer userId, Integer productId, int quantity) {
        Cart cartProduct = cartProductRepo.findCartProductByCartIdAndProductId(userId, productId);
        cartProduct.setQuantity(quantity);
        cartProductRepo.save(cartProduct);
    }

    @Transactional
    @Override
    public void emptyCart(Integer userId) {
        cartProductRepo.deletaAllCartProductByUserId(userId);
    }
}
