package com.example.mascotas.restApi;

import com.example.mascotas.restApi.model.FotoLikeResponse;
import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.example.mascotas.restApi.model.MascotaPerfilResponse;
import com.example.mascotas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @GET(ConstantesRestApi.KEY_GET_LIKES)
    Call<FotoLikeResponse> obtenerLikes(@Path("id") String id);

    @FormUrlEncoded //Por el endpoint en el Node.js que se ejecutan los fields como req.body....
    @POST(ConstantesRestApi.KEY_POST_LIKE)
    Call<FotoLikeResponse> registrarLikeEnFoto(@Field("id_foto_instagram") String id_foto_instagram,
                                                @Field("id_usuario_instagram") String id_usuario_instagram,
                                                @Field("id_dispositivo") String id_dispositivo,
                                                @Field("likes") int likes);

    @PUT(ConstantesRestApi.KEY_PUT_LIKES)
    Call<FotoLikeResponse> actualizarLikesEnFoto(@Path("id") String id,
                                               @Path("likes") String likes);
}
