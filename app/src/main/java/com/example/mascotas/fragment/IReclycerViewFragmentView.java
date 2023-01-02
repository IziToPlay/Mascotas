package com.example.mascotas.fragment;

import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.pojo.Mascota;

import java.util.ArrayList;

public interface IReclycerViewFragmentView {
    void generarLinearLayoutVertical();
    MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);
    void inicializarAdaptador(MascotaAdapter mascotaAdapter);
}
