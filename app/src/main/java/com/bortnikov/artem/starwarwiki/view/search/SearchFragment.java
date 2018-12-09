package com.bortnikov.artem.starwarwiki.view.search;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bortnikov.artem.starwarwiki.R;
import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;
import com.bortnikov.artem.starwarwiki.presenter.search.SearchPresenter;
import com.bortnikov.artem.starwarwiki.presenter.search.SearchingView;
import com.bortnikov.artem.starwarwiki.view.Adapter;
import com.bortnikov.artem.starwarwiki.view.ItemViewAlertDialogFragment;


import java.util.List;
import java.util.Objects;

public class SearchFragment extends MvpAppCompatFragment implements SearchingView,
        Adapter.OnFeedClickListener,
        SearchView.OnQueryTextListener,
        MenuItem.OnActionExpandListener {

    @InjectPresenter
    SearchPresenter searchPresenter;

    private Adapter adapter = new Adapter(this);

    private RecyclerView searchRecyclerView;

    private TextView nothingFoundTextView;

    private ProgressBar progressBar;

    private MenuItem searchItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.search_progress_bar);

        nothingFoundTextView = view.findViewById(R.id.search_nothing_found_text_view);

        searchRecyclerView = view.findViewById(R.id.search_recycler_view);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        searchRecyclerView.setAdapter(adapter);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onDestroy() {
        super.onDestroy();
        searchItem.setVisible(false);
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setItems(List<DataViewModel> items) {
        adapter.setItems(items);
    }

    @Override
    public void updateList() {
        if (adapter.getItemCount() == 0) {
            nothingFoundTextView.setVisibility(View.VISIBLE);
        } else {
            nothingFoundTextView.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            searchRecyclerView.invalidate();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        searchItem = menu.findItem(R.id.action_search);

        searchItem.setVisible(true);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        searchPresenter.searchNewInfo(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchPresenter.searchNewInfo(s);
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return true;
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
}

