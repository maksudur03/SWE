package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Batch_17 extends AppCompatActivity {


    RecyclerView class_schedulee;
    private batch_adapter mAdapter;
    public  static List<Profile> profile_17 = new ArrayList<>();
    SharedPreferences sp;
    String batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_17);



        class_schedulee = findViewById(R.id.batch_17_recycler);
        mAdapter = new batch_adapter(profile_17,Batch_17.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Batch_17.this);
        class_schedulee.setLayoutManager(mLayoutManager);
        class_schedulee.setItemAnimator(new DefaultItemAnimator());
        class_schedulee.setAdapter(mAdapter);


        final ProgressDialog Dialog = new ProgressDialog(Batch_17.this);
        Dialog.setMessage("Please Wait.....");
        Dialog.show();
        DatabaseReference info_list = FirebaseDatabase.getInstance().getReference();

        info_list.child("Profile").child("2017").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile p = dataSnapshot.getValue(Profile.class);
                profile_17.add(p);
                mAdapter.notifyDataSetChanged();
                Dialog.dismiss();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }
}

