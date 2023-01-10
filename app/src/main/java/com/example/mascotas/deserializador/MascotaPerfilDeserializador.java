package com.example.mascotas.deserializador;

import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.model.MascotaPerfil;
import com.example.mascotas.restApi.JsonKeys;
import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.example.mascotas.restApi.model.MascotaPerfilResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MascotaPerfilDeserializador implements JsonDeserializer<MascotaPerfilResponse> {

    @Override
    public MascotaPerfilResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        gson.fromJson(json, MascotaPerfilResponse.class);
        MascotaPerfilResponse mascotaPerfilResponse = gson.fromJson(json, MascotaPerfilResponse.class);
        JsonObject detalleMascotaResponseData = json.getAsJsonObject();

        mascotaPerfilResponse.setMascotaPerfil(deserializarContactoDeJson(detalleMascotaResponseData));
        return mascotaPerfilResponse;
    }

    private MascotaPerfil deserializarContactoDeJson(JsonObject mascotaPerfilResponseData){
        MascotaPerfil mascotaPerfil = new MascotaPerfil();
        //for(int i = 0; i < mascotaPerfilResponseData.size(); i++){
            //JsonObject detalleMascotaResponseDataObject = mascotaPerfilResponseData.get(i).getAsJsonObject(); //Con esto se entra al objeto 0 del json

            //JsonObject userJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            //String user_id = userJson.get("USER_ID").getAsString();

            String id = mascotaPerfilResponseData.get(JsonKeys.USER_ID).getAsString();
            String username = mascotaPerfilResponseData.get(JsonKeys.USER_USERNAME).getAsString();

            mascotaPerfil.setId(id);
            mascotaPerfil.setUsername(username);
        //}
        return mascotaPerfil;
    }

}
