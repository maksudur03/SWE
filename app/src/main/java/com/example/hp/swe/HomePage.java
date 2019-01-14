package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomePage extends AppCompatActivity implements ClassSchedule.OnFragmentInteractionListener,ExamSchedule.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    private FirebaseAuth mAuth;
    public static String id_profile;
    TextView nav_user;
    public static String user_name = "hh";
    String id,batch;
    SharedPreferences sp;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nav_view);
        nv = (NavigationView) findViewById(R.id.nav_view);
        View hView =  nv.getHeaderView(0);
         nav_user = (TextView)hView.findViewById(R.id.nav_header_textView);

        Intent i = getIntent();

        sp = getSharedPreferences("login",MODE_PRIVATE);
        id_profile = sp.getString("registration_number","");

//        ///////////////////////////////////////////////////////////////////////////////////////
        batch = id_profile.substring(0,4);

        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);
        final ProgressDialog Dialog = new ProgressDialog(HomePage.this);
        Dialog.setMessage("Please Wait.....");
        Dialog.show();

        ref.child("Profile").child(batch).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile p = dataSnapshot.getValue(Profile.class);
                if(p.getRegistration_number().equals(id_profile)){
                    SetName(p.getName());
                    Dialog.dismiss();
                }
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


        ////////////////////////////////////////////////////////////////////////////////
        TabLayout tabLayout = findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("Class Scedule"));
        tabLayout.addTab(tabLayout.newTab().setText("Exam Scedule"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = findViewById(R.id.pager);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /////////////////////////////////////////////////////////////////////////////////



        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.group_msg:
                        Intent intent = new Intent(HomePage.this,Group_messege.class);
                        intent.putExtra("ID",id_profile);
                        startActivity(intent);
                        break;




                }
                return false;
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String weekdays[] = new DateFormatSymbols(Locale.ENGLISH).getWeekdays();
        String day = weekdays[dayOfWeek];






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {
            // Android home
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
            return true;
            case R.id.action_settings:
                //Toast.makeText(HomePage.this,"HII",Toast.LENGTH_LONG).show();
                final ProgressDialog Dialog = new ProgressDialog(HomePage.this);
                Dialog.setMessage("Please Wait.....");
                Dialog.show();
                AuthUI.getInstance()
                        .signOut(HomePage.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                                Dialog.dismiss();
                                SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.commit();
                                startActivity(new Intent(HomePage.this,Login.class));
                                finish();


                            }
                        });

                return true;
        }
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private static long back_pressed;
    @Override
    public void onBackPressed(){
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            System.exit(0);
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }
    void SetName(String s){

        nav_user.setText(s);
    }
}