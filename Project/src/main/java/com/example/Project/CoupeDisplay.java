package com.example.Project;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("coupeDisplay")
public class CoupeDisplay {
    private Category category;

    @Autowired
    @Qualifier("coupe")
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
        System.out.println("Инициализация CoupeDisplay для " + (category != null ? category.getName() : "null"));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Очистка CoupeDisplay для " + (category != null ? category.getName() : "null"));
    }
}