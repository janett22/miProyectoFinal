package com.janett.miproyectofinal;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janett.miproyectofinal.modelo.Propietario;
import com.janett.miproyectofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> cargarHeader;
    private Context context;

    public MenuViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getCargarPropietario(){
        if(cargarHeader==null){
            cargarHeader= new MutableLiveData<>();
        }
        return cargarHeader;
    }

    public void cargarHeaderPropietraio(){

        String token = ApiClient.getToken(context);
        Call<Propietario> obtPropietario = ApiClient.getMyApiClient().obtenerPropietario(token);
        Log.d("Token" , token);
        obtPropietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse (Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful())
                    cargarHeader.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar header", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure (Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error en la peticion a la api", Toast.LENGTH_LONG).show();
            }
        });

    }

}
