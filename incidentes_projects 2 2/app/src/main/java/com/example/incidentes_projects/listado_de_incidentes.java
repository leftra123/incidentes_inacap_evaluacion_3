package com.example.incidentes_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import operacion.Incidente;

public class listado_de_incidentes extends AppCompatActivity {
    //variables
    Incidente incidente;
    Bundle bundle;
    Intent intent;
    TextView txt_nombre, txt_rut, txt_descripcion, txt_fecha_hora, txt_laboratorio;
    Button btn_volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_incidentes);
        //instaciamos el objeto incidente
        incidente = new Incidente();
        //vinculamos los elementos
        vincularElementos();
        //obtenemos los datos
        obtenerDatos();
        //asociar los datos
        asociarDatos();
        //mostramos los datos
        mostrarDatos();
    }
    private void vincularElementos() {
        txt_fecha_hora = (TextView) findViewById(R.id.txt_fecha_hora);
        txt_nombre = (TextView) findViewById(R.id.txt_nombre);
        txt_rut = (TextView) findViewById(R.id.txt_rut);
        txt_descripcion = (TextView) findViewById(R.id.txt_descripcion);
        txt_laboratorio = (TextView) findViewById(R.id.txt_laboratorio);
    }
    private void obtenerDatos() {
        intent = getIntent();
        bundle = intent.getExtras();
    }
    private void asociarDatos() {
        String nombre = bundle.getString("nombre");
        String rut = bundle.getString("rut");
        String descripcion = bundle.getString("descripcion");
        String laboratorio = bundle.getString("laboratorio");
        //
        incidente.setNombre_persona(nombre);
        incidente.setRut(rut);
        incidente.setDescripcion(descripcion);
        incidente.setNombre_laboratorio(laboratorio);
    }
    private void mostrarDatos() {
        txt_fecha_hora.setText(incidente.getFecha() + " " + incidente.getHora());
        txt_nombre.setText("Nombre: " + incidente.getNombre_persona());
        txt_rut.setText("Rut: " + incidente.getRut());
        txt_descripcion.setText("Descripcion: " + incidente.getDescripcion());
        txt_laboratorio.setText("Laboratorio: " + incidente.getNombre_laboratorio());

    }
    public void volver(View view){
        if (view.getId()==R.id.btn_volver) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }}
}