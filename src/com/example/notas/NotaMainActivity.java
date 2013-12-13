package com.example.notas;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Notas.listeners.ListViewItemClickListener;
import com.example.diario.R;
import com.example.diario.db.MyAppDataSource;

public class NotaMainActivity extends ListActivity {

	public static final int REQUEST_CODE_ADD_NOTA = 1;
	public static final int REQUEST_CODE_UPDATE_NOTA = 2;
	
	public static final String EXTRA_NOTA = "nota";
	public static final String EXTRA_REMOVE = "remove";

	private MyAppDataSource ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota_main);
		
		ds = new MyAppDataSource(this);
	    ds.open();
	    
	    List<Nota> values = ds.getDatos();
	    
	    // use the SimpleCursorAdapter to show the elements in a ListView
	    ArrayAdapter<Nota> adapter = new ArrayAdapter<Nota>(
	    		this,
	    		android.R.layout.simple_list_item_1, 
	    		values
	    	);
	    
	    this.setListAdapter(adapter);
	    
	    ListView lv = (ListView) this.findViewById(android.R.id.list);
	    lv.setOnItemClickListener(new ListViewItemClickListener(this));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(data.hasExtra(EXTRA_NOTA))
		{
			List<Nota> values = ds.getDatos();
		    
		    // use the SimpleCursorAdapter to show the elements in a ListView
		    ArrayAdapter<Nota> adapter = new ArrayAdapter<Nota>(
		    		this,
		    		android.R.layout.simple_list_item_1, 
		    		values
		    	);
		    
		    ListView lv = (ListView) this.findViewById(android.R.id.list);
		    lv.setAdapter(adapter);
		    
			if(requestCode == REQUEST_CODE_ADD_NOTA) {
				// do something here when a person is added
			}
			else if(requestCode == REQUEST_CODE_UPDATE_NOTA)
			{
				Boolean remove = data.getBooleanExtra(EXTRA_REMOVE, false);
				
				if(remove) {
					// do something here when a person is removed
				}
				else {
					// do something here when a person is updated
				}
			}
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onAddButtonClicked(View view) {
		Intent i = new Intent(this, NotaActivity.class);
		this.startActivityForResult(i, REQUEST_CODE_ADD_NOTA);
	}

}
