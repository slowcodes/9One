package com.shashank.platform.classroomappui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.listeners.OnItemClickListener;
import com.shashank.platform.classroomappui.models.Subscriptions;

import java.util.ArrayList;

public class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionsAdapter.SimpleViewHolder> {

    private final Context mContext;
    private ArrayList<Subscriptions> examArrayList = new ArrayList<>();
    private final OnItemClickListener onItemClickListener;

    public SubscriptionsAdapter(Context context, ArrayList<Subscriptions> arrayList, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.examArrayList = arrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_subscription, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bindData(examArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return examArrayList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {

        private final MaterialCardView mcv_subs;

        public SimpleViewHolder(final View itemView) {
            super(itemView);
            mcv_subs = itemView.findViewById(R.id.mcv_subs);
        }

        public void bindData(Subscriptions subscription) {
            try {
                mcv_subs.setOnClickListener(view -> onItemClickListener.onItemClick(view, getAdapterPosition()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}