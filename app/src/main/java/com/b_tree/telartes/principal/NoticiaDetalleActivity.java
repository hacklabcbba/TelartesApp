package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.bluejamesbond.text.DocumentView;
import com.squareup.picasso.Picasso;


/**
 * Created by noemi on 21-02-16.
 */
public class NoticiaDetalleActivity extends BaseTelartesActivity {
    private TextView lblTitulo;
    private  TextView lbl_fecha;
    private TextView lbl_fuente;
    private DocumentView txt_n_noticia;
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
        txt_n_noticia = (DocumentView)findViewById(R.id.txt_n_noticia);
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

        if(noticia!=null){
            lblTitulo.setText(noticia.getTitulo());
            lbl_fecha.setText("subido por: "+noticia.getEnviado_por()+ " el "+noticia.getFecha());
            txt_n_noticia.setText(noticia.getDescripcion());
            Picasso.with(getBaseContext()).load(noticia.getImagen()).into(imgNoticias);
            txtEnlaceAutor.setText(noticia.getAutorEnlace());
            txtNombreAutor.setText(noticia.getAutorNombre());
            txtFuente.setText(noticia.getFuente());
        }

    }




}
