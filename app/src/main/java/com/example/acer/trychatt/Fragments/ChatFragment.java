package com.example.acer.trychatt.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.trychatt.Adapter.ChatAdapter;
import com.example.acer.trychatt.R;
import com.example.acer.trychatt.model.chatModel;
import com.example.acer.trychatt.myInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatFragment extends Fragment {
    RecyclerView recyclerView;
    Button fab;
    ChatAdapter myAdapter;
    DatabaseReference databaseReference;
    ArrayList<chatModel> arrayList = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    DatabaseReference forlogindbreference;
    FirebaseUser firebaseUser;
    myInterface myinterfaced;

    public void setMyinterfaced(myInterface myinterfaced) {
        this.myinterfaced = myinterfaced;
    }

    public ChatFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserTable");

        recyclerView = (RecyclerView) view.findViewById(R.id.chat_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new ChatAdapter(arrayList, getContext());
        recyclerView.setAdapter(myAdapter);

        if (arrayList.size() <= 0)
            prepareData();


        fab = (Button) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myinterfaced.myMessage("Hello Chat Fragment");
            }
        });

        return view;
    }

    private void prepareData() {

        arrayList.clear();
        myAdapter.notifyDataSetChanged();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                firebaseUser = firebaseAuth.getCurrentUser();

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    chatModel model = data.getValue(chatModel.class);
                    if (firebaseUser != null) {
                        if (!model.getId().equals(firebaseUser.getUid())) {
                            arrayList.add(model);
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                    Log.e("arraylistsize", "" + String.valueOf(arrayList.size()));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e("Exception", databaseError.getMessage());

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        forlogindbreference = FirebaseDatabase.getInstance().getReference().child("UserTable").child(firebaseAuth.getCurrentUser().getUid());
        if (firebaseAuth.getCurrentUser() != null) {
            forlogindbreference.child("status").setValue("online");

        }
    }

    @Override
    public void onStop() {
        super.onStop();

        arrayList.clear();
        myAdapter.notifyDataSetChanged();

        Log.e("arraylist size onStop", "" + String.valueOf(arrayList.size()));

        if (firebaseAuth.getCurrentUser() != null) {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("status").setValue("offline").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        Log.e("offlineupdate", "true");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("exception", e.getMessage());

                }
            });
        } else {
            Log.e("UserUtil", "Null");
        }

    }
}
