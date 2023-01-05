package com.example.mascotas.model;

public class MascotaPerfil {
    private int id;
    private String username;

    public MascotaPerfil(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public MascotaPerfil() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
