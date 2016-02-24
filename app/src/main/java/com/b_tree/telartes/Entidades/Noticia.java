package com.b_tree.telartes.Entidades;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.net.URI;


public class Noticia implements Serializable {
    int nid;
    String titulo;
    String descripcion;
    String categoria;
    String fuente;
    String imagen;
    String fecha;
    String enviadopor;
    String autorEnlace;
    String autorNombre;

    public Noticia(){

    }

    public Noticia(int nid, String titulo, String descripcion, String categoria, String fuente, String imagen, String fecha, String enviadopor, String autorEnlace, String autorNombre) {
        this.nid = nid;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fuente = fuente;
        this.imagen = imagen;
        this.fecha = fecha;
        this.enviadopor = enviadopor;
        this.autorEnlace = autorEnlace;
        this.autorNombre = autorNombre;
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
            this.enviadopor=jsonObject.getString("enviado por");
            URI uri = new URI(jsonObject.getString("enlace del autor"));
            String domain = uri.getHost();
            this.autorEnlace= "http://"+domain;
            this.autorNombre = jsonObject.getString("nombre del autor");
            Log.d("ERROR FUENTE ",fuente);
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

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getEnviadopor() {
        return enviadopor;
    }

    public void setEnviadopor(String enviadopor) {
        this.enviadopor = enviadopor;
    }

    public String getAutorEnlace() {
        return autorEnlace;
    }

    public void setAutorEnlace(String autorEnlace) {
        this.autorEnlace = autorEnlace;
    }

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }
}
