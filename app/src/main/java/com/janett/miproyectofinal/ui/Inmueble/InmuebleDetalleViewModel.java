package com.janett.miproyectofinal.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> detalleInmueble;
    Context context;
    Inmueble inm;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);


        context = application.getApplicationContext();
    }


    public LiveData<Inmueble> getdetalleInmueble() {
        if ( detalleInmueble== null ) {
          detalleInmueble = new MutableLiveData<>();
        }
        return detalleInmueble;
    }

    public void setInmueble(Bundle bundle) {
        inm= (Inmueble) bundle.getSerializable("inmueble");
        this.detalleInmueble.setValue(inm);
    }


    public void cargarCambios(Bundle bundle){

        inm= (Inmueble) bundle.getSerializable("inmueble");

        String token = ApiClient.getToken(context);
        Call<Inmueble> actInmueble = ApiClient.getMyApiClient().actualizarInmueble(token, inm.getId());
        actInmueble.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    detalleInmueble.postValue(response.body());
                    Toast.makeText(context, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}