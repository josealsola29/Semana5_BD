package com.example.semana5_bd;

import com.example.semana5_bd.adapter.PetAdapter;
import com.example.semana5_bd.model.Pet;

import java.util.ArrayList;

public interface RecyclerViewViewModel {
    public void generarLinearLayoutVertical();

    public PetAdapter crearAdaptador(ArrayList<Pet> pets);

    public void inicializarAdaptador(PetAdapter petAdapter);
}
