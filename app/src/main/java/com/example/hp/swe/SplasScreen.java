package com.example.hp.swe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplasScreen extends AppCompatActivity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas_screen);
        sp = getSharedPreferences("login",MODE_PRIVATE);




        if(sp.getBoolean("logged",true)){
            Intent i = new Intent(SplasScreen.this,Login.class);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(SplasScreen.this,"HI",Toast.LENGTH_LONG).show();
        }


    }
}
