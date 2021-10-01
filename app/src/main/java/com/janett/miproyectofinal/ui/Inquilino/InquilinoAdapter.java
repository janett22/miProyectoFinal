package com.janett.miproyectofinal.ui.Inquilino;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleAdapter;

import java.util.ArrayList;
import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder>{
    private ArrayList<Inmueble> lista;
    private Context context;
    private LayoutInflater layoutInflater;


    public InquilinoAdapter(ArrayList<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public InquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.fragment_item_inquilinos, parent, false);
        return new InquilinoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.ViewHolder holder, int position) {

        Inmueble inmueble = lista.get(position);
        holder.tvDireccion.setText(lista.get(position).getDireccion());

        Glide.with(context)
                .load(inmueble.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu).navigate(R.id.inmuebleDetalleFragment, bundle);

            }
        });

    }


    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccion;
        private ImageView ivFoto;
        private Button btInquilino;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            btInquilino = itemView.findViewById(R.id.btInquilino);
        }
    }
}



