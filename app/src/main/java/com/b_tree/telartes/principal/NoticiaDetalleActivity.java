package com.b_tree.telartes.principal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.b_tree.telartes.Entidades.Noticia;
import com.b_tree.telartes.R;
import com.b_tree.telartes.adapter.NoticiaDetalleAdapter;
import com.b_tree.telartes.base.BaseTelartesActivity;
import com.b_tree.telartes.base.Global;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.lucasr.twowayview.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by noemi on 21-02-16.
 */
public class NoticiaDetalleActivity extends BaseTelartesActivity {

    private ImageView imgShare;
    private ImageView menuInfo;
    private  DrawerLayout drawerLayout;
    private LinearLayout linear_autor;
    public List<Noticia> noticiasList;
    @Override
    protected String getScreenLabel() {
        return "NOTICIAS";
    }

    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        imgShare = (ImageView)findViewById(R.id.img_share);
        menuInfo = (ImageView)findViewById(R.id.menu_info);

        Intent i = getIntent();
        int indice = (Integer)i.getIntExtra("noticia",0);

        noticiasList = Global.getMiglobal().getDaosession().getNoticiaDao().loadAll();
        NoticiaDetalleAdapter noticiaadapter = new NoticiaDetalleAdapter(getBaseContext(), noticiasList, indice);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.lvItems);
        lvTest.setAdapter(noticiaadapter);
        imgShare.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imgShare.setBackgroundColor(getResources().getColor(R.color.plomo));
                        imgShare.setImageDrawable(getResources().getDrawable(R.mipmap.compartir_hover));
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Este texto se enviara.");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;
                    case MotionEvent.ACTION_UP:
                        imgShare.setBackgroundColor(getResources().getColor(R.color.blanco));
                        imgShare.setImageDrawable(getResources().getDrawable(R.mipmap.compartir));

                }
                return true;
            }
        });
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

    }

    @Override
    protected int getResLayout() {
        return R.layout.noticia_detalle_activity;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {


    }

    public void setupFacebookShareIntent() {

        ShareDialog shareDialog;

        FacebookSdk.sdkInitialize(getApplicationContext());

        shareDialog = new ShareDialog(this);



        ShareLinkContent linkContent = new ShareLinkContent.Builder()

                .setContentTitle("Title")

                .setContentDescription(

                        "\"Body Of Test Post\"")

                .setContentUrl(Uri.parse("http://someurl.com/here"))

                .build();
        shareDialog.show(linkContent);

    }


}
