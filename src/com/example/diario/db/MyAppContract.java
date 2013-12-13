package com.example.diario.db;

import android.provider.BaseColumns;

public final class MyAppContract {

	public MyAppContract() {
		// this empty constructor prevent accidentally instantiating the contract class
	}
	
	public static abstract class Datos implements BaseColumns {
		public static final String TABLE_NAME = "datos";
		public static final String COLUMN_NAME_FIRST_NAME = "first_name";
		public static final String COLUMN_NAME_LAST_NAME = "last_name";
		public static final String COLUMN_NAME_EMAIL = "email";
	}
}
