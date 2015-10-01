package com.b_tree.telartes.base;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

;import com.b_tree.telartes.R;

/**
 * Created by Diana on 26/09/2015.
 */
public abstract class BaseTelartesActivity extends FragmentActivity{
    TextView lblTitulo;
    View btnAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransitionStart();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getResLayout());

        lblTitulo = (TextView) findViewById(R.id.lbl_titulo);
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
        }
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


}
