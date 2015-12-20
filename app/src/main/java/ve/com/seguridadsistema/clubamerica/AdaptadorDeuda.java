package ve.com.seguridadsistema.clubamerica;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorDeuda extends BaseAdapter{
	  
    protected Activity activity;
    //ARRAYLIST CON TODOS LOS ITEMS
    protected ArrayList<Deuda> items;
     
    //CONSTRUCTOR
    public AdaptadorDeuda(Activity activity, ArrayList<Deuda> items) {
        this.activity = activity;
        this.items = items;
      }

    //CUENTA LOS ELEMENTOS
    @Override
    public int getCount() {
        return items.size();
    }

    //DEVUELVE UN OBJETO DE UNA DETERMINADA POSICION
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    //METODO PRINCIPAL, AQUI SE LLENAN LOS DATOS
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // SE GENERA UN CONVERTVIEW POR MOTIVOS DE EFICIENCIA DE MEMORIA
     //ES UN NIVEL MAS BAJO DE VISTA, PARA QUE OCUPEN MENOS MEMORIA LAS
 
        View v = convertView;
        //ASOCIAMOS LA VISTA AL LAYOUT DEL RECURSO XML DONDE ESTA LA BASE DE

        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView. Para ello primero creamos el
        // inflater, y después inflamos la vista.
       if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_deuda, null);
        }

        Deuda i = items.get(position);
        //LLENAMOS LOS ITEM CON LO QUE NOS TRAE LA CLASE DEUDA
        TextView fecven = (TextView) v.findViewById(R.id.textFecVen);
        TextView descr = (TextView) v.findViewById(R.id.textConcepto);
        TextView monto = (TextView) v.findViewById(R.id.textMonto);
        TextView deudavencida = (TextView) v.findViewById(R.id.textVencido);
       // TextView mensaje = (TextView) v.findViewById(R.id.label_estatus);

        fecven.setText(i.getFecven());
        descr.setText(i.getDescr());
        monto.setText(i.getMonto());
        deudavencida.setText(i.getDeudaVencida());
       // mensaje.setText(i.getMensaje());

        // DEVOLVEMOS VISTA
        return v;
    }
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}