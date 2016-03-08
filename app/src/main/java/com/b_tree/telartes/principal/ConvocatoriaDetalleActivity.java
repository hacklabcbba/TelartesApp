package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Convocatoria;
import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.bluejamesbond.text.DocumentView;
import com.squareup.picasso.Picasso;

/**
 * Created by noemi on 22-02-16.
 */
public class ConvocatoriaDetalleActivity  extends BaseTelartesActivity {
    private TextView lblTitulo;
    private  TextView lbl_fecha;
    private TextView lbl_fuente;
    private DocumentView lbl_descripcion;
    private ImageView imgConvocatoria;
    private Convocatoria convocatoria;
    @Override
    protected String getScreenLabel() {
        return "CONVOCATORIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        lblTitulo = (TextView)findViewById(R.id.lbl_titulo_convocatoria);
        lbl_fecha = (TextView)findViewById(R.id.lbl_fecha);
        lbl_fuente = (TextView)findViewById(R.id.lbl_convocante);
        lbl_descripcion = (DocumentView)findViewById(R.id.lbl_descripcion);
        imgConvocatoria = (ImageView)findViewById(R.id.img_convocatoria);
        Intent i = getIntent();
        this.convocatoria = (Convocatoria)i.getSerializableExtra("convocatoria");
    }

    @Override
    protected int getResLayout() {
        return R.layout.convocatoria_detalle_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {

        if(convocatoria!=null){
            lblTitulo.setText(convocatoria.getTitulo());
            lbl_fecha.setText(convocatoria.getFechalimite());
            lbl_fuente.setText(convocatoria.getConvocante());
            lbl_descripcion.setText(convocatoria.getDescripcion());
            Picasso.with(getBaseContext()).load(convocatoria.getImagen()).into(imgConvocatoria);
        }

    }




}
