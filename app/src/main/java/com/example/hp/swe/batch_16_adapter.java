package com.example.hp.swe;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class batch_16_adapter extends RecyclerView.Adapter<batch_16_adapter.MyViewHolder> {

    private List<Profile> class_list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView regestration,name;

        public MyViewHolder(View view) {
            super(view);
//            regestration = (TextView) view.findViewById(R.id.textViewDesc);
//            name =  view.findViewById(R.id.textViewHead);
        }
    }

    public batch_16_adapter(List<Profile> class_list) {
        this.class_list = class_list;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
