package com.b_tree.telartes.principal;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.b_tree.telartes.Entidades.Actualizacion;
import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.Entidades.NoticiaDao;
import com.b_tree.telartes.Utils.Utils;
import com.b_tree.telartes.base.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.NoticiaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.NoticiasService;
import com.b_tree.telartes.rest.NuevaNoticiaService;
import com.b_tree.telartes.rest.RestClientJar;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import com.b_tree.telartes.listeners.NoticiaListener;



public class NoticiaActivity extends BaseTelartesActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener , NoticiaListener {
    private ListView lvNoticias;
    private SwipeRefreshLayout swipeLayout;
    private NoticiaAdapter noticiaAdapter;
    private NoticiasService noticiasService;
    private NuevaNoticiaService nuevaNoticiaService;
    public List<Noticia> noticiasList;
    private ImageView menu_filter;
    private ImageView menuSetting;
    private ImageView menuInfo;
    private TextView filterSelect;
    private  DrawerLayout drawerLayout;
    private ActualizacionDao actualizacionDao;

    private static final String LIST_FRAGMENT_TAG = "NoticiasFiltroFragment";
    private  List<Actualizacion> actNoticia;
    private long  valornid;



    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        lvNoticias = (ListView) findViewById(R.id.lv_noticias);
        menu_filter = (ImageView) findViewById(R.id.image_menu);
        menuInfo = (ImageView) findViewById(R.id.menu_info);
        filterSelect = (TextView)findViewById(R.id.text_select);
        menuInfo.setOnClickListener(this);
        menu_filter.setOnClickListener(this);
        actualizacionDao = Global.getMiglobal().getDaosession().getActualizacionDao();
        noticiasList = new ArrayList<>();


    }
    private  void verNoticias(){
        if (RestClientJar.getInternetState(this)) {
            actNoticia = new ArrayList<>();
            actNoticia = (actualizacionDao.queryBuilder().where(ActualizacionDao.Properties.Nombre.eq("Noticia"))).list();
            final Long idNoticiaActual = actNoticia.get(0).getIdActual();
            nuevaNoticiaService = new NuevaNoticiaService(this) {

                @Override
                public void onSuccessObtenerNuevaNoticias(Long nid) {
                    Toast.makeText(getBaseContext(),"prueba succes", Toast.LENGTH_LONG).show();
                    final ProgressDialog progresNoticias;
                    progresNoticias = new ProgressDialog(getBaseContext());
                    progresNoticias.setMax(100);
                    progresNoticias.setTitle("Actualizando...");
                    progresNoticias.setMessage("Espere un momento...");
                    progresNoticias.setCancelable(false);
                    progresNoticias.setIndeterminate(true);
                    progresNoticias.show();
                    valornid= nid;
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    obtenerNoticiasBD();
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    Toast.makeText(getBaseContext(),"prueba finish", Toast.LENGTH_LONG).show();

                    if (valornid > idNoticiaActual) {
                        actualizarNoticias();
                        actualizacionDao.deleteByKey(actNoticia.get(0).getId());
                        actualizacionDao.insertOrReplace(new Actualizacion(null, "Noticia", Utils.getFechaActual(), valornid));
                    }else{
                        obtenerNoticiasBD();
                    }

                }
            };
            nuevaNoticiaService.ObtenerNuevaNoticias();
        } else {
           obtenerNoticiasBD();
        }

    }
    private void actualizarNoticias(){
        noticiasService = new NoticiasService(this) {
            @Override
            public void onSuccessObtenerNoticias(List<Noticia> noticias) {
                Global.getMiglobal().getDaosession().getNoticiaDao().deleteAll();
                Global.getMiglobal().getDaosession().getNoticiaDao().insertInTx(noticias);
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                obtenerNoticiasBD();
            }
            @Override
            public void onFinish() {
                super.onFinish();
                obtenerNoticiasBD();

            }
        };
        noticiasService.ObtenerNoticias();
    }

    public void obtenerNoticiasBD(){
        noticiasList = Global.getMiglobal().getDaosession().getNoticiaDao().loadAll();
        if(!noticiasList.isEmpty()){
            noticiaAdapter = new NoticiaAdapter(getBaseContext(), noticiasList);
            lvNoticias.setAdapter(noticiaAdapter);
            lvNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(NoticiaActivity.this, NoticiaDetalleActivity.class);
                    i.putExtra("noticia", position);
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


    @Override public void onRefresh() {

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

        verNoticias();


    }


    @SuppressLint("NewApi")
    private void mostrarCategorias() {
        Fragment f = getFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
        if (f != null) {
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_up,R.anim.slide_down)
                    .add(R.id.list_fragment_container, NoticiasFiltroFragment
                                    .instantiate(this, NoticiasFiltroFragment.class.getName()),
                            LIST_FRAGMENT_TAG
                    ).addToBackStack(null).commit();
        }

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
    public void filtrodebusqueda(ArrayList<String> listfiltro) {
        ArrayList<Noticia> listNoticiasAll = new ArrayList<>();
        List<Noticia> noticiasListCategoria=  new ArrayList<>();
        String  filter="Filtrado por: ";
        String content = "";
        for (int i = 0; i < listfiltro.size(); i++) {
            if (!listfiltro.get(i).equals("Todas")) {
                //noticiasListCategoria = Global.getMiglobal().getDaosession().getNoticiaDao().queryBuilder().where(NoticiaDao.Properties.Categoria.eq(listfiltro.get(i))).list();
                noticiasList = Global.getMiglobal().getDaosession().getNoticiaDao().queryBuilder().where(NoticiaDao.Properties.Categoria.eq(listfiltro.get(i))).list();
                for (int j = 0; j <noticiasList.size() ; j++) {
                    listNoticiasAll.add(noticiasList.get(j));
                    filter +=noticiasList.get(j).getCategoria()+ ", " ;
                }
            }else{
                content = "Todas";
                filter += content;
                break;
            }
        }

        if((!listNoticiasAll.isEmpty())){
            LinearLayout listContent = (LinearLayout)findViewById(R.id.content_list);
            listContent.removeAllViews();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (LinearLayout.LayoutParams.MATCH_PARENT), (LinearLayout.LayoutParams.MATCH_PARENT));
            ListView tv = new ListView(getApplicationContext());
            tv.setLayoutParams(lp);
            listContent.addView(tv);
            noticiaAdapter = new NoticiaAdapter(getBaseContext(), listNoticiasAll);
            noticiaAdapter.notifyDataSetChanged();
            tv.setAdapter(noticiaAdapter);
            tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(NoticiaActivity.this, NoticiaDetalleActivity.class);
                    i.putExtra("noticia", position);
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
                tv.setTextColor(getApplicationContext().getResources().getColor(R.color.lila));
                listContent.addView(tv);
            }

        }
        filterSelect.setText(filter);
        filterSelect.setVisibility(View.VISIBLE);

    }
}
