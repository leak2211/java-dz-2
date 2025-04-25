package com.example.Project;

public class CategoryViewer<T extends Category> {
    private T category;

    public void setCategory(T category) {
        this.category = category;
    }

    public void display() {
        category.displayCategory();
    }
}
