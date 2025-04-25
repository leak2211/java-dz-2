package com.example.Project;


public class CategoryDisplay<T extends Category> {
    private T category;

    public void setCategory(T category) {
        this.category = category;
    }

    public void display() {
        System.out.println("Категория: " + category.getName());
    }

    public void init() {
        System.out.println("Инициализация CategoryDisplay");
    }

    public void destroy() {
        System.out.println("Очистка CategoryDisplay");
    }
}