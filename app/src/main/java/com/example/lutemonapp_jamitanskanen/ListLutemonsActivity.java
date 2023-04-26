package com.example.lutemonapp_jamitanskanen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

// ListLutemonsActivity displays a list of all lutemons in a RecyclerView.
public class ListLutemonsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLutemons;
    private LutemonRecyclerViewAdapter adapter;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_lutemons);

        // Get the storage instance and retrieve all lutemons.
        storage = Storage.getInstance(getApplicationContext());
        List<Lutemon> lutemons = storage.getAllLutemons();

        // Initialize the RecyclerView to display the list of lutemons.
        recyclerViewLutemons = findViewById(R.id.recycler_view_lutemons);
        recyclerViewLutemons.setHasFixedSize(true);
        recyclerViewLutemons.setLayoutManager(new LinearLayoutManager(this));

        // Create an adapter for the RecyclerView and set it.
        adapter = new LutemonRecyclerViewAdapter(this, lutemons);
        recyclerViewLutemons.setAdapter(adapter);
    }
}
