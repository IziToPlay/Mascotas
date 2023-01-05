package com.example.mascotas.deserializador;

import com.example.mascotas.DetalleMascota;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.restApi.JsonKeys;
import com.example.mascotas.restApi.model.MascotaMediaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DetalleMascotaDeserializador implements JsonDeserializer<MascotaMediaResponse> {

    @Override
    public MascotaMediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        gson.fromJson(json, MascotaMediaResponse.class);
        MascotaMediaResponse mascotaMediaResponse = gson.fromJson(json, MascotaMediaResponse.class);
        JsonArray detalleMascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaMediaResponse.setMediaMascotas(deserializarContactoDeJson(detalleMascotaResponseData));
        return mascotaMediaResponse;
    }

    private ArrayList<MascotaMedia> deserializarContactoDeJson(JsonArray mascotaMediaResponseData){
        ArrayList<MascotaMedia> mascotaMedias = new ArrayList<>();
        for(int i = 0; i < mascotaMediaResponseData.size(); i++){
            JsonObject detalleMascotaResponseDataObject = mascotaMediaResponseData.get(i).getAsJsonObject(); //Con esto se entra al objeto 0 del json

            //JsonObject userJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            //String user_id = userJson.get("USER_ID").getAsString();

            long id = Long.valueOf(detalleMascotaResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString());
            int likes = Integer.valueOf(detalleMascotaResponseDataObject.get(JsonKeys.MEDIA_CAPTION).getAsString());
            String urlFoto = detalleMascotaResponseDataObject.get(JsonKeys.MEDIA_MEDIA_URL).getAsString();

            MascotaMedia mascotaMediaActual = new MascotaMedia();
            mascotaMediaActual.setId(id);
            mascotaMediaActual.setCaption(likes);
            mascotaMediaActual.setUrl(urlFoto);
            mascotaMedias.add(mascotaMediaActual);
        }
        return mascotaMedias;
    }
}
