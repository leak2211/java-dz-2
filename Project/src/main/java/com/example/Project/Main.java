package com.example.Project;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            CategoryDisplay<?> cabrioletDisplay = context.getBean("cabrioletDisplay", CategoryDisplay.class);
            cabrioletDisplay.display();

            CategoryDisplay<?> coupeDisplay = context.getBean("coupeDisplay", CategoryDisplay.class);
            coupeDisplay.display();
        }
    }
}