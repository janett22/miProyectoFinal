package com.janett.miproyectofinal.ui.Pago;

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
import com.janett.miproyectofinal.modelo.Pago;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {


    private MutableLiveData<List<Pago>> pagos;
    private Context context;

    public PagosViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();

    }


    public LiveData<List<Pago>> getPagos() {
        if (pagos==null){
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }


    public void cargarPagos(Bundle bundle)
    {
        Contrato contrato = (Contrato) bundle.getSerializable("pagos");

        String token = ApiClient.getToken(context);
        Call<List<Pago>> listarPagos = ApiClient.getMyApiClient().Pagos(contrato.getId(), token);
        listarPagos.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if ( response.isSuccessful() ) {
                    pagos.postValue(response.body());
                } else {
                        Toast.makeText(context, "Error al cargar los pagos", Toast.LENGTH_LONG).show();
                }
                    Log.d("msj", "Pagos NO encontrados ");
                }


            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Error al hacer la peticion", Toast.LENGTH_LONG).show();
                Log.d("msj", "OCURRIO UN ERROR");
            }
        });


        /*
        ApiClient api = ApiClient.getApi();
        Contrato contrato = (Contrato) bundle.getSerializable("pagos");
        pagos.setValue(api.obtenerPagos(contrato));
*/

    }

}