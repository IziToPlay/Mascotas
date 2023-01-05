package com.example.mascotas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.pojo.MascotaFoto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MascotaFotoAdapter extends RecyclerView.Adapter<MascotaFotoAdapter.MascotaFotoViewHolder>{
    ArrayList<MascotaMedia> mascotaMedias;
    Activity activity;
    public MascotaFotoAdapter(ArrayList<MascotaMedia> mascotaMedias, Activity activity) {
        this.mascotaMedias = mascotaMedias;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MascotaFotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotafoto,parent,false); //Dar vida al layout al asociar al RecycleView para que se muestre en la pantalla
        return new MascotaFotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaFotoViewHolder mascotaFotoViewHolder, int position) {
        MascotaMedia mascotaMedia = mascotaMedias.get(position);
        Picasso.with(activity).load(mascotaMedia.getUrl()).placeholder(R.mipmap.img_pug).into((mascotaFotoViewHolder.imgFotoMascotaCV));
        //mascotaFotoViewHolder.imgFotoMascotaCV.setImageResource(mascotaMedia.getFoto());
        mascotaFotoViewHolder.tvRaitingMascotaCV.setText(Integer.toString(mascotaMedia.getCaption()));
    }

    @Override
    public int getItemCount() {
        return mascotaMedias.size();
    }

    public static class MascotaFotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFotoMascotaCV;
        private TextView tvRaitingMascotaCV;

        public MascotaFotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFotoMascotaCV = itemView.findViewById(R.id.imgFotoMascotaCV);
            tvRaitingMascotaCV = itemView.findViewById(R.id.tvRaitingMascotaCV);
        }
    }
}
