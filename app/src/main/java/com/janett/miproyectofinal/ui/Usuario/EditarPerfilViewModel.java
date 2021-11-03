package com.janett.miproyectofinal.ui.Usuario;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janett.miproyectofinal.modelo.Propietario;
import com.janett.miproyectofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPerfilViewModel {
/*

    private MutableLiveData<Propietario> usuario;
    private MutableLiveData<Integer> editar;
    private MutableLiveData<Integer> guardar;
    private MutableLiveData<Boolean> estado;
    public MutableLiveData<String> errorMutable;
    Context context;
    //ApiClient api;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<Propietario> getUsuario(){
        if(usuario==null){
            usuario = new MutableLiveData<>();
        }
        return usuario;

    }

    public LiveData<Integer> getEditar(){
        if(editar==null){
            editar = new MutableLiveData<>();
        }
        return editar;

    }

    public LiveData<Integer> getGuardar(){
        if(guardar==null){
            guardar = new MutableLiveData<>();
        }
        return guardar;
    }
    public LiveData<Boolean> getEstado(){
        if(estado==null){
            estado = new MutableLiveData<>();
        }
        return estado;
    }

    public LiveData<String> getErrorMutable(){
        if (errorMutable == null){
            errorMutable = new MutableLiveData<>();
        }
        return errorMutable;
    }

    public void LeerPropietario(){

        String token = ApiClient.getToken(context);
        Call<Propietario> obtPropietario = ApiClient.getMyApiClient().obtenerPropietario(token);
        Log.d("Token" , token);
        obtPropietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse (Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful())
                    usuario.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar el perfil", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure (Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error en la peticion a la api", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ModificarPropietario(Propietario propi){
        /*if(propi.getNombre().isEmpty() || propi.getApellido().isEmpty() || propi.getDni().isEmpty() || p.getEmail().isEmpty() || p.getTelefono().isEmpty()){
            errorMutable.setValue("Los campos no pueden estar vacios.");
        }else if(p.getDni().toString().length() != 8){
            errorMutable.setValue("El DNI ingresado no es válido (8 dígitos necesarios)");
        }else if(p.getNombre().length() > 16 || p.getNombre().length() < 3 || p.getApellido().length() > 16 || p.getApellido().length() < 3){
            errorMutable.setValue("El nombre/apellido ingresado no es válido (3 caracteres mínimo, 16 máximo)");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(p.getEmail()).matches()){
            errorMutable.setValue("La dirección de correo electrónico ingresada no es válida.");
        }else if(p.getTelefono().length() > 15 || p.getTelefono().length() < 9){
            errorMutable.setValue("El número de teléfono ingresado no es válido (9-15 dígitos)");
        }else{




            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                errorMutable.postValue("Error al editar el usuario");
            }
        });

    }


    public void cambiarEstado(){
        estado.setValue(true);
        editar.setValue(View.INVISIBLE);
        guardar.setValue(View.VISIBLE);
    }


}

*/



}
