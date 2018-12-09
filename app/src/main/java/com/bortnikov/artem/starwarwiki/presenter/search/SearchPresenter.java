package com.bortnikov.artem.starwarwiki.presenter.search;

import com.arellomobile.mvp.InjectViewState;
import com.bortnikov.artem.starwarwiki.MainApp;
import com.bortnikov.artem.starwarwiki.data.usecases.DataUseCase;
import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;
import com.bortnikov.artem.starwarwiki.presenter.base.BaseRestPresenter;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import javax.inject.Inject;

@InjectViewState
public class SearchPresenter extends BaseRestPresenter<ArrayList<DataViewModel>, SearchingView> {

    @Inject
    DataUseCase usecase;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        MainApp.getComponent().injectsToSearchPresenter(this);
    }

    @Override
    public void attachView(SearchingView view) {
        super.attachView(view);

        loadData();
    }

    public void searchNewInfo(String s) {
        getViewState().startLoading();
        usecase.getSearch(s).subscribe(this);
    }

    private void loadData() {
        getViewState().startLoading();
        usecase.getPeople().subscribe(this);
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(ArrayList<DataViewModel> items) {
        getViewState().setItems(items);
    }

    @Override
    public void onError(Throwable e) {
        getViewState().showError(e);
        getViewState().hideLoading();
    }

    @Override
    public void onComplete() {
        getViewState().updateList();
        getViewState().hideLoading();
    }
}
