package com.example.lutemonapp_jamitanskanen;

public class Pink extends Lutemon {
    public String type = "Pink";
    public Pink(String name, String color, String category) {
        super(name, color, 7, 2, 18, category);
    }

    public Pink(String category) {
        this("", "Pink", category);
    }
}
