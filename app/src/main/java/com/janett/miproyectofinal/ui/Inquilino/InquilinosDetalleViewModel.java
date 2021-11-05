package com.janett.miproyectofinal.ui.Inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> detalleInquilinoMutable;
    private MutableLiveData<Inquilino> inquilinoMutableLiveData;
    Context context;


    public InquilinosDetalleViewModel(@NonNull Application application) {

        super(application);

                context = application.getApplicationContext();

    }

    public LiveData<Inquilino> getInquilinoMutableLiveData(){
        if(inquilinoMutableLiveData==null){
            inquilinoMutableLiveData = new MutableLiveData<>();
        } return inquilinoMutableLiveData;
    }
    public LiveData<Contrato> getInquilino() {
        if (detalleInquilinoMutable == null) {
            detalleInquilinoMutable = new MutableLiveData<>();
        }
        return detalleInquilinoMutable;
    }

    public void obtenerInqui(Bundle bundle){
        Inquilino inqui = (Inquilino) bundle.getSerializable("Inquilino");
        inquilinoMutableLiveData.setValue(inqui);
    }


    public void cargarInquilino(Bundle bundle)
    {

        Contrato contrato = (Contrato) bundle.getSerializable("inquilinoContrato");
        String token = ApiClient.getToken(context);

        Call<Contrato> detaInquilino =  ApiClient.getMyApiClient().detalleInquilino(contrato.getId(), token);
        detaInquilino.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    detalleInquilinoMutable.postValue(response.body());
                }
                else {
                    Log.d("msj", "cargarDetalleAInquilino(): No encontrado.");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("msj", "OCURRIO ALGUN ERROR.");

            }
        });

        /*
        Call<Inquilino> detalleInquilino= ApiClient.getMyApiClient().obtenerInquilino(token,inmueble.getId());
        detalleInquilino.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {

                if ( response.isSuccessful() ) {
                    inquilinoMutable.postValue(response.body());
                } else {
                    Log.d("msj", "cargarDetalleAInquilino(): No encontrado.");
                }

            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Log.d("msj", "OCURRIO ALGUN ERROR.");
            }
        });
*/
    }
}
