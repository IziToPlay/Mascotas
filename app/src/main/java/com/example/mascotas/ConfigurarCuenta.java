package com.example.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mascotas.model.MascotaPerfil;
import com.example.mascotas.presentador.PerfilFragmentPresenter;
import com.example.mascotas.restApi.EndpointsApi;
import com.example.mascotas.restApi.adapter.RestApiAdapter;
import com.example.mascotas.restApi.model.MascotaPerfilResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurarCuenta extends AppCompatActivity {
    private MascotaPerfil mascotaPerfil;
    EditText etInputUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etInputUsuario = findViewById(R.id.etInputUsuario);
    }

    public void obtenerPerfilMascota(View view) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        //Se construye la forma en que se quiere deserializar los datos del json a recibir
        Gson gsonPerfilMascota = restApiAdapter.construyeGsonDeserializadorPerfilMascota();
        //Se hace la consulta al API para obtener el json
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiUserInstagram(gsonPerfilMascota);
        Call<MascotaPerfilResponse> contactoResponseCall = endpointsApi.getPerfilMascota();
        contactoResponseCall.enqueue(new Callback<MascotaPerfilResponse>() {
            @Override
            public void onResponse(Call<MascotaPerfilResponse> call, Response<MascotaPerfilResponse> response) {
                MascotaPerfilResponse mascotaPerfilResponse = response.body();
                mascotaPerfil = mascotaPerfilResponse.getMascotaPerfil();
                guardarUsuario(mascotaPerfil);
            }

            @Override
            public void onFailure(Call<MascotaPerfilResponse> call, Throwable t) {
                Toast.makeText(ConfigurarCuenta.this, "Ocurrí algo mal con la conexión! Intente de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLÓ LA CONEXIÓN ", t.toString());
            }
        });
    }
    public void guardarUsuario(MascotaPerfil mascotaPerfil){
        SharedPreferences miPreferenciaCompartida = getSharedPreferences(getResources().getString(R.string.nombre_cuenta_configurada), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

        //Llamar al endpoint para obtener data de usuario

        editor.putString(getResources().getString(R.string.spEtUsuario), mascotaPerfil.getUsername());
        editor.putString(getResources().getString(R.string.spEtIDUsuario), mascotaPerfil.getId());

        editor.commit();
        Toast.makeText(ConfigurarCuenta.this, getResources().getString(R.string.cuenta_configurada), Toast.LENGTH_SHORT).show();
        etInputUsuario.setText("");
    }
}