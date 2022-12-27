package com.example.mascotas.pojo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.R;

import java.util.ArrayList;

public class DetalleMascota extends AppCompatActivity {
    ArrayList<Mascota> mascotas;
    ArrayList<Mascota> mascotas_mostrar;
    private RecyclerView listaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_cinco_mascotas);
        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = findViewById(R.id.rvTopCincoMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager glm = new GridLayoutManager(this,2);

        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas_mostrar = new ArrayList<Mascota>();
        Bundle bundle = getIntent().getExtras();
        mascotas = (ArrayList<Mascota>) bundle.getSerializable(getResources().getString(R.string.lista_top_mascotas));
        Mascota.OrdernarMascotas.main(mascotas);
        for (int i = 0; i < mascotas.size(); i++) {
            if (i < 5) {
                mascotas_mostrar.add(mascotas.get(i));
            }
        }
        /*Collections.sort(mascotas, new Comparator<Mascota>() {
            @Override
            public int compare(Mascota o1, Mascota o2) {
                return o1.getRaiting().compareTo(o2.getRaiting());
            }
        });*/

    }
    public void inicializarAdaptador(){
        MascotaAdapter adaptador = new MascotaAdapter(mascotas_mostrar,this);
        listaMascotas.setAdapter(adaptador);
    }
}
