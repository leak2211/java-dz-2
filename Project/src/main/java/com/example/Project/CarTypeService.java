package com.example.Project;

import org.springframework.stereotype.Service;

@Service
public class CarTypeService {

    public String determineCarType(double width, double height) {
        if (width >= 1.8 && width <= 2.0 && height <= 1.4) {
            return "Купе";
        } else if (width >= 1.7 && width <= 2.0 && height <= 1.5) {
            return "Кабриолет";
        } else if (width > 2.0 && height > 1.5) {
            return "Внедорожник";
        } else {
            return "Не определено";
        }
    }

    public boolean matchesCategory(String categoryName, double width, double height) {
        switch (categoryName) {
            case "Купе":
                return width >= 1.8 && width <= 2.0 && height <= 1.4;
            case "Кабриолет":
                return width >= 1.7 && width <= 2.0 && height <= 1.5;
            case "Внедорожник":
                return width > 2.0 && height > 1.5;
            default:
                return false;
        }
    }
}