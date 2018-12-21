package com.example.acer.trychatt.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.trychatt.R;

import java.util.ArrayList;

/**
 * Created by acer on 9/6/2018.
 */

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.MyHolderView>{
ArrayList<String> arrayList = new ArrayList<>();
    Context context;

    public ChattingAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chatting_layout, parent, false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(MyHolderView holder, int position) {
        String box = arrayList.get(position);
        holder.textmessage.setText(box);

    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder{
        TextView textmessage;
        View holderview;

        public MyHolderView(View view) {
            super(view);

            holderview = view;
            textmessage = (TextView) view.findViewById(R.id.textmsg);
        }
    }
}
