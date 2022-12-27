package com.example.mascotas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.R;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity activity;
    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    //Infla el layout y lo pasa al viewholder para que obtenga los views.
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false); //Dar vida al layout al asociar al RecycleView para que se muestre en la pantalla
        return new MascotaViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFotoCV.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvRaitingCV.setText(Integer.toString(mascota.getRaiting()));

        /*mascotaViewHolder.btnDarHueso.setOnClickListener(view -> {
            Toast.makeText(activity, mascota.getNombre(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, DetalleMascota.class);
            activity.startActivity(intent);
        });*/

        mascotaViewHolder.btnDarHueso.setOnClickListener(view -> {
            int raiting = mascota.getRaiting();
            mascota.setRaiting(++raiting);
            Toast.makeText(activity,"Diste un hueso a: " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene la lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFotoCV;
        private ImageButton btnDarHueso;
        private TextView tvNombreCV;
        private TextView tvRaitingCV;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFotoCV = itemView.findViewById(R.id.imgFotoCV);
            btnDarHueso = itemView.findViewById(R.id.btnDarHueso);
            tvNombreCV = itemView.findViewById(R.id.tvNombreCV);
            tvRaitingCV = itemView.findViewById(R.id.tvRaitingCV);
        }
    }
}

