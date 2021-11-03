package com.janett.miproyectofinal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janett.miproyectofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> resultadoMutable;
    private MutableLiveData<String> mensajeMutable;
    Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
    }


    public LiveData<Boolean> getResultadoMutable() {
        if (resultadoMutable == null) {
            resultadoMutable = new MutableLiveData<>();
        }
        return resultadoMutable;
    }


    public LiveData<String> getMensajeMutable(){
        if (mensajeMutable == null)
            mensajeMutable = new MutableLiveData<>();
        return mensajeMutable;
    }


    public void verificarDatos(String usuario, String clave){
        if (usuario != null && clave != null && clave.length() > 0 && usuario.length() > 0){

            Call<String> respuestaToken = ApiClient.getMyApiClient().Login(usuario, clave);
            respuestaToken.enqueue(new Callback<String>() {
                @Override
                public void onResponse (Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        Log.d("Token", response.body());

                        SharedPreferences sharedPreferences = context.getSharedPreferences("token.dat", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", "Bearer " + response.body());
                        editor.commit();

                        resultadoMutable.postValue(true);

                    }else{
                        mensajeMutable.postValue("Usuario y/o Clave Incorrecta");
                    }
                }

                @Override
                public void onFailure (Call<String> call, Throwable t) {
                    Log.d("Token", "Salida Error" + t.getMessage());
                }
            });

        } else {
            mensajeMutable.postValue("Datos insuficientes");
        }
    }
        /*
    //para ingresar en la app anterior
    public void login(String usuario, String contrasenia) {

        if (usuario != null && contrasenia != null && usuario.length() > 0 && contrasenia.length() > 0) {
            ApiClient api = ApiClient.getApi();
            Propietario propietario = api.login(usuario, contrasenia);
            if (propietario != null) {
                Intent intent = new Intent(context, MenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Propietario", propietario);
                intent.putExtra("Propietario", bundle);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                resultadoMutable.setValue("Bienvenidos a inmobiliaria Victoria");//de tipo string en este caso

            } else {
                resultadoMutable.setValue("Datos incorrectos, por favor intente nuevamente");
            }
        } else {
            resultadoMutable.setValue("Por favor complete todos los campos");
        }
    }
*/
}