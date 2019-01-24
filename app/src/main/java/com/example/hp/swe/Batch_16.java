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

public class Batch_16 extends AppCompatActivity {


    RecyclerView class_schedulee;
    private batch_16_adapter mAdapter;
    public  List<Profile>profile_16 = new ArrayList<>();
    SharedPreferences sp;
    String batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_16);



        class_schedulee = findViewById(R.id.batch_16_recycler);
        mAdapter = new batch_16_adapter(profile_16,Batch_16.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Batch_16.this);
        class_schedulee.setLayoutManager(mLayoutManager);
        class_schedulee.setItemAnimator(new DefaultItemAnimator());
//        classList.add(new Profile("Nayeem","2016831025","A+","sagags","afsa","affs","afssagasg","agsagsag","safsfa"));
        class_schedulee.setAdapter(mAdapter);









        final ProgressDialog Dialog = new ProgressDialog(Batch_16.this);
        Dialog.setMessage("Please Wait.....");
        Dialog.show();
        DatabaseReference info_list = FirebaseDatabase.getInstance().getReference();

        info_list.child("Profile").child("2016").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile p = dataSnapshot.getValue(Profile.class);
                profile_16.add(p);
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
