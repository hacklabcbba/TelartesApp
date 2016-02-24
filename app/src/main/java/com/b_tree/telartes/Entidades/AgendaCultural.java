package com.b_tree.telartes.Entidades;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by noemi on 21-02-16.
 */
public class AgendaCultural implements Serializable{
    private int nid;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String imagen;
    private String campo;
    private String costo;
    private String departamento;
    private String enlace;
    private String fechaInicio;
    private String fechaFin;
    private String lugar_direccion;
    private String pdf;
    private String enviado_por;



    public AgendaCultural(JSONObject jsonObject){
        try {
        titulo = android.text.Html.fromHtml(jsonObject.getString("node_title")).toString();
        campo =  android.text.Html.fromHtml(jsonObject.getString("campo")).toString();
        costo = jsonObject.getString("costo");
        departamento =  android.text.Html.fromHtml(jsonObject.getString("departamento")).toString();
        descripcion =  android.text.Html.fromHtml(jsonObject.getString("descripción")).toString();
        enlace =    android.text.Html.fromHtml(jsonObject.getString("enlace")).toString();
        lugar_direccion =  android.text.Html.fromHtml(jsonObject.getString("lugar/dirección")).toString();
        pdf =  android.text.Html.fromHtml(jsonObject.getString("pdf")).toString();
        this.nid = Integer.parseInt(jsonObject.getString("nid"));
        enviado_por = jsonObject.getString("enviado por");
        fechaInicio =  android.text.Html.fromHtml(jsonObject.getString("fecha inicio")).toString();
        fechaFin =  android.text.Html.fromHtml(jsonObject.getString("fecha fin")).toString();
            this.imagen = (jsonObject.getString("imagen"));
            int start = imagen.indexOf("src=\"") + 5;
            int end = imagen.indexOf("\"", start);
            String src = imagen.substring(start, end);
            this.imagen=src;
        }catch (Exception e){
            Log.d("Error json", e.getMessage());
        }

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getLugar_direccion() {
        return lugar_direccion;
    }

    public void setLugar_direccion(String lugar_direccion) {
        this.lugar_direccion = lugar_direccion;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getEnviado_por() {
        return enviado_por;
    }

    public void setEnviado_por(String enviado_por) {
        this.enviado_por = enviado_por;
    }
}

