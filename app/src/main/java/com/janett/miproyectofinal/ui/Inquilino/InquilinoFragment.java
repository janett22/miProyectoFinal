package com.janett.miproyectofinal.ui.Inquilino;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleAdapter;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleViewModel;

import java.util.ArrayList;
import java.util.List;

public class InquilinoFragment extends Fragment {

    private RecyclerView rvInquilinos;
    private InquilinoViewModel inquilinoViewModel;
    private InmuebleAdapter adapter;
    private Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inquilinos, container, false);
        inquilinoViewModel =
                new ViewModelProvider(this).get(InquilinoViewModel.class);
        context = root.getContext();

        inicializarVista(root);

        inquilinoViewModel.getlistaMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {

                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                rvInquilinos.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleAdapter((ArrayList<Inmueble>) inmuebles, getContext(), getLayoutInflater());
                rvInquilinos.setAdapter(adapter);

            }
        });
      inquilinoViewModel.leerInquilinos();
        return root;
    }

    public void inicializarVista(View root){

        rvInquilinos = root.findViewById(R.id.rvInquilinos);


    }

}