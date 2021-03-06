package com.janett.miproyectofinal.ui.CerrarSesion;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class CerrarSesionViewModel extends AndroidViewModel {

    private Context context;


    public CerrarSesionViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void cerrarSesion() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("datos",  0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "");
        editor.putInt("id", 0);
        editor.putString("nombre", "");
        editor.putString("apellido","");
        editor.putString("dni", "");
        editor.putString("email", "");
        editor.putString("contraseña", "");
        editor.putString("telefono", "");
        editor.commit();
    }
}