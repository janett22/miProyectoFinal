package com.janett.miproyectofinal.ui.Pago;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Pago;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;

public class PagosViewModel extends AndroidViewModel {


    private MutableLiveData<ArrayList<Pago>> pagos;
    private Context context;

    public PagosViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<ArrayList<Pago>> getPagos() {
        if (pagos==null){
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }


    public void cargarPagos(Bundle bundle)
    {

        ApiClient api = ApiClient.getApi();
        Contrato contrato = (Contrato) bundle.getSerializable("pagos");
        pagos.setValue(api.obtenerPagos(contrato));


    }

}