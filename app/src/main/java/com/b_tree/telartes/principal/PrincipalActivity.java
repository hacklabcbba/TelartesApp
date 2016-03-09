package com.b_tree.telartes.principal;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;

import org.w3c.dom.Text;


public class PrincipalActivity extends BaseTelartesActivity  {
	private LinearLayout btnNoticias;
	private LinearLayout btnAgenda;
	private LinearLayout btnConvocatorias;
	private TextView txtConvocatoria;
	private TextView txtNoticia;
	private TextView txtagenda;
	private ImageView imgNoticias;
	private ImageView imgAgenda;
	private ImageView imgConvocatoria;
	private ImageView imgConfiguracion;
	private ImageView imgInformacion;

	private Point p;
	@Override
	protected void inicializarVariables(Bundle savedInstanceState) {
	}

	@Override
	protected int getResLayout() {
		return R.layout.acivity_principal;
	}

	@Override
	protected void instaciarAsignarIGU(Bundle savedInstanceState) {
		btnNoticias = (LinearLayout) findViewById(R.id.btn_noticias);
		btnAgenda = (LinearLayout) findViewById(R.id.btn_agenda);
		btnConvocatorias = (LinearLayout) findViewById(R.id.btn_convocatoria);
		txtConvocatoria = (TextView)findViewById(R.id.txt_convocatoria);
		txtNoticia = (TextView)findViewById(R.id.txt_noticia);
		txtagenda = (TextView)findViewById(R.id.txt_agenda);
		imgNoticias = (ImageView)findViewById(R.id.img_noticias);
		imgAgenda = (ImageView)findViewById(R.id.img_agenda);
		imgConvocatoria = (ImageView)findViewById(R.id.img_convocatoria);
		imgConfiguracion = (ImageView)findViewById(R.id.img_configuracion);
		imgInformacion = (ImageView)findViewById(R.id.img_informacion);
		btnNoticias.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						txtNoticia.setTextColor(getResources().getColor(R.color.lila));
						imgNoticias.setImageDrawable(getResources().getDrawable(R.mipmap.noticias_hover));
						view.setBackgroundColor(getResources().getColor(R.color.blanco));
						iniciarNoticiaActivity();
						break;
					case  MotionEvent.ACTION_UP:
						txtNoticia.setTextColor(getResources().getColor(R.color.blanco));
						imgNoticias.setImageDrawable(getResources().getDrawable(R.mipmap.noticias));
						view.setBackgroundColor(getResources().getColor(R.color.lila));

				}
				return true;
			}
		});
		btnAgenda.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						txtagenda.setTextColor(getResources().getColor(R.color.verde));
						imgAgenda.setImageDrawable(getResources().getDrawable(R.mipmap.agenda_cultural_hover));
						view.setBackgroundColor(getResources().getColor(R.color.blanco));
						iniciarAgendaActivity();
						break;
					case MotionEvent.ACTION_UP:
						txtagenda.setTextColor(getResources().getColor(R.color.blanco));
						imgAgenda.setImageDrawable(getResources().getDrawable(R.mipmap.agenda_cultural));
						view.setBackgroundColor(getResources().getColor(R.color.verde));
				}
				return true;
			}
		});
		btnConvocatorias.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						txtConvocatoria.setTextColor(getResources().getColor(R.color.rojo));
						imgConvocatoria.setImageDrawable(getResources().getDrawable(R.mipmap.convocatoria_hover));
						view.setBackgroundColor(getResources().getColor(R.color.blanco));
						iniciarConcovatoriasActivity();
						break;
					case MotionEvent.ACTION_UP:
						txtConvocatoria.setTextColor(getResources().getColor(R.color.blanco));
						imgConvocatoria.setImageDrawable(getResources().getDrawable(R.mipmap.convocatoria));
						view.setBackgroundColor(getResources().getColor(R.color.rojo));
				}
				return true;
			}
		});
		imgConfiguracion.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:

						imgConfiguracion.setBackgroundColor(getResources().getColor(R.color.plomo));
						imgConfiguracion.setImageDrawable(getResources().getDrawable(R.mipmap.tuerca_hover));
						int[] location = new int[2];
						imgConfiguracion.getLocationOnScreen(location);
						p = new Point();
						p.x = location[0];
						p.y = location[1];
						if (p != null) {
							showPopupConfiguracion(PrincipalActivity.this, p);
						}
						break;
					case MotionEvent.ACTION_UP:
						imgConfiguracion.setBackgroundColor(getResources().getColor(R.color.blanco));
						imgConfiguracion.setImageDrawable(getResources().getDrawable(R.mipmap.tuerca));

				}
				return true;
			}
		});
		imgInformacion.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						imgInformacion.setBackgroundColor(getResources().getColor(R.color.plomo));
						imgInformacion.setImageDrawable(getResources().getDrawable(R.mipmap.info_hover));
						int[] location = new int[2];
						imgInformacion.getLocationOnScreen(location);
						p = new Point();
						p.x = location[0];
						p.y = location[1];
						if (p != null) {
							showPopupInformacion(PrincipalActivity.this, p);
						}
						break;
					case MotionEvent.ACTION_UP:
						imgInformacion.setBackgroundColor(getResources().getColor(R.color.blanco));
						imgInformacion.setImageDrawable(getResources().getDrawable(R.mipmap.info));

				}
				return true;
			}
		});
	}


	private void iniciarNoticiaActivity() {
		startActivity(new Intent(this, NoticiaActivity.class));
	}
	private void iniciarAgendaActivity() {
		startActivity(new Intent(this, AgendaActivity.class));
	}
	private void iniciarConcovatoriasActivity() {startActivity(new Intent(this, ConvocatoriaActivity.class));}
	@Override
	protected String getScreenLabel() {
		return "Principal";
	}
	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {


	}

	private void showPopupConfiguracion(final Activity context, Point p) {
		int popupWidth = 600;
		Display display = getWindowManager().getDefaultDisplay();
		int popupHeight = display.getHeight();
		LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.menu_configuracion);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.configuracion_telartes, viewGroup);
		final PopupWindow popup = new PopupWindow(context);
		popup.setContentView(layout);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);
		int OFFSET_X = 110;
		int OFFSET_Y = 110;
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

//		// Getting a reference to Close button, and close the popup when clicked.
//		Button close = (Button) layout.findViewById(R.id.btn_menu_close);
//		close.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				popup.dismiss();
//			}
//		});
	}
	private void showPopupInformacion(final Activity context, Point p) {
		int popupWidth = 600;
		Display display = getWindowManager().getDefaultDisplay();
		int popupHeight = display.getHeight();

		// Inflate the popup_layout.xml
		LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.menu_informacion);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.informacion_telartes, viewGroup);
		final PopupWindow popup = new PopupWindow(context);
		popup.setContentView(layout);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);
		int OFFSET_X = 110;
		int OFFSET_Y = 110;
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

//		// Getting a reference to Close button, and close the popup when clicked.
//		Button close = (Button) layout.findViewById(R.id.btn_menu_close);
//		close.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				popup.dismiss();
//			}
//		});
	}

}
