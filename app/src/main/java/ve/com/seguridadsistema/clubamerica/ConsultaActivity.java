package ve.com.seguridadsistema.clubamerica;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;
import android.widget.TextView;

public class ConsultaActivity extends AppCompatActivity {

    static ArrayList<Deuda> ListadoDeuda;
    private String claveSHA = "!54-*39!7!*-631!";
    //String URL ="http://192.168.1.105/Descargas/Migracion/buscarClubAmerica.php";
    String URL ="http://segclusisame.no-ip.org:82/rest/buscarClubAmerica.php";
    private String nrocarnet, cedula;
    Activity a;
    private ListView Listview;
    private TextView textNombreSocio, textNroCarnet2;
    private TextView mensaje;
    private Herramienta H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        H = new Herramienta();

        ///verificamos si tenemos conexion ///
        if (!H.verificaConexion(this)) {
            H.MensajeTiempoCorto(this, "Comprueba su conexión a Internet.");
            this.finish();
        }else{
            a = this;
            ListadoDeuda = new ArrayList<Deuda>();
            textNroCarnet2 = (TextView) findViewById(R.id.textNroCarnet2);
            TextView textcedula = (TextView) findViewById(R.id.textcedula);
            textNombreSocio = (TextView) findViewById(R.id.textNombreSocio);
            Listview = (ListView) findViewById(R.id.listView);
            mensaje = (TextView) findViewById(R.id.label_estatus);

            //nrocarnet = getIntent().getStringExtra("nrocarnet");
            //textNroCarnet2.setText(nrocarnet);
            cedula = getIntent().getStringExtra("nrocarnet");
            textcedula.setText(cedula);
            //textNombreSocio.setText("Jesus Maria Lobaton Escobar");

            new ObtenerDeuda(Listview).execute();
        }
    }

    private class ObtenerDeuda extends AsyncTask<Void, Void, Void> {
        ListView list;
        private ProgressDialog pDialog;

        public ObtenerDeuda(ListView Listview) {
            this.list=Listview;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ConsultaActivity.this);
            pDialog.setIcon(R.drawable.ic_logov4);
            pDialog.setMessage("Buscando ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // CREAMOS LA INSTANCIA DE LA CLASE
            JSONParser sh = new JSONParser();

            //String carnet = nrocarnet.toString();
            List params = new ArrayList();
            params.add(new BasicNameValuePair("nrocarnet",cedula));
            params.add(new BasicNameValuePair("sha",claveSHA));

            String jsonStr = sh.makeServiceCall(URL, JSONParser.GET, params);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray datos = jsonObj.getJSONArray("deudas");

                   // int CantidadReg = datos.length();
                    //Log.d("SYSJM", "Cantidad de Registro "+String.valueOf(CantidadReg));

                    if (datos.length() > 0){
                        //lista.clear();
                        // looping through All Equipos
                        for (int i = 0; i < datos.length(); i++) {
                            JSONObject c = datos.getJSONObject(i);

                            // Log.d("SYSJM", String.valueOf(c.getInt("estatus")));
                            if (c.getInt("estatus") == 0 )
                            {
                                Log.d("SYSJM", "Pagina En Mantenimiento");
                            }else{
//#ffff2603
                                String nrocarnet = c.getString("nrocarnet");
                                String nombre = c.getString("nombre");
                                String fecven = c.getString("fecven");
                                String descr = c.getString("descr");
                                String monto = c.getString("monto");
                                String deudavencida = c.getString("deudavencida"); //(c.getString("deudavencida")!="") ? c.getString("deudavencida") : "";
                                String mensaje = c.getString("mensaje");

                                Deuda e = new Deuda();
                                e.setFecven(fecven);
                                e.setDescr(descr);
                                e.setMonto(monto);
                                e.setNombre(nombre);
                                e.setNrocarnet(nrocarnet);
                                e.setDeudaVencida(deudavencida);
                                e.setMensaje(mensaje);
                                ListadoDeuda.add(e);
                            }
                        }
                    }else{
                        Log.d("SYSJM","No se encontro deuda");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("SYSJM", "Problema al cargar el JSON");
            }

            return null;
        }

    protected void onPostExecute(Void result) {
        String texto;
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing()){
            pDialog.dismiss();
        }

        if (ListadoDeuda.size() == 0){
            H.MensajeTiempoCorto(a, "No se encontro este Socio o No tiene Deuda");
            //Toast.makeText(a, "No se encontro este Socio o No tiene Deuda", Toast.LENGTH_LONG).show();
        }else{
            textNombreSocio.setText(ListadoDeuda.get(0).getNombre());
            textNroCarnet2.setText(ListadoDeuda.get(0).getNrocarnet());
            ColorEstatus(ListadoDeuda.get(0).getMensaje());
            new CargarListTask().execute();
        }

        //Log.d("SYSJM", "Cantidad de Registro "+String.valueOf(CantidadReg));
        //texto = txtcodigo.getText().toString();
        //if (texto.isEmpty()){ texto = "Productos "; }else{ texto = texto.toUpperCase(); }
        //resultado.setText(String.valueOf(CantidadReg)+" "+texto+" Encontrados");
    }

    public void ColorEstatus(String valor){
        if ( valor.equals("SOLVENTE") ){
            mensaje.setBackgroundColor(Color.BLUE);
            mensaje.setTextColor(Color.WHITE);
        }else{
            mensaje.setBackgroundColor(Color.YELLOW);
        }

        mensaje.setText(valor);
     }

    //HILO PARA CARGAR LOS DATOS EN EL LISTVIEW
    class CargarListTask extends AsyncTask<Void,String, AdaptadorDeuda>{
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        protected AdaptadorDeuda doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            try{

            }catch(Exception ex){
                ex.printStackTrace();
            }
            //Adapter adaptador = new Adapter(a,lista);
            AdaptadorDeuda adaptador = new AdaptadorDeuda(a, ListadoDeuda);
            return adaptador;
        }

        @Override
        protected void onPostExecute(AdaptadorDeuda result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Listview.setAdapter(result);


        }
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.compartir) {

            H.Compartir(this, H.MensajeCompartirTitulo, H.MensajeCompartir);

        }else if (id == R.id.acercade) {
            Intent i = new Intent(this, Acercade.class);
            startActivity(i);

        }else if (id == R.id.contactenos) {
            Intent i = new Intent(this, Contactenos.class);
            startActivity(i);

        }else if (id == R.id.sugerencia) {
            Intent i = new Intent(this, Sugerencia.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
