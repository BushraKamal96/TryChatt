package com.example.acer.trychatt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class Profile_Account extends AppCompatActivity {

    ImageView imageView;
    EditText updates_status ;
    TextView name, num, status;
    String entername, enternum, enterstatus;
    Button upload,pick, save, post, edit_post;
    public static final int PICK_IMAGE = 1;
    public static final int CAPTURE_IMAGE = 2;
    LinearLayout profile, new_status;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);


        preferences = getSharedPreferences(Common.sharedPreferences, MODE_PRIVATE);
        enternum = preferences.getString("Number", null);
        entername = preferences.getString("Name", null);

        imageView = (ImageView) findViewById(R.id.profile_image);
        status = (TextView) findViewById(R.id.quotation);
        name = (TextView) findViewById(R.id.personName);
        num = (TextView) findViewById(R.id.personNumber);
        upload = (Button) findViewById(R.id.pic_upload);
        pick = (Button) findViewById(R.id.pic_pick);
        updates_status= (EditText) findViewById(R.id.new_status);
        post = (Button) findViewById(R.id.update);
        profile = (LinearLayout) findViewById(R.id.profiledetail);
        new_status = (LinearLayout) findViewById(R.id.updatestatus);
        edit_post = (Button) findViewById(R.id.edit);
        edit_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setVisibility(View.GONE);
                new_status.setVisibility(View.VISIBLE);
                String mystatus= updates_status.getText().toString();
                status.setText(mystatus);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setVisibility(View.VISIBLE);
                new_status.setVisibility(View.GONE);


            }
        });

        save = (Button) findViewById(R.id.save_changes);




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent captured = new Intent(ACTION_IMAGE_CAPTURE);
                startActivityForResult(captured, CAPTURE_IMAGE);


            }
        });

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pick = new Intent(Intent.ACTION_PICK);
                pick.setType("image/*");
                startActivityForResult(Intent.createChooser(pick, "Pick image"), PICK_IMAGE);


            }
        });


        name.setText(entername);
        num.setText(enternum);
        status.setText(enterstatus);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entername = name.getText().toString();
                enternum = num.getText().toString();
                enterstatus = status.getText().toString();


                if (entername.isEmpty() || enternum.isEmpty()) {
                    Toast.makeText(Profile_Account.this, "enter data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Profile_Account.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CAPTURE_IMAGE && resultCode == Activity.RESULT_OK && data != null) {


            Bitmap capimg = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(capimg);


        }

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {


            Uri pickimg = data.getData();
            imageView.setImageURI(pickimg);
            preferences.getString("Image", pickimg.toString());

            //preferences.commit();
        }

    }
}
