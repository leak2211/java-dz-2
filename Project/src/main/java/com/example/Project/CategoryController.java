package com.example.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String home() {
        return "redirect:/categories";
    }

    @GetMapping("/categories")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories-list";
    }

    @GetMapping("/showCategory")
    public String showCategory(@RequestParam("index") int index, Model model) {
        Object category = categoryService.getCategoryByIndex(index);
        if (category == null) {
            model.addAttribute("error", "Категория не найдена!");
            return "category-details";
        }
        model.addAttribute("category", category);
        return "category-details";
    }


    @GetMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model) {
        double result;
        String errorMessage = null;

        try {
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        errorMessage = "Ошибка: Деление на ноль!";
                        model.addAttribute("error", errorMessage);
                        return "calculation-result";
                    }
                    result = num1 / num2;
                    break;
                default:
                    errorMessage = "Ошибка: Неверная операция! Используйте +, -, * или /";
                    model.addAttribute("error", errorMessage);
                    return "calculation-result";
            }
        } catch (Exception e) {
            errorMessage = "Ошибка: Неверные входные данные!";
            model.addAttribute("error", errorMessage);
            return "calculation-result";
        }

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operation", operation);
        model.addAttribute("result", result);
        return "calculation-result";
    }
}