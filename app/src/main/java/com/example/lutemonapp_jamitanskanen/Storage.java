package com.example.lutemonapp_jamitanskanen;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private String name;
    private HashMap<Integer, Lutemon> lutemons;
    private static final String FILENAME = "lutemons.json";

    // Singleton instance and getInstance method
    private static Storage instance;

    public static Storage getInstance(Context context) {
        if (instance == null) {
            instance = new Storage();
            instance.loadLutemonsFromStorage(context);
        }
        return instance;
    }

    private Storage() {
        this.name = "Lutemon Storage";
        this.lutemons = new HashMap<>();
    }

    public void addLutemon(Lutemon lutemon, Context context) {
        if (lutemon != null) {
            lutemon.setCategory("Home");
            lutemons.put(lutemon.getId(), lutemon);
            saveAllLutemonsToStorage(context);
        }
    }


    // Retrieves a lutemon by ID
    public Lutemon getLutemon(int id) {
        return lutemons.get(id);
    }

    // Retrieves all lutemons as a list
    public List<Lutemon> getAllLutemons() {
        return new ArrayList<>(lutemons.values());
    }

    // Loads lutemons from storage into memory
    public void loadLutemonsFromStorage(Context context) {
        List<Lutemon> storedLutemons = loadAllLutemons(context);
        for (Lutemon lutemon : storedLutemons) {
            if (lutemon != null) {
                lutemons.put(lutemon.getId(), lutemon);
            }
        }
    }

    // Saves all lutemons to storage by converting the list of lutemons into a JSON string
    // and writing it to a file
    public void saveAllLutemonsToStorage(Context context) {
        List<Lutemon> lutemonList = getAllLutemons();

        // Creates a Gson object with a custom LutemonAdapter for serialization
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Lutemon.class, new LutemonAdapter())
                .create();
        String jsonString = gson.toJson(lutemonList);

        // Opens a FileOutputStream, write the JSON string, and closes the stream
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(jsonString);
            osw.flush();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads all lutemons from storage by reading the JSON string from a file
    // and converting it back into a list of lutemons
    public List<Lutemon> loadAllLutemons(Context context) {
        List<Lutemon> lutemonList = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder jsonString = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }

            // Closes the BufferedReader, InputStreamReader, and FileInputStream
            br.close();
            isr.close();
            fis.close();

            // Creates a Gson object with a custom LutemonAdapter for deserialization
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Lutemon.class, new LutemonAdapter())
                    .create();

            Type listType = new TypeToken<List<Lutemon>>() {
            }.getType();
            // Deserializes the JSON string into a list of lutemons
            lutemonList = gson.fromJson(jsonString.toString(), listType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lutemonList;
    }

    public List<Lutemon> getLutemonsByCategory(String category) {
        List<Lutemon> filteredLutemons = new ArrayList<>();
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.getCategory().equalsIgnoreCase(category)) {
                filteredLutemons.add(lutemon);
            }
        }
        return filteredLutemons;
    }

    public void moveLutemonsToCategory(List<Lutemon> lutemons, String newCategory, Context context) {
        for (Lutemon lutemon : lutemons) {
            lutemon.setCategory(newCategory);

            // Restores health to the maximum if the lutemon is moved to the "Home" category
            if ("Home".equals(newCategory)) {
                lutemon.setHealth(lutemon.getMaxHealth());
            }
        }

        saveAllLutemonsToStorage(context);
    }


}

