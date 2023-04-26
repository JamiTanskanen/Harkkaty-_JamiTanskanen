package com.example.lutemonapp_jamitanskanen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Context;


import java.util.HashMap;
import java.util.List;

// The CategoryFragment class represents a fragment that displays a list of lutemons in a specific category.
public class CategoryFragment extends Fragment {

    private LinearLayout linearLayoutLutemons;
    private String category;
    private Storage storage;
    private HashMap<Integer, CheckBox> lutemonCheckboxes;

    // Constructor initializes the category and an empty HashMap for lutemonCheckboxes.
    public CategoryFragment(String category) {
        this.category = category;
        lutemonCheckboxes = new HashMap<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        storage = Storage.getInstance(getContext());
        linearLayoutLutemons = rootView.findViewById(R.id.linear_layout_lutemons);

        lutemonCheckboxes = new HashMap<>();

        updateLutemons(null);

        return rootView;
    }
    // Update the list of lutemons in the fragment based on the provided lutemonList.
    public void updateLutemons(List<Lutemon> lutemonList) {
        if (lutemonList == null) {
            lutemonList = storage.getLutemonsByCategory(category);
        }

        linearLayoutLutemons.removeAllViews();
        lutemonCheckboxes.clear();

        Context context = getContext();
        if (context == null) {
            return;
        }
        // Add a CheckBox for each lutemon in the list.
        for (Lutemon lutemon : lutemonList) {
            CheckBox checkBox = new CheckBox(context);
            checkBox.setText(lutemon.getName());
            checkBox.setId(lutemon.getId());
            linearLayoutLutemons.addView(checkBox);
            lutemonCheckboxes.put(lutemon.getId(), checkBox);
        }
    }



    public HashMap<Integer, CheckBox> getLutemonCheckboxes() {
        return lutemonCheckboxes;
    }
}
