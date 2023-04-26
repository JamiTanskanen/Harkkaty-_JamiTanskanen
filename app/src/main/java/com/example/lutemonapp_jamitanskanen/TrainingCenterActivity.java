package com.example.lutemonapp_jamitanskanen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

// A class representing the training center activity where lutemons can be trained
public class TrainingCenterActivity extends AppCompatActivity {
    private Spinner spinnerLutemons;
    private Button buttonTrain;
    private TextView textViewTrainingLog;
    private Storage storage;
    private List<Lutemon> trainLutemons;
    private int selectedLutemonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_center);

        // Initializes the storage instance and loads lutemons from storage
        storage = Storage.getInstance(getApplicationContext());
        storage.loadLutemonsFromStorage(getApplicationContext());
        trainLutemons = storage.getLutemonsByCategory("Training");
        spinnerLutemons = findViewById(R.id.spinner_lutemons);
        buttonTrain = findViewById(R.id.button_train);
        textViewTrainingLog = findViewById(R.id.textView_training_log);

        ArrayAdapter<Lutemon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, trainLutemons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLutemons.setAdapter(adapter);

        // Sets the selected lutemon ID based on the spinner selection
        spinnerLutemons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLutemonId = trainLutemons.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Trains the selected lutemon and update the training log when the "Train" button is clicked
        buttonTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainingArea trainingArea = new TrainingArea(storage);
                trainingArea.train(selectedLutemonId, TrainingCenterActivity.this);
                Lutemon lutemon = storage.getLutemon(selectedLutemonId);
                textViewTrainingLog.setText(String.format("%s\n%s has received 1 experience point, 1 attack point, and 1 defense point.", lutemon.getStats(), lutemon.getName()));
            }
        });
    }
}
