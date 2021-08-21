package com.shashank.platform.classroomappui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.listeners.OnItemClickListener;
import com.shashank.platform.classroomappui.models.Schedules;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.SimpleViewHolder> {

    private final Context mContext;
    private ArrayList<Schedules> examArrayList = new ArrayList<>();
    private final OnItemClickListener onItemClickListener;

    public ScheduleAdapter(Context context, ArrayList<Schedules> arrayList, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.examArrayList = arrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_exam, parent, false);
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

        private final MaterialCardView mcv_schedule;
        private final TextView tv_title;
        private final TextView tv_des;
        private final TextView tv_time;
        private final TextView tv_date;
        private final TextView tv_address;
        private final TextView tv_lecture;

        public SimpleViewHolder(final View itemView) {
            super(itemView);
            mcv_schedule = itemView.findViewById(R.id.mcv_schedule);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_des = itemView.findViewById(R.id.tv_des);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_lecture = itemView.findViewById(R.id.tv_lecture);
        }

        public void bindData(Schedules schedules) {
            try {
                tv_title.setText(schedules.getTitle());
                tv_des.setText(schedules.getDescription());

                mcv_schedule.setOnClickListener(view -> onItemClickListener.onItemClick(view, getAdapterPosition()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}