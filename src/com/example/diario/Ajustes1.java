package com.example.diario;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Switch;

public class Ajustes1 extends Activity {

	public final static String SWITCH_VALUE1 = "com.example.notas.SWITCH_VALUE";
	public final static String SWITCH_VALUE2 = "com.example.notas.SWITCH_VALUE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajustes1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajustes1, menu);
		return true;
	}

	public void onClickAjustes1(View view) {

		Intent i = new Intent(this, Ajustes2.class);

		Switch switchEdit1 = (Switch) findViewById(R.id.switchAjustes1);
		Boolean switchEditslide1 = switchEdit1.isChecked();

		Switch switchEdit2 = (Switch) findViewById(R.id.switchAjustes2);
		Boolean switchEditslide2 = switchEdit2.isChecked();

		SharedPreferences sharedPref = getSharedPreferences("app",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		editor.putBoolean(SWITCH_VALUE1, switchEditslide1);
		editor.putBoolean(SWITCH_VALUE2, switchEditslide2);

		editor.commit();

		startActivity(i);
	}
	  public void onajustes1(View view) {
	        Intent ajust = new Intent(this,Ajustes2.class);
	        this.startActivity(ajust);
	        
	        
	    
	        
	     }
}