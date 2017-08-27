package com.mobile.udem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by osmar on 07-28-17.
 */

public class User {
    @SerializedName("Usuario")
    @Expose
    private String usuario;
    @SerializedName("Foto")
    @Expose
    private String foto;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
