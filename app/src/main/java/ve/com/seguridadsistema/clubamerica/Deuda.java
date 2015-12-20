package ve.com.seguridadsistema.clubamerica;

/**
 * Created by schuma on 25/07/15.
 */
public class Deuda {

    private String Fecven;
    private String Descr;
    private String Monto;
    private String Estatus;
    private String Nombre;
    private String DeudaVencida;
    private String Mensaje;
    private String Nrocarnet;

    public Deuda() { super(); }

    public String getDescr() {
        return Descr;
    }

    public void setDescr(String descr) {
        Descr = descr;
    }

    public String getFecven() {
        return Fecven;
    }

    public void setFecven(String fecven) {
        Fecven = fecven;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String estatus) {
        Estatus = estatus;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String monto) {
        Monto = monto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDeudaVencida() {
        return DeudaVencida;
    }

    public void setDeudaVencida(String deudaVencida) {
        DeudaVencida = deudaVencida;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getNrocarnet() {
        return Nrocarnet;
    }

    public void setNrocarnet(String nrocarnet) {
        Nrocarnet = nrocarnet;
    }
}
