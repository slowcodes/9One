package com.shashank.platform.classroomappui.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.models.News;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private ArrayList<News> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = (TextView) itemView.findViewById(R.id.cardtitle);
            this.textViewContent = (TextView) itemView.findViewById(R.id.cardcontent);
        }
    }

    public CustomAdapter(ArrayList<News> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newscardview, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewTitle = holder.textViewTitle;
        TextView textViewContent = holder.textViewContent;

        textViewTitle.setText(dataSet.get(listPosition).getTitle());
        textViewContent.setText(dataSet.get(listPosition).getDetail());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

