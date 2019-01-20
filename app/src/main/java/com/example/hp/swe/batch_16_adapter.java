package com.example.hp.swe;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class batch_16_adapter extends RecyclerView.Adapter<batch_16_adapter.MyViewHolder> {

    private List<Profile> class_list;
    Context cn;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView regestration,name;

        public MyViewHolder(View view) {
            super(view);
            regestration = (TextView) view.findViewById(R.id.textViewDesc);
            name =  view.findViewById(R.id.textViewHead);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myactivity = new Intent(cn.getApplicationContext(), Show_Profile.class);
                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    myactivity.putExtra("Position",""+getAdapterPosition());
                    cn.getApplicationContext().startActivity(myactivity);                }
            });
        }
    }

    public batch_16_adapter(List<Profile> class_list,Context cn) {
        this.class_list = class_list;
        this.cn = cn;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.batch_list, viewGroup, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            Profile p = class_list.get(i);
            myViewHolder.name.setText(p.getName());
            myViewHolder.regestration.setText(p.getRegistration_number());

    }

    @Override
    public int getItemCount() {
        return class_list.size();
    }



}