package com.example.hp.swe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Group_messege extends AppCompatActivity {


    RecyclerView recyclerView;
    EditText editText;
    ImageView addBtn;
    DatabaseReference ref;

    private List<ChatMessage> msgList = new ArrayList<>();
    private group_messege_adapter mAdapter;
    public static String user_name;

    SharedPreferences sp;

    String id,batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_messege);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        editText = (EditText) findViewById(R.id.editText);
        addBtn =  findViewById(R.id.addBtn);

        id = sp.getString("registration_number","");
        batch = id.substring(0,4);




        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);


        ref.child("Profile").child(batch).child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_name = dataSnapshot.child("name").getValue().toString().trim();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Group_messege.this,user_name,Toast.LENGTH_LONG).show();
                String message = editText.getText().toString().trim();
                hideKeyboardwithoutPopulate(Group_messege.this);
                if (!message.equals("")) {

                    ChatMessage chatMessage = new ChatMessage(message,user_name);
                    ref.child("chat").child(batch).push().setValue(chatMessage);
                }
                editText.setText("");
            }
        });




        mAdapter = new group_messege_adapter(msgList,user_name);
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
    public static void hideKeyboardwithoutPopulate(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
         return true;
    }

}
