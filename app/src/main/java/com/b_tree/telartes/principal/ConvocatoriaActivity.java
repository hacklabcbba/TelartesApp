package com.b_tree.telartes.principal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

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
    private ImageView menu_filter;
    private ImageView menuSetting;
    private  Point p;
    @Override
    protected String getScreenLabel() {
        return "CONVOCATORIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        convocatoriaList = new ArrayList<>();
        lvConvocatoria = (ListView) findViewById(R.id.lv_convocatoria);
        menu_filter = (ImageView)findViewById(R.id.image_menu);
        menuSetting = (ImageView)findViewById(R.id.menu_setting);
        menuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p != null)
                    showPopup(ConvocatoriaActivity.this, p);
            }
        });
        menu_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ConvocatoriaActivity.this, ConvocatoriaCategoriaActivity.class);
                startActivity(i);
            }
        });
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
}
