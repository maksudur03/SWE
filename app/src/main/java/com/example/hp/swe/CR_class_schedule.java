package com.example.hp.swe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CR_class_schedule extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    String[] day = { "Select Day", "Sunday", "Monday", "Tuesday", "Wednesday","Thrusday"};
    String selected_day;
    Button btn;
    SharedPreferences sp;

    RecyclerView class_schedulee;
    private class_schedule_adapter mAdapter;

    String id,batch;
    Spinner spin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cr_class_schedule);


        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        id = sp.getString("registration_number","");
        batch = id.substring(0,4);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,day);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        btn = findViewById(R.id.popup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CR_class_schedule.this,Pop_up_class_adder.class);
                startActivity(i);
            }
        });




        ////////////////////////////////////////////////////////////////////////////////////////////////
        class_schedulee = findViewById(R.id.class_schedule_cr_panel);
        mAdapter = new class_schedule_adapter(Pop_up_class_adder.cr_classList,"ss");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CR_class_schedule.this);
        class_schedulee.setLayoutManager(mLayoutManager);
        class_schedulee.setItemAnimator(new DefaultItemAnimator());

        class_schedulee.setAdapter(mAdapter);
        ///////////////////////////////////////////////////////////////////////////////////////////////

        btn = findViewById(R.id.confirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbs = FirebaseDatabase.getInstance().getReference();
                String day = spin.getSelectedItem().toString();
                Button btn;

                for(int i = 0;i<Pop_up_class_adder.cr_classList.size();i++)
                    dbs.child("Routine").child(batch).child(day).push().setValue(Pop_up_class_adder.cr_classList.get(i));

                Intent i = new Intent(CR_class_schedule.this,HomePage.class);
                startActivity(i);

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected_day = day[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
