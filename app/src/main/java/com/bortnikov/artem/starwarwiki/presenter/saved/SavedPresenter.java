package com.bortnikov.artem.starwarwiki.presenter.saved;

import com.arellomobile.mvp.InjectViewState;
import com.bortnikov.artem.starwarwiki.MainApp;
import com.bortnikov.artem.starwarwiki.data.model.realm.RealmModel;
import com.bortnikov.artem.starwarwiki.data.usecases.DataUseCase;
import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;
import com.bortnikov.artem.starwarwiki.presenter.base.BaseRestPresenter;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class SavedPresenter extends BaseRestPresenter<ArrayList<DataViewModel>, SavedView> {

    @Inject
    DataUseCase usecase;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        MainApp.getComponent().injectsToSavedPresenter(this);
        setData();
    }

    @Override
    public void attachView(SavedView view) {
        super.attachView(view);
    }

    private void setData() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmModel> feed = realm.where(RealmModel.class).findAll();
        ArrayList<DataViewModel> result = new ArrayList<>();
        for (RealmModel curItem : feed) {
            result.add(new DataViewModel(curItem));
        }
        getViewState().setItems(result);
        realm.close();
    }

    @Override
    public void onSubscribe(Subscription s) {
    }

    @Override
    public void onNext(ArrayList<DataViewModel> items) {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
