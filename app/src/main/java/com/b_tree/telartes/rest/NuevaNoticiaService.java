package com.b_tree.telartes.rest;

import android.content.Context;
import android.util.Log;

import com.b_tree.telartes.Entidades.Noticia;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 18-03-16.
 */
public abstract class NuevaNoticiaService  extends ClasesRest {

    public NuevaNoticiaService(Context context) {
        super(context);

    }

    @Override
    protected String getURL() {
        // TODO Auto-generated method stub
        return "http://telartes.org.bo/rest/views/";
    }

    @Override
    protected String getMetod() {
        // TODO Auto-generated method stub
        return "nuevanoticiarest";
    }



    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        Log.d("ERROR", bytes.toString());
    }

    public void ObtenerNuevaNoticias() {

        callToPostWithutParameterMetods(this);
    }


    @Override
    public void onSuccess(int i, Header[] headers, byte[] bytes) {
        try {
            JSONArray jsonArray =  new JSONArray(new String(bytes));
            JSONObject  object = jsonArray.getJSONObject(0);
            onSuccessObtenerNuevaNoticias(Long.valueOf(object.getString("nid")));

        } catch (Exception e) {
            Log.d("error  nuevas noticias", e.getMessage());
        }
    }



    @Override
    protected void onErrorLogico(String error) {

    }

    public abstract void onSuccessObtenerNuevaNoticias(Long nid);
}
