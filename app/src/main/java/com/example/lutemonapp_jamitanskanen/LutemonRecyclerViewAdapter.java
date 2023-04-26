package com.example.lutemonapp_jamitanskanen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// LutemonRecyclerViewAdapter is a custom RecyclerView.Adapter for displaying lutemon objects in a list.
public class LutemonRecyclerViewAdapter extends RecyclerView.Adapter<LutemonRecyclerViewAdapter.LutemonViewHolder> {

    private List<Lutemon> lutemons;
    private Context context;

    public LutemonRecyclerViewAdapter(Context context, List<Lutemon> lutemons) {
        this.lutemons = lutemons;
        this.context = context;
    }

    private int getLutemonImageResource(String color) {
        switch (color.toLowerCase()) {
            case "green":
                return R.drawable.lutemon_green;
            case "black":
                return R.drawable.lutemon_black;
            case "white":
                return R.drawable.lutemon_white;
            case "pink":
                return R.drawable.lutemon_pink;
            case "orange":
                return R.drawable.lutemon_orange;
            default:
                return R.drawable.lutemon_default;
        }
    }


    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lutemon_item, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);

        String nameAndColor = lutemon.getName() + " (" + lutemon.getColor() + ")";
        String stats = "Attack: " + lutemon.getAttack() +
                ", Defense: " + lutemon.getDefense() +
                ", Life: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() +
                ", Experience: " + lutemon.getExperience();

        holder.nameAndColor.setText(nameAndColor);
        holder.stats.setText(stats);
        holder.lutemonImage.setImageResource(getLutemonImageResource(lutemon.getColor()));
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    // LutemonViewHolder is a custom ViewHolder for holding lutemon views.
    static class LutemonViewHolder extends RecyclerView.ViewHolder {

        TextView nameAndColor;
        TextView stats;
        ImageView lutemonImage;

        LutemonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameAndColor = itemView.findViewById(R.id.text_view_lutemon_name_and_color);
            stats = itemView.findViewById(R.id.text_view_lutemon_stats);
            lutemonImage = itemView.findViewById(R.id.image_view_lutemon);
        }
    }
}
