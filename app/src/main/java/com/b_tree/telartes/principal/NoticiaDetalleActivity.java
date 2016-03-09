package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
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
    private TextView txtNoticia;
    //private DocumentView txtNoticia;
    private ImageView imgNoticias;
    private Noticia noticia;
    private TextView txtEnlaceAutor;
    private TextView txtNombreAutor;
    private TextView txtFuente;
    private ImageView imgShare;
    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        lblTitulo = (TextView)findViewById(R.id.lbl_titulo_noticia);
        lbl_fecha = (TextView)findViewById(R.id.lbl_fecha);
       // txtNoticia = (DocumentView)findViewById(R.id.txt_n_noticia);
        txtNoticia = (TextView)findViewById(R.id.txt_n_noticia);
        imgNoticias = (ImageView)findViewById(R.id.img_noticia);
        txtEnlaceAutor = (TextView)findViewById(R.id.txt_enlace_autor);
        txtNombreAutor = (TextView)findViewById(R.id.txt_nombre_autor);
        txtFuente = (TextView)findViewById(R.id.txt_fuente_noticia);
        imgShare = (ImageView)findViewById(R.id.img_share);
        Intent i = getIntent();
        this.noticia = (Noticia)i.getSerializableExtra("noticia");
        imgShare.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imgShare.setBackgroundColor(getResources().getColor(R.color.plomo));
                        imgShare.setImageDrawable(getResources().getDrawable(R.mipmap.compartir_hover));
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Este texto se enviara.");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;
                    case MotionEvent.ACTION_UP:
                        imgShare.setBackgroundColor(getResources().getColor(R.color.blanco));
                        imgShare.setImageDrawable(getResources().getDrawable(R.mipmap.compartir));

                }
                return true;
            }
        });

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
            txtNoticia.setText(Html.fromHtml(noticia.getDescripcion()));
            Picasso.with(getBaseContext()).load(noticia.getImagen()).into(imgNoticias);
            txtEnlaceAutor.setText(noticia.getAutorEnlace());
            txtNombreAutor.setText(noticia.getAutorNombre());
            txtFuente.setText(Html.fromHtml(noticia.getFuente()));
            txtFuente.setClickable(true);
            txtFuente.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }




}
