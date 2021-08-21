package com.shashank.platform.classroomappui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.models.Announcement;

import java.util.ArrayList;

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

        // TODO: 18-06-2021 remove comments
        /*recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String announcement = sharedPreferences.getString("announcement", "defv");

            final ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Announcement> announcementArray = objectMapper.readValue(announcement, new TypeReference<ArrayList<Announcement>>() {
            });
            data = announcementArray;
            adapter = new AnnoucementCustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
