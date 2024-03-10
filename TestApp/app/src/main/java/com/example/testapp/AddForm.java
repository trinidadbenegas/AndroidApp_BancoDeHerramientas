package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testapp.controllers.HerramientasDbHelper;
import com.example.testapp.models.Herramienta;
import com.google.android.material.textfield.TextInputEditText;

public class AddForm extends AppCompatActivity {

    Context context;
    TextInputEditText tipoHerramienta, nombrePropietario, nombreUsuario, direccionPropietario, direccionUsuario, disponibilidad;
    Button btnAgregar, btnActualizar, btnBorrar;
    int id;
    HerramientasDbHelper herramientasDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form);
        init();
    }


// Obtenemos referencia a los elementos
    private void init(){
        context= this;
        tipoHerramienta= findViewById(R.id.tipoHerramienta);
        nombrePropietario= findViewById(R.id.nombrePropietario);
        nombreUsuario= findViewById(R.id.nombrePrestatario);
        direccionPropietario= findViewById(R.id.direccionPropietario);
        direccionUsuario= findViewById(R.id.direccionPrestatario);
        disponibilidad= findViewById(R.id.disponibilidad);
        btnAgregar= findViewById(R.id.btnAgregar);
        btnActualizar= findViewById(R.id.btnEditar);
        btnBorrar= findViewById(R.id.btnEliminar);

        Intent intent = getIntent();
        Bundle bolsa= intent.getExtras();
        id= bolsa.getInt("id");
        Log.d("ID", "id: " + id);
        if ( id != 0){

            tipoHerramienta.setText(bolsa.getString("tipoHerramienta"));
            nombrePropietario.setText(bolsa.getString("nombrePropietario"));
            nombreUsuario.setText(bolsa.getString("nombreUsuario"));
            direccionPropietario.setText(bolsa.getString("direccionPropietario"));
            direccionUsuario.setText(bolsa.getString("direccionUsuario"));
            disponibilidad.setText(bolsa.getString("disponibilidad"));
            btnAgregar.setEnabled(false);
        }else{
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
        }


    }

    private void limpiarForm(){
        id= 0;
        tipoHerramienta.setText("");
        nombrePropietario.setText("");
        nombreUsuario.setText("");
        direccionPropietario.setText("");
        direccionUsuario.setText("");
        disponibilidad.setText("");

    }


    public Herramienta obtenerHerramienta(){
        Herramienta herramienta = new Herramienta();

        String tipo = tipoHerramienta.getText().toString();
        String nombreProp = nombrePropietario.getText().toString();
        String nombreUser= nombreUsuario.getText().toString();
        String direccionProp= direccionPropietario.getText().toString();
        String direccionUser= direccionUsuario.getText().toString();
        String disponible= disponibilidad.getText().toString();

        herramienta.setId(id);
        herramienta.setTipoHerramienta(tipo);
        herramienta.setNombrePropietario(nombreProp);
        herramienta.setNombreUsuario(nombreUser);
        herramienta.setDireccionPropietario(direccionProp);
        herramienta.setDireccionUsuario(direccionUser);
        herramienta.setDisponibilidad(disponible);

        return herramienta;
    }


    public void guardarHerramienta( View view){
        herramientasDbHelper = new HerramientasDbHelper( context, "HerramientasDbHelper.db", null, 1 );
        Herramienta herramienta= obtenerHerramienta();
        Log.d("id herramienta", "herramienta id: " + herramienta.getId());
        if (id == 0){
            herramientasDbHelper.agregarHerramienta(herramienta);
            limpiarForm();
            Toast.makeText(context, "Nueva herramienta guardada", Toast.LENGTH_LONG).show();
        }
        else{


            herramientasDbHelper.editarHerramienta(id, herramienta);
            btnAgregar.setEnabled(false);
            btnBorrar.setEnabled(false);
            Toast.makeText(context, "Herramienta actualizada", Toast.LENGTH_SHORT).show();

        }

    }

    public void eliminarHerramienta( View view){
        herramientasDbHelper = new HerramientasDbHelper( context, "HerramientasDbHelper.db", null, 1 );
        Herramienta herramienta= obtenerHerramienta();
        Log.d("id herramienta", "herramienta id: " + herramienta.getId());
        if (id == 0){

            Toast.makeText(context, "No se puede eliminar", Toast.LENGTH_LONG).show();
        }
        else{

            herramientasDbHelper.eliminarHerramienta(id);
            limpiarForm();
            btnAgregar.setEnabled(true);
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            Toast.makeText(context, "Se eliminó", Toast.LENGTH_SHORT).show();

        }




    }

    public void listarHerramientas( View view){
        Intent intent = new Intent(this, listadoHerramientas.class);
        startActivity(intent);

    }



}