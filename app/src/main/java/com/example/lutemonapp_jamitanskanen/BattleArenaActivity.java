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
import android.util.Log;


import java.util.List;

// This activity represents the Battle Arena screen where users can choose
// two lutemons to fight against each other.
public class BattleArenaActivity extends AppCompatActivity {
    private Spinner spinnerLutemonA;
    private Spinner spinnerLutemonB;
    private Button buttonStartBattle;
    private TextView textViewBattleOutput;
    private int lutemonIdA;
    private int lutemonIdB;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_arena);

        // Initializes the storage instance to manage lutemons data
        storage = Storage.getInstance(getApplicationContext());
        storage.loadLutemonsFromStorage(getApplicationContext());

        spinnerLutemonA = findViewById(R.id.spinner_lutemon_a);
        spinnerLutemonB = findViewById(R.id.spinner_lutemon_b);
        buttonStartBattle = findViewById(R.id.button_start_battle);
        textViewBattleOutput = findViewById(R.id.text_view_battle_output);

        // Gets all lutemons that belong to the "Battle" category
        List<Lutemon> lutemons = storage.getLutemonsByCategory("Battle");

        // Creates and sets an ArrayAdapter to populate both spinners with lutemons
        ArrayAdapter<Lutemon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lutemons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLutemonA.setAdapter(adapter);
        spinnerLutemonB.setAdapter(adapter);

        // Set listeners to capture the selected lutemon ID in each spinner
        spinnerLutemonA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lutemonIdA = lutemons.get(position).getId();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLutemonB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lutemonIdB = lutemons.get(position).getId();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonStartBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creates a BattleField instance to execute the fight between lutemons
                BattleField battlefield = new BattleField();
                BattleResult battleResult = battlefield.fight(lutemonIdA, lutemonIdB, storage);

                Lutemon winnerLutemon = battleResult.getWinnerLutemon();
                Lutemon loserLutemon = battleResult.getLoserLutemon();
                String battleLog = battleResult.getBattleLog();

                // Updates the losing lutemon's status and moves it to the "Dead" category
                if (loserLutemon == null) {
                    textViewBattleOutput.setText("Invalid Lutemon ID");
                } else {
                    loserLutemon.setHealth(0);
                    List<Lutemon> loserLutemonList = new ArrayList<>();
                    loserLutemonList.add(loserLutemon);
                    storage.moveLutemonsToCategory(loserLutemonList, "Dead", getApplicationContext());
                    textViewBattleOutput.setText(battleLog + loserLutemon.getName() + " lost the battle and was moved to the Dead category.");

                    // Increases the attack and defense of the winning lutemon
                    winnerLutemon.setAttack(winnerLutemon.getAttack() + 1);
                    winnerLutemon.setDefense(winnerLutemon.getDefense() + 1);
                    Log.d("BattleArenaActivity", "Updated attack: " + winnerLutemon.getAttack() + " Updated defense: " + winnerLutemon.getDefense());

                    // Saves updated lutemons to the storage
                    storage.saveAllLutemonsToStorage(getApplicationContext());
                }

                // Updates the spinner to exclude the dead lutemon from the Battle category
                List<Lutemon> updatedLutemons = storage.getLutemonsByCategory("Battle");
                ArrayAdapter<Lutemon> updatedAdapter = new ArrayAdapter<>(BattleArenaActivity.this, android.R.layout.simple_spinner_item, updatedLutemons);
                updatedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerLutemonA.setAdapter(updatedAdapter);
                spinnerLutemonB.setAdapter(updatedAdapter);
            }
        });

    }
}

