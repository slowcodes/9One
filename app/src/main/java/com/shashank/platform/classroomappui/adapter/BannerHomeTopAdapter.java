package com.shashank.platform.classroomappui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.listeners.OnItemClickListener;

import java.util.ArrayList;

public class BannerHomeTopAdapter extends RecyclerView.Adapter<BannerHomeTopAdapter.SimpleViewHolder> {

    private Context mContext;
    private ArrayList<String> arrayList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public BannerHomeTopAdapter(Context context, ArrayList<String> arrayList, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.arrayList = arrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_home_top, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bindData(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_banner;
        ConstraintLayout cl_banner;

        public SimpleViewHolder(final View itemView) {
            super(itemView);
            iv_banner = itemView.findViewById(R.id.iv_banner);
            cl_banner = itemView.findViewById(R.id.cl_banner);
        }

        public void bindData(String data) {
            try {
                Glide.with(mContext)
                        .load(data)
                        .placeholder(R.drawable.ic_placeholder_banner)
                        .error(R.drawable.ic_placeholder_banner)
                        .into(iv_banner);
                cl_banner.setOnClickListener(view -> onItemClickListener.onItemClick(view, getAdapterPosition()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}