//package sg.nus.edu.mystore.controller;
//
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import sg.nus.edu.mystore.entity.User;
//import sg.nus.edu.mystore.service.CartImplementation;
//
//@Controller
//public class CartController {
//    @Autowired
//    private CartImplementation cartImpl;
//
//    @GetMapping("/cart")
//    public String cart(ModelMap model, HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        model.addAttribute("cart", cartImpl.viewCartList(user.getId()));
//        return "cart";
//    }
//}
