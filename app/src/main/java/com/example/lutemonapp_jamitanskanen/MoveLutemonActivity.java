package com.example.lutemonapp_jamitanskanen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import android.widget.CheckBox;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// MoveLutemonActivity is an activity that allows users to move lutemons between different categories.
public class MoveLutemonActivity extends AppCompatActivity {

    private Spinner spinnerLutemonDestination;
    private Button buttonMoveSelectedLutemons;
    private Storage storage;
    private ViewPager viewPager;
    private CategoryFragmentPagerAdapter fragmentPagerAdapter;
    private TabLayout tabLayout;
    private List<CategoryFragment> categoryFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_lutemon);

        storage = Storage.getInstance(getApplicationContext());
        storage.loadLutemonsFromStorage(getApplicationContext());

        spinnerLutemonDestination = findViewById(R.id.spinner_lutemon_destination);
        buttonMoveSelectedLutemons = findViewById(R.id.button_move_selected_lutemons);

        viewPager = findViewById(R.id.viewPager);
        fragmentPagerAdapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        // Initializes and sets arguments for each category fragment
        categoryFragments = new ArrayList<>(fragmentPagerAdapter.getCount());
        for (int i = 0; i < fragmentPagerAdapter.getCount(); i++) {
            CategoryFragment fragment = new CategoryFragment(fragmentPagerAdapter.categories[i]);
            Bundle args = new Bundle();
            args.putString("category", fragmentPagerAdapter.categories[i]);
            fragment.setArguments(args);
            categoryFragments.add(fragment);
        }


        buttonMoveSelectedLutemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDestination = spinnerLutemonDestination.getSelectedItem().toString();
                StringBuilder movedLutemons = new StringBuilder();
                List<Lutemon> lutemonsToMove = new ArrayList<>();

                // Collects all checked lutemons to move
                for (int i = 0; i < fragmentPagerAdapter.getCount(); i++) {
                    CategoryFragment fragment = categoryFragments.get(i);
                    HashMap<Integer, CheckBox> lutemonCheckboxes = fragment.getLutemonCheckboxes();

                    for (Map.Entry<Integer, CheckBox> entry : lutemonCheckboxes.entrySet()) {
                        CheckBox checkBox = entry.getValue();
                        if (checkBox.isChecked()) {
                            int lutemonId = entry.getKey();
                            Lutemon lutemon = Storage.getInstance(MoveLutemonActivity.this).getLutemon(lutemonId);

                            if (lutemon != null) {
                                lutemonsToMove.add(lutemon);
                                movedLutemons.append(lutemon.getName()).append("\n");
                            }
                        }
                    }
                }
                if (lutemonsToMove.isEmpty()) {
                    Toast.makeText(MoveLutemonActivity.this, "No Lutemons selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Moves lutemons to the selected destination and update the corresponding fragment
                storage.moveLutemonsToCategory(lutemonsToMove, selectedDestination, getApplicationContext());
                Toast.makeText(MoveLutemonActivity.this, "Moved Lutemons to " + selectedDestination + ":\n" + movedLutemons, Toast.LENGTH_SHORT).show();
                for (CategoryFragment fragment : categoryFragments) {
                    if (fragment.getArguments().getString("category").equals(selectedDestination)) {
                        fragment.updateLutemons(storage.getLutemonsByCategory(selectedDestination));
                    }
                }
            }
        });

    }

    // CategoryFragmentPagerAdapter is an adapter for the ViewPager that holds the category fragments.
    private class CategoryFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] categories = {"Home", "Training", "Battle", "Dead"};

        public CategoryFragmentPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return categoryFragments.get(position);
        }

        @Override
        public int getCount() {
            return categories.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return categories[position];
        }
    }

}

