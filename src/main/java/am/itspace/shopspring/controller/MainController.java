package am.itspace.shopspring.controller;

import am.itspace.shopspring.model.User;
import am.itspace.shopspring.model.UserRole;
import am.itspace.shopspring.service.CategoryService;
import am.itspace.shopspring.service.UserService;
import am.itspace.shopspring.service.security.SpringUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final CategoryService categoryService;

    @Value("${shop.upload.images.directory.path}")
    private String imageDirectoryPath;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal SpringUser userPrincipal, ModelMap modelMap) {
        if (userPrincipal != null) {
            modelMap.addAttribute("user", userPrincipal.getUser());
        }
        modelMap.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @GetMapping("/successLogin")
    public String successLogin(@AuthenticationPrincipal SpringUser springUser, ModelMap modelMap) {
        if (springUser == null) {
            return "redirect:/loginPage";
        }
        if (springUser.getUser().getRole() == UserRole.ADMIN) {
            return "redirect:/adminHome";
        }
        modelMap.addAttribute("categories", categoryService.findAll());
        return "redirect:/";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "loginPage";
    }

    @GetMapping("/registerPage")
    public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "registerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return "redirect:/registerPage";
        }
        userService.save(user);
        return "redirect:/loginPage";
    }

    @GetMapping("/image/get")
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) {
        File file = new File(imageDirectoryPath + picName);
        if (file.exists()) {
            try {
                return FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }


}
