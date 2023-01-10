package com.example.mascotas.restApi;

import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.example.mascotas.restApi.model.MascotaPerfilResponse;
import com.example.mascotas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EndpointsApi {
    //Con ROOT_URL_USER
    @GET(ConstantesRestApi.URL_GET_INFORMATION_USER)
    Call<MascotaPerfilResponse> getPerfilMascota();

    //Con ROOT_URL_MEDIA
    @GET(ConstantesRestApi.URL_GET_MEDIA_USER)
    Call<MascotaMediaResponse> getMediaMascota();

    @FormUrlEncoded //Por el endpoint en el Node.js que se ejecuta en la misma URL
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("id_dispositivo") String id_dispositivo,
                                           @Field("id_usuario_instagram") String id_usuario_instagram);
}
