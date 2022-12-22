package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        listaMascotas = findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager glm = new GridLayoutManager(this,2);

        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();
    }
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Perro 1",10,R.mipmap.img_pug));
        mascotas.add(new Mascota("Perro 2",5,R.mipmap.img_perro));
        mascotas.add(new Mascota("Perro 3",3,R.mipmap.img_perro2));
        mascotas.add(new Mascota("Perro 4",1,R.mipmap.img_perro3));
        mascotas.add(new Mascota("Perro 5",6,R.mipmap.img_perro4));
        mascotas.add(new Mascota("Perro 6",8,R.mipmap.img_perro5));
    }
    public void inicializarAdaptador(){
        MascotaAdapter adaptador = new MascotaAdapter(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        //mascotas = new ArrayList<Mascota>();

        //MenuItem topFive = menu.findItem(R.id.topFiveStar);
        /*topFive.setOnMenuItemClickListener(view -> {
            Intent intent = new Intent(this,DetalleMascota.class);
            intent.putExtra(getResources().getString(R.string.lista_top_mascotas),mascotas);
            startActivity(intent);
            return super.onCreateOptionsMenu(menu);
        });*/

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.topFiveStar:
                Intent intent = new Intent(this,DetalleMascota.class);
                intent.putExtra(getResources().getString(R.string.lista_top_mascotas),mascotas);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}