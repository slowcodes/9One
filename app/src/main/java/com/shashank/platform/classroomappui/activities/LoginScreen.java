package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shashank.platform.classroomappui.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.models.NevsUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    public static final String Token = "token";

    private EditText mEditTextUsername,mEditTextPassword;

    private Button signIn;
    private TextView signUp;
    private TextView wronglogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mEditTextUsername = findViewById(R.id.email);
        mEditTextPassword = findViewById(R.id.password);
        wronglogin = findViewById(R.id.wronglogin);

        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.sign_up);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    private void loginUser(){

        String username = mEditTextUsername.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();


       String encryptedPassword = md5(password);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<NevsUser> call = apiService.login(username,encryptedPassword);

        call.enqueue(new Callback<NevsUser>() {
            @Override
            public void onResponse(Call<NevsUser> call, Response<NevsUser> response) {
                if(response.code() == 201){
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Token, response.body().getToken());
                    String json = new Gson().toJson(response.body().getAnnouncement());
                    editor.putString( "announcement", json);


                    String img = response.body().getPhoto().replace("data:image/png;base64,","");
                    editor.putString( "image", img);

                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);

                }
                else{
                    wronglogin.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<NevsUser> call, Throwable t) {
                Log.d("nn","Response = "+t.toString());

            }
        });
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


    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
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
