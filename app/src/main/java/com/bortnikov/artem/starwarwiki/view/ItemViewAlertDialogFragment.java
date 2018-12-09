package com.bortnikov.artem.starwarwiki.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bortnikov.artem.starwarwiki.R;

import java.util.Objects;

public class ItemViewAlertDialogFragment extends DialogFragment {

    public ItemViewAlertDialogFragment() {
    }

    public static ItemViewAlertDialogFragment newInstance(String title,
                                                          String imageUrl,
                                                          String birth,
                                                          String height,
                                                          String mass,
                                                          String gender,
                                                          String hair,
                                                          String skin) {
        ItemViewAlertDialogFragment frag = new ItemViewAlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("name", title);
        args.putString("imageUrl", imageUrl);
        args.putString("birth", birth);
        args.putString("height", height);
        args.putString("mass", mass);
        args.putString("gender", gender);
        args.putString("hair", hair);
        args.putString("skin", skin);
        frag.setArguments(args);
        return frag;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_view_dialog, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Light_NoTitleBar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button backButton = view.findViewById(R.id.fd_back_button);
        backButton.setOnClickListener(view1 -> getDialog().dismiss());

        TextView nameTextView = view.findViewById(R.id.fd_name_text_view);
        nameTextView.setText(getStringArgument("name", "Name"));

        TextView birthTextView = view.findViewById(R.id.fd_birth_year_text_view);
        birthTextView.setText(getString(R.string.birth_year,
                getStringArgument("birth", "Birth")));

        TextView heightTextView = view.findViewById(R.id.fd_height_text_view);
        heightTextView.setText(getString(R.string.height,
                getStringArgument("height", "Height")));

        TextView massTextView = view.findViewById(R.id.fd_mass_text_view);
        massTextView.setText(getString(R.string.mass,
                getStringArgument("mass", "Mass")));

        TextView genderTextView = view.findViewById(R.id.fd_gender_text_view);
        genderTextView.setText(getString(R.string.gender,
                getStringArgument("gender", "Gender")));

        TextView hairTextView = view.findViewById(R.id.fd_hair_color_text_view);
        hairTextView.setText(getString(R.string.hair_color,
                getStringArgument("hair", "Hair")));

        TextView skinTextView = view.findViewById(R.id.fd_skin_color_text_view);
        skinTextView.setText(getString(R.string.skin_color,
                getStringArgument("skin", "Skin")));

        ImageView photoImageView = view.findViewById(R.id.fd_image_view);
        assert getArguments() != null;
        String imageUrl = getArguments()
                .getString("imageUrl", getString(R.string.placeholder));
        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(new ColorDrawable(Color.GRAY))
                .into(photoImageView);
    }

    private String getStringArgument(String tag, String defaultValue) {
        return Objects.requireNonNull(getArguments()).getString(tag, defaultValue);
    }
}

