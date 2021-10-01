package com.janett.miproyectofinal.ui.Contrato;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<Inmueble>> listaMutable;
    ArrayList<Inmueble> lista;
    private Context context;

    public ContratosViewModel(@NonNull Application application) {

        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<ArrayList<Inmueble>> getListaMutable(){
        if (listaMutable == null){
            listaMutable = new MutableLiveData<>();
        }
        return listaMutable;
    }


    public void LeerPropiedadesAlquiladas(){
        ApiClient api= ApiClient.getApi();
        lista=api.obtenerPropiedadesAlquiladas();
        listaMutable.setValue(lista);
    }
}