package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.adapter.BannerHomeTopAdapter;
import com.shashank.platform.classroomappui.adapter.ScheduleAdapter;
import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.base.BaseActivity;
import com.shashank.platform.classroomappui.listeners.OnItemClickListener;
import com.shashank.platform.classroomappui.models.Announcement;
import com.shashank.platform.classroomappui.models.Exam;
import com.shashank.platform.classroomappui.models.NevsUser;
import com.shashank.platform.classroomappui.models.Schedules;
import com.shashank.platform.classroomappui.util.ParamArgus;
import com.shashank.platform.classroomappui.util.PearlTextUtils;
import com.shashank.platform.classroomappui.util.Preferences;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    LinearLayout profile;
    FloatingActionButton fab;
    Toolbar toolbar;
    TextView mTextView;
    TextView mTextViewtime;
    TextView mTextViewAnnouncements;
    TextView mTextViewcontent;
    TextView mTextViewExamTitle;
    TextView mTextViewExamcontent;
    TextView mTextViewExamviewall;
    TextView mTextViewExamends, tv_no_schedules, tv_no_exams, tv_no_announcement;
    ImageView mImageView;
    Bitmap decodedByte;
    ViewPager2 viewPager2;
    DotsIndicator dotsIndicator;
    RecyclerView rv_schedules;
    CardView mcv_exam, mcv_announcement;

    Timer timer;
    ArrayList<Exam> examList = new ArrayList<>();

    ScheduleAdapter scheduleAdapter;
    ArrayList<Schedules> schedulesArrayList = new ArrayList<>();

    private ArrayList<String> bannerList = new ArrayList<>();
    private BannerHomeTopAdapter bannerHomeTopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        initViews();
        setListeners();
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        this.mTextView = findViewById(R.id.announintro);
        rv_schedules = findViewById(R.id.rv_schedules);
        tv_no_exams = findViewById(R.id.tv_no_exams);
        mcv_exam = findViewById(R.id.mcv_exam);
        mcv_announcement = findViewById(R.id.mcv_announcement);
        tv_no_announcement = findViewById(R.id.tv_no_announcement);
        tv_no_schedules = findViewById(R.id.tv_no_schedules);
        this.mTextViewtime = findViewById(R.id.auth);
        this.mTextViewcontent = findViewById(R.id.content1);
        this.mTextViewtime = findViewById(R.id.auth);
        this.mTextViewExamTitle = findViewById(R.id.examtitle);
        this.mTextViewExamcontent = findViewById(R.id.examcontent);
        this.mTextViewExamviewall = findViewById(R.id.viewexams);
        mTextViewAnnouncements = findViewById(R.id.viewannou);
        mTextViewExamends = findViewById(R.id.examends);
        viewPager2 = findViewById(R.id.viewPager2);
        dotsIndicator = findViewById(R.id.dots_indicator);
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        NevsUser nevsUser = Preferences.getUserData();
        String img = nevsUser.getPhoto();
        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        profile = header.findViewById(R.id.profile);
        profile.setOnClickListener(this);
        mImageView = header.findViewById(R.id.profileimg);
        mImageView.setImageBitmap(decodedByte);

        TextView profilename = header.findViewById(R.id.profilename);
        TextView profilemail = header.findViewById(R.id.profilemail);
        TextView tv_snn = header.findViewById(R.id.tv_snn);

        String firstName = PearlTextUtils.isBlank(nevsUser.getFirstName()) ? "" : nevsUser.getFirstName();
        String lastName = PearlTextUtils.isBlank(nevsUser.getLastName()) ? "" : nevsUser.getLastName();
        String snn = PearlTextUtils.isBlank(nevsUser.getSnn()) ? "" : nevsUser.getSnn();

        profilename.setText(firstName + " " + lastName);
        tv_snn.setText(snn);
        profilemail.setText(nevsUser.getEmail());

        getExams();

        try {
            List<Announcement> announcement = Preferences.getUserData().getAnnouncement();
            if (announcement != null && announcement.size() > 0) {
                mTextView.setText(announcement.get(0).getTitle());
                mTextViewtime.setText(announcement.get(0).getIntro());
                mTextViewcontent.setText(announcement.get(0).getDetail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        bannerHomeTopAdapter = new BannerHomeTopAdapter(this, bannerList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        viewPager2.setAdapter(bannerHomeTopAdapter);
        dotsIndicator.setViewPager2(viewPager2);

        scheduleAdapter = new ScheduleAdapter(this, schedulesArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rv_schedules.setAdapter(scheduleAdapter);

        getBanners();
        getSchedules();
    }

    @Override
    public void setListeners() {
        mTextViewExamviewall.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Exams.class);
            if (examList.size() > 0) {
                intent.putExtra(ParamArgus.MODEL_LIST, examList);
            }
            startActivity(intent);
        });
        mTextViewAnnouncements.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Announcements.class);
            startActivity(intent);
        });
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Intent intent = new Intent(getApplicationContext(), Courses.class);
            startActivity(intent);
        } else if (id == R.id.nav_events) {
            Intent intent = new Intent(getApplicationContext(), Events.class);
            startActivity(intent);

        } else if (id == R.id.nav_lectures) {
            Intent intent = new Intent(getApplicationContext(), Lectures.class);
            startActivity(intent);
        } else if (id == R.id.nav_announcements) {
            Intent intent = new Intent(getApplicationContext(), Announcements.class);
            startActivity(intent);
        } else if (id == R.id.nav_subscription) {
            Intent intent = new Intent(getApplicationContext(), SubscriptionsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_revalidation) {
            Intent intent = new Intent(getApplicationContext(), RevalidationActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            Preferences.removePref();
            finish();
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_rate) {
            rateApp();
        } else if (id == R.id.nav_report_bug) {
            Intent intent = new Intent(getApplicationContext(), BugReportActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.profile) {
            Intent intent = new Intent(getApplicationContext(), MyProfile.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }

    private void getExams() {
        String token = Preferences.getUserData().getToken();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Exam>> call = apiService.getExams(token);
        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                if (response.body() != null && response.body().size() > 0) {

                    mcv_exam.setVisibility(View.VISIBLE);
                    tv_no_exams.setVisibility(View.GONE);
                    examList.addAll(response.body());

                    mTextViewExamTitle.setText(examList.get(0).getTitle());
                    mTextViewExamcontent.setText("start : " + examList.get(0).getStart());
                    mTextViewExamends.setText("ends : " + examList.get(0).getEnd());
                }
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {
                mcv_exam.setVisibility(View.GONE);
                tv_no_exams.setVisibility(View.VISIBLE);
                Log.e("TAG", "Response = " + t.toString());

            }
        });
    }

    private void getSchedules() {
        String snn = Preferences.getUserData().getSnn();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Schedules>> call = apiService.getSchedules(snn);
        call.enqueue(new Callback<List<Schedules>>() {
            @Override
            public void onResponse(Call<List<Schedules>> call, Response<List<Schedules>> response) {
                if (response.body() != null && response.body().size() > 0) {
                    schedulesArrayList.addAll(response.body());
                    rv_schedules.setVisibility(View.VISIBLE);
                    tv_no_schedules.setVisibility(View.GONE);

                } else {
                    rv_schedules.setVisibility(View.GONE);
                    tv_no_schedules.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Schedules>> call, Throwable t) {
                Log.e("TAG", "Response = " + t.toString());
                rv_schedules.setVisibility(View.GONE);
                tv_no_schedules.setVisibility(View.VISIBLE);

            }
        });
    }

    private void getBanners() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<String>> call = apiService.getSlidersImages();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.code() == 200) {
                    List<String> banners = response.body();

                    if (banners != null && banners.size() > 0) {
                        bannerList.addAll(banners);
                        bannerHomeTopAdapter.notifyDataSetChanged();

                        if (bannerList.size() > 1) {
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    viewPager2.post(() -> {
                                        viewPager2.setCurrentItem((viewPager2.getCurrentItem() + 1) % bannerList.size());
                                    });
                                }
                            };
                            timer = new Timer();
                            timer.schedule(timerTask, 1000, 3000);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("TAG", "Response = " + t.toString());

            }
        });
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

