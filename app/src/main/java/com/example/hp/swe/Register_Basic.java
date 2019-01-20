package com.example.hp.swe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_Basic extends AppCompatActivity {

    TextView login;
    EditText registration_number,password,confirm_password;
    String reg_num,pass,con_pass;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__basic);
        login = findViewById(R.id.link_login);
        signup = findViewById(R.id.btn_signup);
        registration_number = findViewById(R.id.input_registration_number);
        password = findViewById(R.id.input_password);
        confirm_password = findViewById(R.id.input_confirm_password);

        final DatabaseReference check = FirebaseDatabase.getInstance().getReference();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg_num = registration_number.getText().toString().trim();
                pass = password.getText().toString().trim();
                con_pass = confirm_password.getText().toString().trim();
                if(pass.equals(con_pass)){
                    check.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.hasChild("Profile/"+reg_num.substring(0,4)+"/"+reg_num)) {
                                        Toast.makeText(Register_Basic.this,"Already Registered",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Intent i = new Intent(Register_Basic.this,Registration_Profile.class);
                                i.putExtra("ID",reg_num);
                                i.putExtra("PASS",pass);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    Toast.makeText(Register_Basic.this,"Password Do not Match ",Toast.LENGTH_LONG).show();
                    password.setText("");
                    confirm_password.setText("");
                }
            }
        });

        Intent ii = getIntent();
        Toast.makeText(Register_Basic.this,ii.getStringExtra("Position"),Toast.LENGTH_LONG).show();




    }
}
