package com.example.mascotas.pojo;

public class MascotaFoto {
    private int foto;
    private int rating;

    public MascotaFoto(int foto, int rating) {
        this.foto = foto;
        this.rating = rating;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
