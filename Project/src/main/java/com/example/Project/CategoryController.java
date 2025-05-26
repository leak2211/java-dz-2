package com.example.Project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {
    private final List<CategoryDisplay> categoryDisplays = new ArrayList<>();
    private final CarTypeService carTypeService;

    public CategoryController(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
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
    public String listCategories(Model model, 
            @RequestParam(value = "width", required = false) Double width,
            @RequestParam(value = "height", required = false) Double height) {
        List<CategoryItem> categories = new ArrayList<>();
        for (CategoryDisplay display : categoryDisplays) {
            if (width != null && height != null) {
                if (carTypeService.matchesCategory(display.getCategoryName(), width, height)) {
                    categories.add(new CategoryItem(display.getId(), display));
                }
            } else {
                categories.add(new CategoryItem(display.getId(), display));
            }
        }
        model.addAttribute("categories", categories);
        model.addAttribute("width", width);
        model.addAttribute("height", height);
        return "categories-list";
    }

    @GetMapping("/categories/determine")
    public String showDetermineForm(Model model) {
        model.addAttribute("width", "");
        model.addAttribute("height", "");
        return "determine-category";
    }

    @PostMapping("/categories/determine")
    public String determineCategory(
            @RequestParam("width") Double width,
            @RequestParam("height") Double height,
            RedirectAttributes redirectAttributes) {
        if (width == null || height == null || width <= 0 || height <= 0) {
            redirectAttributes.addFlashAttribute("error", "Ширина и высота должны быть положительными числами");
            return "redirect:/categories/determine";
        }
        String carType = carTypeService.determineCarType(width, height);
        redirectAttributes.addFlashAttribute("message", "Тип машины: " + carType);
        return "redirect:/categories/determine";
    }

    @GetMapping("/categories/{id}")
    public String showCategoryDetails(@PathVariable("id") String id, Model model) {
        CategoryDisplay selectedCategory = findCategoryById(id);
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
    public String addCategory(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        if (name == null || name.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Название категории не может быть пустым");
            return "redirect:/categories/add";
        }
        String id = "category" + UUID.randomUUID().toString().substring(0, 8);
        GenericCategory newCategory = new GenericCategory(name);
        CategoryDisplay newDisplay = new CategoryDisplay(id, newCategory);
        categoryDisplays.add(newDisplay);
        return "redirect:/categories/list";
    }

    @PatchMapping("/categories/{id}")
    public String updateCategory(
            @PathVariable("id") String id,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes) {
        if (name == null || name.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Название категории не может быть пустым");
            return "redirect:/categories/" + id;
        }
        CategoryDisplay categoryDisplay = findCategoryById(id);
        if (categoryDisplay == null) {
            redirectAttributes.addFlashAttribute("error", "Категория с ID " + id + " не найдена");
            return "redirect:/categories/list";
        }
        ((GenericCategory) categoryDisplay.getCategory()).setName(name);
        redirectAttributes.addFlashAttribute("message", "Категория успешно обновлена");
        return "redirect:/categories/" + id;
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        CategoryDisplay categoryDisplay = findCategoryById(id);
        if (categoryDisplay == null) {
            redirectAttributes.addFlashAttribute("error", "Категория с ID " + id + " не найдена");
            return "redirect:/categories/list";
        }
        categoryDisplays.remove(categoryDisplay);
        redirectAttributes.addFlashAttribute("message", "Категория успешно удалена");
        return "redirect:/categories/list";
    }

    private CategoryDisplay findCategoryById(String id) {
        for (CategoryDisplay display : categoryDisplays) {
            if (display.getId().equals(id)) {
                return display;
            }
        }
        return null;
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