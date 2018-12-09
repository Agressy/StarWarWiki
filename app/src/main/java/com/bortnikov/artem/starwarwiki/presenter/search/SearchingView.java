package com.bortnikov.artem.starwarwiki.presenter.search;

import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;
import com.bortnikov.artem.starwarwiki.presenter.base.BaseRestView;

import java.util.List;

public interface SearchingView extends BaseRestView {

    void setItems(List<DataViewModel> items);

    void updateList();
}