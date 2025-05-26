package com.example.Project;

import org.springframework.stereotype.Component;

@Component
public class GenericCategory implements Category {
    private String name;

    public GenericCategory() {
    }

    public GenericCategory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}