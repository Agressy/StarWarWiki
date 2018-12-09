package com.bortnikov.artem.starwarwiki.data.model.realm;

import javax.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmModel extends RealmObject {

    @PrimaryKey
    public int id;

    @Nullable
    public String name;
    @Nullable
    public String height;
    @Nullable
    public String mass;
    @Nullable
    public String hair;
    @Nullable
    public String skin;
    @Nullable
    public String eye;
    @Nullable
    public String birth;
    @Nullable
    public String gender;
    @Nullable
    public String url;

}
