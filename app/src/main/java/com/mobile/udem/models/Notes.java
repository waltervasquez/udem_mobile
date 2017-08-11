package com.mobile.udem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by osmar on 08-10-17.
 */

public class Notes {

    @SerializedName("Asignatura")
    @Expose
    private String asignatura;
    @SerializedName("Ac")
    @Expose
    private String ac;
    @SerializedName("Ef")
    @Expose
    private String ef;
    @SerializedName("Nf")
    @Expose
    private String nf;
    @SerializedName("Res")
    @Expose
    private String res;

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getEf() {
        return ef;
    }

    public void setEf(String ef) {
        this.ef = ef;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

}
