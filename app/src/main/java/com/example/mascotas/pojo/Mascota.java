package com.example.mascotas.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Mascota implements Serializable {
    private String nombre;
    private int foto;
    private int raiting;

    public Mascota(String nombre, int raiting, int foto) {
        this.nombre = nombre;
        this.raiting = raiting;
        this.foto = foto;
    }
    public static class OrdernarMascotas {
        public static void main(ArrayList<Mascota> args) {
            Collections.sort(args, (o1, o2) -> Integer.valueOf(o2.raiting).compareTo(o1.raiting));
        }
    }
            //ArrayList<Mascota> alMascotas = new ArrayList<>();
            //Ordenar ascendentemente el arrayList
//Ordenar descendentemente el arrayList
/*
@Override
public int compare(Mascota o1, Mascota o2) {
    return Integer.valueOf(o2.raiting).compareTo(o1.raiting);
}*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }
}

