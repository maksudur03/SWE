package com.example.hp.swe;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

public class SplasScreen extends AppCompatActivity {

    SharedPreferences sp;
    String id,pass,refreshedToken;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas_screen);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if(sp.getBoolean("logged",true)){
                    ////////////////Not Logged in/////////////////////
                    startActivity(new Intent(SplasScreen.this,Login.class));
                    finish();
//                    refreshedToken = FirebaseInstanceId.getInstance().getToken();
//                    Toast.makeText(SplasScreen.this,refreshedToken,Toast.LENGTH_LONG).show();


                }
                else{
                    //////////////Keep logged////////////////



                    final ProgressDialog Dialog = new ProgressDialog(SplasScreen.this);
                    Dialog.setMessage("Logging In.......");
                    Dialog.show();
//
//                    Toast.makeText(SplasScreen.this,sp.getString("password",""),Toast.LENGTH_LONG).show();
                    id = sp.getString("registration_number","");
                    pass = sp.getString("password","");

                    mAuth.signInWithEmailAndPassword(id+"@gmail.com", pass)
                            .addOnCompleteListener(SplasScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(SplasScreen.this,HomePage.class);
                                        Dialog.dismiss();
                                        startActivity(i);
                                        finish();


                                    } else {
                                        Toast.makeText(SplasScreen.this,"Registration and password is incorrect",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

//            Toast.makeText(SplasScreen.this,sp.getString("registration_number",""),Toast.LENGTH_LONG).show();
//
//            Intent i = new Intent(SplasScreen.this,Login.class);
//            startActivity(i);
                }

            }
        }, 1000);









    }
}
