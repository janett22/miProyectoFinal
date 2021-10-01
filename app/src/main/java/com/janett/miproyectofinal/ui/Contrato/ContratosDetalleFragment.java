package com.janett.miproyectofinal.ui.Contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Contrato;

public class ContratosDetalleFragment extends Fragment {

    private TextView codigo, fechaInicio, fechaFin, inmueble, inquilino, monto;
    private Button btPagos;

    private ContratosDetalleViewModel contratoDetalleViewModel;


    public static ContratosDetalleFragment newInstance() {
        return new ContratosDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contratos_detalle, container, false);

        contratoDetalleViewModel = new ViewModelProvider(this).get(ContratosDetalleViewModel.class);

        inicializar(root);

        contratoDetalleViewModel.getContratosMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                codigo.setText(contrato.getIdContrato()+"");
                fechaInicio.setText(contrato.getFechaInicio());
                fechaFin.setText(contrato.getFechaFin());
                monto.setText(contrato.getMontoAlquiler()+"");
                inmueble.setText(contrato.getInmueble().getDireccion());
                inquilino.setText(contrato.getInquilino().getNombre() + " " +contrato.getInquilino().getApellido());


                btPagos.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagos", contrato);
                        Navigation.findNavController(view).navigate(R.id.pagosFragment, bundle);
                    }
                });


            }
        });


        return root;
    }


    public void inicializar(View view){
        codigo = view.findViewById(R.id.tvCodigoContrato);
        fechaInicio = view.findViewById(R.id.tvFechaInicio);
        fechaFin = view.findViewById(R.id.tvFechaFin);
        monto = view.findViewById(R.id.tvMontoAqluiler);
        inquilino = view.findViewById(R.id.tvInquilino);
        inmueble = view.findViewById(R.id.tvInmueble);
        btPagos = view.findViewById(R.id.btPagos);


    }
}