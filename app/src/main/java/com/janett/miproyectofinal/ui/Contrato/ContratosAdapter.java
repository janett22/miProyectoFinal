package com.janett.miproyectofinal.ui.Contrato;

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
import com.janett.miproyectofinal.MenuActivity;
import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;

public class ContratosAdapter  extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {
        private ArrayList<Contrato>  inmueblesAlquilados;
        private Context context;
        private LayoutInflater layoutInflater;

    public ContratosAdapter(ArrayList<Contrato> inmueblesAlquilados, Context context, LayoutInflater layoutInflater) {
        this.inmueblesAlquilados = inmueblesAlquilados;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
        @Override
        public ContratosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.fragment_item_contrato, parent, false);
            return new ContratosAdapter.ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Contrato contrato = inmueblesAlquilados.get(position);
            holder.tvDireccion.setText(contrato.getInmueble().getDireccion());
            Glide.with(context)
                    .load(ApiClient.SERVER+contrato.getInmueble().getImagen())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivFoto);

            holder.btContrato.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ContratoId", contrato);
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu).navigate(R.id.fragmentContratosDetalle, bundle);

                }
            });
        }

        @Override
        public int getItemCount() {
            return inmueblesAlquilados.size();
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


