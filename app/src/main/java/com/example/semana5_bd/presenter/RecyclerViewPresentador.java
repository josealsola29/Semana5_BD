package com.example.semana5_bd.presenter;

import android.content.Context;

import com.example.semana5_bd.RecyclerViewViewModel;
import com.example.semana5_bd.db.PetConstructor;
import com.example.semana5_bd.model.Pet;

import java.util.ArrayList;

public class RecyclerViewPresentador implements RecyclerViewPresenter {
    private RecyclerViewViewModel recyclerViewViewModel;
    private Context context;
    private PetConstructor petConstructor;
    private ArrayList<Pet> pets;

    public RecyclerViewPresentador(RecyclerViewViewModel recyclerViewViewModel, Context context) {
        this.recyclerViewViewModel = recyclerViewViewModel;
        this.context = context;
        obtenerPetsBD();
    }

    @Override
    public void obtenerPetsBD() {
        petConstructor = new PetConstructor(context);
        pets = petConstructor.obtainData();
        mostrarPetsRV();
    }

    @Override
    public void mostrarPetsRV() {
        recyclerViewViewModel.inicializarAdaptador(recyclerViewViewModel.crearAdaptador(pets));
        recyclerViewViewModel.generarLinearLayoutVertical();
    }
}
