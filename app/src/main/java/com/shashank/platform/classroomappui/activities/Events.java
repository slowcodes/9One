package com.shashank.platform.classroomappui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.models.News;
import com.shashank.platform.classroomappui.util.Preferences;

import java.util.ArrayList;

public class Events extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<News> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String token = Preferences.getUserData().getToken();

        // TODO: 18-06-2021
        /*Call<List<News>> call = apiService.getNews(token);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                Log.d("TAG", response.toString());

                data = new ArrayList<News>();
                for (News n : response.body()) {
                    News new1 = new News();
                    new1.setTitle(n.getTitle());
                    new1.setDetail(n.getDetail());
                    data.add(new1);
                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());

            }
        });*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
