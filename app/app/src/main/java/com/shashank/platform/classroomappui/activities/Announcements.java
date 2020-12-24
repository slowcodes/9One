package com.shashank.platform.classroomappui.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.models.Announcement;
import com.shashank.platform.classroomappui.models.News;
import com.shashank.platform.classroomappui.util.AnnoucementCustomAdapter;
import com.shashank.platform.classroomappui.util.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class Announcements extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Announcement> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        try {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());


        String announcement = sharedPreferences.getString("announcement","defv");

            final ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Announcement> announcementArray = objectMapper.readValue(announcement, new TypeReference<ArrayList<Announcement>>(){});
            data = announcementArray;

            adapter = new AnnoucementCustomAdapter(data);
            recyclerView.setAdapter(adapter);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
