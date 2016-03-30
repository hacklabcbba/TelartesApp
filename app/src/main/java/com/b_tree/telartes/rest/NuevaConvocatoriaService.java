package com.b_tree.telartes.rest;

import android.content.Context;
import android.util.Log;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by noemi on 30-03-16.
 */
public abstract class NuevaConvocatoriaService extends  ClasesRest {

    public NuevaConvocatoriaService(Context context) {
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
        return "nuevaconvocatoriarest";
    }



    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        Log.d("ERROR", bytes.toString());
    }

    public void ObtenerNuevasConvocatorias() {

        callToPostWithutParameterMetods(this);
    }


    @Override
    public void onSuccess(int i, Header[] headers, byte[] bytes) {
        try {
            JSONArray jsonArray =  new JSONArray(new String(bytes));
            JSONObject object = jsonArray.getJSONObject(0);
            onSuccessObtenerNuevaConvocatoria(Long.valueOf(object.getString("nid")));

        } catch (Exception e) {
            Log.d("error  convocatorias", e.getMessage());
        }
    }



    @Override
    protected void onErrorLogico(String error) {

    }

    public abstract void onSuccessObtenerNuevaConvocatoria(Long nid);
}
