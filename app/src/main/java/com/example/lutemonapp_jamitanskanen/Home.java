package com.example.lutemonapp_jamitanskanen;

import android.content.Context;

public class Home {
    private Storage storage;

    public Home(Storage storage) {
        this.storage = storage;
    }

    // This method creates a lutemon with a given name, color, and category, and saves it to storage.
    public Lutemon createLutemon(String name, String color, String category, Context context) {
        Lutemon lutemon;
        switch (color) {
            case "white":
                lutemon = new White(name, color, category);
                break;
            case "green":
                lutemon = new Green(name, color, category);
                break;
            case "pink":
                lutemon = new Pink(name, color, category);
                break;
            case "orange":
                lutemon = new Orange(name, color, category);
                break;
            case "black":
                lutemon = new Black(name, color, category);
                break;
            default:
                throw new IllegalArgumentException("Invalid color");
        }
        storage.addLutemon(lutemon, context);
        return lutemon;
    }
}
