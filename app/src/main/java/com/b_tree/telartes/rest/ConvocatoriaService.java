package com.b_tree.telartes.rest;

import android.content.Context;
import android.util.Log;

import com.b_tree.telartes.Entidades.Convocatoria;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 23-02-16.
 */
public  abstract  class ConvocatoriaService  extends ClasesRest {
    private List<Convocatoria> listaConvocatoria;

    public ConvocatoriaService(Context context) {
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
        return "convocatoriasrest";
    }


    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

    }

    public void obtenerConvocatorias() {

        callToPostWithutParameterMetods(this);
    }


    @Override
    public void onSuccess(int i, Header[] headers, byte[] bytes) {
        try {
            listaConvocatoria = new ArrayList<>();
            JSONArray jsonArray =  new JSONArray(new String(bytes));
            for (int c = 0; c <jsonArray.length() ; c++) {
                listaConvocatoria.add(new Convocatoria(jsonArray.getJSONObject(c)));
            }
            onSuccessObtenerConvocatoria(listaConvocatoria);

        } catch (Exception e) {
            Log.d("no consume noticias", e.getMessage());
        }
    }



    @Override
    protected void onErrorLogico(String error) {

    }

    public abstract void onSuccessObtenerConvocatoria(List<Convocatoria> convocatorialist);
}
