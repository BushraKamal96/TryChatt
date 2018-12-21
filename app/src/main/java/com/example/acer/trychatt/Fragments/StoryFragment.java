package com.example.acer.trychatt.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.trychatt.Adapter.StoryAdapter;
import com.example.acer.trychatt.R;
import com.example.acer.trychatt.model.Story_model;
import com.example.acer.trychatt.myInterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private StoryAdapter myAdapter;
    myInterface myinterfaced;
    Button fab;

    public StoryFragment() {
        // Required empty public constructor

    }
    public void setMyinterfaced(myInterface myinterfaced) {
        this.myinterfaced = myinterfaced;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_story, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new StoryAdapter(prepareData(), getContext());
        recyclerView.setAdapter(myAdapter);

        fab= (Button)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myinterfaced.myMessage("Hello Status Fragment");
            }
        });

        return view;    }

    private ArrayList<Story_model> prepareData() {
        ArrayList<Story_model> arrayList = new ArrayList<>();
        Story_model model = new Story_model(R.drawable.ic_person_black_24dp, "Name 1");
        arrayList.add(model);
        model = new Story_model(R.drawable.ic_person_black_24dp, "Name 2");
        arrayList.add(model);
        model = new Story_model(R.drawable.ic_person_black_24dp, "Name 3");
        arrayList.add(model);
        return arrayList;
    }
    }


