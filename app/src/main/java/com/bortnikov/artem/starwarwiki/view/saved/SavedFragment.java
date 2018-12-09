package com.bortnikov.artem.starwarwiki.view.saved;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bortnikov.artem.starwarwiki.R;
import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;
import com.bortnikov.artem.starwarwiki.presenter.saved.SavedPresenter;
import com.bortnikov.artem.starwarwiki.presenter.saved.SavedView;
import com.bortnikov.artem.starwarwiki.view.Adapter;
import com.bortnikov.artem.starwarwiki.view.ItemViewAlertDialogFragment;

import java.util.List;
import java.util.Objects;

public class SavedFragment extends MvpAppCompatFragment implements SavedView,
        Adapter.OnFeedClickListener {

    @InjectPresenter
    SavedPresenter savedPresenter;

    private Adapter adapter = new Adapter(this);

    private TextView nothingFoundTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nothingFoundTextView = view.findViewById(R.id.saved_nothing_found_text_view);

        RecyclerView archiveRecyclerView = view.findViewById(R.id.saved_recycler_view);
        archiveRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        archiveRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setItems(List<DataViewModel> items) {
        adapter.setItems(items);
        if (adapter.getItemCount() == 0) {
            nothingFoundTextView.setVisibility(View.VISIBLE);
        } else {
            nothingFoundTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFeedClick(View clickSource,
                            String name,
                            String imageUrl,
                            String birth,
                            String height,
                            String mass,
                            String gender,
                            String hair,
                            String skin) {
        int[] clickCoords = new int[2];
        clickSource.getLocationOnScreen(clickCoords);
        clickCoords[0] += clickSource.getWidth() / 2;
        clickCoords[1] += clickSource.getHeight() / 2;
        performRevealAnimation(Objects.requireNonNull(this.getView()),
                clickCoords[0],
                clickCoords[1]);

        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        ItemViewAlertDialogFragment alertDialog = ItemViewAlertDialogFragment
                .newInstance(name, imageUrl, birth, height, mass, gender, hair, skin);
        alertDialog.show(fm, "alert_dialog");
    }

    private void performRevealAnimation(View view, int screenCenterX, int screenCenterY) {
        int[] animatingViewCoords = new int[2];
        view.getLocationOnScreen(animatingViewCoords);
        int centerX = screenCenterX - animatingViewCoords[0];
        int centerY = screenCenterY - animatingViewCoords[1];
        Point size = new Point();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getSize(size);
        int maxRadius = size.y;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0, maxRadius)
                    .start();
        }

    }

    @Override
    public void updateList() {
    }

    @Override
    public void startLoading() {
    }

    @Override
    public void hideLoading() {
    }
}

