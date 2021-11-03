package com.janett.miproyectofinal.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel{
    private MutableLiveData<List<Inmueble>> listaMutable;
    Context context;


    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<List<Inmueble>> getlistaMutable(){
        if(listaMutable==null){
            listaMutable= new MutableLiveData<>();
        }
        return listaMutable;
    }

    public void LeerInmuebles(){

        String token = ApiClient.getToken(context);
        Call<List<Inmueble>> obtenerInmuebles = ApiClient.getMyApiClient().listaInmuebles(token);
        obtenerInmuebles.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse (Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful())
                 listaMutable.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure (Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
            }
        });
    }

}