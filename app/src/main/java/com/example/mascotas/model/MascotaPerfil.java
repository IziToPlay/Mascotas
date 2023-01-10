package com.example.mascotas.model;

public class MascotaPerfil {
    private String id;
    private String username;

    public MascotaPerfil(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public MascotaPerfil() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
