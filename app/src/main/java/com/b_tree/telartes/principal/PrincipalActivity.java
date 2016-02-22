package com.b_tree.telartes.principal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import com.b_tree.telartes.R;
import com.b_tree.telartes.base.BaseTelartesActivity;


public class PrincipalActivity extends BaseTelartesActivity implements OnClickListener {
	private Button btnContenido;
	private Button btnPruebas;
	private Button btnEstadisticas;

	@Override
	protected void inicializarVariables(Bundle savedInstanceState) {
	}

	@Override
	protected int getResLayout() {
		return R.layout.acivity_principal;
	}

	@Override
	protected void instaciarAsignarIGU(Bundle savedInstanceState) {
		btnContenido = (Button) findViewById(R.id.btn_contenido);
		btnPruebas = (Button) findViewById(R.id.btn_pruebas);
		btnEstadisticas = (Button) findViewById(R.id.btn_convocatoria);
		btnContenido.setOnClickListener(this);
		btnPruebas.setOnClickListener(this);
		btnEstadisticas.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_contenido:
			iniciarNoticiaActivity();
			break;
		case R.id.btn_pruebas:
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

	private void iniciarConcovatoriasActivity() {
		startActivity(new Intent(this, ConvocatoriaActivity.class));
	}
	@Override
	protected String getScreenLabel() {
		return "Principal";
	}


	@Override
	protected void onResume() {
		super.onResume();

	}
}
