package by.naumenko.controller;

import by.naumenko.entity.User;
import by.naumenko.service.RoleService;
import by.naumenko.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.naumenko.util.HtmlPages.*;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("")
    public String userList(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                           Model model) {
        model.addAttribute("principal", principal);
        model.addAttribute("listUser", userService.findAll());
        return ADMIN;
    }

    @GetMapping("/new")
    public String addUser(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAllRoles());
        return ADDUSER;
    }

    @PostMapping("/add")
    public String createOrUpdateUser(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAllRoles());
        return userService.saveUser(user, model) ? REDIRECT_ADMIN : ADDUSER;
    }

    @GetMapping("/{id}/edit")
    public String editUserGet(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.findAllRoles());
        return ADDUSER;
    }


    @GetMapping("/{id}/remove")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return REDIRECT_ADMIN;
    }


    @GetMapping("user/{id}/info")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return USER;
    }

}
