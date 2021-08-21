package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.adapter.ExamsAdapter;
import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.base.BaseActivity;
import com.shashank.platform.classroomappui.listeners.OnItemClickListener;
import com.shashank.platform.classroomappui.models.Exam;
import com.shashank.platform.classroomappui.util.ParamArgus;
import com.shashank.platform.classroomappui.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Exams extends BaseActivity {

    private static RecyclerView rv_exams;
    ExamsAdapter examsAdapter;
    private ArrayList<Exam> examArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        findViews();
        initViews();
        setListeners();
    }

    private void findViews() {
        rv_exams = findViewById(R.id.rv_exams);
    }

    @Override
    public void initViews() {
        examsAdapter = new ExamsAdapter(this, examArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rv_exams.setItemAnimator(new DefaultItemAnimator());
        rv_exams.setAdapter(examsAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ArrayList<Exam> exams;
            exams = (ArrayList<Exam>) bundle.getSerializable(ParamArgus.MODEL_LIST);
            examArrayList.addAll(exams);
        } else {
            getExams();
        }
    }

    @Override
    public void setListeners() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    private void getExams() {
        showProgressDialog();
        String token = Preferences.getUserData().getToken();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Exam>> call = apiService.getExams(token);
        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                hideDialog();
                if (response.body() != null && response.body().size() > 0) {
                    examArrayList.addAll(response.body());
                    examsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {
                hideDialog();
                Log.e("TAG", "Response = " + t.toString());

            }
        });
    }
}