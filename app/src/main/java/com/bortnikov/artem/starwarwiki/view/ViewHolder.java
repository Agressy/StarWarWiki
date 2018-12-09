package com.bortnikov.artem.starwarwiki.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bortnikov.artem.starwarwiki.R;
import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;

public class ViewHolder extends RecyclerView.ViewHolder {

    private View root;
    private ImageView image;
    private TextView title;

    private DataViewModel model;
    private Adapter.OnFeedClickListener listener;

    private ViewHolder(View view) {
        super(view);
        root = view;
        root.setOnClickListener(view1 -> {
                    if (listener != null) {
                        listener.onFeedClick(view,
                                             model.getName(),
                                             String.valueOf(model.getImageLink()),
                                             model.getBirth(),
                                             model.getHeight(),
                                             model.getMass(),
                                             model.getGender(),
                                             model.getHair(),
                                             model.getSkin());
                    }
                }
        );
        image = root.findViewById(R.id.rv_item_image);
        title = root.findViewById(R.id.rv_item_title);
    }

    void bind(DataViewModel model, Adapter.OnFeedClickListener listener) {
        this.listener = listener;
        this.model = model;
        title.setText(model.getName());
        setImage(model.getImageLink());
    }

    public void setImage(String imageLink) {
        GlideApp.with(root.getContext())
                .load(imageLink)
                .placeholder(new ColorDrawable(Color.GRAY))
                .centerCrop()
                .into(image);
    }

    static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item, parent, false));
    }
}
