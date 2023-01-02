package com.example.mascotas.db;

public class ConstantesBaseDatos {
    public static final String DATABASE_NAME  = "Mascotas";
    public static final int DATABASE_VERSION = 1;

    //Tabla Top Mascotas
    public static final String TABLE_MASCOTAS = "tb_mascotas";
    public static final String TABLE_MASCOTAS_ID = "id";
    public static final String TABLE_MASCOTAS_NOMBRE = "nombre";
    public static final String TABLE_MASCOTAS_FOTO = "foto";
    public static final String TABLE_MASCOTAS_RATING = "rating";

    //Tabla Mascotas_Ratings
    public static final String TABLE_MASCOTAS_RATINGS = "tb_mascota_ratings";
    public static final String TABLE_MASCOTAS_RATINGS_ID = "id";
    public static final String TABLE_MASCOTAS_RATINGS_MASCOTA_ID = "id_mascota";
    public static final String TABLE_MASCOTAS_RATINGS_NUMERO_RATINGS = "cantidad_ratings";
}
