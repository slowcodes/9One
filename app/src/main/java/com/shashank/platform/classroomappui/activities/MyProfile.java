package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.base.BaseActivity;
import com.shashank.platform.classroomappui.models.NevsUser;
import com.shashank.platform.classroomappui.util.Preferences;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends BaseActivity {

    private RelativeLayout rellay1;
    private RelativeLayout imgUser;
    private CircleImageView civ_photo;
    private TextView tv_name;
    private TextView tv_address;
    private ImageView profile_setting;
    private LinearLayout linlay1;
    private TextView tv_email;
    private TextView tv_phone;
    private TextView tv_dob;
    private TextView tv_sex;
    private TextView tv_religion;
    private TextView tv_marital_status;
    private TextView tv_home_town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_profile);

        findViews();
        initViews();
        setListeners();
    }

    private void findViews() {
        rellay1 = findViewById(R.id.rellay1);
        imgUser = findViewById(R.id.imgUser);
        civ_photo = findViewById(R.id.civ_photo);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        profile_setting = findViewById(R.id.profile_setting);
        linlay1 = findViewById(R.id.linlay1);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_dob = findViewById(R.id.tv_dob);
        tv_sex = findViewById(R.id.tv_sex);
        tv_religion = findViewById(R.id.tv_religion);
        tv_marital_status = findViewById(R.id.tv_marital_status);
        tv_home_town = findViewById(R.id.tv_home_town);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void initViews() {
        NevsUser nevsUser = Preferences.getUserData();
        if (nevsUser != null) {
            String img = nevsUser.getPhoto();
            byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            civ_photo.setImageBitmap(decodedByte);

            tv_name.setText("" + nevsUser.getFirstName() + " " + nevsUser.getLastName());
            tv_address.setText("" + nevsUser.getAddress());
            tv_email.setText("" + nevsUser.getEmail());
            tv_phone.setText("" + nevsUser.getPhone());
            tv_dob.setText("" + nevsUser.getDob());

            tv_sex.setText("" + nevsUser.getSex());
            tv_religion.setText("" + nevsUser.getReligion());
            tv_marital_status.setText("" + nevsUser.getMarritalStatus());
            tv_home_town.setText("" + nevsUser.getHome_town());
        }
    }

    @Override
    public void setListeners() {
        findViewById(R.id.profile_setting).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
        });
    }

    @Override
    public Context getContext() {
        return this;
    }
}
