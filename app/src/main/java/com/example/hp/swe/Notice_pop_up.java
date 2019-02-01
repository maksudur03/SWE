package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static com.example.hp.swe.Registration_Profile.PICK_IMAGE;

public class Notice_pop_up extends AppCompatActivity {

    EditText title,text;
    Button btn;
    String notice_title,notice_text;
    String id,batch,profile_pic;
    SharedPreferences sp;
    DatabaseReference ref;
    ImageView attach_img,smallimg;
    TextView attach_txt;
    Uri selectedImage;
    private StorageReference nStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_pop_up);

        nStorage = FirebaseStorage.getInstance().getReference();
        text = findViewById(R.id.notice);
        title = findViewById(R.id.title_id);
        btn = findViewById(R.id.btn_id);
        attach_img = findViewById(R.id.attach_img);
        attach_txt = findViewById(R.id.attach_txt);
        smallimg = findViewById(R.id.smallimg);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        id = sp.getString("registration_number","");
        batch = id.substring(0,4);
        profile_pic = sp.getString("profile_pic","");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice_text = text.getText().toString();
                notice_title = title.getText().toString();

                ref = FirebaseDatabase.getInstance().getReference();
                ref.keepSynced(true);

                if(Uri.EMPTY.equals(selectedImage)){
                    ref.child("Notice").child(batch).push().setValue(new Notice(notice_title,HomePage.user_name,id,"null",notice_text,profile_pic));
                    Notice_pop_up.this.finish();

                }
                else{


                    StorageReference filepath = nStorage.child("Photos").child(selectedImage.getLastPathSegment());

                    final ProgressDialog Dialog = new ProgressDialog(Notice_pop_up.this);
                    Dialog.setMessage("Posting .....");
                    Dialog.show();

                    filepath.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                // Sign in success, update UI with the signed-in user's information
//                                Profile p = new Profile(s_name, id, s_blood, s_dob, s_phone_number, s_emergency_number, s_email, "student", taskSnapshot.getDownloadUrl().toString());
                                final DatabaseReference check = FirebaseDatabase.getInstance().getReference();
//                                check.child("Profile").child(id.substring(0, 4)).child(id).setValue(p);
                                Dialog.dismiss();
//                                startActivity(new Intent(Registration_Profile.this, Login.class));
                            ref.child("Notice").child(batch).push().setValue(new Notice(notice_title, HomePage.user_name, id, taskSnapshot.getDownloadUrl().toString(), notice_text, profile_pic));
                            startActivity(new Intent(Notice_pop_up.this,Notice_activity.class));
                            Notice_pop_up.this.finish();

                        }
                    });



                    }
            }
        });

        attach_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        attach_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == PICK_IMAGE && resultCode== RESULT_OK && data != null) {
            selectedImage= data.getData();

            smallimg.setImageURI(selectedImage);
            smallimg.setVisibility(View.VISIBLE);
            attach_img.setVisibility(View.GONE);
            attach_txt.setVisibility(View.GONE);


        }
    }
}
