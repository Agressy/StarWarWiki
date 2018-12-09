package com.bortnikov.artem.starwarwiki.data.usecases;


import com.bortnikov.artem.starwarwiki.data.Endpoints;
import com.bortnikov.artem.starwarwiki.data.model.realm.RealmModel;
import com.bortnikov.artem.starwarwiki.data.model.retrofit.Result;
import com.bortnikov.artem.starwarwiki.data.view.DataViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class DataUseCase {

    private Endpoints endpoints;

    public DataUseCase(Endpoints endpoints) {
        this.endpoints = endpoints;
    }

    public Flowable<ArrayList<DataViewModel>> getPeople() {
        return endpoints.getPeopleResponse()
                .map(searchList -> {
                    List<Result> feed = searchList.getResults();
                    ArrayList<DataViewModel> result = new ArrayList<>();
                    int i = 0;
                    for (Result r : feed) {
                        DataViewModel dataItem = new DataViewModel(i,
                                String.valueOf(r.getName()),
                                String.valueOf(r.getHeight()),
                                String.valueOf(r.getMass()),
                                String.valueOf(r.getHairColor()),
                                String.valueOf(r.getSkinColor()),
                                String.valueOf(r.getEyeColor()),
                                String.valueOf(r.getBirthYear()),
                                String.valueOf(r.getGender()),
                                String.valueOf(r.getUrl()));
                        result.add(dataItem);
                        i++;
                    }
                    return result;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ArrayList<DataViewModel>> getSearch(String tag) {
        return endpoints.getSearchResponse(tag)
                .map(searchList -> {
                    List<Result> feed = searchList.getResults();
                    ArrayList<DataViewModel> result = new ArrayList<>();
                    int i = 0;
                    for (Result r : feed) {
                        DataViewModel dataItem = new DataViewModel(i,
                                String.valueOf(r.getName()),
                                String.valueOf(r.getHeight()),
                                String.valueOf(r.getMass()),
                                String.valueOf(r.getHairColor()),
                                String.valueOf(r.getSkinColor()),
                                String.valueOf(r.getEyeColor()),
                                String.valueOf(r.getBirthYear()),
                                String.valueOf(r.getGender()),
                                String.valueOf(r.getUrl()));
                        result.add(dataItem);
                        i++;
                    }

                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransactionAsync((Realm realm1) -> {
                        for (Result r : feed) {
                            RealmModel realmItem = new RealmModel();
                            String[] separated = r.getUrl().split("/");
                            realmItem.id = Integer.parseInt(separated[separated.length - 1]);
                            realmItem.name = (String.valueOf(r.getName()));
                            realmItem.height = (String.valueOf(r.getHeight()));
                            realmItem.mass = (String.valueOf(r.getMass()));
                            realmItem.hair = (String.valueOf(r.getHairColor()));
                            realmItem.skin = (String.valueOf(r.getSkinColor()));
                            realmItem.eye = (String.valueOf(r.getEyeColor()));
                            realmItem.birth = (String.valueOf(r.getBirthYear()));
                            realmItem.gender = (String.valueOf(r.getGender()));
                            realmItem.url = (String.valueOf(r.getUrl()));
                            realm1.copyToRealmOrUpdate(realmItem);
                        }
                    });
                    realm.close();
                    return result;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
