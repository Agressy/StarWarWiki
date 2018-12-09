package com.bortnikov.artem.starwarwiki.di;

import com.bortnikov.artem.starwarwiki.data.Endpoints;
import com.bortnikov.artem.starwarwiki.data.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class DaggerNetModule {
    @Provides
    @Singleton
    Endpoints getEndpoints() {
        return new ServiceGenerator().createService(Endpoints.class);
    }
}