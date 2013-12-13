package com.example.diario;

import com.example.notas.NotaMainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class AmigosActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amigos2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.amigos_activity2, menu);
		return true;
	}
	public void amigosnota (View view){
		Intent nota = new Intent (this, NotaMainActivity.class);
		startActivity(nota);
		}
	public void amigosnota1 (View view){
		Intent nota1 = new Intent (this, NotaMainActivity.class);
		startActivity(nota1);
	}
	public void amigosnota2 (View view){
		Intent nota2 = new Intent (this, NotaMainActivity.class);
		startActivity(nota2);
	}
	public void amigosnota3 (View view){
		Intent nota3 = new Intent (this, NotaMainActivity.class);
		startActivity(nota3);
}
}
