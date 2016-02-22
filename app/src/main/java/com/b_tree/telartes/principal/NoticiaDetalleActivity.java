package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
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
    private TextView lbl_descripcion;
    private ImageView imgNoticias;
    private Noticia noticia;
    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        lblTitulo = (TextView)findViewById(R.id.lbl_titulo_noticia);
        lbl_fecha = (TextView)findViewById(R.id.lbl_fecha);
        lbl_fuente = (TextView)findViewById(R.id.lbl_fuente);
        lbl_descripcion = (TextView)findViewById(R.id.lbl_descripcion);
        imgNoticias = (ImageView)findViewById(R.id.img_noticia);
        Intent i = getIntent();
        this.noticia = (Noticia)i.getSerializableExtra("noticia");

    }

    @Override
    protected int getResLayout() {
        return R.layout.noticia_detalle_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        if(noticia!=null){
            lblTitulo.setText(noticia.getTitulo());
            lbl_fecha.setText(noticia.getFecha());
            lbl_fuente.setText(noticia.getFuente());
            lbl_descripcion.setText(noticia.getDescripcion());
            Picasso.with(getBaseContext()).load(noticia.getImagen()).into(imgNoticias);
        }

    }




}
