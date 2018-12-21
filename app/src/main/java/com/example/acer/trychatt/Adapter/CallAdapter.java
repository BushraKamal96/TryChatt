package com.example.acer.trychatt.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.trychatt.R;
import com.example.acer.trychatt.model.callModel;
import com.example.acer.trychatt.model.callModel;

import java.util.ArrayList;

/**
 * Created by acer on 9/4/2018.
 */

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.MyViewHolder>{

    ArrayList<callModel> arrayList = new ArrayList<>();
    Context context;

    public CallAdapter(ArrayList<callModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.call_recyclerview_output, parent, false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final callModel myData = arrayList.get(position);
        holder.imageView.setImageResource(myData.getImage());
        holder.name.setText(myData.getName());
        holder.number.setText(myData.getNumber());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "calling " +myData.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, number;
        Button call;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.tv1);
            number = (TextView) itemView.findViewById(R.id.tv2);
            call = (Button) itemView.findViewById(R.id.callBtn);
        }}
}
