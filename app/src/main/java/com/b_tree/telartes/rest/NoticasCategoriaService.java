package com.b_tree.telartes.rest;

import android.content.Context;
import android.util.Log;

import com.b_tree.telartes.Entidades.AgendaCultural;
import com.b_tree.telartes.Entidades.Categoria;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noemi on 17-03-16.
 */
public abstract class NoticasCategoriaService  extends  ClasesRest{

    private List<Categoria> listaCategorias;

    public NoticasCategoriaService(Context context) {
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
        return "categorianoticiarest";
    }


    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        Log.d("ERROR", bytes.toString());
    }

    public void obtenerCategoria() {

        callToPostWithutParameterMetods(this);
    }


    @Override
    public void onSuccess(int i, Header[] headers, byte[] bytes) {
        try {
            listaCategorias = new ArrayList<>();
            JSONArray jsonArray =  new JSONArray(new String(bytes));
            for (int c = 0; c <jsonArray.length() ; c++) {
                listaCategorias.add(new Categoria(jsonArray.getJSONObject(c)));
            }
            onSuccessObtenerCategoriaNoticia(listaCategorias,jsonArray);

        } catch (Exception e) {
            Log.d("no consume categorias", e.getMessage());
        }
    }

    @Override
    protected void onErrorLogico(String error) {

    }

    public abstract void onSuccessObtenerCategoriaNoticia(List<Categoria> listaCategorias,JSONArray categoriaList) throws JSONException;
}

