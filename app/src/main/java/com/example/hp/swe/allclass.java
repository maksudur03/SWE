package com.example.hp.swe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class allclass extends AppCompatActivity {

    RecyclerView rec;
    private class_schedule_adapter mAdapter;
    private ArrayList<Class_Schedule_object>classList = new ArrayList<>();
    String id,batch;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allclass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rec = findViewById(R.id.all_class_schedule);
        Intent i = getIntent();
        String s = i.getStringExtra("day");
        sp = getSharedPreferences("login",MODE_PRIVATE);


        id = sp.getString("registration_number","");
        batch = id.substring(0,4);

        mAdapter = new class_schedule_adapter(classList,s);
        ClassSchedule.DAY = s;
        classList.add(new Class_Schedule_object("hhh","hjhjh"));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(allclass.this);
        rec.setLayoutManager(mLayoutManager);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setAdapter(mAdapter);

        DatabaseReference deff = FirebaseDatabase.getInstance().getReference().child("Routine").child(batch).child(s);

        deff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                classList.add(dataSnapshot.getValue(Class_Schedule_object.class));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
