package com.janett.miproyectofinal.ui.Inmueble;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InmuebleViewModel extends AndroidViewModel{


    private MutableLiveData<ArrayList<Inmueble>> listaMutable;
    ArrayList<Inmueble> inmuebles;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<ArrayList<Inmueble>> getlistaMutable(){
        if(listaMutable==null){
            listaMutable= new MutableLiveData<>();
        }
        return listaMutable;
    }

    public void leerInmuebles(){
        ApiClient api = ApiClient.getApi();
        inmuebles = ApiClient.getApi().obtnerPropiedades();
        listaMutable.setValue(inmuebles);

    }
}