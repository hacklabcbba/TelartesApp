package com.b_tree.telartes.base;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

;import com.b_tree.telartes.Entidades.Actualizacion;
import com.b_tree.telartes.Entidades.ActualizacionDao;
import com.b_tree.telartes.Entidades.DaoSession;
import com.b_tree.telartes.Global;
import com.b_tree.telartes.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public abstract class BaseTelartesActivity extends FragmentActivity{
    TextView lblTitulo;
    View btnAtras;
    LinearLayout baner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransitionStart();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getResLayout());

        lblTitulo = (TextView) findViewById(R.id.lbl_titulo);
        baner = (LinearLayout)findViewById(R.id.baner);
        btnAtras = findViewById(R.id.btn_atras);
        if(btnAtras!=null){
        btnAtras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        }
        if(lblTitulo!=null) {

            lblTitulo.setText(getScreenLabel());
            if(lblTitulo.getText().equals("NOTICIAS")){
                btnAtras.setBackgroundColor(getResources().getColor(R.color.lila));
               // baner.setBackgroundColor(getResources().getColor(R.color.verde));

            }
            if(lblTitulo.getText().equals("AGENDA CULTURAL")){
                btnAtras.setBackgroundColor(getResources().getColor(R.color.verde));
                baner.setBackgroundColor(getResources().getColor(R.color.verde));

            }
            if(lblTitulo.getText().equals("CONVOCATORIAS")) {
                btnAtras.setBackgroundColor(getResources().getColor(R.color.rojo));
                baner.setBackgroundColor(getResources().getColor(R.color.rojo));
            }
        }
        iniciarActualizacion();

        inicializarVariables(savedInstanceState);

        instaciarAsignarIGU(savedInstanceState);

    }

    protected abstract String getScreenLabel();

    protected abstract void inicializarVariables(Bundle savedInstanceState);


    protected void overridePendingTransitionStart() {
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    protected abstract int getResLayout();

    protected abstract void instaciarAsignarIGU(Bundle savedInstanceState);

   

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionEnd();
    }

    private void overridePendingTransitionEnd() {
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void iniciarActualizacion(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        String dateNow = dateformat.format(c.getTime());
        ActualizacionDao actualizacionDao =  Global.getMiglobal().getDaosession().getActualizacionDao();
        if (actualizacionDao.loadAll().isEmpty()){
            actualizacionDao.insert(new Actualizacion( null,"Noticia",dateNow, 0));
            actualizacionDao.insert(new Actualizacion( null,"Agenda",dateNow, 0));
            actualizacionDao.insert(new Actualizacion( null,"Convocatoria",dateNow, 0));
            actualizacionDao.insert(new Actualizacion( null,"Categoria",dateNow, 0));
        }
    }


}
