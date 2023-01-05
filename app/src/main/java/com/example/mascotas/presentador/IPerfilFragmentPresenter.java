package com.example.mascotas.presentador;

import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.adapter.MascotaFotoAdapter;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.pojo.Mascota;

import java.util.ArrayList;

public interface IPerfilFragmentPresenter {
    void obtenerMediaMascota();
    void mostrarMediaMascotasRV();
}
