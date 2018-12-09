package com.bortnikov.artem.starwarwiki.di;

import com.bortnikov.artem.starwarwiki.data.Endpoints;
import com.bortnikov.artem.starwarwiki.data.usecases.DataUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class FeedUseCaseModule {
    @Provides
    @Singleton
    DataUseCase feedUseCase(Endpoints endpoints) {
        return new DataUseCase(endpoints);
    }
}
