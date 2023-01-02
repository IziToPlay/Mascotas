package com.example.mascotas.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class RecylerViewFragmentTopCinco extends Fragment implements IReclycerViewFragmentView {
    private RecyclerView rvTopCincoMascotas;
    private RecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview_topcinco, container, false);

        rvTopCincoMascotas = v.findViewById(R.id.rvTopCincoMascotas);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        presenter.obtenerTopCincoMascotasBaseDatos();

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvTopCincoMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter adaptador = new MascotaAdapter(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotaAdapter mascotaAdapter) {
        rvTopCincoMascotas.setAdapter(mascotaAdapter);
    }
}
