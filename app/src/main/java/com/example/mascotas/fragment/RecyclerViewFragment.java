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

import com.example.mascotas.MainActivity;
import com.example.mascotas.R;
import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.presentador.IRecyclerViewFragmentPresenter;
import com.example.mascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements IReclycerViewFragmentView {
    private RecyclerView rvMascotas;
    private RecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        rvMascotas = v.findViewById(R.id.rvMascotas);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        presenter.obtenerMascotasBaseDatos();

        return v;
    }

    /*public void ejecutarListadoAMostrarSegunContext(){
        String contextName = getContext().getClass().getSimpleName();
        switch(contextName){
            case "MainActivity":
                presenter.obtenerMascotasBaseDatos();
                break;
            case "DetalleMascota":
                presenter.obtenerTopCincoMascotasBaseDatos();
                break;
        }
    }*/


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter adaptador = new MascotaAdapter(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotaAdapter mascotaAdapter) {
        rvMascotas.setAdapter(mascotaAdapter);
    }
}
