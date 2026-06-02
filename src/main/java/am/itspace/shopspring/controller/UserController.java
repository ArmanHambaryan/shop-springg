package am.itspace.shopspring.controller;

import am.itspace.shopspring.model.User;
import am.itspace.shopspring.model.UserRole;
import am.itspace.shopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/verify")
    public String verify(@RequestParam("email") String email,
                         @RequestParam("token") String token) {
        userService.verifyUser(email, token);
        return "redirect:/loginPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return "redirect:/registerPage?msg=Email already exists";
        }
        user.setRole(UserRole.USER);
        userService.save(user);
        return "redirect:/loginPage?msg=Registration successful! Please check your email for verification link.";
    }
}
