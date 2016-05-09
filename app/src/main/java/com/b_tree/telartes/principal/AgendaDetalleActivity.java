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

import com.b_tree.telartes.Entidades.AgendaCultural;
import com.b_tree.telartes.R;
import com.b_tree.telartes.Utils.Utils;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.bluejamesbond.text.DocumentView;
import com.squareup.picasso.Picasso;

/**
 * Created by noemi on 24-02-16.
 */
public class AgendaDetalleActivity  extends BaseTelartesActivity {
    private TextView txtTitulo;
    private TextView txtfecha;
    private TextView txtEnlace;
    private TextView txtDescripcion;
    private ImageView imgAgenda;
    private TextView txtDepartamento;
    private TextView txtFechaEvento;
    private TextView txtCosto;
    private TextView txtLugar;
    private ImageView imgShare;
    private ImageView imgInfo;
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
        txtDescripcion = (TextView)findViewById(R.id.txt_da_descripcion);
        imgAgenda = (ImageView)findViewById(R.id.img_da_agenda);
        txtDepartamento = (TextView)findViewById(R.id.txt_da_departamento);
        txtFechaEvento = (TextView)findViewById(R.id.txt_da_fecha_evento);
        txtCosto = (TextView)findViewById(R.id.txt_da_costo);
        txtLugar = (TextView)findViewById(R.id.txt_da_lugar);
        imgShare = (ImageView)findViewById(R.id.img_share);
        imgInfo = (ImageView)findViewById(R.id.menu_info);
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Intent i = getIntent();
        this.agenda = (AgendaCultural)i.getSerializableExtra("agenda");
        imgShare.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imgShare.setBackgroundColor(getResources().getColor(R.color.plomo));
                        imgShare.setImageDrawable(getResources().getDrawable(R.mipmap.compartir_hover));
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Telartes: " + agenda.getTitulo());
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
        return R.layout.agenda_detalle_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        if(agenda!=null){
            txtTitulo.setText(agenda.getTitulo());
            txtfecha.setText(agenda.getFecha());
            txtDescripcion.setText(Html.fromHtml(agenda.getDescripcion()));
            txtDepartamento.setText("Departamento: "+agenda.getDepartamento());
            txtFechaEvento.setText("Fecha: " + "De " + agenda.getFechainicio() + " hasta " + agenda.getFechafin());
            if(!agenda.getCosto().equals("null")){
                txtCosto.setText("Costo: " + agenda.getCosto());
            }else{
                txtCosto.setText("Costo: " + "Ingreso libre");
            }

            txtLugar.setText("Lugar/Direccion: " + agenda.getLugar_direccion());
            imgAgenda.setImageBitmap(Utils.decodeBase64(agenda.getImagen()));
            if (!agenda.getEnlace().isEmpty() && !(agenda.getEnlace().equals("null"))){
                ((TextView)findViewById(R.id.txt_da_enlacetx)).setVisibility(View.INVISIBLE);
                txtEnlace.setText(Html.fromHtml(agenda.getEnlace()));
                txtEnlace.setClickable(true);
                txtEnlace.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }

    }


}
