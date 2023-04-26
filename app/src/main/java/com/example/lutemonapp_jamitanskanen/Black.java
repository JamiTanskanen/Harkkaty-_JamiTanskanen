package com.example.lutemonapp_jamitanskanen;

public class Black extends Lutemon {
    public String type = "Black";
    public Black(String name, String color, String category) {
        super(name, color, 9, 0, 16, category);
    }

    public Black(String category) {
        this("", "Black", category);
    }
}
