package com.example.lutemonapp_jamitanskanen;

public class White extends Lutemon {
    public String type = "White";
    public White(String name, String color, String category) {
        super(name, color, 5, 4, 20, category);
    }

    public White(String category) {
        this("", "White", category);
    }
}
