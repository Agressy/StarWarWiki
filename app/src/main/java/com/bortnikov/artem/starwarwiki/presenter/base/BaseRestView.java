package com.bortnikov.artem.starwarwiki.presenter.base;

import com.arellomobile.mvp.MvpView;

public interface BaseRestView extends MvpView {
    void startLoading();
    void hideLoading();
    void showError(Throwable ex);

}

