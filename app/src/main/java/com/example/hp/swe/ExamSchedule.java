package com.example.hp.swe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExamSchedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExamSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExamSchedule extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Exam_Schedule_object> examList = new ArrayList<>();
    RecyclerView recyclerView;
    private Exam_Schedule_adapter mAdapter;
    SharedPreferences sp;
    String id,batch;
    Date date = null,c;
    SimpleDateFormat df;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ExamSchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExamSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static ExamSchedule newInstance(String param1, String param2) {
        ExamSchedule fragment = new ExamSchedule();
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
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam_schedule, container, false);



        recyclerView = v.findViewById(R.id.exam_schedule);
        mAdapter = new Exam_Schedule_adapter(examList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));


        final ProgressDialog Dialog = new ProgressDialog(getActivity());
        Dialog.setMessage("Please Wait.....");
        Dialog.show();
//        sp = getSharedPreferences("login",MODE_PRIVATE);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        id = preferences.getString("registration_number","");
        batch = id.substring(0,4);
        c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

         df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        String test = "01-Feb-2019";


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Exam").child(batch).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Exam_Schedule_object p = dataSnapshot.getValue(Exam_Schedule_object.class);
                try {
                    date = df.parse(p.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(c.compareTo(date)<=0){
                    examList.add(new Exam_Schedule_object(p.getDate(),p.getTime(),p.getSubject()));
                }
//                examList.add(dataSnapshot.getValue(Exam_Schedule_object.class));
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
