package com.example.Project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {
    private final List<CategoryDisplay> categoryDisplays = new ArrayList<>();

    public CategoryController() {
        // Инициализация начальными категориями
        categoryDisplays.add(new CategoryDisplay("category1", new GenericCategory("Кабриолет")));
        categoryDisplays.add(new CategoryDisplay("category2", new GenericCategory("Купе")));
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/categories";
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<String> categoryNames = new ArrayList<>();
        for (CategoryDisplay display : categoryDisplays) {
            categoryNames.add(display.getCategoryName());
        }
        model.addAttribute("categoryNames", categoryNames);
        return "categories";
    }

    @GetMapping("/categories/list")
    public String listCategories(Model model) {
        List<CategoryItem> categories = new ArrayList<>();
        for (CategoryDisplay display : categoryDisplays) {
            categories.add(new CategoryItem(display.getId(), display));
        }
        model.addAttribute("categories", categories);
        return "categories-list";
    }

    @GetMapping("/categories/{id}")
    public String showCategoryDetails(@PathVariable("id") String id, Model model) {
        CategoryDisplay selectedCategory = null;
        for (CategoryDisplay display : categoryDisplays) {
            if (display.getId().equals(id)) {
                selectedCategory = display;
                break;
            }
        }
        if (selectedCategory == null) {
            model.addAttribute("error", "Категория с ID " + id + " не найдена");
        } else {
            model.addAttribute("category", selectedCategory);
        }
        return "category-details";
    }

    @GetMapping("/categories/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new GenericCategory());
        return "add-category";
    }

    @PostMapping("/categories/add")
    public String addCategory(@RequestParam("name") String name) {
        String id = "category" + UUID.randomUUID().toString().substring(0, 8);
        GenericCategory newCategory = new GenericCategory(name);
        CategoryDisplay newDisplay = new CategoryDisplay(id, newCategory);
        categoryDisplays.add(newDisplay);
        return "redirect:/categories/list";
    }

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