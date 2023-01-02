package com.example.mascotas.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.mascotas.R;
import com.example.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerMascotas(){
        BaseDatos db = new BaseDatos(context);
        if(db.obtenerTodasLasMascotas().size() == 0) {
            insertarMascotas(db);
        }
        return db.obtenerTodasLasMascotas();
    }
    public ArrayList<Mascota> obtenerTopcincoMascotas(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerTopCincoMascotas();
    }
    public void insertarMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        //contentValues.put(A,B) A: NOMBRE DE LA COLUMNA, B: VALOR A INSERTAR EN LA COLUMNA
        //1er Mascota
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Ralph");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.img_perro);
        db.insertarMascotas(contentValues);

        //2do Mascota
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Thomas");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.img_pug);
        db.insertarMascotas(contentValues);

        //3er Mascota
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Nicoli");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.img_perro3);
        db.insertarMascotas(contentValues);

        //4to Mascota
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Perro");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.img_perro4);
        db.insertarMascotas(contentValues);

        //5ta Mascota
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Perro2");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.img_perro5);
        db.insertarMascotas(contentValues);

        //6ta Mascota
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Perro3");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.img_perro2);
        db.insertarMascotas(contentValues);

        //7ma Mascota
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Perro4");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.img_perro4);
        db.insertarMascotas(contentValues);
    }

    public void darRatingMascota (Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        int ratings = obtenerRatingsMascota(mascota);
        ratings++;
        ContentValues contentValues  = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_NUMERO_RATINGS, ratings);
        db.insertarRatingsMascota(contentValues);
    }
    public int obtenerRatingsMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerRatingsMascota(mascota);
    }
}
