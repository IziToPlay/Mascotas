package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mascotas.adapter.PageAdapter;
import com.example.mascotas.fragment.PerfilFragment;
import com.example.mascotas.fragment.RecyclerViewFragment;
import com.example.mascotas.model.Contacto;
import com.example.mascotas.restApi.EndpointsApi;
import com.example.mascotas.restApi.adapter.RestApiAdapter;
import com.example.mascotas.restApi.model.UsuarioResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            case R.id.moRecibirNotificaciones:
                enviarToken();
                break;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    public void enviarToken() {
        // Get token
        // [START log_reg_token]
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("MAIN ACTIVITY TOKEN", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    // Get new FCM registration token
                    String token = task.getResult();

                    // Log and toast
                    //Log.d("MAIN ACTIVITY TOKEN", token);
                    enviarTokenRegistro(token);
                });
    }
    private void enviarTokenRegistro(String token) {
        Log.d("TOKEN DE MAIN ACTIVITY", token);
        SharedPreferences miPreferenciaCompartida = getSharedPreferences("CuentaConfigurada", Context.MODE_PRIVATE);
        String id_usuario_instagram = miPreferenciaCompartida.getString(getResources().getString(R.string.spEtIDUsuario), getResources().getString(R.string.NoExisteVariable));
        if (id_usuario_instagram != getResources().getString(R.string.NoExisteVariable)) {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiFirebase();
            Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token, id_usuario_instagram);
            usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    UsuarioResponse usuarioResponse = response.body();
                    Log.d("ID_DISPOSITIVO", usuarioResponse.getId_dispositivo());
                    Log.d("ID_USUARIO_INSTAGRAM", usuarioResponse.getId_usuario_instagram());
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                }
            });
            Toast.makeText(MainActivity.this, getResources().getString(R.string.configuracion_notificaciones), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getResources().getString(R.string.ErrorIDUsuarioInstagram), Toast.LENGTH_SHORT).show();
        }
    }
}