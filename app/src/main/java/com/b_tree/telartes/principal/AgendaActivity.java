package com.b_tree.telartes.principal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Actualizacion;
import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.AgendaCultural;
import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.Entidades.NoticiaDao;
import com.b_tree.telartes.Utils.Utils;
import com.b_tree.telartes.adapter.NoticiaAdapter;
import com.b_tree.telartes.base.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.AgendaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.listeners.AgendaListener;
import com.b_tree.telartes.rest.AgendaService;
import com.b_tree.telartes.rest.NuevaAgendaService;
import com.b_tree.telartes.rest.RestClientJar;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import com.b_tree.telartes.listeners.NoticiaListener;

/**
 * Created by noemi on 22-02-16.
 */
public class AgendaActivity extends BaseTelartesActivity  implements View.OnClickListener, NoticiaListener, AgendaListener {

    private ListView lvAgenda;
    private AgendaAdapter agendaAdapter;
    private AgendaService agendas;
    private List<AgendaCultural> agendaList;
    private ImageView menu_filter;
    private ImageView menuInfo;
    private  DrawerLayout drawerLayout;
    private NuevaAgendaService nuevaAgendaService;
    private ActualizacionDao actualizacionDao;
    private static final String LIST_FRAGMENT_TAG = "AgendaFiltroFragment";
    @Override
    protected String getScreenLabel() {
        return "AGENDA CULTURAL";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        actualizacionDao = Global.getMiglobal().getDaosession().getActualizacionDao();
        agendaList = new ArrayList<>();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        lvAgenda = (ListView) findViewById(R.id.lv_agenda);
        menu_filter = (ImageView)findViewById(R.id.image_menu);
        menuInfo = (ImageView)findViewById(R.id.menu_info);
        menuInfo.setOnClickListener(this);
        menu_filter.setOnClickListener(this);
        verAgendaCultural();
    }

    private void verAgendaCultural(){
        if (RestClientJar.getInternetState(this)) {
            final List<Actualizacion> actAgenda ;
            actAgenda = Global.getMiglobal().getDaosession().getActualizacionDao().queryBuilder().where(ActualizacionDao.Properties.Nombre.eq("Agenda")).list();
            final Long idNoticiaActual = actAgenda.get(0).getIdActual();
            nuevaAgendaService = new NuevaAgendaService(this) {
                @Override
                public void onSuccessObtenerNuevaAgenda(Long nid) {
                    if (nid > idNoticiaActual) {
                        actualizarAgendaCultural();
                        actualizacionDao.deleteByKey(actAgenda.get(0).getId());
                        actualizacionDao.insertOrReplace(new Actualizacion(null, "Agenda", Utils.getFechaActual(), nid));
                    } else {
                        obtenerAgendaCulturlalBD();
                    }

                }
                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    obtenerAgendaCulturlalBD();
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    obtenerAgendaCulturlalBD();
                }
            };
            nuevaAgendaService.obtenerNuevaAgenda();
        } else {
            obtenerAgendaCulturlalBD();
        }
    }

    public void obtenerAgendaCulturlalBD(){
        agendaList = Global.getMiglobal().getDaosession().getAgendaCulturalDao().loadAll();
        if(!agendaList.isEmpty()){
            agendaAdapter = new AgendaAdapter(getBaseContext(), agendaList);
            lvAgenda.setAdapter(agendaAdapter);
            lvAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(AgendaActivity.this, AgendaDetalleActivity.class);
                    i.putExtra("agenda", agendaList.get(position));
                    startActivity(i);
                }
            });
        }else{
            LinearLayout listContent = (LinearLayout)findViewById(R.id.content_list);
            listContent.removeAllViews();
            View v = this.getLayoutInflater().inflate(R.layout.error_conection_noticias, null);
            listContent.addView(v);
        }
    }

    private void actualizarAgendaCultural(){
        agendas = new AgendaService(this) {
            @Override
            public void onSuccessObtenerAgenda(List<AgendaCultural> agendalist) {
                Global.getMiglobal().getDaosession().getAgendaCulturalDao().deleteAll();
                Global.getMiglobal().getDaosession().getAgendaCulturalDao().insertInTx(agendalist);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                super.onFailure(i, headers, bytes, throwable);
                obtenerAgendaCulturlalBD();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                obtenerAgendaCulturlalBD();
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



    @SuppressLint("NewApi")
    private void mostrarCategorias() {
        Fragment f = getFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
        if (f != null) {
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_up,R.anim.slide_down)
                    .add(R.id.list_fragment_container, AgendaFiltroFragment
                                    .instantiate(this, AgendaFiltroFragment.class.getName()),
                            LIST_FRAGMENT_TAG
                    ).addToBackStack(null).commit();
        }
    }

    @Override
    public void filtrodebusqueda(ArrayList<String> listfiltro) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu_info :
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.image_menu:
                mostrarCategorias();
                break;
        }
    }

    @Override
    public void filtroAgenda(ArrayList<String> listfiltro) {
        ArrayList<AgendaCultural> listAgendaAll = new ArrayList<>();
        String content = "";
        for (int i = 0; i < listfiltro.size(); i++) {
            if (!listfiltro.get(i).equals("Todas")) {

                agendaList = Global.getMiglobal().getDaosession().getAgendaCulturalDao().queryBuilder().where(NoticiaDao.Properties.Categoria.eq(listfiltro.get(i))).list();
                for (int j = 0; j <agendaList.size() ; j++) {
                    listAgendaAll.add(agendaList.get(j));
                }
            }else{
                content = "Todas";
                break;
            }
        }

        if((!listAgendaAll.isEmpty())){
            LinearLayout listContent = (LinearLayout)findViewById(R.id.content_list);
            listContent.removeAllViews();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (LinearLayout.LayoutParams.MATCH_PARENT), (LinearLayout.LayoutParams.MATCH_PARENT));
            ListView tv = new ListView(getApplicationContext());
            tv.setLayoutParams(lp);
            listContent.addView(tv);
            agendaAdapter = new AgendaAdapter(getBaseContext(), listAgendaAll);
            agendaAdapter.notifyDataSetChanged();
            tv.setAdapter(agendaAdapter);
            tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(AgendaActivity.this, AgendaDetalleActivity.class);
                    i.putExtra("noticia", agendaList.get(position));
                    startActivity(i);
                }
            });

        }else{
            if (!content.equals("Todas")){
                LinearLayout listContent = (LinearLayout)findViewById(R.id.content_list);
                listContent.removeAllViews();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        (LinearLayout.LayoutParams.MATCH_PARENT), (LinearLayout.LayoutParams.MATCH_PARENT));
                TextView tv = new TextView(getApplicationContext());
                tv.setLayoutParams(lp);
                tv.setTextColor(getResources().getColor(R.color.negro));
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setText("No hay resultados!!");
                listContent.addView(tv);
            }

        }
    }
}
