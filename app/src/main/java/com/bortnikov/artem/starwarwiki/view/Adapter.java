package com.bortnikov.artem.starwarwiki.view;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<DataViewModel> data = new ArrayList<>();
    private OnFeedClickListener listener;

    public Adapter(OnFeedClickListener listener) {
        super();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItems(List<DataViewModel> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnFeedClickListener {
        void onFeedClick(View view,
                         String title,
                         String imageUrl,
                         String birth,
                         String height,
                         String mass,
                         String gender,
                         String hair,
                         String skin);
    }

}

