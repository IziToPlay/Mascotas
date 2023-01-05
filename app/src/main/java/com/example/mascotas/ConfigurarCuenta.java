package com.example.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigurarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void guardarCuenta(View view) {
            SharedPreferences miPreferenciaCompartida = getSharedPreferences(getResources().getString(R.string.nombre_cuenta_configurada), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

            EditText etInputUsuario = findViewById(R.id.etInputUsuario);

            String usuario = etInputUsuario.getText().toString();

            editor.putString(getResources().getString(R.string.spEtUsuario), usuario);

            editor.commit();

            Toast.makeText(this, getResources().getString(R.string.cuenta_configurada), Toast.LENGTH_SHORT).show();
            etInputUsuario.setText("");
    }
}