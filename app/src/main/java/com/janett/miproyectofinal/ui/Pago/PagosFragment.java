package com.janett.miproyectofinal.ui.Pago;

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
import com.janett.miproyectofinal.modelo.Pago;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel pagosViewModel;
    private RecyclerView rvPagos;
    private Context context;
    private PagosAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.pagos_fragment, container, false);

        pagosViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
        context = root.getContext();

        inicializar(root);

        pagosViewModel.getPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {

                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, RecyclerView.VERTICAL, false);
                rvPagos.setLayoutManager(gridLayoutManager);
                adapter = new PagosAdapter((ArrayList<Pago>) pagos, getContext(), getLayoutInflater());
                rvPagos.setAdapter(adapter);

            }
        });
        pagosViewModel.cargarPagos(getArguments());
        return root;
    }

    private void inicializar(View view) {
        rvPagos = view.findViewById(R.id.rvPagos);
    }
}