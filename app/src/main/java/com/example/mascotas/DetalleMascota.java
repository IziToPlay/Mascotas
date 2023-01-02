package com.example.mascotas;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mascotas.adapter.MascotaAdapter;
import com.example.mascotas.R;
import com.example.mascotas.adapter.PageAdapter;
import com.example.mascotas.fragment.PerfilFragment;
import com.example.mascotas.fragment.RecyclerViewFragment;
import com.example.mascotas.fragment.RecylerViewFragmentTopCinco;
import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class DetalleMascota extends AppCompatActivity {
    //ArrayList<Mascota> mascotas;
    ArrayList<Mascota> mascotas_mostrar;
    private ViewPager viewPagerTopCinco;
    private RecyclerViewFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_cinco_mascotas);
        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Top Cinco Mascotas");

        viewPagerTopCinco = findViewById(R.id.viewPagerTopCinco);

        setUpViewPager();



    }
    private ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecylerViewFragmentTopCinco());
        return fragments;
    }
    private void setUpViewPager(){
        viewPagerTopCinco.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
    }

    /*public void inicializarListaMascotas(){
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
        Collections.sort(mascotas, new Comparator<Mascota>() {
            @Override
            public int compare(Mascota o1, Mascota o2) {
                return o1.getRaiting().compareTo(o2.getRaiting());
            }
        });
    }*/
}
