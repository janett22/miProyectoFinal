package com.janett.miproyectofinal.ui.Pago;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Pago;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleAdapter;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {

        private ArrayList<Pago> lista;
        private Context context;
        private LayoutInflater layoutInflater;


        public PagosAdapter(ArrayList<Pago> lista, Context context, LayoutInflater layoutInflater) {
            this.lista = lista;
            this.context = context;
            this.layoutInflater = layoutInflater;
        }

        @NonNull
        @Override
        public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.fragment_item_pagos, parent, false);
            return new PagosAdapter.ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codigo.setText(String.valueOf(lista.get(position).getIdPago()));
        holder.numero.setText(String.valueOf(lista.get(position).getNumero()));
        holder.fechaPago.setText(String.valueOf(lista.get(position).getFechaDePago()));
        holder.importe.setText(String.valueOf(lista.get(position).getImporte()));
        holder.codContrato.setText(String.valueOf(lista.get(position).getContrato().getIdContrato()));

    }

        @Override
        public int getItemCount() {
            return lista.size();
        }


     public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView codigo,numero,importe,fechaPago,codContrato;

            public ViewHolder(@NonNull View itemView) {
        super(itemView);
            codigo= itemView.findViewById(R.id.tvCodigo);
            numero = itemView.findViewById(R.id.tvNumero);
            importe = itemView.findViewById(R.id.tvImporte);
            fechaPago = itemView.findViewById(R.id.tvFecha);
            codContrato = itemView.findViewById(R.id.tvCodigoContrato);
    }
}
}

