package com.b_tree.telartes.principal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
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

import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.Entidades.NoticiaDao;
import com.b_tree.telartes.Global;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.NoticiaAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.rest.NoticiasService;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * Created by Diana on 27/09/2015.
 */
public class NoticiaActivity extends BaseTelartesActivity {
    private ListView lvNoticias;
    private NoticiaAdapter noticiaAdapter;
    private NoticiasService noticias;
    public List<Noticia> noticiasList;
    private ImageView menu_filter;
    private ImageView menuSetting;
    Point p;
    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        noticiasList = new ArrayList<>();
        lvNoticias = (ListView) findViewById(R.id.lv_noticias);
        menu_filter = (ImageView)findViewById(R.id.image_menu);
        menuSetting = (ImageView)findViewById(R.id.menu_setting);
        menuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p != null)
                    showPopup(NoticiaActivity.this, p);
            }
        });
        menu_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(NoticiaActivity.this, NoticiaCategoriaActivity.class);
                startActivity(i);
            }
        });

        //global=
        noticiasList = Global.getMiglobal().getDaosession().getNoticiaDao().loadAll();
        if(noticiasList.isEmpty()){
            noticias = new NoticiasService(this) {
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
            noticias.ObtenerNoticias();
        }else{
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

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        button.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        int popupWidth = 600;
        int popupHeight = 900;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.menu_pop);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.menu, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

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
//
//    public static interface OnNoticiaReadyListener {
//        void onNoticiaRead(Noticia noticia);
//    }

}
