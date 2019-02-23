package com.example.hp.swe;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Show_Profile extends AppCompatActivity {
    ImageView call;
    ImageView profile_image;
    Profile p;
    TextView name,reg_no,email,phone,emergecny,blood,dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        call = findViewById(R.id.call);
        profile_image = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        reg_no = findViewById(R.id.reg_no);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        emergecny = findViewById(R.id.emergency);
        blood = findViewById(R.id.blood);
        dob = findViewById(R.id.dob);



//        Intent i = getIntent();
//        String s = i.getStringExtra("Position");
//        int pos = Integer.parseInt(s);
        Gson gson = new Gson();
        p = gson.fromJson(getIntent().getStringExtra("myjson"), Profile.class);


//        Profile p = Batch_16.profile_16.get(pos);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (ActivityCompat.checkSelfPermission(Show_Profile.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Show_Profile.this, p.getRole(),Toast.LENGTH_LONG).show();
                }
                else{Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:123456789"));
                    startActivity(callIntent);
                }
            }

        });



        name.setText(p.getName());
        reg_no.setText(p.getRegistration_number());
        email.setText(p.getEmail());
        phone.setText(p.getPhone_number());
        blood.setText(p.getBlood_group());
        dob.setText(p.getDob());
        emergecny.setText(p.getEmergency_number());
        Picasso.get().load(p.getImage()).into(profile_image);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
