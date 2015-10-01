package com.b_tree.telartes.base;

import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

//import org.json.JSONObject;

public class JSONParser {
	private static InputStream is = null;
	// private static JSONObject jObj = null;
	private static String json = "";

	public static String getJSONFromUrl(String url) {
		is = null;
		json = "";
		// Making HTTP request
//		try {
//			// defaultHttpClient
//			DefaultHttpClient httpClient = new DefaultHttpClient();
//			HttpPost httpPost = new HttpPost(url);
//
//			HttpResponse httpResponse = httpClient.execute(httpPost);
//			HttpEntity httpEntity = httpResponse.getEntity();
//			is = httpEntity.getContent();
//
//			try {
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(is, "iso-8859-1"), 8);
//				StringBuilder sb = new StringBuilder();
//				String line = null;
//				while ((line = reader.readLine()) != null) {
//					sb.append(line + "\n");
//				}
//
//				json = sb.toString();
//				is.close();
//			} catch (Exception e) {
//				Log.e("Buffer Error", "Error converting result " + e.toString());
//			}
//
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		return json;

	}
}
