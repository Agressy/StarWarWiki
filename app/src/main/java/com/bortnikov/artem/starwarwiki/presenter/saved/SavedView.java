package com.bortnikov.artem.starwarwiki.presenter.saved;

import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;
import com.bortnikov.artem.starwarwiki.presenter.base.BaseRestView;

import java.util.List;

public interface SavedView extends BaseRestView {

    void setItems(List<DataViewModel> items);

    void updateList();
}