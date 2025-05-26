package com.example.Project;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CategoryDisplay {
    private Category category;
    private String id;

    public CategoryDisplay() {
    }

    public CategoryDisplay(String id, Category category) {
        this.id = id;
        this.category = category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void display() {
        System.out.println("Категория: " + (category != null ? category.getName() : "null"));
    }

    public String getCategoryName() {
        return category != null ? category.getName() : "Не определено";
    }

    @PostConstruct
    public void init() {
        System.out.println("Инициализация CategoryDisplay для " + (category != null ? category.getName() : "null"));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Очистка CategoryDisplay для " + (category != null ? category.getName() : "null"));
    }
}