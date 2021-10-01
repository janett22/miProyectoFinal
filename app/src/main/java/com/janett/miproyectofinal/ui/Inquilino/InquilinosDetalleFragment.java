package com.janett.miproyectofinal.ui.Inquilino;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.databinding.FragmentDetalleInmueblesBinding;
import com.janett.miproyectofinal.databinding.FragmentDetalleInquilinosBinding;
import com.janett.miproyectofinal.modelo.Inquilino;


public class InquilinosDetalleFragment extends Fragment {

    private InquilinosDetalleViewModel inquilinoDetalleViewModel;
    private FragmentDetalleInquilinosBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {


       // View root= inflater.inflate(R.layout.fragment_detalle_inquilinos, container, false);
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinosDetalleViewModel.class);
        binding= FragmentDetalleInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        inquilinoDetalleViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigo.setText(inquilino.getIdInquilino()+"");
                binding.tvNombre.setText(inquilino.getNombre());
                binding.tvApellido.setText(inquilino.getApellido());
                binding.tvDNI.setText(inquilino.getDNI().toString());
                binding.tvEmail.setText(inquilino.getEmail());
                binding.tvTelefono.setText(inquilino.getTelefono());
                binding.tvGarante.setText(inquilino.getNombreGarante());
                binding.tvTelefonoGarante.setText(inquilino.getTelefonoGarante());
            }
        });
        inquilinoDetalleViewModel.obtenerInquilinos(getArguments());
        return root;
    }
}