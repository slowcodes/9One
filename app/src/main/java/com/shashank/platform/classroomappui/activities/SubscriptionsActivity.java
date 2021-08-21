package com.shashank.platform.classroomappui.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.adapter.SubscriptionsAdapter;
import com.shashank.platform.classroomappui.base.BaseActivity;
import com.shashank.platform.classroomappui.listeners.OnItemClickListener;
import com.shashank.platform.classroomappui.models.Subscriptions;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class SubscriptionsActivity extends BaseActivity implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder> {

    DiscreteScrollView discreteScrollView;
    private InfiniteScrollAdapter infiniteAdapter;
    ArrayList<Subscriptions> subscriptionsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);

        findViews();
        initViews();
        setListeners();
    }

    private void findViews() {
        discreteScrollView = findViewById(R.id.discreteScrollView);
    }

    @Override
    public void initViews() {

        for (int i = 0; i < 3; i++) {
            Subscriptions subscriptions = new Subscriptions();
            subscriptionsArrayList.add(subscriptions);
        }

        discreteScrollView.setSlideOnFling(true);
        discreteScrollView.setOrientation(DSVOrientation.HORIZONTAL);
        discreteScrollView.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new SubscriptionsAdapter(this, subscriptionsArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        discreteScrollView.setAdapter(infiniteAdapter);
        discreteScrollView.setItemTransitionTimeMillis(800);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        discreteScrollView.setSlideOnFlingThreshold(300);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onCurrentItemChanged(@Nullable @org.jetbrains.annotations.Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }
}