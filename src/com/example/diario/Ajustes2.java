package com.example.diario;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

public class Ajustes2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajustes2);

		SharedPreferences sharedPref = getSharedPreferences("app",
				Context.MODE_PRIVATE);

		Boolean switchEditslide1 = (Boolean) sharedPref.getBoolean(
				Ajustes1.SWITCH_VALUE1, true);
		Boolean switchEditslide2 = (Boolean) sharedPref.getBoolean(
				Ajustes1.SWITCH_VALUE2, true);

		Switch sw1 = (Switch) findViewById(R.id.switchView1result);
		sw1.setChecked(switchEditslide1);

		Switch sw2 = (Switch) findViewById(R.id.switchView2result);
		sw2.setChecked(switchEditslide2);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajustes2, menu);
		return true;
	}
	
	public void onClickAjustes2(View view) {

		Intent i = new Intent(this, ApplicationActivity.class);
		Toast.makeText(this, "Ajustes Guardados!", Toast.LENGTH_LONG).show();
		startActivity(i);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}