package com.shashank.platform.classroomappui.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.shashank.platform.classroomappui.R;

public class Courses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
