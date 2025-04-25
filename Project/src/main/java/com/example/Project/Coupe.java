package com.example.Project;

import org.springframework.stereotype.Component;

@Component("coupe")
public class Coupe implements Category {
    @Override
    public String getName() {
        return "Купе";
    }
}