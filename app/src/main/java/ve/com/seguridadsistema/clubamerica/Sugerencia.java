package ve.com.seguridadsistema.clubamerica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Sugerencia extends AppCompatActivity {
    protected String CorreoCliente= "CentroAtleticoAmerica@hotmail.com";
    protected String CorreoSYSJM = "ventas@seguridadsistema.com.ve";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencia);

        Herramienta H = new Herramienta();

        Button boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView NroCarnet = (TextView) findViewById(R.id.text_carnet);
                TextView Nombre = (TextView) findViewById(R.id.text_nombre);
                TextView Obser = (TextView) findViewById(R.id.text_obser);

                Intent itSend = new Intent(Intent.ACTION_SEND);
                itSend.setType("plain/text");

                //colocamos los datos para el envío
                itSend.putExtra(Intent.EXTRA_EMAIL, new String[]{ CorreoCliente } );
                itSend.putExtra(Intent.EXTRA_CC, new String[]{CorreoSYSJM});
                itSend.putExtra(Intent.EXTRA_SUBJECT, NroCarnet.getText().toString());
                itSend.putExtra(Intent.EXTRA_TEXT, Obser.getText());

                startActivity(itSend);
            }
        });
    }

}
