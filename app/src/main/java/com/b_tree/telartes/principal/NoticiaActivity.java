package com.b_tree.telartes.principal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.NoticiaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.NoticiasService;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diana on 27/09/2015.
 */
public class NoticiaActivity extends BaseTelartesActivity {
    private ListView lvNoticias;
    private NoticiaAdapter noticiaAdapter;
    private NoticiasService noticias;
    private List<Noticia> noticiasList;


    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        noticiasList = new ArrayList<>();
        noticias = new NoticiasService(this) {
            @Override
            public void onSuccessObtenerNoticias(List<Noticia> noticias) {
                noticiasList = new ArrayList<>();
                noticiasList = noticias;
                noticiaAdapter = new NoticiaAdapter(getBaseContext(), noticias);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                super.onFailure(i, headers, bytes, throwable);
                Log.d("ERROR ", throwable.getMessage());
            }

            @Override
            public void onFinish() {
                super.onFinish();

                lvNoticias = (ListView) findViewById(R.id.lv_noticias);
                lvNoticias.setAdapter(noticiaAdapter);
                lvNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(NoticiaActivity.this, NoticiaDetalleActivity.class);
                        i.putExtra("noticia", noticiasList.get(position));
                        startActivity(i);
                    }
                });
            }
        };
        noticias.ObtenerNoticias();

    }



    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {

    }



    @Override
    protected int getResLayout() {
        return R.layout.noticias_activity;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
//
//    public static interface OnNoticiaReadyListener {
//        void onNoticiaRead(Noticia noticia);
//    }

}
