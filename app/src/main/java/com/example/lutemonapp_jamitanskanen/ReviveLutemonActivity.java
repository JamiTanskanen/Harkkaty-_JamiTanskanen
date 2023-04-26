package com.example.lutemonapp_jamitanskanen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;


import java.util.List;

public class ReviveLutemonActivity extends AppCompatActivity {

    private Spinner spinnerDeadLutemons;
    private Button buttonReviveLutemon;
    private TextView textViewReviveResult;
    private Storage storage;
    private Lutemon selectedLutemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revive_lutemon);

        // Initializes Storage instance and load lutemons
        storage = Storage.getInstance(getApplicationContext());
        storage.loadLutemonsFromStorage(getApplicationContext());

        spinnerDeadLutemons = findViewById(R.id.spinner_dead_lutemons);
        buttonReviveLutemon = findViewById(R.id.button_revive_lutemon);
        textViewReviveResult = findViewById(R.id.textView_revive_result);

        // Sets up the spinner for selecting a dead lutemon to revive
        List<Lutemon> deadLutemons = storage.getLutemonsByCategory("Dead");
        ArrayAdapter<Lutemon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deadLutemons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDeadLutemons.setAdapter(adapter);

        spinnerDeadLutemons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLutemon = (Lutemon) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLutemon = null;
            }
        });

        buttonReviveLutemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedLutemon != null) {
                    // Sets the lutemon's health to its max health
                    selectedLutemon.setHealth(selectedLutemon.getMaxHealth());

                    // Moves the selected lutemon from the "Dead" category to the "Home" category
                    List<Lutemon> revivedLutemonList = new ArrayList<>();
                    revivedLutemonList.add(selectedLutemon);
                    storage.moveLutemonsToCategory(revivedLutemonList, "Home", getApplicationContext());
                    textViewReviveResult.setText(String.format("Revived %s successfully!", selectedLutemon.getName()));

                    // Displays the revived lutemon's updated stats
                    textViewReviveResult.append(String.format("\n%s's updated stats are:\n", selectedLutemon.getName()));
                    textViewReviveResult.append(selectedLutemon.getStats());


                    // Refresh the spinner with the updated list of dead lutemons
                    List<Lutemon> updatedDeadLutemons = storage.getLutemonsByCategory("Dead");
                    ArrayAdapter<Lutemon> updatedAdapter = new ArrayAdapter<>(ReviveLutemonActivity.this, android.R.layout.simple_spinner_item, updatedDeadLutemons);
                    updatedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDeadLutemons.setAdapter(updatedAdapter);
                } else {
                    textViewReviveResult.setText("No Lutemon selected.");
                }
            }
        });
    }
}
