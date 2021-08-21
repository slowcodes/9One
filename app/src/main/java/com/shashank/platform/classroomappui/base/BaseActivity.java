package com.shashank.platform.classroomappui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by Nimesh on 18-06-2021.
 */

public abstract class BaseActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;

    public abstract void initViews();

    public abstract void setListeners();

    public abstract Context getContext();

    public void startActivity(Class aClass, boolean withFinish) {
        startActivity(new Intent(getContext(), aClass));
        if (withFinish)
            finish();
    }

    protected void showProgressDialog() {
        try {
            if (mProgressDialog == null)
                mProgressDialog = ProgressDialog.show(getContext(), "", "Please wait...", false, false);
            if (!mProgressDialog.isShowing())
                mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyBoard(View view, Activity mActivity) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyBoard(View v, Activity mActivity) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
