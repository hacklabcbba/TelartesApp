package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.squareup.picasso.Picasso;


/**
 * Created by noemi on 21-02-16.
 */
public class NoticiaDetalleActivity extends BaseTelartesActivity {
    private TextView lblTitulo;
    private  TextView lbl_fecha;
    private TextView lbl_fuente;
    private WebView lbl_descripcion;
    private ImageView imgNoticias;
    private Noticia noticia;
    private TextView txtEnlaceAutor;
    private TextView txtNombreAutor;
    private TextView txtFuente;
    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        lblTitulo = (TextView)findViewById(R.id.lbl_titulo_noticia);
        lbl_fecha = (TextView)findViewById(R.id.lbl_fecha);
        lbl_descripcion = (WebView)findViewById(R.id.lbl_descripcion);
        imgNoticias = (ImageView)findViewById(R.id.img_noticia);
        txtEnlaceAutor = (TextView)findViewById(R.id.txt_enlace_autor);
        txtNombreAutor = (TextView)findViewById(R.id.txt_nombre_autor);
        txtFuente = (TextView)findViewById(R.id.txt_fuente_noticia);

        Intent i = getIntent();
        this.noticia = (Noticia)i.getSerializableExtra("noticia");

    }

    @Override
    protected int getResLayout() {
        return R.layout.noticia_detalle_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String myData = "Hello World! This tutorial is to show demo of displaying text with justify alignment in WebView.";
        if(noticia!=null){
            lblTitulo.setText(noticia.getTitulo());
            lbl_fecha.setText(noticia.getFecha());
            lbl_descripcion.loadData(String.format(htmlText, noticia.getDescripcion()), "text/html", "utf-8");
            Picasso.with(getBaseContext()).load(noticia.getImagen()).into(imgNoticias);
            txtEnlaceAutor.setText(noticia.getAutorEnlace());
            txtNombreAutor.setText(noticia.getAutorNombre());
            txtFuente.setText(noticia.getFuente());
        }

    }




}
