package com.example.hp.swe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Exam_Schedule_adapter extends RecyclerView.Adapter<Exam_Schedule_adapter.MyViewHolder> {

    private List<Exam_Schedule_object> exam_list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date_exam, time_exam, subject_exam;

        public MyViewHolder(View view) {
            super(view);
            date_exam = (TextView) view.findViewById(R.id.exam_date);
            time_exam = (TextView) view.findViewById(R.id.exam_time);
            subject_exam = (TextView) view.findViewById(R.id.exam_subject);
        }
    }


    public Exam_Schedule_adapter(List<Exam_Schedule_object> exam_list) {
        this.exam_list = exam_list;
    }






    @NonNull
    @Override
    public Exam_Schedule_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exam_schedule_layout, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Exam_Schedule_adapter.MyViewHolder myViewHolder, int i) {
        Exam_Schedule_object exam_schedule_object = exam_list.get(i);
        myViewHolder.date_exam.setText(exam_schedule_object.getDate());
        myViewHolder.time_exam.setText(exam_schedule_object.getTime());
        myViewHolder.subject_exam.setText(exam_schedule_object.getSubject());
    }

    @Override
    public int getItemCount() {
        return exam_list.size();
    }
}
