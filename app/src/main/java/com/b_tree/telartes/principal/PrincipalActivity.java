package com.b_tree.telartes.principal;


import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;


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
	private  DrawerLayout drawerLayout;
	private Point p;
	@Override
	protected void inicializarVariables(Bundle savedInstanceState) {
	}

	@Override
	protected int getResLayout() {
		return R.layout.activity_principal;
	}

	@Override
	protected void instaciarAsignarIGU(Bundle savedInstanceState) {
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		btnNoticias = (LinearLayout) findViewById(R.id.btn_noticias);
		btnAgenda = (LinearLayout) findViewById(R.id.btn_agenda);
		btnConvocatorias = (LinearLayout) findViewById(R.id.btn_convocatoria);
		txtConvocatoria = (TextView)findViewById(R.id.txt_convocatoria);
		txtNoticia = (TextView)findViewById(R.id.txt_noticia);
		txtagenda = (TextView)findViewById(R.id.txt_agenda);
		imgNoticias = (ImageView)findViewById(R.id.img_noticias);
		imgAgenda = (ImageView)findViewById(R.id.img_agenda);
		imgConvocatoria = (ImageView)findViewById(R.id.img_convocatoria);
		imgInformacion = (ImageView)findViewById(R.id.img_informacion);
		final TextView urlTelartes = (TextView)findViewById(R.id.txt_url_telartes);
		urlTelartes.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {

				return false;
				

			}

			});
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

		imgInformacion.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
					drawerLayout.closeDrawer(Gravity.RIGHT);
				} else {
					drawerLayout.openDrawer(Gravity.RIGHT);
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


}
