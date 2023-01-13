package com.example.mascotas.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.mascotas.R;
import com.example.mascotas.fragment.IPerfilFragmentView;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.restApi.EndpointsApi;
import com.example.mascotas.restApi.adapter.RestApiAdapter;
import com.example.mascotas.restApi.model.FotoLikeResponse;
import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter{

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;

    private ArrayList<MascotaMedia> mascotaMedias;
    public int likes;

    public PerfilFragmentPresenter(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
    }

    public PerfilFragmentPresenter() {
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
                for (MascotaMedia mascotaMedia: mascotaMedias) {
                    obtenerLikesXFoto(String.valueOf(mascotaMedia.getId()));
                    mascotaMedia.setCaption(likes);
                }
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

    @Override
    public void obtenerLikesXFoto(String id) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiFirebase();
        Call<FotoLikeResponse> fotoLikeResponseCall = endpoints.obtenerLikes(id);
        fotoLikeResponseCall.enqueue(new Callback<FotoLikeResponse>() {
            @Override
            public void onResponse(Call<FotoLikeResponse> call, Response<FotoLikeResponse> response) {
                FotoLikeResponse fotoLikeResponse = response.body();
                likes = fotoLikeResponse.getLikes();
            }

            @Override
            public void onFailure(Call<FotoLikeResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void registrarLikeEnFoto(String id_foto_instagram, String id_usuario_instagram, String id_dispositivo) {
        //SharedPreferences miPreferenciaCompartida = context.getSharedPreferences("CuentaConfigurada", Context.MODE_PRIVATE);
        //String token_cuenta_vinculada = miPreferenciaCompartida.getString(context.getResources().getString(R.string.spEtToken), context.getResources().getString(R.string.NoExisteVariable));
        //String id_usuario_instagram = miPreferenciaCompartida.getString(context.getResources().getString(R.string.spEtUsuario), context.getResources().getString(R.string.NoExisteVariable));

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiFirebase();
        Call<FotoLikeResponse> fotoLikeResponseCall = endpoints.registrarLikeEnFoto(id_foto_instagram,id_usuario_instagram,id_dispositivo,1);
        fotoLikeResponseCall.enqueue(new Callback<FotoLikeResponse>() {
            @Override
            public void onResponse(Call<FotoLikeResponse> call, Response<FotoLikeResponse> response) {
                FotoLikeResponse fotoLikeResponse = response.body();
            }
            @Override
            public void onFailure(Call<FotoLikeResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void actualizarLikesEnFoto(String id, String _likes) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiFirebase();
        Call<FotoLikeResponse> fotoLikeResponseCall = endpoints.actualizarLikesEnFoto(id, _likes);
        fotoLikeResponseCall.enqueue(new Callback<FotoLikeResponse>() {
            @Override
            public void onResponse(Call<FotoLikeResponse> call, Response<FotoLikeResponse> response) {
                FotoLikeResponse fotoLikeResponse = response.body();
            }

            @Override
            public void onFailure(Call<FotoLikeResponse> call, Throwable t) {

            }
        });
    }
    public void obtenerToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("MAIN ACTIVITY TOKEN", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    // Get new FCM registration token
                    String token = task.getResult();

                    // Log and toast
                    //Log.d("MAIN ACTIVITY TOKEN", token);
                    SharedPreferences miPreferenciaCompartida = context.getSharedPreferences(context.getResources().getString(R.string.nombre_cuenta_configurada), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

                    //Llamar al endpoint para obtener data de usuario

                    editor.putString(context.getResources().getString(R.string.spEtToken), token);

                    editor.commit();
                });
    }

}
