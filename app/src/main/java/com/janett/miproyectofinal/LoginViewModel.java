package com.janett.miproyectofinal;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janett.miproyectofinal.modelo.Propietario;
import com.janett.miproyectofinal.request.ApiClient;

import java.io.Serializable;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> visible;
    private MutableLiveData<String> resultadoMutable;
    MutableLiveData<Boolean> okMutable;
    Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
    }


    public LiveData<String> getResultadoMutable() {
        if (resultadoMutable == null) {
            resultadoMutable = new MutableLiveData<>();
        }
        return resultadoMutable;
    }

    public LiveData<Boolean> getOkMutable() {
        if (okMutable == null) {
            okMutable = new MutableLiveData<>();
        }
        return okMutable;
    }

    public LiveData<Integer> getVisible() {
        if (visible == null) {
            visible = new MutableLiveData<>();
        }
        return visible;
    }

    public void login(String usuario, String contrasenia) {

        if (usuario != null && contrasenia != null && usuario.length() > 0 && contrasenia.length() > 0) {
            ApiClient api = ApiClient.getApi();
            Propietario propietario = api.login(usuario, contrasenia);
            if (propietario != null) {
                Intent intent = new Intent(context, LoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Propietario", propietario);
                intent.putExtra("propietario", bundle);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                resultadoMutable.setValue("Bienvenidos a inmobiliaria Victoria");
                okMutable.setValue(true);

            } else {
                resultadoMutable.setValue("Datos incorrectos, por favor intente nuevamente");
            }
        } else {
            resultadoMutable.setValue("Por favor complete todos los campos");
        }
    }

}        /*
        public void verificarDatos(String usuario, String contrasenia){
            if(usuario !=null && contrasenia!=null && usuario.length()>0 && contrasenia.length()>0){
                ApiClient api= ApiClient.getApi();
                if (api.login(usuario, contrasenia)!=null){
                    resultadoMutable.setValue("Bienvenidos a Inmobiliaria Victoria");
                    okMutable.setValue(true);
                }else{
                    resultadoMutable.setValue("Datos incorrectos, por favor intente nuevamente");
                }
            }else{
                resultadoMutable.setValue("Por favor complete todos los campos");
            }

        }

*/






