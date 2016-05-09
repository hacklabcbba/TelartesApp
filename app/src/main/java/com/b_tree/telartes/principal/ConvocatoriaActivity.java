package com.b_tree.telartes.principal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.b_tree.telartes.Entidades.Actualizacion;
import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.Convocatoria;
import com.b_tree.telartes.Utils.Utils;
import com.b_tree.telartes.adapter.NoticiaAdapter;
import com.b_tree.telartes.base.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.ConvocatoriaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.listeners.ConvocatoriaListener;
import com.b_tree.telartes.rest.ConvocatoriaService;
import com.b_tree.telartes.rest.NuevaConvocatoriaService;
import com.b_tree.telartes.rest.RestClientJar;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 22-02-16.
 */
public class ConvocatoriaActivity extends BaseTelartesActivity implements ConvocatoriaListener {

    private ListView lvConvocatoria;
    private ConvocatoriaAdapter convocatoriaAdapter;
    private ConvocatoriaService convocatoriaService;
    private List<Convocatoria> convocatoriaList;
    private ImageView menu_filter;

    private ImageView menuInfo;
    private  DrawerLayout drawerLayout;
    private static final String LIST_FRAGMENT_TAG = "ConvocatoriaFiltroFragment";
    private  List<Actualizacion> actualizarConvocatoria;
    private long  valornid;
    private NuevaConvocatoriaService nuevaConvocatoria;
    private ActualizacionDao actualizacionDao;
    @Override
    protected String getScreenLabel() {
        return "CONVOCATORIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        convocatoriaList = new ArrayList<>();
        lvConvocatoria = (ListView) findViewById(R.id.lv_convocatoria);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        menu_filter = (ImageView)findViewById(R.id.image_menu);
        menuInfo = (ImageView)findViewById(R.id.menu_info);
        menu_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarCategorias();

            }
        });
        actualizacionDao = Global.getMiglobal().getDaosession().getActualizacionDao();
        menuInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
         verConvocatorias();
    }
    private void  verConvocatorias (){
        if (RestClientJar.getInternetState(this)) {
            actualizarConvocatoria = new ArrayList<>();
            actualizarConvocatoria = (actualizacionDao.queryBuilder().where(ActualizacionDao.Properties.Nombre.eq("Convocatoria"))).list();
            final Long idConvocatoriaActual = actualizarConvocatoria.get(0).getIdActual();
            if (idConvocatoriaActual == 0){
                actualizarConvocatoria();
            }else{
                nuevaConvocatoria = new NuevaConvocatoriaService(this) {
                    @Override
                    public void onSuccessObtenerNuevaConvocatoria(Long nid) {
                        valornid= nid;
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        obtenerConvocatoriaBD();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        if (valornid > idConvocatoriaActual) {
                            actualizarConvocatoria();
                            actualizacionDao.deleteByKey(actualizarConvocatoria.get(0).getId());
                            actualizacionDao.insertOrReplace(new Actualizacion(null, "Convocatoria", Utils.getFechaActual(), valornid));
                        }else{
                            obtenerConvocatoriaBD();
                        }
                    }
                };
                nuevaConvocatoria.ObtenerNuevasConvocatorias();
            }

        }else{
            obtenerConvocatoriaBD();
        }

    }

    private void actualizarConvocatoria() {
            convocatoriaService = new ConvocatoriaService(this) {
                @Override
                public void onSuccessObtenerConvocatoria(List<Convocatoria> convocatorialist) {
                    Global.getMiglobal().getDaosession().getConvocatoriaDao().deleteAll();
                    Global.getMiglobal().getDaosession().getConvocatoriaDao().insertInTx(convocatorialist);
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    super.onFailure(i, headers, bytes, throwable);
                    obtenerConvocatoriaBD();
                }

                @Override
                public void onFinish() {
                   obtenerConvocatoriaBD();
                }
            };
            convocatoriaService.obtenerConvocatorias();
    }

    @Override
    protected int getResLayout() {
        return R.layout.convocatoria_activity;
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
                    .add(R.id.list_fragment_container, ConvocatoriaFiltroFragment
                                    .instantiate(this, ConvocatoriaFiltroFragment.class.getName()),
                            LIST_FRAGMENT_TAG
                    ).addToBackStack(null).commit();
        }

    }
    @Override
    public void filtroConvocatoria(ArrayList<String> listfiltro) {

    }

    public void obtenerConvocatoriaBD(){
        convocatoriaList = Global.getMiglobal().getDaosession().getConvocatoriaDao().loadAll();
        if(!convocatoriaList.isEmpty()){
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
        }else{
            LinearLayout listContent = (LinearLayout)findViewById(R.id.content_list);
            listContent.removeAllViews();
            View v = this.getLayoutInflater().inflate(R.layout.error_conection_noticias, null);
            listContent.addView(v);
        }
    }
}
