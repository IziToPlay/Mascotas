package com.example.mascotas.fragment;

import com.example.mascotas.adapter.MascotaFotoAdapter;
import com.example.mascotas.model.MascotaMedia;

import java.util.ArrayList;

public interface IPerfilFragmentView {
    void generarGridLayout();
    MascotaFotoAdapter crearAdaptadorMascotaMedia(ArrayList<MascotaMedia> mascotaMedias);
    void inicializarAdaptadorMascotaMedia(MascotaFotoAdapter mascotaMediaAdapter);
}
