package com.b_tree.telartes.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.b_tree.telartes.Entidades.Noticia;

public abstract class NoticiasService extends ClasesRest {
	private List<Noticia> listaNoticias;
	Context context;
	public NoticiasService(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected String getURL() {
		// TODO Auto-generated method stub
		return "http://telartes.org.bo/rest/views/";
	}

	@Override
	protected String getMetod() {
		// TODO Auto-generated method stub
		return "noticiasrest";
	}



	@Override
	public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("ERROR" , throwable.getMessage());

	}

	public void ObtenerNoticias() {

		callToPostWithutParameterMetods(this);
	}


	@Override
	public void onSuccess(int i, Header[] headers, byte[] bytes) {
		try {

			listaNoticias = new ArrayList<>();
			JSONArray jsonArray =  new JSONArray(new String(bytes));
			for (int c = 0; c <jsonArray.length() ; c++) {
				listaNoticias.add(new Noticia(jsonArray.getJSONObject(c)));
			}
			onSuccessObtenerNoticias(listaNoticias);

		} catch (Exception e) {
			Log.d("no consume noticias", e.getMessage());
		}
	}

	@Override
	public void onFinish() {
		super.onFinish();
	}

	@Override
	protected void onErrorLogico(String error) {

	}

	public abstract void onSuccessObtenerNoticias(List<Noticia> noticias);
}
