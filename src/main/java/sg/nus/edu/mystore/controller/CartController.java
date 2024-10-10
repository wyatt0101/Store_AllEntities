package sg.nus.edu.mystore.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sg.nus.edu.mystore.entity.Cart;
import sg.nus.edu.mystore.entity.Product;
import sg.nus.edu.mystore.entity.User;
import sg.nus.edu.mystore.service.CartImplementation;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartImplementation cartImpl;

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        User user1 = new User();
        user1.setId(1);
        session.setAttribute("user", user1);

        // start
        User user = (User) session.getAttribute("user");
        List<Cart> cartList = cartImpl.viewCartList(user.getId());
        session.setAttribute("cartList", cartList);

        // Retrieve Total price
        double totalPrice = cartImpl.calculateTotalPrice(user.getId());

        // Get selectedProductIds from session or initialize an empty list
        List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
        if (selectedProductIds == null) {
            selectedProductIds = new ArrayList<>();
        }
        model.addAttribute("cart", cartList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("selectedProductIds", selectedProductIds); // Add this line
        return "CartPage2";
    }


    @PostMapping("/cart/searching")
    public String searchProduct(@RequestParam("keyword") String k, HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<Cart> cartList = cartImpl.viewCartListByProductName(user.getId(), k);

        // Preserve selected product ids
        List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
        if (selectedProductIds == null) {
            selectedProductIds = new ArrayList<>();
        }
        session.setAttribute("cartList", cartList);

        model.addAttribute("cart", cartList);
        model.addAttribute("selectedProductIds", selectedProductIds); // Pass selectedProductIds to the model
        return "CartPage2";
    }

    @PostMapping("/cart/add")
    public String addProduct(@RequestParam("quantity") Integer quantity, @RequestParam("productId") Integer productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        cartImpl.addProductToCart(user.getId(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String removeCart(@RequestParam("productId") Integer productId, HttpSession session){
        User user = (User) session.getAttribute("user");
        cartImpl.removeProductFromCart(user.getId(), productId);

        List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
        session.setAttribute("selectedProductIds", selectedProductIds);
        return "redirect:/cart";
    }

    @PostMapping("cart/delete_all")
    public String removeAllCart(HttpSession session){
        User user = (User) session.getAttribute("user");
        cartImpl.removeAllProductsFromCart(user.getId());
        return "redirect:/cart";
    }

    // Update quantity of a product in cart
    @PostMapping("/cart/update")
    public String updateQuantity(@RequestParam("quantity") Integer quantity, @RequestParam("productId") Integer productId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        cartImpl.updateQuantity(user.getId(), productId, quantity);

        List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
        session.setAttribute("selectedProductIds", selectedProductIds);

        return "redirect:/cart";
    }


}