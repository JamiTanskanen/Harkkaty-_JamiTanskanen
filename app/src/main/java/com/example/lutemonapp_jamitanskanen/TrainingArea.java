package com.example.lutemonapp_jamitanskanen;

import android.content.Context;


public class TrainingArea {
    private Storage storage;

    public TrainingArea(Storage storage) {
        this.storage = storage;
    }

    // Trains a lutemon by its ID, incrementing its experience, attack, and defense stats,
    // then save the updated lutemon data to storage
    public void train(int lutemonId, Context context) {
        Lutemon lutemon = storage.getLutemon(lutemonId);
        if (lutemon != null) {
            // Increments the lutemon's experience, attack, and defense stats
            lutemon.setExperience(lutemon.getExperience() + 1);
            lutemon.setAttack(lutemon.getAttack() + 1);
            lutemon.setDefense(lutemon.getDefense() + 1);
            storage.saveAllLutemonsToStorage(context);
        }
    }

}

