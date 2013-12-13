package com.example.notas;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diario.R;
import com.example.diario.db.MyAppDataSource;

public class NotaActivity extends Activity {

	private MyAppDataSource ds;
	private Nota notaToUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota);
		// Show the Up button in the action bar.
		setupActionBar();
		
		ds = new MyAppDataSource(this);
	    ds.open();
	    
	    Intent i = this.getIntent();
	    
	    if(i.hasExtra(NotaMainActivity.EXTRA_NOTA))
	    {
	    	Nota n = (Nota) i.getSerializableExtra(NotaMainActivity.EXTRA_NOTA);
	    	
	    	EditText firstNameField = (EditText) this.findViewById(R.id.firstNameField);
			firstNameField.setText(n.getFirstName());
			
			EditText lastNameField = (EditText) this.findViewById(R.id.lastNameField);
			lastNameField.setText(n.getLastName());
			
			EditText emailField = (EditText) this.findViewById(R.id.emailField);
			emailField.setText(n.getEmail());
			
			Button saveButton = (Button) this.findViewById(R.id.saveButton);
			saveButton.setText("Update");
			
			Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
			deleteButton.setVisibility(Button.VISIBLE);
			
			deleteButton.setText("Delete");
			
			this.setTitle("Update Nota");
			
			this.notaToUpdate = n;
	    }
	    else
	    {
	    	Button saveButton = (Button) this.findViewById(R.id.saveButton);
	    	saveButton.setText("Create");
	    	
	    	Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
	    	deleteButton.setVisibility(Button.INVISIBLE);
	    	
	    	this.setTitle("Create Person");
	    	
	    	this.notaToUpdate = null;
	    }
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.add_person, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onSaveButtonClicked(View view) {
		EditText firstNameField = (EditText) this.findViewById(R.id.firstNameField);
		String firstName = firstNameField.getText().toString();
		
		EditText lastNameField = (EditText) this.findViewById(R.id.lastNameField);
		String lastName = lastNameField.getText().toString();
		
		EditText emailField = (EditText) this.findViewById(R.id.emailField);
		String email = emailField.getText().toString();
		
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty())
		{
			Toast.makeText(this, "Complete the form before saving", Toast.LENGTH_LONG).show();
			return;
		}
		
		Nota n = null;
		
		if(this.notaToUpdate != null)
		{
			n = ds.updateNota(this.notaToUpdate, firstName, lastName, email);
		}
		else
		{
			n = ds.createNota(firstName, lastName, email);
		}
		
		Intent i = new Intent();
		i.putExtra(NotaMainActivity.EXTRA_NOTA, n);
		i.putExtra(NotaMainActivity.EXTRA_REMOVE, false);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	public void onDeleteButtonClicked(View view) {
		
		Nota n = ds.deleteNota(this.notaToUpdate);
		
		Intent i = new Intent();
		i.putExtra(NotaMainActivity.EXTRA_NOTA, n);
		i.putExtra(NotaMainActivity.EXTRA_REMOVE, true);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	@Override
	protected void onResume() {
		ds.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		ds.close();
		super.onPause();
	}

}
