package com.example.Project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    private final CategoryDisplay cabrioletDisplay;
    private final CoupeDisplay coupeDisplay;

    @Autowired
    public CategoryController(
            @Qualifier("cabrioletDisplay") CategoryDisplay cabrioletDisplay,
            @Qualifier("coupeDisplay") CoupeDisplay coupeDisplay) {
        this.cabrioletDisplay = cabrioletDisplay;
        this.coupeDisplay = coupeDisplay;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("cabrioletName", cabrioletDisplay.getCategoryName());
        model.addAttribute("coupeName", coupeDisplay.getCategoryName());
        return "categories";
    }
}