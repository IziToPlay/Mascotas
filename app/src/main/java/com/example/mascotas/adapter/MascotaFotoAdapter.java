package com.example.mascotas.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.model.MascotaMedia;
import com.example.mascotas.pojo.Mascota;
import com.example.mascotas.pojo.MascotaFoto;
import com.example.mascotas.presentador.PerfilFragmentPresenter;
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

        mascotaFotoViewHolder.imgFotoMascotaCV.setOnClickListener(view -> {
            PerfilFragmentPresenter perfilFragmentPresenter = new PerfilFragmentPresenter();
            if(mascotaMedia.getCaption() == 0){
                SharedPreferences miPreferenciaCompartida = activity.getSharedPreferences("CuentaConfigurada", Context.MODE_PRIVATE);
                String id_usuario_instagram = miPreferenciaCompartida.getString(activity.getResources().getString(R.string.spEtUsuario), "5649402008522057");
                String token_cuenta_vinculada = miPreferenciaCompartida.getString(activity.getResources().getString(R.string.spEtToken), "f7i5w7Y7SVGWHfhw7tsCcI:APA91bF_wSab_iIo5VrZ3oj31ngA_dJOXYmuFcdmoZB4rssrQ3CM_X6uwtBfj2UZ7hkjvgcmXRcWnSsQwz6AgPMcFY59zezM_IVF7aNnkOcDMtk7rqAZvR6D6110vHhseamF60tXOq-9");

                perfilFragmentPresenter.registrarLikeEnFoto(Long.toString(mascotaMedia.getId()),id_usuario_instagram,token_cuenta_vinculada);
                mascotaMedia.setCaption(1);
                mascotaFotoViewHolder.tvRaitingMascotaCV.setText(String.valueOf(mascotaMedia.getCaption()));
                Toast.makeText(activity, "Haz dado like a esta foto!", Toast.LENGTH_LONG).show();

            }else{
                int likes = mascotaMedia.getCaption();
                likes++;
                perfilFragmentPresenter.actualizarLikesEnFoto(String.valueOf(mascotaMedia.getId()),String.valueOf(likes));
                mascotaMedia.setCaption(likes);
                mascotaFotoViewHolder.tvRaitingMascotaCV.setText(Integer.toString(mascotaMedia.getCaption()));
                Toast.makeText(activity, "Haz dado like a esta foto!", Toast.LENGTH_LONG).show();

            }
        });
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
