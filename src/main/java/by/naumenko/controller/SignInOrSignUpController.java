package by.naumenko.controller;

import by.naumenko.entity.User;
import by.naumenko.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static by.naumenko.util.HtmlPages.*;

@Controller
@AllArgsConstructor
public class SignInOrSignUpController {
    private final UserService userService;

    @GetMapping("/signin")
    public String loginPage() {
        return SIGNIN;
    }

    @GetMapping("/signup")
    public String registrationPage() {
        return SIGNUP;
    }

    @PostMapping("/signup")
    public String addUser(User user, Model model) {
        model.addAttribute("user", user);
        return userService.saveUser(user, model) ? USER : SIGNUP;
    }
}