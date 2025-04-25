package com.example.Project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        CategoryViewer<Category> viewer = (CategoryViewer<Category>) context.getBean("categoryViewer");
        viewer.display();
    }
}
