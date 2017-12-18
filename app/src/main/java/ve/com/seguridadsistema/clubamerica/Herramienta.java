package ve.com.seguridadsistema.clubamerica;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by schuma on 04/10/14.
 */

public class Herramienta {
    public String MensajeErrorGeneral = "Ha ocurrido un Error, vuelva a intentarlo...";
    protected String MensajeCompartirTitulo = "Te Recomiendo esta Aplicación";
    protected String NombreApp = "app.ClubAmerica";
    protected String URLApk = "https://play.google.com/store/apps/details?id=ve.com.seguridadsistema.clubamerica"; //"http://bit.ly/clubamerica"; //http://bit.ly/1Iz8bpW";
    protected String MensajeCompartir = "Hola, te recomiendo esta aplicación <"+NombreApp+"> permite consultar tus Deudas Pendiente a Cancenlar asignada en el Club América... Puedes Descargarlo desde aquí "+URLApk;
    protected String CorreoSYSJM = "ventas@seguridadsistema.com.ve";

    public Herramienta(){

    }

    public void Mensaje(Activity act){

        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(act).create();
        alertDialog.setTitle("Hola");
        alertDialog.setMessage("5555");
        alertDialog.show();
    }

    public void MensajeTiempoCorto(Activity act, String txt){
        Toast.makeText(act,
                txt,
                Toast.LENGTH_SHORT).show();
    }



    public void Compartir(Context context,String MensajeCompartirTitulo, String MensajeCompartir ) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, MensajeCompartirTitulo);
        intent.putExtra(Intent.EXTRA_TEXT, MensajeCompartir);
        //intent.putExtra(Intent.EXTRA_EMAIL, new String[]{CorreoSYSJM});

        try {
            context.startActivity(Intent.createChooser(intent, "Compartir"));
        } catch (ActivityNotFoundException ex){

            Toast.makeText(context, MensajeErrorGeneral, Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < redes.length; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

}