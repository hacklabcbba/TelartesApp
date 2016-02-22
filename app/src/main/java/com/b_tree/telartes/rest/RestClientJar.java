package com.b_tree.telartes.rest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.params.ClientPNames;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RestClientJar {

	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void setTimeOut() {
		client.setTimeout(600000);
	}

	public static void allowCircularRedirects() {
		client.getHttpClient().getParams()
				.setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
	}

	public static void get(String BASE_URL, String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(BASE_URL, url), params, responseHandler);
	}

	public static void post(Context context, String BASE_URL, String url, Header[] headers,
			HttpEntity entity, String contentType,
			AsyncHttpResponseHandler responseHandler) {
		client.post(context, getAbsoluteUrl(BASE_URL,url), headers , entity, contentType,
				responseHandler);
	}

	public static void cancel(Context context) {
		client.cancelRequests(context, true);
	}

	private static String getAbsoluteUrl(String BASE_URL, String relativeUrl) {
		Log.i("fullurl",(BASE_URL+relativeUrl));
		return BASE_URL + relativeUrl;
	}

	public static boolean getInternetState(Context context) {
		boolean hasConnectedWifi = false;
		boolean hasConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo[] netInfo = cm.getAllNetworkInfo();

		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("wifi"))
				if (ni.isConnected())
					hasConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("mobile"))
				if (ni.isConnected())
					hasConnectedMobile = true;
		}
		return hasConnectedWifi || hasConnectedMobile;
	}
}
