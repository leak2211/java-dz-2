package com.example.Project;

import org.springframework.stereotype.Component;

@Component("cabriolet")
public class Cabriolet implements Category {
    @Override
    public String getName() {
        return "Кабриолет";
    }
}
