package com.example.lutemonapp_jamitanskanen;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

// LutemonAdapter is a custom Gson TypeAdapter for serializing and deserializing lutemon objects.
public class LutemonAdapter extends TypeAdapter<Lutemon> {

    @Override
    public void write(JsonWriter out, Lutemon lutemon) throws IOException {
        if (lutemon == null) {
            out.nullValue();
            return;
        }

        out.beginObject();

        // Writes the lutemon subclass type
        if (lutemon instanceof White) {
            out.name("type").value("White");
        } else if (lutemon instanceof Green) {
            out.name("type").value("Green");
        } else if (lutemon instanceof Pink) {
            out.name("type").value("Pink");
        } else if (lutemon instanceof Orange) {
            out.name("type").value("Orange");
        } else if (lutemon instanceof Black) {
            out.name("type").value("Black");
        } else {
            throw new IllegalArgumentException("Unknown Lutemon subclass: " + lutemon.getClass());
        }

        // Writes lutemon properties
        out.name("name").value(lutemon.getName());
        out.name("color").value(lutemon.getColor());
        out.name("attack").value(lutemon.getAttack());
        out.name("defense").value(lutemon.getDefense());
        out.name("experience").value(lutemon.getExperience());
        out.name("health").value(lutemon.getHealth());
        out.name("maxHealth").value(lutemon.getMaxHealth());
        out.name("id").value(lutemon.getId());
        out.name("category").value(lutemon.getCategory()); // Add the category property

        out.endObject();
    }

    @Override
    public Lutemon read(JsonReader in) throws IOException {
        in.beginObject();

        String type = "";
        String name = "";
        String color = "";
        String category = ""; // Add the category variable
        int attack = 0;
        int defense = 0;
        int experience = 0;
        int health = 0;
        int maxHealth = 0;
        int id = 0;

        // Reads lutemon properties
        while (in.hasNext()) {
            String propertyName = in.nextName();
            if (propertyName.equals("type")) {
                type = in.nextString();
            } else if (propertyName.equals("name")) {
                name = in.nextString();
            } else if (propertyName.equals("color")) {
                color = in.nextString();
            } else if (propertyName.equals("category")) {
                category = in.nextString();
            } else if (propertyName.equals("attack")) {
                attack = in.nextInt();
            } else if (propertyName.equals("defense")) {
                defense = in.nextInt();
            } else if (propertyName.equals("experience")) {
                experience = in.nextInt();
            } else if (propertyName.equals("health")) {
                health = in.nextInt();
            } else if (propertyName.equals("maxHealth")) {
                maxHealth = in.nextInt();
            } else if (propertyName.equals("id")) {
                id = in.nextInt();
            } else {
                in.skipValue();
            }
        }

        in.endObject();

        Lutemon lutemon;

        // Instantiates the correct lutemon subclass based on the "type" property
        switch (type) {
            case "White":
                lutemon = new White(name, color, category);
                break;
            case "Green":
                lutemon = new Green(name, color, category);
                break;
            case "Pink":
                lutemon = new Pink(name, color, category);
                break;
            case "Orange":
                lutemon = new Orange(name, color, category);
                break;
            case "Black":
                lutemon = new Black(name, color, category);
                break;
            default:
                lutemon = null;
        }

        if (lutemon != null) {
            lutemon.setExperience(experience);
            lutemon.setHealth(health);
            lutemon.setId(id);
            lutemon.setAttack(attack);
            lutemon.setDefense(defense);
        }

        return lutemon;
    }
}

