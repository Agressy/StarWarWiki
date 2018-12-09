package com.bortnikov.artem.starwarwiki;

import android.app.Application;

import com.bortnikov.artem.starwarwiki.di.AppComponent;
import com.bortnikov.artem.starwarwiki.di.DaggerAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainApp extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        component = DaggerAppComponent.builder().build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}