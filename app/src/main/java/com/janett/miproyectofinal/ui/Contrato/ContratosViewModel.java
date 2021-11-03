package com.janett.miproyectofinal.ui.Contrato;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PUT;

public class ContratosViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<Inmueble>> listaMutable;
    public MutableLiveData<ArrayList<Contrato>> contratoMutable;
    ArrayList<Inmueble> lista;
    private Context context;

    public ContratosViewModel(@NonNull Application application) {

        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<ArrayList<Inmueble>> getListaMutable(){
        if (listaMutable == null){
            listaMutable = new MutableLiveData<>();
        }
        return listaMutable;
    }

    public LiveData<ArrayList<Contrato>> getContratoMutable(){
        if (contratoMutable == null){
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void LeerPropiedadesAlquiladas(){

        String token = ApiClient.getToken(context);

        Call<List<Contrato>> contratoInmueble = ApiClient.getMyApiClient().listaContratos(token);
        contratoInmueble.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
             if(response.isSuccessful()){
                 contratoMutable.postValue((ArrayList<Contrato>) response.body());
             }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Error al hacer la peticion", Toast.LENGTH_LONG).show();

            }
        });


/*
        Call<List<Inmueble>> obtenerInmuebles = ApiClient.getMyApiClient().listaInmuebles(token);
        obtenerInmuebles.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse (Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful())
                    listaMutable.postValue((ArrayList<Inmueble>) response.body());
                else
                    Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure (Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
            }
        });
        /*ApiClient api= ApiClient.getApi();
        lista=api.obtenerPropiedadesAlquiladas();
        listaMutable.setValue(lista);
    */}
}