package com.janett.miproyectofinal.ui.Contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Inmueble;

import java.util.ArrayList;

public class ContratosFragment extends Fragment {

    private RecyclerView rvContrato;
    private ContratosViewModel contratoViewModel;
    private ContratoAdapter adapter;
    private Context context;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.contratos_fragment, container, false);
        contratoViewModel =
                new ViewModelProvider(this).get(ContratosViewModel.class);

        context = root.getContext();
        InicializarVista(root);

        contratoViewModel.getListaMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {

                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                rvContrato.setLayoutManager(gridLayoutManager);
                adapter = new ContratoAdapter(inmuebles, getContext(), getLayoutInflater());
                rvContrato.setAdapter(adapter);

            }
        });
        contratoViewModel.LeerPropiedadesAlquiladas();
        return  root;
    }

    public void InicializarVista(View root) {
        rvContrato = root.findViewById(R.id.rvContrato);
    }

}