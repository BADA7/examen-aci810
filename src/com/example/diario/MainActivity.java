package com.example.diario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {
	
	
	
	public final static String IS_REGISTERES_KEY = "com.example.diario.IS_REGISTERES_KEY";
    public final static String IS_LOGGED_IN_KEY = "com.example.diario.IS_LOGGED_IN_KEY";
    
    public final static String NAME_KEY = "com.example.diario.NAME_KEY";
    public final static String EMAIL_KEY = "com.example.diario.EMAIL_KEY";
    public final static String PASSWORD_KEY = "com.example.diario.PASSWORD_KEY";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences sharedPref = getSharedPreferences("app-data",Context.MODE_PRIVATE);
        Boolean isLoggedIn = sharedPref.getBoolean(MainActivity.IS_LOGGED_IN_KEY, false);
        Boolean isRegistered = sharedPref.getBoolean(MainActivity.IS_REGISTERES_KEY, false);
        
        if(!isRegistered)
        {
                Intent register = new Intent(this, RegisterActivity.class);
                this.startActivity(register);
        }
        else if(!isLoggedIn)
        {
                Intent login = new Intent(this, LoginActivity.class);
                this.startActivity(login);
        }
        else
        {
                Intent app = new Intent(this, ApplicationActivity.class);
                this.startActivity(app);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
