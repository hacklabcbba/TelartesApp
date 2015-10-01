package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b_tree.telartes.Entidades.Evento;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.EventoAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.base.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diana on 27/09/2015.
 */
public class EventoActivity extends BaseTelartesActivity {
    private ListView lvEventos;
    private EventoAdapter eventoAdapter;
    @Override
    protected String getScreenLabel() {
        return "Eventos";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        eventoAdapter = new EventoAdapter(this, Utils.cargarEventos());
    }



    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        lvEventos= (ListView) findViewById(R.id.lv_eventos);
        lvEventos.setAdapter(eventoAdapter);
        lvEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(EventoActivity.this,RutaEntregaActivity.class));
            }
        });
    }



    @Override
    protected int getResLayout() {
        return R.layout.eventos_activity;
    }


}
