package com.example.semana5_bd;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana5_bd.adapter.PetFaceAdapter;
import com.example.semana5_bd.model.Pet;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {
    private CircularImageView circularImageView;
    private ArrayList<Pet> pets;

    public PerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        circularImageView = v.findViewById(R.id.imageView);
        configCircularImage();

        RecyclerView recyclerView = v.findViewById(R.id.rvPetFace);

        LinearLayoutManager linearLayoutManager;
        try {
            linearLayoutManager = new LinearLayoutManager(getActivity());

            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            initDummyData();
            PetFaceAdapter petAdapter = new PetFaceAdapter(pets);
            recyclerView.setAdapter(petAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    private void configCircularImage() {
        // Set Color
        circularImageView.setCircleColor(Color.MAGENTA);
        // or with gradient
        circularImageView.setCircleColorStart(Color.BLACK);
        circularImageView.setCircleColorEnd(Color.RED);
        circularImageView.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

        // Set Border
        circularImageView.setBorderWidth(10f);
        circularImageView.setBorderColor(Color.BLACK);
        // or with gradient
        circularImageView.setBorderColorStart(Color.BLACK);
        circularImageView.setBorderColorEnd(Color.RED);
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

        // Add Shadow with default param
        circularImageView.setShadowEnable(true);
        // or with custom param
        circularImageView.setShadowRadius(7f);
        circularImageView.setShadowColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);

    }

    private void initDummyData() {
        pets = new ArrayList<>();
        pets.add(new Pet(1,"Schrödinger", 24, R.drawable.icons8_black_cat));
        pets.add(new Pet(1,"Shaggy", 5, R.drawable.icons8_dog));
        pets.add(new Pet(1,"Inu", 8, R.drawable.icons8_dog_));
        pets.add(new Pet(1,"Iku", 1, R.drawable.icons8_turtle));
        pets.add(new Pet(1,"Pushi", 15, R.drawable.icons8_cute_hamster_96));
        pets.add(new Pet(1,"Mashu", 74, R.drawable.icons8_yorkshire_terrier_96));
        pets.add(new Pet(1,"Riko", 63, R.drawable.icons8_running_rabbit_96));
        pets.add(new Pet(1,"Kyu", 87, R.drawable.icons8_pug_96));
        pets.add(new Pet(1,"Max", 16, R.drawable.icons8_corgi_96));
    }
}