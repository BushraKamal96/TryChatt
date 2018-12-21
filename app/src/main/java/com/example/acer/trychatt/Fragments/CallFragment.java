package com.example.acer.trychatt.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.trychatt.Adapter.CallAdapter;
import com.example.acer.trychatt.R;
import com.example.acer.trychatt.model.callModel;
import com.example.acer.trychatt.myInterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment extends Fragment {

    private RecyclerView recyclerView;
    private CallAdapter myAdapter;
    myInterface myinterfaced;
    Button fab;





    public CallFragment() {
        // Required empty public constructor
    }
    public void setMyinterfaced(myInterface myinterfaced) {
        this.myinterfaced = myinterfaced;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new CallAdapter(prepareData(), getContext());
        recyclerView.setAdapter(myAdapter);

        fab = (Button) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myinterfaced.myMessage("Hello Call Fragment");
            }
        });

        return view;
    }

    private ArrayList<callModel> prepareData() {
        ArrayList<callModel> arrayList = new ArrayList<>();
        callModel model = new callModel(R.drawable.ic_person_black_24dp,"Ushna", "356" );
        arrayList.add(model);
        model = new callModel(R.drawable.ic_person_black_24dp,"Rimsha", "7636" );
        arrayList.add(model);
        model = new callModel(R.drawable.ic_person_black_24dp,"Bushra", "12345" );
        arrayList.add(model);
        model = new callModel(R.drawable.ic_person_black_24dp,"Name 4", "143" );
        arrayList.add(model);

        return arrayList;
    }

    }


