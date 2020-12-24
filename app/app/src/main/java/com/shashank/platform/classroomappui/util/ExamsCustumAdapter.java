package com.shashank.platform.classroomappui.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.models.Exam;

import java.util.ArrayList;

public class ExamsCustumAdapter extends  RecyclerView.Adapter<ExamsCustumAdapter.MyViewHolder>{
    private ArrayList<Exam> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewContent;
        TextView textViewTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = (TextView) itemView.findViewById(R.id.examtitle);
            this.textViewContent = (TextView) itemView.findViewById(R.id.examcontent);
            this.textViewTime = (TextView) itemView.findViewById(R.id.time1);

        }
    }

    public ExamsCustumAdapter(ArrayList<Exam> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcementcard, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewTitle = holder.textViewTitle;
        TextView textViewContent = holder.textViewContent;
        TextView textViewTime = holder.textViewTime;


        textViewContent.setText(dataSet.get(listPosition).getTitle());
        textViewTime.setText(dataSet.get(listPosition).getStart());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
