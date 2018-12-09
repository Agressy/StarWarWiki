package com.bortnikov.artem.starwarwiki.di;

import com.bortnikov.artem.starwarwiki.data.usecases.DataUseCase;
import com.bortnikov.artem.starwarwiki.presenter.saved.SavedPresenter;
import com.bortnikov.artem.starwarwiki.presenter.search.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DaggerNetModule.class, FeedUseCaseModule.class})
public interface AppComponent {
    DataUseCase dataUseCase();

    void injectsToSearchPresenter(SearchPresenter searchPresenter);

    void injectsToSavedPresenter(SavedPresenter savedPresenter);
}