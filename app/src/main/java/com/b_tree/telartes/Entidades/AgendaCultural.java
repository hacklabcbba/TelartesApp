package com.b_tree.telartes.Entidades;

import org.json.JSONObject;

/**
 * Created by noemi on 21-02-16.
 */
public class AgendaCultural {

    private String titulo;
    private String fecha;
    private String descripcion;
    private String imagen;


    public AgendaCultural(String titulo, String fecha, String descripcion, String imagen) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public AgendaCultural(JSONObject jsonObject){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

