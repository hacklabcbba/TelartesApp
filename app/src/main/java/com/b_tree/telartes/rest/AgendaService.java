package com.b_tree.telartes.rest;

import android.content.Context;
import android.util.Log;

import com.b_tree.telartes.Entidades.AgendaCultural;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 23-02-16.
 */
public abstract class AgendaService  extends ClasesRest {
    private List<AgendaCultural> listaAgenda;

    public AgendaService(Context context) {
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
        return "agendaculturalrest";
    }


    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        Log.d("ERROR", bytes.toString());
    }

    public void obtenerAgendaCultural() {

        callToPostWithutParameterMetods(this);
    }


    @Override
    public void onSuccess(int i, Header[] headers, byte[] bytes) {
        try {
            listaAgenda = new ArrayList<>();
            JSONArray jsonArray =  new JSONArray(new String(bytes));
            for (int c = 0; c <jsonArray.length() ; c++) {
                listaAgenda.add(new AgendaCultural(jsonArray.getJSONObject(c)));
            }
            onSuccessObtenerAgenda(listaAgenda);

        } catch (Exception e) {
            Log.d("no consume agenda", e.getMessage());
        }
    }



    @Override
    protected void onErrorLogico(String error) {

    }

    public abstract void onSuccessObtenerAgenda(List<AgendaCultural> agendalist);
}

