package com.mobile.udem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by osmar on 09-03-17.
 */

public class Schedule {

    @SerializedName("Asignatura")
    @Expose
    private String asignaura;

    @SerializedName("Grupo")
    @Expose
    private String grupo;

    @SerializedName("Dia")
    @Expose
    private String dia;

    @SerializedName("Hora")
    @Expose
    private String hora;

    @SerializedName("Aula")
    @Expose
    private String aula;

    public String getAsignaura() {
        return asignaura;
    }

    public void setAsignaura(String asignaura) {
        this.asignaura = asignaura;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}

