package com.example.diario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.notas.NotaMainActivity;

public class ApplicationActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_application);
            
            SharedPreferences sharedPref = getSharedPreferences("app-data",Context.MODE_PRIVATE);
            String name = sharedPref.getString(MainActivity.NAME_KEY, "");
            
            if(name.length() > 0)
            {
                    TextView nameTextView = (TextView) this.findViewById(R.id.nameTextViewField);
                    nameTextView.setText("Welcome " + name + "!        ");
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.application, menu);
            return true;
    }
    
    public void onProfileButtonClicked(View view) {
            Intent register = new Intent(this, RegisterActivity.class);
            this.startActivity(register);
    }
    
    public void onLogoutButtonClicked(View view) {
            
            SharedPreferences sharedPref = getSharedPreferences("app-data",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(MainActivity.IS_LOGGED_IN_KEY, false);
            editor.commit();
            
            Intent login = new Intent(this, LoginActivity.class);
            this.startActivity(login);
    }
    
    
    public void onHistorialNotas(View view) {
        Intent notas = new Intent(this, NotaMainActivity.class);
        this.startActivity(notas);

}
    public void onamigoboton(View view) {
        Intent friend = new Intent(this, AmigosActivity2.class);
        this.startActivity(friend);

}
    public void onamigoboton1(View view) {
        Intent cir = new Intent(this,CirculoActivity.class);
        this.startActivity(cir);
    
    }
    public void onajustes(View view) {
        Intent ajus = new Intent(this,Ajustes1.class);
        this.startActivity(ajus);
}
    public void onajustes1(View view) {
        Intent ajust = new Intent(this,Ajustes2.class);
        this.startActivity(ajust);
        
        
    }
    public void onajustes2(View view) {
        Intent ajuste = new Intent(this,ApplicationActivity.class);
        this.startActivity(ajuste);
        
        
    }
        
}
