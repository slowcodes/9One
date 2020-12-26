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
import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.models.Announcement;
import com.shashank.platform.classroomappui.models.Exam;
import com.shashank.platform.classroomappui.models.News;
import com.shashank.platform.classroomappui.util.AnnoucementCustomAdapter;
import com.shashank.platform.classroomappui.util.CustomAdapter;
import com.shashank.platform.classroomappui.util.ExamsCustumAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Exams extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Exam> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        String token = sharedPreferences.getString("token","defv");

        Call<List<Exam>> call = apiService.getExams(token);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {

                Log.d("TAG",response.toString());

                data = new ArrayList<Exam>();
                for (Exam n :  response.body()) {
                    Exam new1 = new Exam();
                    new1.setTitle(n.getTitle());
                    new1.setStart(n.getStart());
                    data.add(new1);
                }
                adapter = new ExamsCustumAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {

            }

        });


    }
}