package com.shashank.platform.classroomappui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.models.Announcement;
import com.shashank.platform.classroomappui.models.Exam;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    LinearLayout profile;
    TextView mTextView;
    TextView mTextViewtime;
    TextView mTextViewAnnouncements;
    TextView mTextViewcontent;
    TextView mTextViewExamTitle;
    TextView mTextViewExamcontent;
    TextView mTextViewExamviewall;
    TextView mTextViewExamends;
    ImageView mImageView;
    Bitmap decodedByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.mTextView = findViewById(R.id.announintro);
        this.mTextViewtime = findViewById(R.id.auth);
        this.mTextViewcontent = findViewById(R.id.content1);
        this.mTextViewtime = findViewById(R.id.auth);
        this.mTextViewExamTitle = findViewById(R.id.examtitle);
        this.mTextViewExamcontent = findViewById(R.id.examcontent);
        this.mTextViewExamviewall = findViewById(R.id.viewexams);
        mTextViewAnnouncements =findViewById(R.id.viewannou);
        mTextViewExamends =findViewById(R.id.examends);


        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        String img = sharedPreferences.getString("image","defv");
        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
         decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);






        mTextViewExamviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Exams.class);
                startActivity(intent);
            }
        });
        mTextViewAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Announcements.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        profile = header.findViewById(R.id.profile);
        profile.setOnClickListener(this);
        mImageView = (ImageView)header.findViewById(R.id.profileimg);

        mImageView.setImageBitmap(decodedByte);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        String token = sharedPreferences.getString("token","defv");

        Call<List<Exam>> call = apiService.getExams(token);

        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {

                mTextViewExamTitle.setText(response.body().get(0).getTitle());
                mTextViewExamcontent.setText("start : "+response.body().get(0).getStart());
                mTextViewExamends.setText("ends : "+response.body().get(0).getEnd());

            }
            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());

            }
        });

        try {
            String announcement = sharedPreferences.getString("announcement", "defv");

            final ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Announcement> announcementArray = objectMapper.readValue(announcement, new TypeReference<ArrayList<Announcement>>() {
            });

            mTextView.setText(announcementArray.get(0).getTitle());
            mTextViewtime.setText(announcementArray.get(0).getIntro());
            mTextViewcontent.setText(announcementArray.get(0).getDetail());

        }
     catch(
    JsonProcessingException e)
    {
        e.printStackTrace();
    }

}

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Intent intent = new Intent(getApplicationContext(), Courses.class);
            startActivity(intent);
        } else if (id == R.id.nav_events) {
            Intent intent = new Intent(getApplicationContext(), Events.class);
            startActivity(intent);

        } else if (id == R.id.nav_lectures) {
            Intent intent = new Intent(getApplicationContext(), Lectures.class);
            startActivity(intent);
        } else if (id == R.id.nav_announcements) {
            Intent intent = new Intent(getApplicationContext(), Announcements.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            finish();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.profile) {
            Intent intent = new Intent(getApplicationContext(), MyProfile.class);
            startActivity(intent);
        }

    }
}
