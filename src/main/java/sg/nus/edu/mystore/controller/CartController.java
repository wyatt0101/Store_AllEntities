package sg.nus.edu.mystore.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.nus.edu.mystore.entity.Cart;
import sg.nus.edu.mystore.entity.Product;
import sg.nus.edu.mystore.entity.User;
import sg.nus.edu.mystore.service.CartImplementation;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartImplementation cartImpl;

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // retrieve Cart List
        List<Cart> cartList = cartImpl.viewCartList(user.getId());
        // retrieve Total price
        double totalPrice = cartImpl.calculateTotalPrice(user.getId());

        model.addAttribute("cart", cartList);
        model.addAttribute("totalPrice", totalPrice);

        return "CartPage";
    }

    @PostMapping("/cart/searching")
    public String searchProduct(@RequestParam("keyword") String k, HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<Cart> cartList = cartImpl.viewCartListByProductName(user.getId(), k);
        model.addAttribute("cart", cartList);
        return "redirect:/cart";
    }

    @PostMapping("/cart/add")
    public String addProduct(@RequestParam("quantity") Integer quantity, @RequestParam("product") Product product, HttpSession session) {
        User user = (User) session.getAttribute("user");
        cartImpl.addProductToCart(user.getId(), product.getId(), quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String removeCart(@RequestParam("product") Product product, HttpSession session){
        User user = (User) session.getAttribute("user");
        cartImpl.removeProductFromCart(user.getId(), product.getId());
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
    public String updateQuantity(@RequestParam("quantity") Integer quantity, @RequestParam("product") Product product, HttpSession session) {
        User user = (User) session.getAttribute("user");
        cartImpl.updateQuantity(user.getId(), product.getId(), quantity);
        return "redirect:/cart";
    }


}