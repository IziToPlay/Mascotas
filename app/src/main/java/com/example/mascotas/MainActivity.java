package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mascotas.adapter.PageAdapter;
import com.example.mascotas.fragment.PerfilFragment;
import com.example.mascotas.fragment.RecyclerViewFragment;
import com.example.mascotas.model.Contacto;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setUpViewPager();
    }
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        //mascotas = new ArrayList<Mascota>();

        //MenuItem topFive = menu.findItem(R.id.topFiveStar);
        /*topFive.setOnMenuItemClickListener(view -> {
            Intent intent = new Intent(this,DetalleMascota.class);
            intent.putExtra(getResources().getString(R.string.lista_top_mascotas),mascotas);
            startActivity(intent);
            return super.onCreateOptionsMenu(menu);
        });*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.topFiveStar:
                Intent intent = new Intent(this, DetalleMascota.class);
                //intent.putExtra(getResources().getString(R.string.lista_top_mascotas),mascotas);
                startActivity(intent);
                break;
            case R.id.moContacto:
                Intent i = new Intent(this, Contacto.class);
                startActivity(i);
                break;
            case R.id.moAcercaDe:
                Intent in = new Intent(this, AcercaDe.class);
                startActivity(in);
                break;
            case R.id.moConfigurarCuenta:
                Intent intent1 = new Intent(this, ConfigurarCuenta.class);
                startActivity(intent1);
                break;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}