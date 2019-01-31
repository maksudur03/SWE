package com.example.hp.swe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

public class Show_day_list extends AppCompatActivity {

    day_adapter mAdapter;
    ArrayList<String> msgList = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_day_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new day_adapter(msgList,Show_day_list.this
        );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        msgList.add("Sunday");
        msgList.add("Monday");msgList.add("Tuesday");msgList.add("Wednesday");msgList.add("Thrusday");
        recyclerView.addItemDecoration(new DividerItemDecoration(Show_day_list.this,
                DividerItemDecoration.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
//        recyclerView.scrollToPosition(mAdapter.getItemCount()-1);


    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
