package com.example.lutemonapp_jamitanskanen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

// The CreateLutemonActivity class represents an activity that allows users to create a new lutemon.
public class CreateLutemonActivity extends AppCompatActivity {

    private Spinner spinnerLutemonColor;
    private EditText editTextLutemonName;
    private Button buttonAddNewLutemon;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lutemon);

        storage = Storage.getInstance(getApplicationContext());

        spinnerLutemonColor = findViewById(R.id.spinner_lutemon_color);
        editTextLutemonName = findViewById(R.id.edit_text_lutemon_name);
        buttonAddNewLutemon = findViewById(R.id.button_add_new_lutemon);

        // Set up the adapter for the color spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lutemon_colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLutemonColor.setAdapter(adapter);

        // Set up the click listener for the add new lutemon button
        buttonAddNewLutemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lutemonColor = spinnerLutemonColor.getSelectedItem().toString();
                String lutemonName = editTextLutemonName.getText().toString();

                if (!lutemonName.isEmpty()) {
                    // Create a new lutemon based on user inputs and save it
                    Home home = new Home(storage);
                    Lutemon newLutemon = home.createLutemon(lutemonName, lutemonColor.toLowerCase(), lutemonColor, getApplicationContext());

                    storage.addLutemon(newLutemon, getApplicationContext());
                    storage.saveAllLutemonsToStorage(getApplicationContext());

                    Toast.makeText(CreateLutemonActivity.this, "Lutemon created: " + lutemonColor + " " + lutemonName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateLutemonActivity.this, "Please enter a name for the Lutemon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
