package com.janett.miproyectofinal.ui.Inmueble;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.janett.miproyectofinal.R;

import com.janett.miproyectofinal.databinding.FragmentDetalleInmueblesBinding;
import com.janett.miproyectofinal.databinding.FragmentInmueblesBinding;
import com.janett.miproyectofinal.modelo.Inmueble;



public class InmuebleDetalleFragment extends Fragment {
    private InmuebleDetalleViewModel inmuebleDetalleViewModel;
    private FragmentDetalleInmueblesBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

       //View root = inflater.inflate(R.id.fragment_inmuebles_detalle, container, false);
       inmuebleDetalleViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);


        binding = FragmentDetalleInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inmuebleDetalleViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.tvId.setText(inmueble.getIdInmueble()+"");
                binding.tvDireccion.setText(inmueble.getDireccion());
                binding.tvAmbientes.setText(inmueble.getAmbientes()+"");
                binding.tvPrecio.setText(inmueble.getPrecio()+"");
                binding.tvUso.setText(inmueble.getUso());
                binding.tvTipo.setText(inmueble.getTipo());

                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivImagenInmueble);
                binding.cbEstado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inmuebleDetalleViewModel.cargarCambios(
                                binding.cbEstado.isChecked());

                    }
                });
            }
        });
        inmuebleDetalleViewModel.setInmueble(getArguments());
        return root;
    }

}