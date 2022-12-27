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

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {
    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        rvMascotas = v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Perro 1",10,R.mipmap.img_pug));
        mascotas.add(new Mascota("Perro 2",5,R.mipmap.img_perro));
        mascotas.add(new Mascota("Perro 3",3,R.mipmap.img_perro2));
        mascotas.add(new Mascota("Perro 4",1,R.mipmap.img_perro3));
        mascotas.add(new Mascota("Perro 5",6,R.mipmap.img_perro4));
        mascotas.add(new Mascota("Perro 6",8,R.mipmap.img_perro5));

    }
    public void inicializarAdaptador(){
        MascotaAdapter adaptador = new MascotaAdapter(mascotas,getActivity());

        rvMascotas.setAdapter(adaptador);
    }
}
