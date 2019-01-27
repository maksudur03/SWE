package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Registration_Profile extends AppCompatActivity {

    String id,pass;
    ImageView profilepic;
    Button photobutton,register_profile_signup;
    public static final int PICK_IMAGE = 2;
    EditText name,blood,dob,phone_number,email,emergency_number;
    String s_name,s_blood,s_dob,s_phone_number,s_email,s_emergency_number;
    private FirebaseAuth mAuth;
    private StorageReference nStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__profile);
        Intent i = getIntent();
        id = i.getStringExtra("ID");
        pass = i.getStringExtra("PASS");
        mAuth = FirebaseAuth.getInstance();

        nStorage = FirebaseStorage.getInstance().getReference();
        profilepic = findViewById(R.id.profilepic);
        photobutton = findViewById(R.id.photobutton);

        register_profile_signup = findViewById(R.id.register_profile_signup);
        name = findViewById(R.id.input_name);
        blood = findViewById(R.id.input_blood);
        dob = findViewById(R.id.input_dob);
        phone_number = findViewById(R.id.input_phone_number);
        email = findViewById(R.id.input_email);
        emergency_number = findViewById(R.id.input_emergency_number);




        photobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);//
                //Toast.makeText(Registration_Profile.this,"Feature Not added Sorry",Toast.LENGTH_LONG).show();

            }
        });









        register_profile_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_blood = blood.getText().toString();
                s_dob = dob.getText().toString();
                s_email = email.getText().toString();
                s_emergency_number = emergency_number.getText().toString();
                s_name = name.getText().toString();
                s_phone_number = phone_number.getText().toString();
                if(s_name.equals("") || s_blood.equals("") || s_dob.equals("") || s_phone_number.equals("") || s_email.equals("") || s_emergency_number.equals("")){
                    Toast.makeText(Registration_Profile.this,"Fill all Field",Toast.LENGTH_LONG).show();
                }
                else{

                    final ProgressDialog Dialog = new ProgressDialog(Registration_Profile.this);
                    Dialog.setMessage("Account is Creating.....");
                    Dialog.show();

                    mAuth.createUserWithEmailAndPassword(id + "@gmail.com", pass)
                            .addOnCompleteListener(Registration_Profile.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Profile p = new Profile(s_name,id,s_blood,s_dob,s_phone_number,s_emergency_number,s_email,"student","null");
                                        final DatabaseReference check = FirebaseDatabase.getInstance().getReference();
                                        check.child("Profile").child(id.substring(0,4)).child(id).setValue(p);
                                        Dialog.dismiss();
                                        startActivity(new Intent(Registration_Profile.this,Login.class));

                                    } else {
                                        Toast.makeText(Registration_Profile.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();


                                    }       // ...
                                    }
                                });


                }


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == PICK_IMAGE && resultCode== RESULT_OK && data != null) {
            Uri selectedImage= data.getData();
            StorageReference filepath= nStorage.child("Photos").child(selectedImage.getLastPathSegment());
            filepath.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Registration_Profile.this,"Upload done", Toast.LENGTH_LONG).show();
                }
            });
            profilepic.setImageURI(selectedImage);


        }
    }

}

