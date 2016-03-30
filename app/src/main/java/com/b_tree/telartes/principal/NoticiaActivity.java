package com.b_tree.telartes.principal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.b_tree.telartes.Entidades.Actualizacion;
import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.DaoSession;
import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.NoticiaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.NoticiasService;
import com.b_tree.telartes.rest.NuevaNoticiaService;
import com.b_tree.telartes.rest.RestClientJar;

import org.apache.http.Header;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NoticiaActivity extends BaseTelartesActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ListView lvNoticias;
    private SwipeRefreshLayout swipeLayout;
    private NoticiaAdapter noticiaAdapter;
    private NoticiasService noticiasService;
    private NuevaNoticiaService nuevaNoticiaService;
    public List<Noticia> noticiasList;
    private ImageView menu_filter;
    private ImageView menuSetting;
    private Point p;
    private ActualizacionDao actualizacionDao;
    private static final String LIST_FRAGMENT_TAG = "list_fragment";
    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        noticiasList = new ArrayList<>();
        lvNoticias = (ListView) findViewById(R.id.lv_noticias);
        menu_filter = (ImageView) findViewById(R.id.image_menu);
        menuSetting = (ImageView) findViewById(R.id.menu_setting);
        actualizacionDao = Global.getMiglobal().getDaosession().getActualizacionDao();
        menuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p != null)
                    showPopup(NoticiaActivity.this, p);
            }
        });
        if (RestClientJar.getInternetState(this)) {
            List<Actualizacion> actNoticia = new ArrayList<>();
            actNoticia = (actualizacionDao.queryBuilder().where(ActualizacionDao.Properties.Nombre.eq("Noticia"))).list();
            final int idNoticiaActual = actNoticia.get(0).getIdActual();
            final List<Actualizacion> finalActNoticia = actNoticia;
            nuevaNoticiaService = new NuevaNoticiaService(this) {
                @Override
                public void onSuccessObtenerNuevaNoticias(Long nid) {
                    if (nid > idNoticiaActual) {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
                        String dateNow = dateformat.format(c.getTime());
                        actualizacionDao.deleteByKey(finalActNoticia.get(0).getId());
                        actualizacionDao.insertOrReplace(new Actualizacion(null, "Noticia", dateNow, Math.round(nid)));
                        ConsumirNoticias();
                    } else {
                        noticiasList = Global.getMiglobal().getDaosession().getNoticiaDao().loadAll();
                        noticiaAdapter = new NoticiaAdapter(getBaseContext(), noticiasList);
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
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    super.onFailure(i, headers, bytes, throwable);
                    Log.d("Error al actualizar ", throwable.getMessage());
                }


            };
            nuevaNoticiaService.ObtenerNuevaNoticias();
        } else {
            noticiasList = Global.getMiglobal().getDaosession().getNoticiaDao().loadAll();
            noticiaAdapter = new NoticiaAdapter(getBaseContext(), noticiasList);
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


        menu_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleList();

            }
        });
    }
     //   swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
     //   swipeLayout.setOnRefreshListener(this);
    public void ConsumirNoticias(){
        noticiasService = new NoticiasService(getApplicationContext()) {
            @Override
            public void onSuccessObtenerNoticias(List<Noticia> noticias) {
                noticiasList = noticias;
                Global.getMiglobal().getDaosession().getNoticiaDao().insertInTx(noticias);
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
        noticiasService.ObtenerNoticias();
    }


    @Override public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
             //   swipeLayout.setRefreshing(false);
            }
        }, 5000);
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
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        ImageView button = (ImageView) findViewById(R.id.menu_setting);
        button.getLocationOnScreen(location);
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    private void showPopup(final Activity context, Point p) {
        int popupWidth = 600;
        int popupHeight = 900;
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.menu_pop);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.menu, viewGroup);
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);
//        popup.setAnimationStyle();

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 110;
        int OFFSET_Y = 110;

        // Clear the default translucent background
        //popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.btn_menu_close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    @SuppressLint("NewApi")
    private void toggleList() {
        Fragment f = getFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
        if (f != null) {
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_up,R.anim.slide_down)
                    .add(R.id.list_fragment_container, SlidingListFragment
                                    .instantiate(this, SlidingListFragment.class.getName()),
                            LIST_FRAGMENT_TAG
                    ).addToBackStack(null).commit();
        }
    }
}
