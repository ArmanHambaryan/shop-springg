package am.itspace.shopspring.controller;

import am.itspace.shopspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/admin/home";
    }

    @GetMapping("/products/category/{id}")
    public String productsByCategory(@PathVariable("id") int categoryId, ModelMap modelMap) {
        productService.findAllByCategoryId(categoryId);
        modelMap.addAttribute("products", productService.findAllByCategoryId(categoryId));
        return "products";
    }


    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("product", productService.findById(id));
        return "productDetails";
    }
}



