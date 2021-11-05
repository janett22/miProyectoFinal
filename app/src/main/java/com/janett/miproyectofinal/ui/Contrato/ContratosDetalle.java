package com.janett.miproyectofinal.ui.Contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Contrato;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContratosDetalle extends Fragment {
    private TextView codigo, fechaInicio, fechaFin, inmueble, inquilino, monto;
    private Button btPagos;

    private ContratosDetalleViewModel contratoDetalleViewModel;

    private Contrato contrato;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detalle_contrato, container, false);

        contratoDetalleViewModel = new ViewModelProvider(this).get(ContratosDetalleViewModel.class);

        inicializar(root);

        contratoDetalleViewModel.getContratosMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                codigo.setText(contrato.getId()+"");
                DateTimeFormatter dt= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDate ldT=LocalDate.parse(contrato.getFechaInicio(), dt);
                fechaInicio.setText(ldT.toString());
                fechaFin.setText(contrato.getFechaFin());
                DateTimeFormatter dtF= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDate ldF=LocalDate.parse(contrato.getFechaInicio(), dt);
                fechaFin.setText(ldF.toString());
                monto.setText(contrato.getPrecio()+"");
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
        contratoDetalleViewModel.cargarContrato(getArguments());

        return root;
    }


    public void inicializar(View view){
        codigo = view.findViewById(R.id.tvCodigoContrato);
        fechaInicio = view.findViewById(R.id.tvFechaInicio);

        //LocalDateTime fechIn= LocalDateTime.parse(contrato.getFechaInicio());
        //LocalDate fechaLocal = fechIn.toLocalDate();
        //fechaInicio.setText(fechaLocal.toString());

        fechaFin = view.findViewById(R.id.tvFechaFin);
        //LocalDateTime fechFin= LocalDateTime.parse(contrato.getFechaFin());
        //LocalDate fechLocal = fechIn.toLocalDate();
        //fechaFin.setText(fechLocal.toString());

        monto = view.findViewById(R.id.tvMontoAqluiler);
        inquilino = view.findViewById(R.id.tvInquilino);
        inmueble = view.findViewById(R.id.tvInmueble);
        btPagos = view.findViewById(R.id.btPagos);


    }
}
