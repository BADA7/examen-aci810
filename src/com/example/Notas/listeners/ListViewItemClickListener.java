package com.example.Notas.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.notas.Nota;
import com.example.notas.NotaActivity;
import com.example.notas.NotaMainActivity;

public class ListViewItemClickListener implements AdapterView.OnItemClickListener {

	private Activity activity;
	
	public ListViewItemClickListener(Activity activity) {
		this.activity = activity;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Nota n = (Nota) parent.getItemAtPosition(position);
		
		if(n != null)
		{
			Intent i = new Intent(this.activity, NotaActivity.class);
			i.putExtra("nota", n);
			this.activity.startActivityForResult(i, NotaMainActivity.REQUEST_CODE_UPDATE_NOTA);			
		}
	}
}
