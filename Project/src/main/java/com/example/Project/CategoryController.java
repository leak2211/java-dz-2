package com.example.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Qualifier;

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
}