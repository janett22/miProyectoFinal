package com.janett.miproyectofinal;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.janett.miproyectofinal.ui.Inmueble.InmuebleViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
    private EditText usuario, contrasenia;
    private Button btLogin;
    private ImageView icono;
    private LoginViewModel vm;
    private SensorManager sensorManager;
    private List<Sensor> sensores;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pedirPermisos();

        vm =  new ViewModelProvider(this).get(LoginViewModel.class);


        inicializarVista();

        vm.getMensajeMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Error")
                        .setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        vm.getResultadoMutable().observe((this), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensores.size() > 0) {
            sensorManager.registerListener(this, sensores.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
        flag = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    private void pedirPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
        }
        pedirPermisosMapas();
    }

    private void pedirPermisosMapas() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }
    }

    private void inicializarVista() {
        usuario = findViewById(R.id.etUsuario);
        contrasenia = findViewById(R.id.etContraseña);
        icono = findViewById(R.id.ivIcono);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.verificarDatos(usuario.getText().toString(), contrasenia.getText().toString());
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        Log.d("msj", x + "");

        if (flag && (x > 5 || x < -5) ) {
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel: 999"));
            startActivity(i);
            flag = false;
        }
    }

    @Override
    public void onAccuracyChanged (Sensor sensor, int accuracy)
    {

    }
}
