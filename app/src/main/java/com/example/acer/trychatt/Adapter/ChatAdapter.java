package com.example.acer.trychatt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.trychatt.ChatBox;
import com.example.acer.trychatt.R;
import com.example.acer.trychatt.model.chatModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by acer on 9/3/2018.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    ArrayList<chatModel> arrayList = new ArrayList<>();
    Context context;
    FirebaseUser currentuser;
    DatabaseReference firebaseDatabase;

    public ChatAdapter(ArrayList<chatModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        currentuser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final chatModel myData = arrayList.get(position);

        Picasso.get().load(myData.getImage()).into(holder.imageView);
        holder.name.setText(myData.getName());

        if (myData.getStatus().equals("online"))
            holder.online_icon.setVisibility(View.VISIBLE);
        else holder.online_icon.setVisibility(View.INVISIBLE);


        holder.holderview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatBox.class);
                intent.putExtra("name", myData.getName());
                intent.putExtra("user_id", myData.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View holderview;
        ImageView imageView, online_icon;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            holderview = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.image);
            online_icon = (ImageView) itemView.findViewById(R.id.online);
            name = (TextView) itemView.findViewById(R.id.tv1);
        }
    }
}
