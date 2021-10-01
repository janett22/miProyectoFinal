package com.janett.miproyectofinal.ui.Inquilino;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.request.ApiClient;

public class InquilinosDetalleViewModel extends ViewModel {

    private MutableLiveData<Inquilino> inquilino;
    Context context;


    public LiveData<Inquilino> getInquilino() {
        if ( inquilino== null ) {
            inquilino = new MutableLiveData<Inquilino>();
        }
        return inquilino;
    }

    public  void obtenerInquilinos(Bundle bundle) {

        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient api = ApiClient.getApi();
        Inquilino inqui = api.obtenerInquilino(inmueble);
        inquilino.setValue(inqui);
    }
}
