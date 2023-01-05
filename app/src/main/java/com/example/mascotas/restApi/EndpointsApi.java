package com.example.mascotas.restApi;

import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.example.mascotas.restApi.model.MascotaPerfilResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsApi {
    //Con ROOT_URL_USER
    @GET(ConstantesRestApi.URL_GET_INFORMATION_USER)
    Call<MascotaPerfilResponse> getPerfilMascota();

    //Con ROOT_URL_MEDIA
    @GET(ConstantesRestApi.URL_GET_MEDIA_USER)
    Call<MascotaMediaResponse> getMediaMascota();
}
