package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Notice_pop_up extends AppCompatActivity {

    EditText title,text;
    Button btn;
    String notice_title,notice_text;
    String id,batch,profile_pic;
    SharedPreferences sp;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_pop_up);

        text = findViewById(R.id.notice);
        title = findViewById(R.id.title_id);
        btn = findViewById(R.id.btn_id);

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

                ref.child("Notice").child(batch).push().setValue(new Notice(notice_title,HomePage.user_name,id,"null",notice_text,profile_pic));
                Notice_pop_up.this.finish();

            }
        });


    }
}
