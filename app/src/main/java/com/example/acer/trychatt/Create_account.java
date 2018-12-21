package com.example.acer.trychatt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.signin.SignIn;

public class Create_account extends AppCompatActivity {

    Button signin, login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);

        signin = (Button) findViewById(R.id.signUpBtn);
        login = (Button) findViewById(R.id.loginBtn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Create_account.this, Signin.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Create_account.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }


