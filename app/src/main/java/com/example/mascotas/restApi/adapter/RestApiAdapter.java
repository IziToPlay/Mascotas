package com.example.mascotas.restApi.adapter;

import com.example.mascotas.deserializador.DetalleMascotaDeserializador;
import com.example.mascotas.deserializador.MascotaPerfilDeserializador;
import com.example.mascotas.restApi.ConstantesRestApi;
import com.example.mascotas.restApi.EndpointsApi;
import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.example.mascotas.restApi.model.MascotaPerfilResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public EndpointsApi establecerConexionRestApiUserInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_USER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionRestApiMediaInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_MEDIA)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionRestApiFirebase(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    //DESERIALIZADONDO EL GSON
    public Gson  construyeGsonDeserializadorPerfilMascota(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaPerfilResponse.class, new MascotaPerfilDeserializador());

        return gsonBuilder.create();
    }

    public Gson  construyeGsonDeserializadorMediaMascota(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaMediaResponse.class, new DetalleMascotaDeserializador());

        return gsonBuilder.create();
    }
}
