package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.AgendaCultural;
import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by noemi on 24-02-16.
 */
public class AgendaDetalleActivity  extends BaseTelartesActivity {
    private TextView txtTitulo;
    private TextView txtfecha;
    private TextView txtEnlace;
    private WebView txtDescripcion;
    private ImageView imgAgenda;
    private TextView txtDepartamento;
    private TextView txtFechaEvento;
    private TextView txtCosto;
    private TextView txtLugar;
    private AgendaCultural agenda;
    @Override
    protected String getScreenLabel() {
        return "AGENDA CULTURAL";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        txtTitulo = (TextView)findViewById(R.id.txt_da_titulo);
        txtfecha = (TextView)findViewById(R.id.txt_da_fecha);
        txtEnlace = (TextView)findViewById(R.id.txt_da_enlace);
        txtDescripcion = (WebView)findViewById(R.id.txt_da_descripcion);
        imgAgenda = (ImageView)findViewById(R.id.img_da_agenda);
        txtDepartamento = (TextView)findViewById(R.id.txt_da_departamento);
        txtFechaEvento = (TextView)findViewById(R.id.txt_da_fecha_evento);
        txtCosto = (TextView)findViewById(R.id.txt_da_costo);
        txtLugar = (TextView)findViewById(R.id.txt_da_lugar);
        Intent i = getIntent();
        this.agenda = (AgendaCultural)i.getSerializableExtra("agenda");
    }

    @Override
    protected int getResLayout() {
        return R.layout.agenda_detalle_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String myData = "Hello World! This tutorial is to show demo of displaying text with justify alignment in WebView.";
        if(agenda!=null){
              txtTitulo.setText(agenda.getTitulo());
              txtfecha.setText(agenda.getFecha());
              txtDescripcion.loadData(String.format(htmlText, agenda.getDescripcion()), "text/html", "utf-8");
                txtDepartamento.setText("Departamento: "+agenda.getDepartamento());
            txtFechaEvento.setText("Fecha: "+"De "+ agenda.getFechainicio()+ " hasta "+agenda.getFechafin());
            txtCosto.setText("Costo: Entrada libre");
            txtLugar.setText("Lugar/Direccion: "+agenda.getLugar_direccion());

//            lbl_descripcion.setText(agenda.getDescripcion());
            Picasso.with(getBaseContext()).load(agenda.getImagen()).into(imgAgenda);
        }

    }




}
