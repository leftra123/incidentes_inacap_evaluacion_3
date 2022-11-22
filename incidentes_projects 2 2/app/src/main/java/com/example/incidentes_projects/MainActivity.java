package com.example.incidentes_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import operacion.Incidente;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Variables
    Button btn_grabar_incidente;
    EditText txt_nombre, txt_rut, txt_descripcion, txt_laboratorio;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vincularElementos();

        //        Firebase
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        activarListenerBoton();
    }

    private void vincularElementos() {
        //boton
        btn_grabar_incidente = (Button) findViewById(R.id.btn_grabar_incidente);
        //campos de texto
        txt_nombre = (EditText) findViewById(R.id.txt_nombre);
        txt_rut = (EditText) findViewById(R.id.txt_rut);
        txt_descripcion = (EditText) findViewById(R.id.txt_descripcion);
        txt_laboratorio = (EditText) findViewById(R.id.txt_laboratorio);
    }

    @Override
    public void onClick(View view) {
    //creamos un intento
        Intent intent = new Intent(MainActivity.this, listado_de_incidentes.class);
        String nombre = txt_nombre.getText().toString();
        String rut = txt_rut.getText().toString();
        String descripcion = txt_descripcion.getText().toString();
        String laboratorio = txt_laboratorio.getText().toString();
        //pasamos los datos
        intent.putExtra("nombre", nombre);
        intent.putExtra("rut", rut);
        intent.putExtra("descripcion", descripcion);
        intent.putExtra("laboratorio", laboratorio);


        if (implementar_validacion()){
            //Creamos una instancia de incidente
            Incidente incidente = new Incidente(UUID.randomUUID().toString(), nombre, rut, laboratorio, descripcion);

            //guardamos el dato en la firebase
            incidenteAdd(incidente);

            //iniciamos la actividad
            startActivity(intent);

        }
    }

    private void incidenteAdd(Incidente incidente) {
//        Database Post
        databaseReference.child("incidentes").child(incidente.getIncidenteID()).setValue(incidente);
    }

    private void activarListenerBoton() {
        btn_grabar_incidente.setOnClickListener(this);
    }

    /**
     *  Valida rut de la forma XXXXXXXX-X
     */
    public static Boolean validaRut ( String rut ) {
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if ( matcher.matches() == false ) return false;
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
    }

    /**
     * Valida el dígito verificador
     */
    public static String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }

    //validar los campos de texto
    private boolean implementar_validacion() {
        Boolean todo_correcto = true;
        //validar que los campos no se encuentren vacios
        if (txt_nombre.getText().toString().isEmpty() || txt_rut.getText().toString().isEmpty() || txt_descripcion.getText().toString().isEmpty() || txt_laboratorio.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Completa todos los campos", Toast.LENGTH_LONG).show();
            todo_correcto = false;
            if(txt_nombre.getText().toString().isEmpty()){
                txt_nombre.setError("Campo obligatorio");
                todo_correcto = false;
            }
            if(txt_rut.getText().toString().isEmpty()){
                txt_rut.setError("Campo obligatorio");
                todo_correcto = false;
            }
            if(txt_descripcion.getText().toString().isEmpty()){
                txt_descripcion.setError("Campo obligatorio");
                todo_correcto = false;
            }
            if(txt_laboratorio.getText().toString().isEmpty()){
                txt_laboratorio.setError("Campo obligatorio");
                todo_correcto = false;
            }
        }
        //Si los campos estan completados me aseguro que sean correctos
        else {
            //validar si el rut es valido
            if (!validaRut(String.valueOf(txt_rut.getText()))) {
                Toast.makeText(getApplicationContext(), "El rut no es valido", Toast.LENGTH_LONG).show();
                todo_correcto = false;
            }
            else{
                Toast.makeText(getApplicationContext(),"Incidente guardado", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, listado_de_incidentes.class);
                startActivity(i);
                todo_correcto = true;
            }
        }
        return todo_correcto;
    }
    public void guardar(View view){
        if (view.getId()==R.id.btn_grabar_incidente){
            implementar_validacion();
        }
    }
}