package ca.georgebrown.recipeapplication.controller;

import ca.georgebrown.recipeapplication.model.User;
import ca.georgebrown.recipeapplication.service.RoleService;
import ca.georgebrown.recipeapplication.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;


@Controller

public class SecurityController {

    private final UserService userService;
    private final RoleService roleService;

    public SecurityController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping({"/index", "/"})
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model theModel) {
        theModel.addAttribute("user", new User());
        return "/security/login";
    }

    @GetMapping("/register")
    public String register(Model theModel) {
        theModel.addAttribute("user", new User());
        return "/security/register";
    }

    @PostMapping("/register")
    public String processRegister(User user) {
        System.err.println(user.toString());

        if (userService.findByEmail(user.getEmail()) == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRoles(Set.of(roleService.getDefaultRole()));
            user.setEnabled(true);
            userService.save(user);
            return "redirect:/user/update";
        } else {
            return "security/register-fail";
        }
    }

}

