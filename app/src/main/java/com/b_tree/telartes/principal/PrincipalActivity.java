package com.b_tree.telartes.principal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;

import org.w3c.dom.Text;


public class PrincipalActivity extends BaseTelartesActivity implements OnClickListener {
	private LinearLayout btnNoticias;
	private LinearLayout btnAgenda;
	private LinearLayout btnConvocatorias;
	private TextView txtConvocatoria;
	private TextView txtNoticia;
	private TextView txtagenda;
	private ImageView imgConvocatoria;
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
		imgConvocatoria = (ImageView)findViewById(R.id.ic_convocatoria);
		btnNoticias.setOnClickListener(this);
		btnAgenda.setOnClickListener(this);
		btnConvocatorias.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_noticias:
			iniciarNoticiaActivity();
			break;
		case R.id.btn_agenda:
			iniciarAgendaActivity();
			break;
		case R.id.btn_convocatoria:
			iniciarConcovatoriasActivity();
			break;
		default:
			break;
		}
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
}
