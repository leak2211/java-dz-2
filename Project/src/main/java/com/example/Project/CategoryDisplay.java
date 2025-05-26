package com.example.Project;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("cabrioletDisplay")
public class CategoryDisplay {
    private Category category;

    @Autowired
    @Qualifier("cabriolet")
    public void setCategory(Category category) {
        this.category = category;
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