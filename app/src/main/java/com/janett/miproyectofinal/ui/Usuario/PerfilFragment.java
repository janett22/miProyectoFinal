package com.janett.miproyectofinal.ui.Usuario;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.janett.miproyectofinal.R;

import com.janett.miproyectofinal.modelo.Propietario;
import com.janett.miproyectofinal.databinding.FragmentPerfilBinding;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleViewModel;

public class PerfilFragment extends Fragment {
    private PerfilViewModel perfilViewModel;
    private FragmentPerfilBinding binding;
    private TextView id;
    private EditText dni,nombre,apellido,email,clave,telefono;
    private ImageView foto;
    private Button btGuardar,btEditar;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        InicilizarVista(root);



        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                id.setText(propietario.getId()+"");
                dni.setText(propietario.getDni());
                nombre.setText(propietario.getNombre());
                apellido.setText(propietario.getApellido());
                email.setText(propietario.getEmail());
                clave.setText(propietario.getContraseña());
                telefono.setText(propietario.getTelefono());
                foto.setImageResource(propietario.getAvatar());
            }
        });
        perfilViewModel.getEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //     id.setEnabled(aBoolean);
                dni.setEnabled(aBoolean);
                nombre.setEnabled(aBoolean);
                apellido.setEnabled(aBoolean);
                email.setEnabled(aBoolean);
                clave.setEnabled(aBoolean);
                telefono.setEnabled(aBoolean);
            }
        });
        perfilViewModel.getEditar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                btEditar.setVisibility(integer);
            }
        });
        perfilViewModel.getGuardar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                btGuardar.setVisibility(integer);
            }
        });
        perfilViewModel.getErrorMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Toast.makeText(root.getContext(), s, Toast.LENGTH_LONG).show();

            }
        });
      perfilViewModel.LeerPropietario();
        return root;
}

    private void InicilizarVista(View root) {
        id= root.findViewById(R.id.etId);
        dni =root.findViewById(R.id.tvDNI);
        nombre = root.findViewById(R.id.tvNombre);
        apellido = root.findViewById(R.id.tvApellido);
        email = root.findViewById(R.id.tvEmail);
        clave = root.findViewById(R.id.etContraseña);
        telefono = root.findViewById(R.id.tvTelefono);
        foto= root.findViewById(R.id.ivFoto);
        btEditar = root.findViewById(R.id.btEditar);
        btGuardar = root.findViewById(R.id.btGuardar);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                perfilViewModel.cambiarEstado();
            }
        });
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Salida", "GUARDANDO");
                Propietario propietario = new Propietario();
                propietario.setId(Integer.parseInt(id.getText().toString()));
                propietario.setDni(dni.getText().toString());
                propietario.setNombre(nombre.getText().toString());
                propietario.setApellido(apellido.getText().toString());
                propietario.setEmail(email.getText().toString());
                propietario.setContraseña(clave.getText().toString());
                propietario.setTelefono(telefono.getText().toString());

                perfilViewModel.ModificarPropietario(propietario);
            }
        });



    }

}