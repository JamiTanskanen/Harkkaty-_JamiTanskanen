package com.example.lutemonapp_jamitanskanen;

public class Green extends Lutemon {
    public String type = "Green";
    public Green(String name, String color, String category) {
        super(name, color, 6, 3, 19, category);
    }

    public Green(String category) {
        this("", "Green", category);
    }
}
