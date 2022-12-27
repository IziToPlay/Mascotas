package com.example.mascotas.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.adapter.MascotaFotoAdapter;
import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.pojo.MascotaFoto;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {
    private CircularImageView ivFotoPerfil;
    private TextView tvNombrePerfil;
    private RecyclerView rvFotosMascota;
    private ArrayList<MascotaFoto> mascotaFotos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);

        ivFotoPerfil = v.findViewById(R.id.ivFotoPerfil);
        tvNombrePerfil = v.findViewById(R.id.tvNombrePerfil);
        rvFotosMascota = v.findViewById(R.id.rvFotosMascota);

        ivFotoPerfil.setImageResource(R.mipmap.img_pug);
        tvNombrePerfil.setText(getResources().getString(R.string.nombre_perfil));

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        rvFotosMascota.setLayoutManager(glm);
        inicializarListaFotos();
        inicializarAdaptador();

        return v;
    }
    public void inicializarListaFotos(){
        mascotaFotos = new ArrayList<MascotaFoto>();

        mascotaFotos.add(new MascotaFoto(R.mipmap.img_pug,9));
        mascotaFotos.add(new MascotaFoto(R.mipmap.img_perro,11));
        mascotaFotos.add(new MascotaFoto(R.mipmap.img_perro2,20));
        mascotaFotos.add(new MascotaFoto(R.mipmap.img_perro3,50));
        mascotaFotos.add(new MascotaFoto(R.mipmap.img_perro4,8));
        mascotaFotos.add(new MascotaFoto(R.mipmap.img_perro5,15));
    }
    public void inicializarAdaptador(){
        MascotaFotoAdapter adaptador = new MascotaFotoAdapter(mascotaFotos,getActivity());
        rvFotosMascota.setAdapter(adaptador);
    }
}
