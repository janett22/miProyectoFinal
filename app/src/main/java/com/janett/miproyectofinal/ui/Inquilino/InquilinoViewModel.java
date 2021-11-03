package com.janett.miproyectofinal.ui.Inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> listaMutable;
    private MutableLiveData<List<Contrato>> listMutableContrato;
    Context context;
    ArrayList<Inmueble> inmuebles;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }


    public LiveData<List<Inmueble>> getlistaMutable(){
        if(listaMutable==null){
            listaMutable= new MutableLiveData<>();
        }
        return listaMutable;
    }

    public LiveData<List<Contrato>> getListaMutableContrato(){
        if(listMutableContrato==null){
            listMutableContrato = new MutableLiveData<>();
        } return listMutableContrato;
    }

/*
    public void cargarInquilino(){

        String token = ApiClient.getToken(context);
        Call<List<Inmueble>> obtenerInquilino = ApiClient.getMyApiClient().listaInmuebles(token);
        Log.d("Salida", "viene token");
       obtenerInquilino.enqueue(new Callback<List<Inmueble>>() {
           @Override
           public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {

               if(response.isSuccessful()){
                   listaMutable.postValue(response.body());
                   Log.d("Salida", response.message());
               }else {
                   Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
               }

           }
           @Override
           public void onFailure(Call<List<Inmueble>> call, Throwable t) {

           }
       });
        /*
        ApiClient api = ApiClient.getApi();
        inmuebles = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        listaMutable.setValue(inmuebles);
}*/



    public void leerInquilinos(){

        String token = ApiClient.getToken(context);
        Call<List<Contrato>>obtenerInquilino=ApiClient.getMyApiClient().listaContratos(token);
        Log.d("Salida","viene token");

        obtenerInquilino.enqueue(new Callback<List<Contrato>>(){
@Override
public void onResponse(Call<List<Contrato>>call,Response<List<Contrato>>response){
        if(response.isSuccessful()){
        listMutableContrato.postValue(response.body());
        Log.d("Salida",response.message());
        }else{
        Toast.makeText(context,"Error al cargar los inmuebles",Toast.LENGTH_LONG).show();
        }

        }

@Override
public void onFailure(Call<List<Contrato>>call,Throwable t){
        Toast.makeText(context,"Error al hacer la peticion en la api",Toast.LENGTH_LONG).show();

        }
        });

        /*
        ApiClient api = ApiClient.getApi();
        inmuebles = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        listaMutable.setValue(inmuebles);
}*/
        }
    }