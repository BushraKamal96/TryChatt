package com.example.acer.trychatt;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.daasuu.bl.ArrowDirection;
import com.example.acer.trychatt.Adapter.MyVH;
import com.example.acer.trychatt.model.ChatMessage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChatBox extends AppCompatActivity {

    FirebaseRecyclerAdapter<ChatMessage, MyVH> adapter;
    RelativeLayout main;
    Button submit_button;
    EditText emojiconEditText;
    String username;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_box);

        toolbar = findViewById(R.id.page_toolbar);
        setSupportActionBar(toolbar);

        username = getIntent().getExtras().getString("name");
        toolbar.setTitle(username);

        submit_button = findViewById(R.id.submit_button);
        emojiconEditText = findViewById(R.id.emojicon_edit_text);

        displayMessages();

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Messages").push().setValue(new ChatMessage(emojiconEditText.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                emojiconEditText.setText("");
                emojiconEditText.requestFocus();
            }
        });

    }



    private void displayMessages() {

        final RecyclerView listOfMessage = findViewById(R.id.list_of_message);
        listOfMessage.setLayoutManager(new LinearLayoutManager(ChatBox.this));

        Query query = FirebaseDatabase.getInstance().getReference("Messages");

        FirebaseRecyclerOptions<ChatMessage> options = new FirebaseRecyclerOptions.Builder<ChatMessage>()
                .setQuery(query, ChatMessage.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<ChatMessage, MyVH>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyVH holder, int position, @NonNull ChatMessage model) {
                holder.message.setText(model.getMessageText());
                holder.messageTime.setText(DateFormat.format("dd-MM-yy (HH:mm)", model.getMessageTime()));
                holder.messageUserName.setText(model.getMessageUser());

                if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(model.getMessageUser())){
                    holder.bubbleLayout.setArrowDirection(ArrowDirection.RIGHT);
                    holder.linearLayout.setGravity(Gravity.RIGHT);
                }


                listOfMessage.scrollToPosition(position);
            }

            @NonNull
            @Override
            public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false));
            }
        };


        adapter.startListening();
        listOfMessage.setAdapter(adapter);

    }


    }


