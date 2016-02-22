package com.b_tree.telartes.Entidades;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by noemi on 21-02-16.
 */
public class Convocatoria implements Serializable {

    private String titulo;
    private String fecha_limite;
    private String descripcion;
    private String lugar;
    private String imagen;
    private String archivo;
    private String areas;
    private String campos;
    private String categoria;
    private String convocante;
    private String direccion_web;
    private String correo;
    private int nId;


    public Convocatoria(JSONObject jsonObject){
        try {

            this.titulo = android.text.Html.fromHtml(jsonObject.getString("node_title")).toString();
            this.categoria = android.text.Html.fromHtml(jsonObject.getString("categoría")).toString();
            this.correo = jsonObject.getString("correo electrónico");
            this.direccion_web = android.text.Html.fromHtml(jsonObject.getString("dirección web")).toString();
            this.archivo =  jsonObject.getString("archivo pdf");
            this.imagen = (jsonObject.getString("imagen"));
            int start = imagen.indexOf("src=\"") + 5;
            int end = imagen.indexOf("\"", start);
            String src = imagen.substring(start, end);
            this.imagen=src;
            this.areas= android.text.Html.fromHtml(jsonObject.getString("areas")).toString();
            this.campos = android.text.Html.fromHtml(jsonObject.getString("campos")).toString();
            this.convocante = android.text.Html.fromHtml(jsonObject.getString("convocante")).toString();
            this.fecha_limite = android.text.Html.fromHtml(jsonObject.getString("fecha limite")).toString();
            this.lugar = android.text.Html.fromHtml(jsonObject.getString("ciudad")).toString();
            this.correo  = android.text.Html.fromHtml(jsonObject.getString("correo electrónico")).toString();
            this.nId = Integer.parseInt(jsonObject.getString("nid"));
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

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getCampos() {
        return campos;
    }

    public void setCampos(String campos) {
        this.campos = campos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConvocante() {
        return convocante;
    }

    public void setConvocante(String convocante) {
        this.convocante = convocante;
    }

    public String getDireccion_web() {
        return direccion_web;
    }

    public void setDireccion_web(String direccion_web) {
        this.direccion_web = direccion_web;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }
}
