package com.example.mascotas.restApi.model;

import com.example.mascotas.model.MascotaPerfil;

public class FotoLikeResponse {
    private String id;
    private String id_foto_instagram;
    private String id_usuario_instagram;
    private String id_dispositivo;
    private int likes;

    public FotoLikeResponse(String id, String id_foto_instagram, String id_usuario_instagram, String id_dispositivo, int likes) {
        this.id = id;
        this.id_foto_instagram = id_foto_instagram;
        this.id_usuario_instagram = id_usuario_instagram;
        this.id_dispositivo = id_dispositivo;
        this.likes = likes;
    }

    public FotoLikeResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
