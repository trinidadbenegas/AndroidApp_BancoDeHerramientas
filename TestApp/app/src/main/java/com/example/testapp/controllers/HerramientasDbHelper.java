package com.example.testapp.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.testapp.models.Herramienta;

import java.util.ArrayList;
import java.util.List;

public class HerramientasDbHelper extends SQLiteOpenHelper implements IHerramientasDB{

    Context context;
    private List<Herramienta> listaHerramientas= new ArrayList<>();

    String sql = "CREATE TABLE herramientas (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipoHerramienta TEXT, " +
            "nombrePropietario TEXT, " +
            "nombreUsuario TEXT, " +
            "direccionPropietario TEXT, " +
            "direccionUsuario TEXT, " +
            "disponibilidad TEXT ) ";

    String preloadedData = "INSERT INTO herramientas VALUES (null, " +
            "'Pala mecanica', " +
            "'Ramon Cortez', " +
            "'Erica Lopez', " +
            "'Siempre Viva 123', " +
            "'Libertad 123', " +
            "'true')";


    public HerramientasDbHelper(@Nullable Context context, @Nullable String nombreDB, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreDB, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql);
        Log.d("HerramientasDbHelper", "Database created");
        db.execSQL(preloadedData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS herramientas");
        db.execSQL(sql);
    }

    @Override
    public Herramienta herramientaById(int id) {

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM herramientas WHERE _id=" + id, null);
        try {
            if (cursor.moveToNext())
                return extraerHerramienta(cursor);

            else
                return null;
        }catch(Exception e) {
            Log.d("TAG", "Error herramienta(id)"+ e.getMessage());
            throw e;

        } finally {
            if( cursor != null) cursor.close();
        }

    }

    private Herramienta extraerHerramienta(Cursor cursor) {
        Herramienta herramienta = new Herramienta();
        herramienta.setId(cursor.getInt(0));
        herramienta.setTipoHerramienta(cursor.getString(1));
        herramienta.setNombrePropietario(cursor.getString(2));
        herramienta.setNombreUsuario(cursor.getString((3)));
        herramienta.setDireccionPropietario(cursor.getString((4)));
        herramienta.setDireccionUsuario(cursor.getString((5)));
        herramienta.setDisponibilidad(cursor.getString(6));

        return herramienta;
    }

    @Override
    public List<Herramienta> listaHerramientas() {

        SQLiteDatabase database = getReadableDatabase();
        String sqlQuery = "SELECT * FROM herramientas ORDER BY tipoHerramienta ASC";
        Cursor cursor = database.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst()){
            do{
                listaHerramientas.add(
                        new Herramienta(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6)
                        )
                );
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listaHerramientas;
    }

    @Override
    public void agregarHerramienta(Herramienta herramienta) {

        SQLiteDatabase database= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("tipoHerramienta", herramienta.getTipoHerramienta());
        values.put("nombrePropietario", herramienta.getNombrePropietario());
        values.put("nombreUsuario", herramienta.getNombreUsuario());
        values.put("direccionPropietario", herramienta.getDireccionPropietario());
        values.put("direccionUsuario", herramienta.getDireccionUsuario());
        values.put("disponibilidad", herramienta.getDisponibilidad());
        database.insert("herramientas", null, values);

    }

    @Override
    public void editarHerramienta(int id, Herramienta herramienta) {
        SQLiteDatabase database= getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        ContentValues values= new ContentValues();
        values.put("tipoHerramienta", herramienta.getTipoHerramienta());
        values.put("nombrePropietario", herramienta.getNombrePropietario());
        values.put("nombreUsuario", herramienta.getNombreUsuario());
        values.put("direccionPropietario", herramienta.getDireccionPropietario());
        values.put("direccionUsuario", herramienta.getDireccionUsuario());
        values.put("disponibilidad", herramienta.getDisponibilidad());
        database.update("herramientas", values, "_id=?", parametros );

    }

    @Override
    public void eliminarHerramienta(int id) {
        SQLiteDatabase database= getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        database.delete("herramientas", "_id=?", parametros);

    }
}
