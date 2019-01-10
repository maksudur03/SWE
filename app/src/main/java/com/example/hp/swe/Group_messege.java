package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class Group_messege extends AppCompatActivity {


    RecyclerView recyclerView;
    EditText editText;
    ImageView addBtn;
    DatabaseReference ref;
    private List<ChatMessage> msgList = new ArrayList<>();
    private group_messege_adapter mAdapter;

    String id,batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_messege);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        editText = (EditText) findViewById(R.id.editText);
        addBtn =  findViewById(R.id.addBtn);

        Intent i = getIntent();
        id = i.getStringExtra("ID");
        batch = id.substring(0,4);



        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = editText.getText().toString().trim();

                if (!message.equals("")) {

                    ChatMessage chatMessage = new ChatMessage(message,id);
                    ref.child("chat").child(batch).push().setValue(chatMessage);
                }
                editText.setText("");
            }
        });


        msgList.add(new ChatMessage("kutta","user"));
        msgList.add(new ChatMessage("kutta","user"));
        msgList.add(new ChatMessage("kutta","user"));

        mAdapter = new group_messege_adapter(msgList,id);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.scrollToPosition(mAdapter.getItemCount()-1);





        DatabaseReference msg = FirebaseDatabase.getInstance().getReference().child("chat").child(batch);
        msg.keepSynced(true);
        final ProgressDialog Dialog = new ProgressDialog(Group_messege.this);
        Dialog.setMessage("Please Wait.....");
        Dialog.show();

        msg.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                msgList.add(dataSnapshot.getValue(ChatMessage.class));
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
