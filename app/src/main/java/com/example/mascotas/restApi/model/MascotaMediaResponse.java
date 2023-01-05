package com.example.mascotas.restApi.model;

import com.example.mascotas.model.MascotaMedia;

import java.util.ArrayList;

public class MascotaMediaResponse {
    ArrayList<MascotaMedia> mediaMascotas;

    public ArrayList<MascotaMedia> getMediaMascotas() {
        return mediaMascotas;
    }

    public void setMediaMascotas(ArrayList<MascotaMedia> mediaMascotas) {
        this.mediaMascotas = mediaMascotas;
    }
}

