package com.example.hp.swe;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class class_schedule_adapter extends RecyclerView.Adapter<class_schedule_adapter.MyViewHolder> {
    private List<Class_Schedule_object> class_list;
    String s;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, subject;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.class_title);
            time = (TextView) view.findViewById(R.id.class_time);
            subject = (TextView) view.findViewById(R.id.class_subject);
        }
    }


    public class_schedule_adapter(List<Class_Schedule_object> class_list) {
        this.class_list = class_list;
    }
    public class_schedule_adapter(List<Class_Schedule_object> class_list,String s) {
        this.class_list = class_list;
        this.s = s;
    }



    @Override
    public class_schedule_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.class_schedule_layout, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull class_schedule_adapter.MyViewHolder myViewHolder, int i) {

        if (s.equals("ss")) {
            Class_Schedule_object class_schedule_object = class_list.get(i);
            myViewHolder.time.setText(class_schedule_object.getTime());
            myViewHolder.subject.setText(class_schedule_object.getSubject());
            myViewHolder.title.setVisibility(View.GONE);
            myViewHolder.subject.setVisibility(View.VISIBLE);
            myViewHolder.time.setVisibility(View.VISIBLE);
        } else {

            if (i == 0) {
                myViewHolder.title.setText(ClassSchedule.DAY);
                myViewHolder.title.setVisibility(View.VISIBLE);
                myViewHolder.subject.setVisibility(View.GONE);
                myViewHolder.time.setVisibility(View.GONE);
            } else {
                Class_Schedule_object class_schedule_object = class_list.get(i);
                myViewHolder.time.setText(class_schedule_object.getTime());
                myViewHolder.subject.setText(class_schedule_object.getSubject());
                myViewHolder.title.setVisibility(View.GONE);
                myViewHolder.subject.setVisibility(View.VISIBLE);
                myViewHolder.time.setVisibility(View.VISIBLE);
            }


        }
    }



    @Override
    public int getItemCount() {
        return class_list.size();
    }
}
