package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b_tree.telartes.Entidades.Convocatoria;
import com.b_tree.telartes.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.ConvocatoriaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.ConvocatoriaService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 22-02-16.
 */
public class ConvocatoriaActivity extends BaseTelartesActivity {

    private ListView lvConvocatoria;
    private ConvocatoriaAdapter convocatoriaAdapter;
    private ConvocatoriaService convocatoriaService;
    private List<Convocatoria> convocatoriaList;

    @Override
    protected String getScreenLabel() {
        return "CONVOCATORIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        convocatoriaList = new ArrayList<>();
        lvConvocatoria = (ListView) findViewById(R.id.lv_convocatoria);
        convocatoriaList = Global.getMiglobal().getDaosession().getConvocatoriaDao().loadAll();
        if (convocatoriaList.isEmpty()){

            convocatoriaService = new ConvocatoriaService(this) {
                @Override
                public void onSuccessObtenerConvocatoria(List<Convocatoria> convocatorialist) {
                    convocatoriaList = convocatorialist;
                    Global.getMiglobal().getDaosession().getConvocatoriaDao().insertInTx(convocatorialist);
                    convocatoriaAdapter = new ConvocatoriaAdapter(getBaseContext(), convocatoriaList);
                }

                @Override
                public void onFinish() {

                    lvConvocatoria.setAdapter(convocatoriaAdapter);
                    lvConvocatoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(ConvocatoriaActivity.this, ConvocatoriaDetalleActivity.class);
                            i.putExtra("convocatoria", convocatoriaList.get(position));
                            startActivity(i);
                        }
                    });

                }
            };
            convocatoriaService.obtenerConvocatorias();

        }else{
            convocatoriaAdapter = new ConvocatoriaAdapter(getBaseContext(), convocatoriaList);
            lvConvocatoria.setAdapter(convocatoriaAdapter);
            lvConvocatoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(ConvocatoriaActivity.this, ConvocatoriaDetalleActivity.class);
                    i.putExtra("convocatoria", convocatoriaList.get(position));
                    startActivity(i);
                }
            });
        }

    }

    @Override
    protected int getResLayout() {
        return R.layout.convocatoria_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        lvConvocatoria = (ListView) findViewById(R.id.lv_convocatoria);
        lvConvocatoria.setAdapter(convocatoriaAdapter);
        lvConvocatoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ConvocatoriaActivity.this, ConvocatoriaDetalleActivity.class);
                i.putExtra("convocatoria", convocatoriaList.get(position));
                startActivity(i);
            }
        });

    }}
