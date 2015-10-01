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
	private Button btnOpciones;

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
		btnEstadisticas = (Button) findViewById(R.id.btn_estadisticas);
		btnOpciones = (Button) findViewById(R.id.btn_opciones);

		btnContenido.setOnClickListener(this);
		btnPruebas.setOnClickListener(this);
		btnEstadisticas.setOnClickListener(this);
		btnOpciones.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_contenido:
			iniciarEventoActivity();
			break;
		case R.id.btn_pruebas:
			iniciarEventoActivity();
			break;
		case R.id.btn_estadisticas:
			iniciarEventoActivity();
			break;
		case R.id.btn_opciones:
			iniciarEventoActivity();
			break;

		default:
			break;
		}
	}

	private void iniciarEventoActivity() {
		startActivity(new Intent(this, EventoActivity.class));
	}


	@Override
	protected String getScreenLabel() {
		return "Principal";
	}



}
