package com.example.mascotas.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.adapter.MascotaFotoAdapter;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.model.MascotaPerfil;
import com.example.mascotas.pojo.MascotaFoto;
import com.example.mascotas.presentador.PerfilFragmentPresenter;
import com.example.mascotas.restApi.EndpointsApi;
import com.example.mascotas.restApi.adapter.RestApiAdapter;
import com.example.mascotas.restApi.model.MascotaPerfilResponse;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment implements IPerfilFragmentView{
    private MascotaPerfil mascotaPerfil;
    private CircularImageView ivFotoPerfil;
    private TextView tvNombrePerfil;
    private PerfilFragmentPresenter presenter;
    private RecyclerView rvFotosMascota;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);

        //obtenerPerfilMascota();
        ivFotoPerfil = v.findViewById(R.id.ivFotoPerfil);
        Picasso.with(getActivity())
                .load("https://instagram.flim28-1.fna.fbcdn.net/v/t51.2885-19/323308466_1548404405659022_8865447066986094930_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.flim28-1.fna.fbcdn.net&_nc_cat=102&_nc_ohc=_xtxjo0kxSIAX8D3eZ7&tn=TSyqXRf_kGQJi7WN&edm=AOQ1c0wBAAAA&ccb=7-5&oh=00_AfAdGpNza_5LlvefRzvCAcju-yny96Zqf7G1x_EdIfVnTw&oe=63BC3C73&_nc_sid=8fd12b")
                .placeholder(R.mipmap.img_pug)
                .into(ivFotoPerfil);

        tvNombrePerfil = v.findViewById(R.id.tvNombrePerfil);
        rvFotosMascota = v.findViewById(R.id.rvFotosMascota);
        presenter = new PerfilFragmentPresenter(this, getContext());
        presenter.obtenerMediaMascota();
        mostrarUsuario();

        return v;
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);

        rvFotosMascota.setLayoutManager(glm);
    }

    @Override
    public MascotaFotoAdapter crearAdaptadorMascotaMedia(ArrayList<MascotaMedia> mascotaMedias) {
        MascotaFotoAdapter adaptador = new MascotaFotoAdapter(mascotaMedias,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorMascotaMedia(MascotaFotoAdapter mascotaMediaAdapter) {
        rvFotosMascota.setAdapter(mascotaMediaAdapter);
    }

    public void mostrarUsuario(){
        SharedPreferences miPreferenciaCompartida = getActivity().getSharedPreferences("CuentaConfigurada", Context.MODE_PRIVATE);
        String nombre = miPreferenciaCompartida.getString(getResources().getString(R.string.spEtUsuario),getResources().getString(R.string.NoExisteVariable));
        tvNombrePerfil.setText(nombre); //\n salto de linea, \t salto con el tabulador
    }
}
