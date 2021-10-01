package com.janett.miproyectofinal.ui.Contrato;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

public class ContratosDetalleViewModel extends ViewModel {
    public MutableLiveData<Contrato> contratoMutable;


    public LiveData<Contrato> getContratosMutable() {
        if (contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void cargarContrato(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient apiClient = ApiClient.getApi();
        Contrato contrato = apiClient.obtenerContratoVigente(inmueble);
        this.contratoMutable.setValue(contrato);
    }
}