package com.example.mascotas.presentador;

import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.adapter.MascotaFotoAdapter;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.pojo.Mascota;

import java.util.ArrayList;

public interface IPerfilFragmentPresenter {
    void obtenerMediaMascota();
    void mostrarMediaMascotasRV();
    void obtenerLikesXFoto(String id);
    void registrarLikeEnFoto(String id_foto_instagram, String usuario_instagram, String token_cuenta_vinculada);
    void actualizarLikesEnFoto(String id, String likes);
}
