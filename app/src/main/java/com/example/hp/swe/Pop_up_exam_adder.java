package com.example.hp.swe;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pop_up_exam_adder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] dayampm = { "a.m.", "p.m."};
    String[] dayhr = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] daymin = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
    String[] day = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] month ={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] year = {"2019","2020","2021","2022","2023"};


    SharedPreferences sp;
    String id,batch;

    Button but2;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_exam_adder);

        sp = getSharedPreferences("login",MODE_PRIVATE);

        id = sp.getString("registration_number","");
        batch = id.substring(0,4);



        but2 = findViewById(R.id.btn);
        ed = findViewById(R.id.sub);

        final Spinner spinampm = (Spinner) findViewById(R.id.ampm);
        spinampm.setOnItemSelectedListener( Pop_up_exam_adder.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaampm = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dayampm);
        aaampm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinampm.setAdapter(aaampm);


        final Spinner spinhr = (Spinner) findViewById(R.id.hr);
        spinhr.setOnItemSelectedListener( Pop_up_exam_adder.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aahr = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dayhr);
        aahr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinhr.setAdapter(aahr);
        final Spinner spinmin = (Spinner) findViewById(R.id.min);
        spinmin.setOnItemSelectedListener( Pop_up_exam_adder.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aamin = new ArrayAdapter(this,android.R.layout.simple_spinner_item,daymin);
        aamin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinmin.setAdapter(aamin);

        final Spinner spinday = (Spinner) findViewById(R.id.day);
        spinday.setOnItemSelectedListener( Pop_up_exam_adder.this);


        ArrayAdapter aaday = new ArrayAdapter(this,android.R.layout.simple_spinner_item,day);
        aaday.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinday.setAdapter(aaday);

        final Spinner spinmnth = (Spinner) findViewById(R.id.mnth);
        spinmnth.setOnItemSelectedListener( Pop_up_exam_adder.this);


        ArrayAdapter aamnth = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
        aamnth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinmnth.setAdapter(aamnth);

        final Spinner spinyr = (Spinner) findViewById(R.id.year);
        spinyr.setOnItemSelectedListener( Pop_up_exam_adder.this);

        ArrayAdapter aayr = new ArrayAdapter(this,android.R.layout.simple_spinner_item,year);
        aayr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinyr.setAdapter(aayr);

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sub = ed.getText().toString();
                String time = spinhr.getSelectedItem().toString() + ":" +spinmin.getSelectedItem().toString()+" "+spinampm.getSelectedItem().toString();
                String date = spinday.getSelectedItem().toString() + "-" + spinmnth.getSelectedItem().toString() + "-" +spinyr.getSelectedItem().toString();


                if(!sub.equals("")){
                    DatabaseReference def = FirebaseDatabase.getInstance().getReference();
                    def.child("Exam").child(batch).push().setValue(new Exam_Schedule_object(date,time,sub));
                    Pop_up_exam_adder.this.finish();
                    Toast.makeText(Pop_up_exam_adder.this,"Exam Added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Pop_up_exam_adder.this,"Add Exam Subject",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
