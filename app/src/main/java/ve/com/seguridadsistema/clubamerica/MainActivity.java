package ve.com.seguridadsistema.clubamerica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.SearchView nrocarnet;
    private Herramienta H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        H = new Herramienta();
        nrocarnet = (SearchView) findViewById(R.id.searchView);
        nrocarnet.setQueryHint("Cédula");

        nrocarnet.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                onClick(nrocarnet);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public void onClick(View v){
        Intent i = new Intent(this, ConsultaActivity.class);
        i.putExtra("nrocarnet", nrocarnet.getQuery().toString());
        startActivity(i);
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