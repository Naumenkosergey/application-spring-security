package by.naumenko.controller;

import by.naumenko.entity.User;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/user")
public class UserController {

    @GetMapping("/user")
    public String user(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                       Model model) {
        model.addAttribute("user", principal);
        return "user-info";
    }
}
