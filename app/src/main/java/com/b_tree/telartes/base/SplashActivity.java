package com.b_tree.telartes.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.b_tree.telartes.R;
import com.b_tree.telartes.principal.PrincipalActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class SplashActivity extends BaseTelartesActivity {
    @Override
    protected String getScreenLabel() {
        return null;
    }



    @Override
    protected void inicializarVariables(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
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
