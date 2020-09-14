package com.example.semana5_bd.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.semana5_bd.R;
import com.example.semana5_bd.model.Pet;

import java.util.ArrayList;
import java.util.Random;

public class PetFaceAdapter extends RecyclerView.Adapter<PetFaceAdapter.PetFaceViewHolder> {
    ArrayList<Pet> pets;

    public PetFaceAdapter(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public PetFaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_petsface,
                parent, false);
        return new PetFaceAdapter.PetFaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetFaceViewHolder holder, int position) {
        Pet pet = pets.get(position);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.ivPet.setBackgroundColor(color);
        holder.ivPet.setImageResource(pet.getPetImage());
        holder.tvPetPoints.setText(String.valueOf(pet.getPetPoints()));
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetFaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPet;
        private TextView tvPetPoints;

        public PetFaceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPet = itemView.findViewById(R.id.ivPetFace);
            tvPetPoints = itemView.findViewById(R.id.tvPetPointsFace);
        }
    }
}