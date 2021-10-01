package com.janett.miproyectofinal.ui.Usuario;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Propietario;
import com.janett.miproyectofinal.request.ApiClient;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<Propietario> usuario;
    private MutableLiveData<Integer> editar;
    private MutableLiveData<Integer> guardar;
    private MutableLiveData<Boolean> estado;
    ApiClient api;


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

    public void obtenerUsuario(){
        ApiClient api = ApiClient.getApi();
        Propietario propietario = api.obtenerUsuarioActual();
        usuario.setValue(propietario);
    }

    public void modificarDatos(Propietario propietario){
        ApiClient api = ApiClient.getApi();
        api.actualizarPerfil(propietario);
        editar.setValue(View.VISIBLE);
        guardar.setValue(View.INVISIBLE);
    }

  public void cambiarEstado(){
       estado.setValue(true);
       editar.setValue(View.INVISIBLE);
       guardar.setValue(View.VISIBLE);
  }


}
