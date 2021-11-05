package com.janett.miproyectofinal.ui.Usuario;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Propietario;
import com.janett.miproyectofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
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
       Log.d("Salida", propi.toString());
        String token = ApiClient.getToken(context);
        Call<Propietario> actPerfil = ApiClient.getMyApiClient().modificarPerfil(token, propi);
        actPerfil.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    usuario.postValue(response.body());
                    estado.postValue(false);
                    editar.postValue(View.VISIBLE);
                    guardar.postValue(View.INVISIBLE);
                }
                else {
                    errorMutable.postValue("Error "+response.message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
              errorMutable.postValue("Error al conectarse con la api");
            }
        });

        }


    public void cambiarEstado(){
        estado.setValue(true);
        editar.setValue(View.INVISIBLE);
        guardar.setValue(View.VISIBLE);
    }


}
