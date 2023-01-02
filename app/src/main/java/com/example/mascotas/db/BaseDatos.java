package com.example.mascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context,ConstantesBaseDatos.DATABASE_NAME,null,ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT," +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + " INTEGER," +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATING + " INTEGER" +
                ")";
        String queryCrearTablaMascotaRatings = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID + " INTEGER," +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_NUMERO_RATINGS + " INTEGER," +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID + ") REFERENCES " +
                ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaMascota);
        sqLiteDatabase.execSQL(queryCrearTablaMascotaRatings);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null); //null porque no tengo ningun filtro para el query

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setRaiting(registros.getInt(3));

            String queryLikes = "SELECT (" + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_NUMERO_RATINGS +") as ratings" +
                    " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS + " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID +
                    "=" + mascotaActual.getId();

            Cursor registrosRatings = db.rawQuery(queryLikes,null);
            if(registrosRatings.moveToNext()){
                mascotaActual.setRaiting(registrosRatings.getInt(0));
            }else{
                mascotaActual.setRaiting(0);
            }

            mascotas.add(mascotaActual);
        }
        db.close();
        return mascotas;
    }
    public ArrayList<Mascota> obtenerTopCincoMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        ArrayList<Mascota> mascotas_mostrar = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null); //null porque no tengo ningun filtro para el query

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setRaiting(registros.getInt(3));

            String queryLikes = "SELECT (" + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_NUMERO_RATINGS +") as ratings" +
                    " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS + " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID +
                    "=" + mascotaActual.getId();

            Cursor registrosRatings = db.rawQuery(queryLikes,null);
            if(registrosRatings.moveToNext()){
                mascotaActual.setRaiting(registrosRatings.getInt(0));
            }else{
                mascotaActual.setRaiting(0);
            }

            mascotas.add(mascotaActual);
        }
        db.close();
        Mascota.OrdernarMascotas.main(mascotas);
        for (int i = 0; i < mascotas.size(); i++) {
            if (i < 5) {
                mascotas_mostrar.add(mascotas.get(i));
            }
        }
        return mascotas_mostrar;
    }
    public int obtenerRatingsMascota(Mascota mascota){
        int ratings = 0;
        String query = "SELECT (" + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_NUMERO_RATINGS + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS + " WHERE " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID + "=" + mascota.getId();
        /*String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_MASCOTA_RATINGS_NUMERO_RATINGS+ ")" +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS + " WHERE " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID + "=" + mascota.getId();*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null); //null porque no tengo ningun filtro para el query

        while(registros.moveToNext()){
            ratings = registros.getInt(0);
        }
        db.close();
        return ratings;
    }
    public int obtenerRatingsMascotaById(int id){
        int ratings = 0;
        String query = "SELECT (" + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_NUMERO_RATINGS + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS + " WHERE " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID + "=" + id;
        /*String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_MASCOTA_RATINGS_NUMERO_RATINGS+ ")" +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS + " WHERE " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID + "=" + id;*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null); //null porque no tengo ningun filtro para el query

        while(registros.moveToNext()){
            ratings = registros.getInt(0);
        }
        //db.close();
        return ratings;
    }
    public void insertarMascotas(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS,null,contentValues);
        db.close();
    }

    public void insertarRatingsMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        int mascota_id = contentValues.getAsInteger(ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS_MASCOTA_ID);
        if(obtenerRatingsMascotaById(mascota_id) > 0){
            db.update(ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS,contentValues,"id_mascota=?", new String[]{ String.valueOf(mascota_id)});
        } else {
            db.insert(ConstantesBaseDatos.TABLE_MASCOTAS_RATINGS, null, contentValues);
        }
        db.close();
    }
}
