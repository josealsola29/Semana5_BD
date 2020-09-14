package com.example.semana5_bd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana5_bd.R;
import com.example.semana5_bd.db.PetConstructor;
import com.example.semana5_bd.model.Pet;

import java.util.ArrayList;
import java.util.Random;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {
    ArrayList<Pet> pets;
    Context context;

    public PetAdapter(Context context, ArrayList<Pet> pets) {
        this.pets = pets;
        this.context = context;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pets,
                parent, false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PetViewHolder holder, final int position) {
        final Pet pet = pets.get(position);
        final PetConstructor petConstructor = new PetConstructor(context);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
                rnd.nextInt(256));
        holder.ivPet.setBackgroundColor(color);
        holder.ivPet.setImageResource(pet.getPetImage());
        holder.tvPetName.setText(pet.getPetName());
        holder.tvPetPoints.setText(String.valueOf(petConstructor.getPointsPetDB(pet)));

        holder.ibPetLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petConstructor.givePoint(pet);
                holder.tvPetPoints.setText(String.valueOf(petConstructor.getPointsPetDB(pet)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPet;
        private TextView tvPetName;
        private TextView tvPetPoints;
        private ImageButton ibPetLike;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPet = itemView.findViewById(R.id.ivPet);
            tvPetName = itemView.findViewById(R.id.tvPetName);
            tvPetPoints = itemView.findViewById(R.id.tvPetPoints);
            ibPetLike = itemView.findViewById(R.id.ivPetLike);
        }
    }
}