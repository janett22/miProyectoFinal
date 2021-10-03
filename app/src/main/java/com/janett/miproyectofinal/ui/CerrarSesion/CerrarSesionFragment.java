package com.janett.miproyectofinal.ui.CerrarSesion;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleViewModel;

public class CerrarSesionFragment extends Fragment {


    private CerrarSesionViewModel cerrarSesionViewModel;


    public static CerrarSesionFragment newInstance() {

        return new CerrarSesionFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.cerrar_sesion_fragment, container, false);

        cerrarSesionViewModel =
                new ViewModelProvider(this).get(CerrarSesionViewModel.class);

        cerrarSesion();
        return root;
    }

    public void cerrarSesion() {

        new AlertDialog.Builder(getContext())
                .setTitle("Cerrar sesión")
                .setMessage("Está seguro de que desea cerrar la sesión?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.nav_inicio);
                    }
                }).show();
    }


}