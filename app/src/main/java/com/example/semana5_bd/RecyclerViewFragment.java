package com.example.semana5_bd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana5_bd.adapter.PetAdapter;
import com.example.semana5_bd.model.Pet;
import com.example.semana5_bd.presenter.RecyclerViewPresentador;
import com.example.semana5_bd.presenter.RecyclerViewPresenter;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements RecyclerViewViewModel {
    private ArrayList<Pet> pets;
    private RecyclerView recyclerView;
    private RecyclerViewPresenter recyclerViewPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = v.findViewById(R.id.recycler);

        recyclerViewPresenter = new RecyclerViewPresentador(this, getContext());
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public PetAdapter crearAdaptador(ArrayList<Pet> pets) {
        return new PetAdapter(getActivity(), pets);
    }

    @Override
    public void inicializarAdaptador(PetAdapter petAdapter) {
        recyclerView.setAdapter(petAdapter);
    }
}
