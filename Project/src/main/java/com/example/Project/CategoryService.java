package com.example.Project;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Object> categories;

    public void init() {
        categories = new ArrayList<>();
        CategoryDisplay cabrioletDisplay = new CategoryDisplay();
        cabrioletDisplay.setCategory(new Cabriolet());
        CoupeDisplay coupeDisplay = new CoupeDisplay();
        coupeDisplay.setCategory(new Coupe());
        categories.add(cabrioletDisplay);
        categories.add(coupeDisplay);
    }

    public List<Object> getAllCategories() {
        return categories;
    }

    public Object getCategoryByIndex(int index) {
        if (index >= 0 && index < categories.size()) {
            return categories.get(index);
        }
        return null;
    }

    public void addCategory(Object category) {
        categories.add(category);
    }
}