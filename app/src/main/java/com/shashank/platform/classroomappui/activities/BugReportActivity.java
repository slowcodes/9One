package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.base.BaseActivity;

public class BugReportActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText edt_title;
    private TextInputEditText edt_des;
    private TextInputEditText edt_device_info;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);

        findViews();
        initViews();
        setListeners();
    }

    private void findViews() {
        edt_title = findViewById(R.id.edt_title);
        edt_des = findViewById(R.id.edt_des);
        edt_device_info = findViewById(R.id.edt_device_info);
        btn_send = findViewById(R.id.btn_send);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setListeners() {
        btn_send.setOnClickListener(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                break;
        }
    }
}