package com.janett.miproyectofinal.ui.Contrato;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleAdapter;

import java.util.ArrayList;



public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder> {

    private ArrayList<Inmueble> lista;
    private Context context;
    private LayoutInflater layoutInflater;

    public ContratoAdapter(ArrayList<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.fragment_item_contrato, parent, false);
        return new ContratoAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmuebles = lista.get(position);
        holder.tvDireccion.setText(lista.get(position).getDireccion());
        Glide.with(context)
                .load(inmuebles.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFoto);

        holder.btContrato.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmuebles);
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu).navigate(R.id.fragmentContratosDetalle, bundle);

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
        private Button btContrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btContrato = itemView.findViewById(R.id.btContrato);
            ivFoto = itemView.findViewById(R.id.ivFoto);

        }
    }
}

