package com.example.acer.trychatt;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity implements myInterface {

    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tablayout;
    PagerAdapter pagerAdapter;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserTable");


        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        tablayout = (TabLayout) findViewById(R.id.main_tab);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Home.this,
                            R.color.colorAccent));
                    tablayout.setBackgroundColor(ContextCompat.getColor(Home.this,
                            R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this,
                                R.color.colorPrimaryDark));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Home.this,
                            R.color.myColor));
                    tablayout.setBackgroundColor(ContextCompat.getColor(Home.this,
                            R.color.myColor));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this,
                                R.color.colorPrimaryDark));
                    }
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Home.this,
                            R.color.colorPrimary));
                    tablayout.setBackgroundColor(ContextCompat.getColor(Home.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this,
                                R.color.colorPrimaryDark));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //no inspection Simplifiable If Statement
        if (id == R.id.profile) {
            Intent intent = new Intent(Home.this, Profile_Account.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.Logout) {

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
            firebaseAuth.signOut();
            startActivity(new Intent(Home.this, Create_account.class));
            finish();


            Toast.makeText(this, "action logout", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


//    @Override
//    public void myMessage(String message) {
//
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }

//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserTable").child(firebaseAuth.getCurrentUser().getUid());
//        if (firebaseAuth.getCurrentUser() != null) {
//            databaseReference.child("status").setValue("online");
//
//        }
//    }

    @Override
    public void myMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

