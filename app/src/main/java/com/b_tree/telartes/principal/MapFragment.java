package com.b_tree.telartes.principal;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.Calendar;

public class MapFragment extends SupportMapFragment {

	private OnMapReadyListener listener;

	public MapFragment() {
		super();
	}

	public static MapFragment newInstance() {
		MapFragment fragment = new MapFragment();
		return fragment;
	}

	public void setListener(OnMapReadyListener listener) {
		this.listener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
		View v = super.onCreateView(arg0, arg1, arg2);
		android.support.v4.app.Fragment fragment = getParentFragment();
		if (fragment != null && fragment instanceof OnMapReadyListener) {
			listener = (OnMapReadyListener) fragment;
		}
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		OnMapReadyTask mapReadyTask = new OnMapReadyTask();
		mapReadyTask.execute(2000L, 100L);
	}

	private class OnMapReadyTask extends AsyncTask<Long, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Long... params) {
			long init = Calendar.getInstance().getTimeInMillis();
			long duracionInMillis = params[0];
			long sleep = params[1];
			long dif = 0;
			do {
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				dif = Calendar.getInstance().getTimeInMillis() - init;
			} while (getMap() == null && dif < duracionInMillis);
			if (getMap() == null) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (listener != null) {
				if (result == Boolean.TRUE) {
					listener.onMapReady(getMap());
				} else {
					listener.onTimeExpiredMapNotInstanceYet();
				}
			}
		}
	}

	public static interface OnMapReadyListener {
		void onMapReady(GoogleMap googleMap);

		void onTimeExpiredMapNotInstanceYet();
	}

}
