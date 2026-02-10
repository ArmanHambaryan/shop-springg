package am.itspace.shopspring.controller;

import am.itspace.shopspring.model.Category;
import am.itspace.shopspring.model.Product;
import am.itspace.shopspring.service.CategoryService;
import am.itspace.shopspring.service.ProductService;
import am.itspace.shopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/adminHome")
    public String adminPage(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAll());
        modelMap.addAttribute("products", productService.findAll());
        modelMap.addAttribute("categories", categoryService.findAll());
        return "adminHome";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/adminHome";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/adminHome";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute Product product, @RequestParam("pic") MultipartFile multipartFile) {
        productService.save(product, multipartFile);
        return "redirect:/adminHome";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/adminHome";
    }


}
