package com.example.acer.trychatt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.trychatt.Progressbar;
import com.example.acer.trychatt.R;
import com.example.acer.trychatt.model.Story_model;

import java.util.ArrayList;

/**
 * Created by acer on 9/4/2018.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder>{

    ArrayList<Story_model> arrayList = new ArrayList<>();
    Context context;

    public StoryAdapter(ArrayList<Story_model> models, Context context) {
        this.arrayList = models;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Story_model model = arrayList.get(position);
        holder.storyimg.setImageResource(model.getImage());
        holder.storyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(context, Progressbar.class);
                context.startActivity(intents);

            }
        });
        holder.storyname.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
ImageView storyimg;
        TextView storyname;
        View holderview;

        public MyViewHolder(View view) {
            super(view);
            holderview = view;
            storyimg = (ImageView) view.findViewById(R.id.story_img);
            storyname= (TextView) view.findViewById(R.id.story_name);

        }
    }
}
