package com.janett.miproyectofinal.ui.Inquilino;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.request.ApiClient;

import java.util.ArrayList;

public class InquilinosDetalleViewModel extends ViewModel {

    private MutableLiveData<Inquilino> inquilinoMutable;
    private Inquilino inquilino;

    public InquilinosDetalleViewModel()
    {
        super();
    }

    public LiveData<Inquilino> getInquilino()
    {
        if (inquilinoMutable == null)
        {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }


    public void cargarInquilino(Bundle bundle)
    {
        inquilino = (Inquilino) bundle.getSerializable("inquilino");
        inquilinoMutable.setValue(inquilino);

    }
}
