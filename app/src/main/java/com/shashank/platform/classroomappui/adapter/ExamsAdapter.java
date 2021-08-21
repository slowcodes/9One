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
import com.shashank.platform.classroomappui.models.Exam;

import java.util.ArrayList;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.SimpleViewHolder> {

    private final Context mContext;
    private ArrayList<Exam> examArrayList = new ArrayList<>();
    private final OnItemClickListener onItemClickListener;

    public ExamsAdapter(Context context, ArrayList<Exam> arrayList, OnItemClickListener onItemClickListener) {
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

        private final MaterialCardView mcv_exam;
        private final TextView tv_title;
        private final TextView tv_start_date;
        private final TextView tv_end_date;

        public SimpleViewHolder(final View itemView) {
            super(itemView);
            mcv_exam = itemView.findViewById(R.id.mcv_exam);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_start_date = itemView.findViewById(R.id.tv_start_date);
            tv_end_date = itemView.findViewById(R.id.tv_end_date);
        }

        public void bindData(Exam exam) {
            try {
                tv_title.setText(exam.getTitle());
                tv_start_date.setText(exam.getStart());
                tv_end_date.setText(exam.getEnd());

                mcv_exam.setOnClickListener(view -> onItemClickListener.onItemClick(view, getAdapterPosition()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}