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
public class NoticiaCategoriaActivity extends BaseTelartesActivity {
    private ListView listFilter;
    private LinearLayout verNoticias;
    @Override
    protected String getScreenLabel() {
        return null;
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        listFilter = (ListView)findViewById(R.id.list_filter);
        verNoticias = (LinearLayout)findViewById(R.id.ver_noticias);
        List<Item> items = new ArrayList<Item>();
        int color = getResources().getColor(R.color.lila);
        items.add(new Header("Filtro por categoría:"));
        items.add(new ListItem("ARTICULACIONES", color));
        items.add(new ListItem("NOTICIAS NACIONALES", color));
        items.add(new ListItem("NOTICIAS INTERNACIONALES", color));
        items.add(new Header("Filtro por áreas:"));
        items.add(new ListItem("CULTURA VIVA COMUNITARIA", color));
        items.add(new ListItem("CULTURA DE RED", color));
        items.add(new ListItem("INCIDENCIA", color));
        items.add(new ListItem("FONRMACIÓN", color));
        items.add(new ListItem("SOSTENIBILIDAD", color));
        items.add(new ListItem("COMUNICACIÓN", color));
        items.add(new ListItem("CIRCULACÓN CULTURAL", color));
        items.add(new Header("Filtro por áreas:"));
        items.add(new ListItem("CULTURA LIBRE", color));
        items.add(new ListItem("INVESTIGACIÓN", color));
        items.add(new ListItem("LITERARIAS", color));
        items.add(new ListItem("MUSICALES", color));
        items.add(new ListItem("VISUALES", color));
        items.add(new ListItem("ESCÉNICAS(DANZA)", color));
        items.add(new ListItem("PATRIMONIO CULTURAL INMATERIAL", color));
        items.add(new ListItem("GESTIÓN CULTURAL", color));
        items.add(new ListItem("ARTE URBANO", color));
        items.add(new ListItem("OTRA", color));

        CategoriaArrayAdapter adapter = new CategoriaArrayAdapter(this, items);
        listFilter.setAdapter(adapter);
        verNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoticiaCategoriaActivity.this, NoticiaActivity.class));
            }
        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.noticia_categoria_layout;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {

    }
}
