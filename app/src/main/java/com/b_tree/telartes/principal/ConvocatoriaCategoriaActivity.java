package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.Item;
import com.b_tree.telartes.base.BaseTelartesActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 03-03-16.
 */
public class ConvocatoriaCategoriaActivity extends BaseTelartesActivity {
    private ListView listFilter;
    private LinearLayout verConvocatoria;
    @Override
    protected String getScreenLabel() {
        return null;
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        listFilter = (ListView)findViewById(R.id.list_filter);
        verConvocatoria = (LinearLayout)findViewById(R.id.ver_convocatoria);
        List<Item> items = new ArrayList<Item>();
        int color = 3;
//        items.add(new SectionItem("Filtro por categorías:"));
//        items.add(new EntryItem("CONCURSO", color));
//        items.add(new EntryItem("MOVILIDAD", color));
//        items.add(new EntryItem("OPORTUNIDAD DE TRABAJO", color));
//        items.add(new SectionItem("Filtro por departamento:"));
//        items.add(new EntryItem("INTERNACIONAL", color));
//        items.add(new EntryItem("NACIONAL", color));
//        items.add(new EntryItem("BENI", color));
//        items.add(new EntryItem("COCHABAMBA", color));
//        items.add(new EntryItem("LA PAZ", color));
//        items.add(new EntryItem("ORURO", color));
//        items.add(new EntryItem("PANDO", color));
//        items.add(new EntryItem("POTOSÍ", color));
//        items.add(new EntryItem("SANTA CRUZ", color));
//        items.add(new EntryItem("SUCRE", color));
//        items.add(new EntryItem("TARIJA", color));
//        items.add(new SectionItem("Filtro por áreas:"));
//        items.add(new EntryItem("CULTURA LIBRE", color));
//        items.add(new EntryItem("INVESTIGACIÓN", color));
//        items.add(new EntryItem("LITERARIAS", color));
//        items.add(new EntryItem("MUSICALES", color));
//        items.add(new EntryItem("VISUALES", color));
//        items.add(new EntryItem("ESCÉNICAS(DANZA)", color));
//        items.add(new EntryItem("PATRIMONIO CULTURAL INMATERIAL", color));
//        items.add(new EntryItem("PLASTICAS", color));
//        items.add(new EntryItem("HISTORIA", color));
//        items.add(new EntryItem("ARTES POPULARES", color));
//        items.add(new EntryItem("PATRIMONIO CULTURAL MATERIAL", color));
//        items.add(new EntryItem("GESTIÓN CULTURAL", color));
//        items.add(new EntryItem("ARTE URBANO", color));
//        items.add(new EntryItem("OTRA", color));

      //  CategoriaArrayAdapter adapter = new CategoriaArrayAdapter(this, items);
        listFilter.setAdapter(null);
        verConvocatoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConvocatoriaCategoriaActivity.this, ConvocatoriaActivity.class));
            }
        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.convocatoria_categoria_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {

    }
}
