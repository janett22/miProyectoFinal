package com.janett.miproyectofinal.ui.Contrato;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosDetalleViewModel extends AndroidViewModel {
    public MutableLiveData<Contrato> contratoMutable;
    private Context context;

    public ContratosDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }


    public LiveData<Contrato> getContratosMutable() {
        if (contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void cargarContrato(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        Inquilino inquilino = (Inquilino) bundle.getSerializable("inquilino");
        Contrato contrato = (Contrato) bundle.getSerializable("ContratoId");
        String token = ApiClient.getToken(context);
        Call<Contrato> detalleContrato= ApiClient.getMyApiClient().detalleInquilino(contrato.getId(),token);
        detalleContrato.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful())
                    contratoMutable.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar los detalles", Toast.LENGTH_LONG).show();
            }
           @Override
           public void onFailure(Call<Contrato> call, Throwable t) {
               Toast.makeText(context, "Error al hacer la peticion", Toast.LENGTH_SHORT).show();

           }
       });

        /*  ApiClient apiClient = ApiClient.getApi();
        Contrato contrato = apiClient.obtenerContratoVigente(inmueble);
        this.contratoMutable.setValue(contrato);
    */}
}