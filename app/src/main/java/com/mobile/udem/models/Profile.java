package com.mobile.udem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by osmar on 08-25-17.
 */

public class Profile {
    @SerializedName("Carrera")
    @Expose
    private String carrera;
    @SerializedName("Turno")
    @Expose
    private String turno;
    @SerializedName("Celular")
    @Expose
    private String celular;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Sexo")
    @Expose
    private String sexo;
    @SerializedName("Nombres")
    @Expose
    private String nombres;

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

}
