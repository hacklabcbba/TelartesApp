package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b_tree.telartes.Entidades.AgendaCultural;
import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.AgendaAdapter;
import com.b_tree.telartes.adapter.NoticiaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.AgendaService;
import com.b_tree.telartes.rest.NoticiasService;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 22-02-16.
 */
public class AgendaActivity extends BaseTelartesActivity {

    private ListView lvAgenda;
    private AgendaAdapter agendaAdapter;
    private AgendaService agendas;
    private List<AgendaCultural> agendaList;


    @Override
    protected String getScreenLabel() {
        return "AGENDA CULTURAL";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        agendaList = new ArrayList<>();
        agendas = new AgendaService(this) {
            @Override
            public void onSuccessObtenerAgenda(List<AgendaCultural> agendalist) {
                agendaList = agendalist;
                agendaAdapter = new AgendaAdapter(getBaseContext(), agendaList);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                super.onFailure(i, headers, bytes, throwable);
                Log.d("ERROR ", throwable.getMessage());
            }

            @Override
            public void onFinish() {
                super.onFinish();

                lvAgenda = (ListView) findViewById(R.id.lv_agenda);
                lvAgenda.setAdapter(agendaAdapter);
                lvAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(AgendaActivity.this, AgendaDetalleActivity.class);
                        i.putExtra("agenda", agendaList.get(position));
                        startActivity(i);
                    }
                });
            }
        };
        agendas.obtenerAgendaCultural();

    }

    @Override
    protected int getResLayout() {
        return R.layout.agenda_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {

    }
}
