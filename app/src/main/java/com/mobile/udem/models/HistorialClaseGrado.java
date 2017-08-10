package com.mobile.udem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by osmar on 08-07-17.
 */

public class HistorialClaseGrado {
    @SerializedName("Asignatura")
    @Expose
    private String asignatura;

    @SerializedName("Aprobado")
    @Expose
    private Boolean aprobado;

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }
}
