package com.example.Project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.Project")
public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
            CategoryDisplay cabrioletDisplay = context.getBean("cabrioletDisplay", CategoryDisplay.class);
            cabrioletDisplay.display();

            CoupeDisplay coupeDisplay = context.getBean("coupeDisplay", CoupeDisplay.class);
            coupeDisplay.display();
        }
    }
}