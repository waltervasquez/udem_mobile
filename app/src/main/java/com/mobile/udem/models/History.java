package com.mobile.udem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by osmar on 08-01-17.
 */

public class History {
    @SerializedName("Grado")
    @Expose
    private Integer grado;
    @SerializedName("HistorialClaseGrado")
    @Expose
    private List<HistorialClaseGrado> historialClaseGrado = null;

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public List<HistorialClaseGrado> getHistorialClaseGrado() {
        return historialClaseGrado;
    }

    public void setHistorialClaseGrado(List<HistorialClaseGrado> historialClaseGrado) {
        this.historialClaseGrado = historialClaseGrado;
    }
}
