package com.example.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/")
    public String home() {
        return "redirect:/categories";
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("cabrioletName", cabrioletDisplay.getCategoryName());
        model.addAttribute("coupeName", coupeDisplay.getCategoryName());
        return "categories";
    }

    @GetMapping("/calculate")
    public String calculate(
            @RequestParam("first") double first,
            @RequestParam("second") double second,
            @RequestParam("operation") String operation,
            Model model) {
        String result;
        try {
            switch (operation.toLowerCase()) {
                case "add":
                    result = String.valueOf(first + second);
                    break;
                case "subtract":
                    result = String.valueOf(first - second);
                    break;
                case "multiply":
                    result = String.valueOf(first * second);
                    break;
                case "divide":
                    if (second == 0) {
                        result = "Ошибка: Деление на ноль";
                    } else {
                        result = String.valueOf(first / second);
                    }
                    break;
                default:
                    result = "Ошибка: Неверная операция. Используйте add, subtract, multiply или divide.";
            }
        } catch (Exception e) {
            result = "Ошибка: Неверные входные данные";
        }

        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("operation", operation);
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/categories/list")
    public String listCategories(Model model) {
        List<CategoryItem> categories = new ArrayList<>();
        categories.add(new CategoryItem("cabrioletDisplay", cabrioletDisplay));
        categories.add(new CategoryItem("coupeDisplay", coupeDisplay));
        model.addAttribute("categories", categories);
        return "categories-list";
    }

    @GetMapping("/categories/{id}")
    public String showCategoryDetails(@PathVariable("id") String id, Model model) {
        Object category;
        if ("cabrioletDisplay".equals(id)) {
            category = cabrioletDisplay;
        } else if ("coupeDisplay".equals(id)) {
            category = coupeDisplay;
        } else {
            model.addAttribute("error", "Категория с ID " + id + " не найдена");
            return "category-details";
        }
        model.addAttribute("category", category);
        return "category-details";
    }

    // Вспомогательный класс для передачи id и объекта
    public static class CategoryItem {
        private final String id;
        private final Object category;

        public CategoryItem(String id, Object category) {
            this.id = id;
            this.category = category;
        }

        public String getId() {
            return id;
        }

        public Object getCategory() {
            return category;
        }
    }
}