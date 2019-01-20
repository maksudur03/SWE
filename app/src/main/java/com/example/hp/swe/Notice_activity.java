package com.example.hp.swe;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Notice_activity extends AppCompatActivity {

    RecyclerView recyclerView;

    private List<Notice> noticeList = new ArrayList<>();
    private Notice_adapter mAdapter;
    String id,batch;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_activity);

        recyclerView = (RecyclerView) findViewById(R.id.notice_recycler);


        mAdapter = new Notice_adapter(noticeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        noticeList.add(new Notice("Class Schedule ","nayeem","2016831025","null","Next class at 10:30 Bus 101","null"));
        recyclerView.setAdapter(mAdapter);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        id = sp.getString("registration_number","");
        batch = id.substring(0,4);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("Notice").child(batch).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Notice p = dataSnapshot.getValue(Notice.class);
                noticeList.add(p);
                mAdapter.notifyDataSetChanged();
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

        recyclerView.addItemDecoration(new DividerItemDecoration(Notice_activity.this,
                DividerItemDecoration.VERTICAL));





    }
}
