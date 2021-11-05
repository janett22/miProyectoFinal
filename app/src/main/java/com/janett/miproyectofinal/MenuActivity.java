package com.janett.miproyectofinal;

import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.janett.miproyectofinal.databinding.ActivityMenuBinding;
import com.janett.miproyectofinal.modelo.Propietario;
import com.janett.miproyectofinal.request.ApiClient;
import com.janett.miproyectofinal.ui.Inmueble.InmuebleViewModel;
public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;
    private MenuViewModel menuViewModel;
    private ImageView avatar;
    private TextView usuario,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenu.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        iniciarHeader(navigationView);

        View header = navigationView.getHeaderView(0);

        menuViewModel =new ViewModelProvider(this).get(MenuViewModel.class);

        menuViewModel.getCargarPropietario().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {

                avatar = header.findViewById(R.id.imgAvatar);
                usuario = header.findViewById(R.id.tvUsuario);
                mail = header.findViewById(R.id.tvMail);

                Glide.with(MenuActivity.this)
                        .load(ApiClient.SERVER + propietario.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(avatar);

                usuario.setText(propietario.getNombre()+ " "+propietario.getApellido());
                mail.setText(propietario.getEmail());

            }
        });
        menuViewModel.cargarHeaderPropietraio();


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio,
                R.id.nav_perfil,
                R.id.nav_inmueble,
                R.id.nav_inquilinos,
                R.id.nav_contrato,
                R.id.nav_cerrar)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




    private void iniciarHeader(NavigationView navigationView){


        //cargar header con datos locales de la plantilla
        //ApiClient api =  ApiClient.getApi();
        //Propietario p = api.obtenerUsuarioActual();
        //avatar.setImageResource(p.getAvatar());
        //usuario.setText(p.getNombre()+ " "+ p.getApellido());
        //mail.setText(p.getEmail());

    }

}
