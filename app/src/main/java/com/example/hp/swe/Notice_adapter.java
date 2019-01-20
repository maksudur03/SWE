package com.example.hp.swe;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Notice_adapter extends RecyclerView.Adapter<Notice_adapter.MyViewHolder> {
    private List<Notice> notice_list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView regestration,name,text,title;
        ImageView image,profilepic;

        public MyViewHolder(View view) {
            super(view);
            regestration = (TextView) view.findViewById(R.id.reg_no);
            name =  view.findViewById(R.id.name);
            text = view.findViewById(R.id.text);
            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
            profilepic = view.findViewById(R.id.profilepic);
        }
    }

    public Notice_adapter(List<Notice> class_list) {
        this.notice_list = class_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sample_blog, viewGroup, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Notice p = notice_list.get(i);
        myViewHolder.name.setText(p.getUsername());
        myViewHolder.regestration.setText(p.getUser_id());
        myViewHolder.text.setText(p.getNotice_text());
        myViewHolder.title.setText(p.getTitle());
        if(p.getPicture().equals("null")){
            ///////gone
            myViewHolder.image.setVisibility(View.GONE);

        }
        else{

        }

      //  myViewHolder.profilepic.


    }

    @Override
    public int getItemCount() {
        return notice_list.size();
    }






}
