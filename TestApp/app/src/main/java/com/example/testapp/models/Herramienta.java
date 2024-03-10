package com.example.testapp.models;

public class Herramienta {

    private int id;
    private String tipoHerramienta, nombrePropietario, nombreUsuario, direccionPropietario,direccionUsuario, disponibilidad;



    public Herramienta(){

    }
    public Herramienta(int id, String tipoHerramienta, String nombrePropietario, String nombreUsuario, String direccionPropietario, String direccionUsuario, String disponibilidad) {
        this.id = id;
        this.tipoHerramienta = tipoHerramienta;
        this.nombrePropietario = nombrePropietario;
        this.nombreUsuario = nombreUsuario;
        this.direccionPropietario = direccionPropietario;
        this.direccionUsuario = direccionUsuario;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoHerramienta() {
        return tipoHerramienta;
    }

    public void setTipoHerramienta(String tipoHerramienta) {
        this.tipoHerramienta = tipoHerramienta;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDireccionPropietario() {
        return direccionPropietario;
    }

    public void setDireccionPropietario(String direccionPropietario) {
        this.direccionPropietario = direccionPropietario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return "Herramienta{" +
                "id=" + id +
                ", tipoHerramienta='" + tipoHerramienta + '\'' +
                ", nombrePropietario='" + nombrePropietario + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", direccionPropietario='" + direccionPropietario + '\'' +
                ", direccionUsuario='" + direccionUsuario + '\'' +
                ", disponibilidad=" + disponibilidad +
                '}';
    }
}




