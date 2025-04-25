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
        System.out.println("Категория: " + category.getName());
    }

    public String getCategoryName() {
        return category.getName();
    }

    @PostConstruct
    public void init() {
        System.out.println("Инициализация CategoryDisplay для " + category.getName());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Очистка CategoryDisplay для " + category.getName());
    }
}