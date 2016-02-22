package com.b_tree.telartes.Entidades;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Diana on 27/09/2015.
 */
public class Noticia implements Serializable {
    String titulo;
    String descripcion;
    String categoria;
    String fuente;
    String imagen;
    String fecha;
    int nid;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }
    public Noticia(){

    }

    public Noticia(String nombre, String descripcion, String categoria, String fuente, String imagen, String fecha, int nid) {
        this.titulo = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fuente = fuente;
        this.imagen = imagen;
        this.fecha = fecha;
        this.nid = nid;
    }

    public Noticia(JSONObject jsonObject){

        try {

            this.titulo = android.text.Html.fromHtml(jsonObject.getString("node_title")).toString();
            this.descripcion = jsonObject.getString("descripcion");
            this.categoria = jsonObject.getString("categoría");
            this.fuente = android.text.Html.fromHtml(jsonObject.getString("fuente del item de noticia")).toString();
            this.imagen = (jsonObject.getString("imagen"));
            int start = imagen.indexOf("src=\"") + 5;
            int end = imagen.indexOf("\"", start);
            String src = imagen.substring(start, end);
            this.imagen=src;
            this.fecha = jsonObject.getString("fecha de mensaje");
            this.nid = Integer.parseInt(jsonObject.getString("nid"));
        }catch (Exception e){
            Log.d("Error json", e.getMessage());
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
