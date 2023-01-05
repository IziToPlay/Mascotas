package com.example.mascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mascotas.adapter.MascotaFotoAdapter;
import com.example.mascotas.db.ConstructorMascotas;
import com.example.mascotas.fragment.IPerfilFragmentView;
import com.example.mascotas.fragment.IReclycerViewFragmentView;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.restApi.EndpointsApi;
import com.example.mascotas.restApi.adapter.RestApiAdapter;
import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter{

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;

    private ArrayList<MascotaMedia> mascotaMedias;

    public PerfilFragmentPresenter(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        obtenerMediaMascota();
    }


    @Override
    public void obtenerMediaMascota() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        //Se construye la forma en que se quiere deserializar los datos del json a recibir
        Gson gsonMediaMascota = restApiAdapter.construyeGsonDeserializadorMediaMascota();
        //Se hace la consulta al API para obtener el json
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiMediaInstagram(gsonMediaMascota);
        Call<MascotaMediaResponse> contactoResponseCall = endpointsApi.getMediaMascota();
        contactoResponseCall.enqueue(new Callback<MascotaMediaResponse>() {
            @Override
            public void onResponse(Call<MascotaMediaResponse> call, Response<MascotaMediaResponse> response) {
                MascotaMediaResponse mascotaMediaResponse = response.body();
                mascotaMedias = mascotaMediaResponse.getMediaMascotas();
                mostrarMediaMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaMediaResponse> call, Throwable t) {
                Toast.makeText(context, "Ocurrí algo mal con la conexión! Intente de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLÓ LA CONEXIÓN ", t.toString());
            }
        });
    }

    public void mostrarMediaMascotasRV() {
        iPerfilFragmentView.inicializarAdaptadorMascotaMedia(iPerfilFragmentView.crearAdaptadorMascotaMedia(mascotaMedias));
        //iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iPerfilFragmentView.generarGridLayout();
    }
}
