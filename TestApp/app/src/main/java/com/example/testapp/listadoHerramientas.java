package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testapp.controllers.HerramientasDbHelper;
import com.example.testapp.controllers.ISelectListener;
import com.example.testapp.models.Herramienta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class listadoHerramientas extends AppCompatActivity implements ISelectListener {

    ListView listView;
    ArrayList <String> tiposHerramientas;
    ArrayList <Integer> idsHerramientas;

    HerramientasDbHelper herramientasDbHelper;

    FloatingActionButton iconAgregar;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_herramientas);
        init();

        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddForm.class);
                Bundle bolsa= new Bundle();
                bolsa.putInt("id", 0);
                intent.putExtras(bolsa);
                startActivity(intent);
            }
        });


    }

    private void init(){
        context= this;
        herramientasDbHelper = new HerramientasDbHelper( context, "HerramientasDbHelper.db", null, 1);
        listView= findViewById(R.id.listaHerramientas);
        iconAgregar = findViewById(R.id.iconAgregar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tool App");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        llenarListView();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cerrar_sesion) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.putExtra(LoginActivity.EXTRA_CLEAR_CREDENTIALS, true);
            startActivity(loginIntent);

        }

        return super.onOptionsItemSelected(item);
    }
    private void llenarListView() {
        Log.d("listadoHerramientas", "Entrando a llenarListView");

        tiposHerramientas= new ArrayList<String>();
        idsHerramientas= new ArrayList<Integer>();
        List<Herramienta> listadoHerramientas = herramientasDbHelper.listaHerramientas();
        Log.d("listadoHerramientas", "Cantidad de herramientas: " + listadoHerramientas.size());

        for(int i = 0 ; i< listadoHerramientas.size(); i++){
          Herramienta  listElemento = listadoHerramientas.get(i);
          Log.d("Elemento", "herramienta: " + listElemento.getTipoHerramienta());
          tiposHerramientas.add(listElemento.getTipoHerramienta());

          idsHerramientas.add(listElemento.getId());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, tiposHerramientas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                        Herramienta herramienta= herramientasDbHelper.herramientaById(idsHerramientas.get(i));
                        Bundle bolsa = new Bundle();
                        bolsa.putInt("id", herramienta.getId());
                        bolsa.putString("tipoHerramienta", herramienta.getTipoHerramienta());
                        bolsa.putString("nombrePropietario", herramienta.getNombrePropietario());
                        bolsa.putString("nombreUsuario", herramienta.getNombreUsuario());
                        bolsa.putString("direccionPropietario", herramienta.getDireccionPropietario());
                        bolsa.putString("direccionUsuario", herramienta.getDireccionUsuario());
                        bolsa.putString("disponibilidad", herramienta.getDisponibilidad());

                        Intent intent = new Intent( context, AddForm.class);
                        intent.putExtras(bolsa);
                        startActivity(intent);

            }
        });

    }

    public void AddElement( View v){
        Intent intent = new Intent(this, AddForm.class);
        startActivity(intent);

    }

    @Override
    public void OnItemClick(String titulo) {

    }
}