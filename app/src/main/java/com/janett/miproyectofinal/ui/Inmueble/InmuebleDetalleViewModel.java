package com.janett.miproyectofinal.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

public class InmuebleDetalleViewModel extends ViewModel {

    private MutableLiveData<Inmueble> inmueble;
    Context context;
    ApiClient api;
    Inmueble inm;


    public LiveData<Inmueble> getInmueble() {
        if ( inmueble== null ) {
           inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void setInmueble(Bundle bundle) {
        inm= (Inmueble) bundle.getSerializable("inmueble");
        this.inmueble.setValue(inm);
    }

    public void cargarCambios(Boolean disponible) {
        api = ApiClient.getApi();
        inm.setEstado(disponible);
        api.actualizarInmueble(inm);
    }


}