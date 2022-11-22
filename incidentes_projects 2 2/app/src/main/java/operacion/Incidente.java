package operacion;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Incidente {
    // Variables
    private String incidenteID; //Primary key
    private String nombre_persona;
    private String nombre_laboratorio;
    private String fecha;
    private String hora;
    private String descripcion;
    private String rut;

    // Constructor vacio
    public Incidente() {
        this.setFecha();
        this.setHora();
    }

    //Constructor con parametros
    public Incidente(String incidenteID, String nombre_persona, String rut, String nombre_laboratorio, String descripcion) {
        this.incidenteID = incidenteID;
        this.nombre_persona = nombre_persona;
        this.rut = rut;
        this.descripcion = descripcion;
        this.nombre_laboratorio = nombre_laboratorio;
        this.setFecha();
        this.setHora();
    }


    // Getters

    public String getIncidenteID() {
        return incidenteID;
    }
    public String getFecha() {
        return fecha;
    }
    public String getHora() {
        return hora;
    }
    public String getNombre_persona() {
        return nombre_persona;
    }
    public String getNombre_laboratorio() {
        return nombre_laboratorio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getRut() {
        return rut;
    }

    // Setters

    public void setIncidenteID(String incidenteID) {
        this.incidenteID = incidenteID;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }
    public void setNombre_laboratorio(String nombre_laboratorio) {
        this.nombre_laboratorio = nombre_laboratorio;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    private void setFecha() {
        // fecha del sistema con hora, minutos y segundos
        Date fecha = new Date();
        // convertir fecha a string
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_str = sdf.format(fecha);
        this.fecha = fecha_str;
    }
    private void setHora() {
        // fecha del sistema con hora, minutos y segundos
        Date fecha = new Date();
        // convertir fecha a string
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String hora_str = sdf.format(fecha);
        this.hora = hora_str;
    }

}