package com.mobile.udem.models;

/**
 * Created by osmar on 08-13-17.
 */

public class Locations {
    private String nombre;
    private String descripcion;
    private double lat;
    private double lng;
    private String foto;

    public Locations(String nombre, String descripcion, double lat, double lng, String foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
