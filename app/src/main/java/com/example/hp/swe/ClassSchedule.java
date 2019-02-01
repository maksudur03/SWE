package com.example.hp.swe;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassSchedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassSchedule extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static  String DAY;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    private class_schedule_adapter mAdapter;
     List<Class_Schedule_object> classList = new ArrayList<>();

    private OnFragmentInteractionListener mListener;
    SharedPreferences sp;
    String id,batch;

    public ClassSchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassSchedule newInstance(String param1, String param2) {
        ClassSchedule fragment = new ClassSchedule();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



//        final View view = inflater.inflate(R.layout.fragment_artist_tracks, container, false);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_class_schedule, container, false);



        recyclerView = v.findViewById(R.id.class_schedule);
        recyclerView.setAdapter(mAdapter);

        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String weekdays[] = new DateFormatSymbols(Locale.ENGLISH).getWeekdays();
        DAY = weekdays[dayOfWeek];
        classList.add(new Class_Schedule_object("10asf","sajkfjsa"));
        mAdapter = new class_schedule_adapter(classList,DAY);
        recyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());




//        classList.add(new Class_Schedule_object("10asf","sajkfjsa"));

        final ProgressDialog Dialog = new ProgressDialog(getActivity());
        Dialog.setMessage("Please Wait.....");
        Dialog.show();
//        sp = getSharedPreferences("login",MODE_PRIVATE);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        id = preferences.getString("registration_number","");
        batch = id.substring(0,4);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Routine").child(batch).child(DAY).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                classList.add(dataSnapshot.getValue(Class_Schedule_object.class));
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
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                Dialog.dismiss();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);





        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
