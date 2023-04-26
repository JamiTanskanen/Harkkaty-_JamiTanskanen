package com.example.lutemonapp_jamitanskanen;

public class Orange extends Lutemon {
    public String type = "Orange";
    public Orange(String name, String color, String category) {
        super(name, color, 8, 1, 17, category);
    }

    public Orange(String category) {
        this("", "Orange", category);
    }
}
