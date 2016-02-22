package com.b_tree.telartes.principal;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b_tree.telartes.Entidades.AgendaCultural;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.AgendaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.NoticiasService;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by noemi on 22-02-16.
 */
public class AgendaActivity extends BaseTelartesActivity {

    private ListView lvAgenda;
    private AgendaAdapter agendaAdapter;
    private NoticiasService noticias;
    private List<AgendaCultural> agendaList;


    @Override
    protected String getScreenLabel() {
        return "AGENDA CULTURAL";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {


        try{
            JSONArray json = new JSONArray(new String(""));
        for (int c = 0; c <json.length() ; c++) {
            agendaList.add(new AgendaCultural(json.getJSONObject(c)));
        }
        agendaAdapter = new AgendaAdapter(getBaseContext(), agendaList);
        }catch (Exception e){

        }

    }

    @Override
    protected int getResLayout() {
        return R.layout.agenda_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        lvAgenda = (ListView) findViewById(R.id.lv_agenda);
        lvAgenda.setAdapter(agendaAdapter);
        lvAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //listener.onNoticiaRead(new Noticia());
            }
        });

    }
}
