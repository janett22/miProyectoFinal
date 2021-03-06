package com.janett.miproyectofinal.request;

import static java.net.Authenticator.RequestorType.SERVER;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.janett.miproyectofinal.R;
import com.janett.miproyectofinal.modelo.Contrato;
import com.janett.miproyectofinal.modelo.Inmueble;
import com.janett.miproyectofinal.modelo.Inquilino;
import com.janett.miproyectofinal.modelo.Pago;
import com.janett.miproyectofinal.modelo.Propietario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {

    private ArrayList<Propietario> propietarios=new ArrayList<>();
    private ArrayList<Inquilino> inquilinos=new ArrayList<>();
    private ArrayList<Inmueble> inmuebles=new ArrayList<>();
    private ArrayList<Contrato> contratos=new ArrayList<>();
    private ArrayList<Pago> pagos=new ArrayList<>();
    private static Propietario usuarioActual=null;
    private static ApiClient api=null;

    private ApiClient(){
        //Nos conectamos a nuestra "Base de Datos"
        cargaDatos();
    }
    //Método para crear una instancia de ApiClient
    public static ApiClient getApi(){
        if (api==null){
            api=new ApiClient();
        }
        return api;

    }




    //Servicios
    //Para que pueda iniciar sesion
    public Propietario login(String mail, final String password){
        for(Propietario propietario:propietarios){
            if(propietario.getEmail().equals(mail)&&propietario.getClave().equals(password)){
                usuarioActual=propietario;
                return propietario;
            }
        }
        return null;
    }


    //Retorna el usuario que inició Sesión
    public Propietario obtenerUsuarioActual(){
        return usuarioActual;
    }

    //Retorna las propiedades del usuario propietario logueado
    public ArrayList<Inmueble> obtnerPropiedades(){
        ArrayList<Inmueble> temp=new ArrayList<>();
        for(Inmueble inmueble:inmuebles){
            if(inmueble.getDuenio().equals(usuarioActual)){
                temp.add(inmueble);
            }
        }
        return temp;
    }

    //Lista de inmuebles con contratos vigentes del Propietario logueado
    public ArrayList<Inmueble> obtenerPropiedadesAlquiladas(){
        ArrayList<Inmueble> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.getInmueble().getDuenio().equals(usuarioActual)){
                temp.add(contrato.getInmueble());
            }
        }
        return temp;
    }


//Dado un inmueble retorna el contrato activo de dicho inmueble

    public Contrato obtenerContratoVigente(Inmueble inmueble){

        for(Contrato contrato:contratos){
            if(contrato.getInmueble().equals(inmueble)){
                return contrato;
            }
        }
        return null;
    }

    //Dado un inmueble, retorna el inquilino del ultimo contrato activo de ese inmueble.
    public Inquilino obtenerInquilino(Inmueble inmueble){
        for(Contrato contrato:contratos){
            if(contrato.getInmueble().equals(inmueble)){
                return contrato.getInquilino();
            }
        }
        return null;
    }
    //Dado un Contrato, retorna los pagos de dicho contrato
    public ArrayList<Pago> obtenerPagos(Contrato contratoVer){
        ArrayList<Pago> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.equals(contratoVer)){
                for(Pago pago:pagos){
                    if(pago.getcontrato().equals(contrato)){
                        temp.add(pago);
                    }
                }
            }
            break;
        }
        return temp;
    }
    //Actualizar Perfil
    public void actualizarPerfil(Propietario propietario){
        int posición=propietarios.indexOf(propietario);
        if(posición!=-1){
            propietarios.set(posición,propietario);
        }
    }

    //ActualizarInmueble
   /* public void actualizarInmueble(Inmueble inmueble){
        int posicion=inmuebles.indexOf(inmueble);
        if(posicion!=-1){
            inmuebles.set(posicion,inmueble);
        }
    }
*/
    private void cargaDatos() {
/*
        //Propietarios
        Propietario juan = new Propietario(1, 23492012L, "Juan", "Perez", "juan@mail.com", "123", "2664553447", R.drawable.juan);
        Propietario sonia = new Propietario(2, 17495869L, "Sonia", "Lucero", "sonia@mail.com", "123", "266485417", R.drawable.sonia);
        propietarios.add(juan);
        propietarios.add(sonia);

        //Inquilinos
        Inquilino mario = new Inquilino(100, 25340691L, "Mario", "Luna", "Aiello sup.", "luna@mail.com", "2664253411", "Lucero Roberto", "2664851422");
        inquilinos.add(mario);

        //Inmuebles
        Inmueble salon = new Inmueble(501, "Colon 340", "comercial", "salon", 2, 20000, juan, true, "http://www.secsanluis.com.ar/servicios/salon1.jpg");
        Inmueble casa = new Inmueble(502, "Mitre 800", "particular", "casa", 2, 15000, juan, true, "http://www.secsanluis.com.ar/servicios/casa1.jpg");
        Inmueble otraCasa = new Inmueble(503, "Salta 325", "particular", "casa", 3, 17000, sonia, true, "http://www.secsanluis.com.ar/servicios/casa2.jpg");
        Inmueble dpto = new Inmueble(504, "Lavalle 450", "particular", "dpto", 2, 25000, sonia, true, "http://www.secsanluis.com.ar/servicios/departamento1.jpg");
        Inmueble casita = new Inmueble(505, "Belgrano 218", "particular", "casa", 5, 90000, sonia, true, "http://www.secsanluis.com.ar/servicios/casa3.jpg");

        inmuebles.add(salon);
        inmuebles.add(casa);
        inmuebles.add(otraCasa);
        inmuebles.add(dpto);
        inmuebles.add(casita);

        //Contratos
        Contrato uno = new Contrato(701, "05/01/2020", "05/01/2021", 17000, mario, otraCasa);
        contratos.add(uno);
        //Pagos
        pagos.add(new Pago(900, 1, uno, 17000, "10/02/2020"));
        pagos.add(new Pago(901, 2, uno, 17000, "10/03/2020"));
        pagos.add(new Pago(902, 3, uno, 17000, "10/04/2020"));

*/
    }





//servicios para conectarme a la inteerfas de retrofit
 public static String getToken(Context context){

  SharedPreferences sharedPreferences = context.getSharedPreferences("token.dat", 0);
  String token = sharedPreferences.getString("token", "Token incorrecto");
  Log.d("tokenApi", "error en token");
     return token;
 }

    public static final String SERVER = "http://192.168.1.105:45455";
    private static final String PATH=SERVER+"/api/";

 private static ApiClient.MyApiInterface myApiInterface;

 public static ApiClient.MyApiInterface getMyApiClient(){

  Gson gson = new GsonBuilder().setLenient().create();
  Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(PATH)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();

  myApiInterface = retrofit.create(ApiClient.MyApiInterface.class);

  Log.d("Salida", retrofit.baseUrl().toString());

  return myApiInterface;
 }

 public interface MyApiInterface {


     @PUT("Propietario")
     Call<Propietario> modificarPerfil(@Header("Authorization") String token, @Body Propietario propi);




     //LOGIN
  @FormUrlEncoded
  @POST("Propietario/Login")
  Call<String> Login(@Field("Usuario")String usuario, @Field("Clave")String clave);

  //PERFIL
  @GET("Propietario")
  Call<Propietario> obtenerPropietario(@Header("Authorization") String token);

  /*
  //editar perfil
  @PUT("Propietario")
  Call<Propietario> editarPerfil(@Header("Authorization") String token, @Body Propietario propi);
*/

     //Inmuebles
  @GET("Inmueble")
  Call<List<Inmueble>> listaInmuebles(@Header("Authorization") String token);

  @GET("Inmueble/{id}")
  Call<Inmueble> detalleInmueble(@Header("Authorization") String token, @Path("id") int Id);

  @PUT("inmueble/{id}")
  Call<Inmueble> actualizarInmueble(@Header("Authorization") String token,@Path("id") int id);

  //inquilino
  @GET ("Inquilino/{id}")
  Call<Inquilino> obtenerInquilino(@Header("Authorization") String token,@Path("id") int id);


  //CONTRATOS
  //me devuelve los contratos vigentes
  @GET("Contrato")
  Call<List<Contrato>> listaContratos(@Header("Authorization") String token );

  @GET("Contrato/{id}")//id inquilino
  Call<Contrato> detalleInquilino(@Path("id") int Id, @Header("Authorization") String token );

     //PAGOS
  //TODOS LOS PAGOS DE UN CONTRATO
  @GET("Pago/{id}")//id de contrato
  Call<List<Pago>> Pagos(@Path("id") int id, @Header("Authorization") String token);
 }

}
