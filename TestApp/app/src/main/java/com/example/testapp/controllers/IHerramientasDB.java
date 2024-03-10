package com.example.testapp.controllers;

import com.example.testapp.models.Herramienta;

import java.util.List;

public interface IHerramientasDB {

    Herramienta herramientaById(int id);
    List<Herramienta> listaHerramientas();

    void agregarHerramienta(Herramienta herramienta);

    void editarHerramienta(int id, Herramienta herramienta);

    void eliminarHerramienta(int id);
}
