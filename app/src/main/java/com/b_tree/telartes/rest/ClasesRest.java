package com.b_tree.telartes.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;

public abstract class ClasesRest extends AsyncHttpResponseHandler {

	protected Context context;
	private boolean finalizado;
	private boolean exitoso;

	public ClasesRest(Context context) {
		this.context = context;
	}

	public boolean isExitoso() {
		return exitoso;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	@Override
	public void onSuccess(int i, Header[] headers, byte[] bytes) {
		exitoso = true;
	}

	protected abstract String getURL();

	protected abstract String getMetod();

	protected abstract void onErrorLogico(String error);

	protected void callToPostWithutParameterMetods(
			AsyncHttpResponseHandler handler) {
		RestClientJar.allowCircularRedirects();
		RestClientJar.setTimeOut();
		RestClientJar.get(getURL(),getMetod(),null,handler);
	}

	@Override
	public void onFinish() {
		finalizado = true;
		super.onFinish();
	}

}
