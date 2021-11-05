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
import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;

import java.util.ArrayList;


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

       /* inquilinoDetalleViewModel.getInquilinoMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigo.setText(inquilino.getId()+"");
                binding.tvNombre.setText(inquilino.getNombre());
                binding.tvApellido.setText(inquilino.getApellido());
                binding.tvDNI.setText(inquilino.getDni());
                binding.tvEmail.setText(inquilino.getEmail());
                binding.tvTelefono.setText(inquilino.getTelefono());
                binding.tvGarante.setText(inquilino.getNombreGarante());
                binding.tvTelefonoGarante.setText(inquilino.getTelefonoGarante());

            }
        });
        */
        inquilinoDetalleViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
           @Override
           public void onChanged(Contrato contrato) {
               binding.tvCodigo.setText(contrato.getId()+"");
               binding.tvNombre.setText(contrato.getInquilino().getNombre());
               binding.tvApellido.setText(contrato.getInquilino().getApellido());
               binding.tvDNI.setText(contrato.getInquilino().getDni());
               binding.tvEmail.setText(contrato.getInquilino().getEmail());
               binding.tvTelefono.setText(contrato.getInquilino().getTelefono());
               binding.tvGarante.setText(contrato.getInquilino().getNombreGarante());
               binding.tvTelefonoGarante.setText(contrato.getInquilino().getTelefonoGarante());

           }
       });
/*        inquilinoDetalleViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigo.setText(inquilino.getId()+"");
                binding.tvNombre.setText(inquilino.getNombre());
                binding.tvApellido.setText(inquilino.getApellido());
                binding.tvDNI.setText(inquilino.getDni().toString()+"");
                binding.tvEmail.setText(inquilino.getEmail());
                binding.tvTelefono.setText(inquilino.getTelefono());
                binding.tvGarante.setText(inquilino.getNombreGarante());
                binding.tvTelefonoGarante.setText(inquilino.getTelefonoGarante());

            }
        });
  */

        inquilinoDetalleViewModel.cargarInquilino(getArguments());
       // inquilinoDetalleViewModel.obtenerInqui(getArguments());
        return root;
    }
}