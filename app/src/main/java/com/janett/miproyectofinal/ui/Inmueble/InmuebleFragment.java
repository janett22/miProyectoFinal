package com.janett.miproyectofinal.ui.Inmueble;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.databinding.FragmentInmueblesBinding;
import com.janett.miproyectofinal.modelo.Inmueble;

import java.util.ArrayList;
import java.util.List;


public class InmuebleFragment extends Fragment {
    private RecyclerView rvInmueble;
    private InmuebleViewModel inmuebleViewModel;
    private InmuebleAdapter adapter;
    private Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inmuebles, container, false);
        inmuebleViewModel =
                new ViewModelProvider(this).get(InmuebleViewModel.class);
        context = root.getContext();

        inicializarVista(root);

        inmuebleViewModel.getlistaMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {

                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                rvInmueble.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleAdapter((ArrayList<Inmueble>) inmuebles, getContext(), getLayoutInflater());
                rvInmueble.setAdapter(adapter);

            }
        });
        inmuebleViewModel.LeerInmuebles();
        return root;
    }

    public void inicializarVista(View root){

        rvInmueble = root.findViewById(R.id.rvInmueble);


    }

}