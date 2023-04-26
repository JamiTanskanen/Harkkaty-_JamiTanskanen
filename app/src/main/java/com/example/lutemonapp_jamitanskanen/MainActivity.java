package com.example.lutemonapp_jamitanskanen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonListLutemons;
    private Button buttonCreateLutemon;
    private Button buttonBattleArena;
    private Button buttonMoveLutemon;
    private Button buttonTrainingCenter;
    private Button buttonReviveLutemon;

    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = Storage.getInstance(getApplicationContext());

        buttonListLutemons = findViewById(R.id.button_list_lutemons);
        buttonCreateLutemon = findViewById(R.id.button_add_new_lutemon);
        buttonBattleArena = findViewById(R.id.button_battle_arena);
        buttonMoveLutemon = findViewById(R.id.button_move_selected_lutemons);
        buttonTrainingCenter = findViewById(R.id.button_training_center);
        buttonReviveLutemon = findViewById(R.id.button_revive_lutemon);

        buttonListLutemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListLutemonsActivity.class);
                startActivity(intent);
            }
        });

        buttonCreateLutemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateLutemonActivity.class);
                startActivity(intent);
            }
        });

        buttonBattleArena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BattleArenaActivity.class);
                startActivity(intent);
            }
        });

        buttonMoveLutemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoveLutemonActivity.class);
                startActivity(intent);
            }
        });

        buttonTrainingCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrainingCenterActivity.class);
                startActivity(intent);
            }
        });

        buttonReviveLutemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReviveLutemonActivity.class);
                startActivity(intent);
            }
        });

    }
}
