package com.b_tree.telartes.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.b_tree.telartes.R;
import com.b_tree.telartes.principal.PrincipalActivity;

/**
 * Created by Diana on 26/09/2015.
 */
public class SplashActivity extends BaseTelartesActivity {
    @Override
    protected String getScreenLabel() {
        return null;
    }



    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {

    }

    @Override
    protected int getResLayout() {
        return R.layout.splash;
    }

    @Override
    protected void instaciarAsignarIGU(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, PrincipalActivity.class));
                finish();
            }
        }, 3000);
    }


}
