package com.example.diario.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.example.diario.db.MyAppContract.Datos;
import com.example.notas.Nota;

public class MyAppDataSource {

	private MyAppDbHelper dbHelper;
	private SQLiteDatabase db;
	
	private String[] allColumns = {
			Datos._ID,
			Datos.COLUMN_NAME_FIRST_NAME,
			Datos.COLUMN_NAME_LAST_NAME,
			Datos.COLUMN_NAME_EMAIL
		    };

	public MyAppDataSource(Context context) {
		this.dbHelper = new MyAppDbHelper(context);
	}
	
	public void open() throws SQLException {
		this.db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public Nota createNota(String firstName, String lastName, String email) {
		ContentValues values = new ContentValues();
		values.put(Datos.COLUMN_NAME_FIRST_NAME, firstName);
		values.put(Datos.COLUMN_NAME_LAST_NAME, lastName);
		values.put(Datos.COLUMN_NAME_EMAIL, email);
		
	    long insertId = db.insert(Datos.TABLE_NAME, null, values);
	    
	    Cursor c = db.query(
	    		Datos.TABLE_NAME,
	    		this.allColumns, Datos._ID + " = " + insertId, 
	    		null,
	    		null, 
	    		null, 
	    		null
	    	);
	    c.moveToFirst();
	    
	    Nota n = cursorToNota(c);
	    c.close();
	    
	    return n;
	}
	
	public Nota updateNota(Nota n, String firstName, String lastName, String email) {
		ContentValues values = new ContentValues();
		values.put(Datos.COLUMN_NAME_FIRST_NAME, firstName);
		values.put(Datos.COLUMN_NAME_LAST_NAME, lastName);
		values.put(Datos.COLUMN_NAME_EMAIL, email);
		
	    db.update(Datos.TABLE_NAME, values, Datos._ID + " = " + n.getId(), null);
	    
	    n.setFirstName(firstName);
	    n.setLastName(lastName);
	    n.setEmail(email);
	    
	    return n;
	}
	
	public List<Nota> getDatos() {
	    List<Nota> datos = new ArrayList<Nota>();
	    
	    String sortOrder = Datos.COLUMN_NAME_FIRST_NAME + " DESC";
	    
	    Cursor c = db.query(
			    Datos.TABLE_NAME,	// The table to query
			    this.allColumns,			// The columns to return
			    null,				// The columns for the WHERE clause
			    null,				// The values for the WHERE clause
			    null,				// don't group the rows
			    null,				// don't filter by row groups
			    sortOrder			// The sort order
		    );

	    c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Nota n = cursorToNota(c);
	      datos.add(n);
	      c.moveToNext();
	    }
	    
	    // make sure to close the cursor
	    c.close();
	    
	    return datos;
	}
	
	public Nota deleteNota(Nota n) {
	    long id = n.getId();
	    db.delete(Datos.TABLE_NAME, Datos._ID + " = " + id, null);
	    n.setId(0);
	    return n;
	}

	
	private Nota cursorToNota(Cursor cursor) {
		Nota n = new Nota();
	    n.setId(cursor.getLong(0));
	    n.setFirstName(cursor.getString(1));
	    n.setLastName(cursor.getString(2));
	    n.setEmail(cursor.getString(3));
	    return n;
	}
}
