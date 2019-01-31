package com.example.hp.swe;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class day_adapter extends RecyclerView.Adapter<day_adapter.MyViewHolder> {
    private List<String> day_list;
    Context cnt;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView today;
        LinearLayout ln;
        public MyViewHolder(View view) {
            super(view);
            today = view.findViewById(R.id.today);
            ln =view.findViewById(R.id.click);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(cnt.getApplicationContext(),allclass.class);
                    intent.putExtra("day",day_list.get(getAdapterPosition()));
                    cnt.startActivity(intent);
//            Toast.makeText(cnt,day_list.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public day_adapter(List<String> class_list,Context cnt) {
        this.day_list = class_list;
        this.cnt = cnt;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.day, viewGroup, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.today.setText(day_list.get(i));
        myViewHolder.ln.setTag(i);
        final int ii = i;

    }

    @Override
    public int getItemCount() {
        return day_list.size();
    }

}

