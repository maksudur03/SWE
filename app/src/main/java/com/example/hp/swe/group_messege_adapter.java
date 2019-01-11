package com.example.hp.swe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class group_messege_adapter extends RecyclerView.Adapter<group_messege_adapter.MyViewHolder> {


    private List<ChatMessage> msg_list;
    String id = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rightText,leftText,name;

        public MyViewHolder(View view) {
            super(view);
            rightText = (TextView) view.findViewById(R.id.rightText);
            leftText = (TextView) view.findViewById(R.id.leftText);
            name = (TextView) view.findViewById(R.id.name_left);
        }
    }


    public group_messege_adapter(List<ChatMessage> exam_list,String id) {
        this.msg_list = exam_list;
        this.id = id;
    }

    @NonNull
    @Override
    public group_messege_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.msglist, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull group_messege_adapter.MyViewHolder holder, int i) {
        ChatMessage model = msg_list.get(i);
        if (model.getMsgUser().equals(Group_messege.user_name)) {
                    holder.rightText.setText(model.getMsgText());
                    holder.name.setVisibility(View.GONE);
                    holder.rightText.setVisibility(View.VISIBLE);
                    holder.leftText.setVisibility(View.GONE);
                } else {
                    holder.leftText.setText(model.getMsgText());
                    holder.name.setVisibility(View.VISIBLE);
                    holder.name.setText(model.getMsgUser());
                    holder.rightText.setVisibility(View.GONE);
                    holder.leftText.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public int getItemCount() {
        return msg_list.size();
    }
}
