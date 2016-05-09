package com.b_tree.telartes.rest;

import android.content.Context;
import android.util.Log;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by noemi
 */
public abstract  class NuevaAgendaService extends  ClasesRest {

    public NuevaAgendaService(Context context) {
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
        return "nuevaagendarest";
    }



    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        Log.d("ERROR", "No se puede consumir nuevas agendas");
    }

    public void obtenerNuevaAgenda() {

        callToPostWithutParameterMetods(this);
    }

    @Override
    public void onSuccess(int i, Header[] headers, byte[] bytes) {
        try {
            JSONArray jsonArray =  new JSONArray(new String(bytes));
            JSONObject object = jsonArray.getJSONObject(0);
            onSuccessObtenerNuevaAgenda(Long.valueOf(object.getString("nid")));

        } catch (Exception e) {
            Log.d("error  agenda", e.getMessage());
        }
    }

    @Override
    protected void onErrorLogico(String error) {

    }

    public abstract void onSuccessObtenerNuevaAgenda(Long nid);
}
