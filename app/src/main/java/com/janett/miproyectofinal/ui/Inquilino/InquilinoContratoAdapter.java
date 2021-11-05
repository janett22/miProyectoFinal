package com.janett.miproyectofinal.ui.Inquilino;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;

public class InquilinoContratoAdapter extends RecyclerView.Adapter<InquilinoContratoAdapter.ViewHolder>{
    private ArrayList<Contrato> listaContrato;
    private Context context;
    private LayoutInflater layoutInflater;


    public InquilinoContratoAdapter(ArrayList<Contrato> listaContrato, Context context, LayoutInflater layoutInflater) {
        this.listaContrato = listaContrato;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public InquilinoContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.fragment_item_inquilinos, parent, false);
        return new InquilinoContratoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contrato contrato = listaContrato.get(position);
        Inquilino inquilino = contrato.getInquilino();
        holder.tvDireccion.setText(contrato.getInmueble().getDireccion());
        Glide.with(context)
                .load(ApiClient.SERVER+contrato.getInmueble().getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFoto);



        holder.btInquilino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilinoContrato", contrato);
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu).navigate(R.id.inquilinosDetalleFragment, bundle);


            }
        });


    }


    @Override
    public int getItemCount() {
        return listaContrato.size();
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

