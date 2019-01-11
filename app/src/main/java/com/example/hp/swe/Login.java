package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText registration_number,password;
    Button login,signup;
    private FirebaseAuth mAuth;
    String s_registration_number,s_password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registration_number = findViewById(R.id.input_login_registration_number);
        password = findViewById(R.id.input_login_password);
        login = findViewById(R.id.login_login);
        signup = findViewById(R.id.login_signup);

        mAuth = FirebaseAuth.getInstance();

        sp = getSharedPreferences("login",MODE_PRIVATE);





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_registration_number = registration_number.getText().toString();
                s_password = password.getText().toString();

                final ProgressDialog Dialog = new ProgressDialog(Login.this);
                Dialog.setMessage("Please Wait.....");
                Dialog.show();


                mAuth.signInWithEmailAndPassword(s_registration_number+"@gmail.com", s_password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(Login.this,HomePage.class);
                                    i.putExtra("ID",s_registration_number);
                                    sp.edit().putBoolean("logged",false).apply();
                                    sp.edit().putString("registration_number",s_registration_number).apply();
                                    sp.edit().putString("password",s_password).apply();

                                    Dialog.dismiss();
                                    startActivity(i);
                                    finish();


                                } else {
                                    Toast.makeText(Login.this,"Registration and password is incorrect",Toast.LENGTH_LONG).show();
                                }
                            }
                        });



            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Register_Basic.class);
                startActivity(i);
            }
        });





    }
}
