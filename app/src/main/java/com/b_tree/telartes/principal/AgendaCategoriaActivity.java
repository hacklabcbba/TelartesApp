package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.Header;
import com.b_tree.telartes.adapter.Item;
import com.b_tree.telartes.adapter.ListItem;
import com.b_tree.telartes.adapter.CategoriaArrayAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 03-03-16.
 */
public class AgendaCategoriaActivity extends BaseTelartesActivity {
    private ListView listFilter;
    private LinearLayout verAgenda;
    @Override
    protected String getScreenLabel() {
        return null;
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        listFilter = (ListView)findViewById(R.id.list_filter);
        verAgenda = (LinearLayout)findViewById(R.id.ver_agenda);
        List<Item> items = new ArrayList<Item>();
        int color = 2;
        items.add(new Header("Filtro por categorías:"));
        items.add(new ListItem("CONCURSO", color));
        items.add(new ListItem("MOVILIDAD", color));
        items.add(new ListItem("OPORTUNIDAD DE TRABAJO", color));
        items.add(new Header("Filtro por departamento:"));
        items.add(new ListItem("INTERNACIONAL", color));
        items.add(new ListItem("NACIONAL", color));
        items.add(new ListItem("BENI", color));
        items.add(new ListItem("COCHABAMBA", color));
        items.add(new ListItem("LA PAZ", color));
        items.add(new ListItem("ORURO", color));
        items.add(new ListItem("PANDO", color));
        items.add(new ListItem("POTOSÍ", color));
        items.add(new ListItem("SANTA CRUZ", color));
        items.add(new ListItem("SUCRE", color));
        items.add(new ListItem("TARIJA", color));
        items.add(new Header("Filtro por áreas:"));
        items.add(new ListItem("CULTURA LIBRE", color));
        items.add(new ListItem("INVESTIGACIÓN", color));
        items.add(new ListItem("LITERARIAS", color));
        items.add(new ListItem("MUSICALES", color));
        items.add(new ListItem("VISUALES", color));
        items.add(new ListItem("ESCÉNICAS(DANZA)", color));
        items.add(new ListItem("PATRIMONIO CULTURAL INMATERIAL", color));
        items.add(new ListItem("PLASTICAS", color));
        items.add(new ListItem("HISTORIA", color));
        items.add(new ListItem("ARTES POPULARES", color));
        items.add(new ListItem("PATRIMONIO CULTURAL MATERIAL", color));
        items.add(new ListItem("GESTIÓN CULTURAL", color));
        items.add(new ListItem("ARTE URBANO", color));
        items.add(new ListItem("OTRA", color));

        CategoriaArrayAdapter adapter = new CategoriaArrayAdapter(this, items);
        listFilter.setAdapter(adapter);
        verAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AgendaCategoriaActivity.this,AgendaActivity.class));
            }
        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.agenda_categoria_layout;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {

    }
}
