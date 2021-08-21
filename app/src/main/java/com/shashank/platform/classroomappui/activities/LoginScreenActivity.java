package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.base.BaseActivity;
import com.shashank.platform.classroomappui.models.NevsUser;
import com.shashank.platform.classroomappui.util.Preferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginScreenActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = LoginScreenActivity.class.getSimpleName();
    //ActivityLoginScreenBinding binding;
    EditText edt_email, edt_password;
    Button sign_in;
    TextView sign_up, wronglogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/
        setContentView(R.layout.activity_login_screen);

        initViews();
        setListeners();

    }

    @Override
    public void initViews() {
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        sign_in = findViewById(R.id.sign_in);
        sign_up = findViewById(R.id.sign_up);
        wronglogin = findViewById(R.id.wronglogin);
    }

    @Override
    public void setListeners() {
        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in) {
            loginUser();

        } else if (view.getId() == R.id.sign_up) {
            Intent intent = new Intent(getApplicationContext(), SignupScreen.class);
            startActivity(intent);
        }
    }

    private void loginUser() {
        showProgressDialog();
        String username = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        String encryptedPassword = md5(password);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<NevsUser> call = apiService.login(username, encryptedPassword);

        call.enqueue(new Callback<NevsUser>() {
            @Override
            public void onResponse(Call<NevsUser> call, Response<NevsUser> response) {
                hideDialog();
                if (response.code() == 201) {
                    if (response.body() instanceof NevsUser) {
                        NevsUser nevsUser = response.body();
                        String img = nevsUser.getPhoto().replace("data:image/png;base64,", "");
                        nevsUser.setPhoto(img);
                        Preferences.setUserData(nevsUser);
                        Preferences.setBoolean(Preferences.KEY_IS_LOGGED_IN, true);
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    try {
                        if (response.body().isError()) {
                            showToastMsg(response.body().getMsg());
                        }
                        wronglogin.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NevsUser> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}