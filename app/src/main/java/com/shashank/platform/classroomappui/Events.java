package com.shashank.platform.classroomappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

class MyProfile extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

//    ImageView img = (ImageView) findViewById(R.id.profile_setting);
//    img.onClickListener(new void OnClickListener() {
//        public void onClick(View v) {
//            // your code here
//        }
//    });
    @Override
    public void onClick(View view) {
        System.out.println("register click");
        if (view.getId() == R.id.profile_setting) {
            System.out.println("Identified object");
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        }
    }

}
