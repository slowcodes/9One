package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.base.BaseActivity;
import com.shashank.platform.classroomappui.util.Preferences;

public class Settings extends BaseActivity implements View.OnClickListener {

    private TextView tv_app_version;
    private MaterialCardView mcv_rate_me;
    private MaterialCardView mcv_share_app;
    private MaterialCardView mcv_email_feedback;
    private MaterialCardView mcv_report_bug;
    private MaterialCardView mcv_send_scholarship_noti;
    private SwitchMaterial switch_scholarships;
    private MaterialCardView mcv_send_revalidation_notice;
    private SwitchMaterial switch_revalidation;
    private MaterialCardView mcv_share_record;
    private SwitchMaterial switch_share_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViews();
        initViews();
        setListeners();
    }

    private void findViews() {
        tv_app_version = findViewById(R.id.tv_app_version);
        mcv_rate_me = findViewById(R.id.mcv_rate_me);
        mcv_share_app = findViewById(R.id.mcv_share_app);
        mcv_email_feedback = findViewById(R.id.mcv_email_feedback);
        mcv_report_bug = findViewById(R.id.mcv_report_bug);
        mcv_send_scholarship_noti = findViewById(R.id.mcv_send_scholarship_noti);
        switch_scholarships = findViewById(R.id.switch_scholarships);
        mcv_send_revalidation_notice = findViewById(R.id.mcv_send_revalidation_notice);
        switch_revalidation = findViewById(R.id.switch_revalidation);
        mcv_share_record = findViewById(R.id.mcv_share_record);
        switch_share_record = findViewById(R.id.switch_share_record);
    }

    @Override
    public void initViews() {
    }

    @Override
    public void setListeners() {
        mcv_rate_me.setOnClickListener(this);
        mcv_share_app.setOnClickListener(this);
        mcv_email_feedback.setOnClickListener(this);
        mcv_report_bug.setOnClickListener(this);
        mcv_send_scholarship_noti.setOnClickListener(this);
        mcv_send_revalidation_notice.setOnClickListener(this);
        mcv_share_record.setOnClickListener(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mcv_rate_me:
                rateApp();
                break;
            case R.id.mcv_share_app:
                shareApp();
                break;
            case R.id.mcv_report_bug:
                Intent intent = new Intent(getApplicationContext(), BugReportActivity.class);
                startActivity(intent);
                break;
            case R.id.switch_scholarships:
                Preferences.setBoolean(Preferences.KEY_SEND_NOTI_SCHOLARSHIPS, switch_scholarships.isChecked());
                break;
            case R.id.switch_revalidation:
                Preferences.setBoolean(Preferences.KEY_REVALIDATION, switch_scholarships.isChecked());
                shareApp();
                break;
            case R.id.switch_share_record:
                Preferences.setBoolean(Preferences.KEY_SHARE_RECORD, switch_scholarships.isChecked());
                shareApp();
                break;
        }
    }

    private void shareApp() {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                intent.putExtra(Intent.EXTRA_TEXT, "Its awesome, Check out this app! \nDownload it for free at \nhttp://play.google.com/store/apps/details?id=" + getPackageName());
                intent.setType("text/plain");
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }

    public void rateApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }
}
