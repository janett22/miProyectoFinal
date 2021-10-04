package com.janett.miproyectofinal.ui.Inquilino;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Inmueble>> listaMutable;
    Context context;
    ArrayList<Inmueble> inmuebles;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<ArrayList<Inmueble>> getlistaMutable(){
        if(listaMutable==null){
            listaMutable= new MutableLiveData<>();
        }
        return listaMutable;
    }

    public void leerInquilinos(){
        ApiClient api = ApiClient.getApi();
        inmuebles = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        listaMutable.setValue(inmuebles);

    }
}


