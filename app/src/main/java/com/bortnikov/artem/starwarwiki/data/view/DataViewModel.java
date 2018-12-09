package com.bortnikov.artem.starwarwiki.data.view;

import com.bortnikov.artem.starwarwiki.data.model.realm.RealmModel;

import java.util.Locale;

public class DataViewModel {
    private int id;
    private String name;
    private String height;
    private String mass;
    private String hair;
    private String skin;
    private String eye;
    private String birth;
    private String gender;
    private String url;

    public DataViewModel(int id,
                         String name,
                         String height,
                         String mass,
                         String hair,
                         String skin,
                         String eye,
                         String birth,
                         String gender,
                         String url) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair = hair;
        this.skin = skin;
        this.eye = eye;
        this.birth = birth;
        this.gender = gender;
        this.url = url;
    }

    public DataViewModel(RealmModel model) {
        this(model.id,
                model.name,
                model.height,
                model.mass,
                model.hair,
                model.skin,
                model.eye,
                model.birth,
                model.gender,
                model.url);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUrl(String index) {
        this.url = index;
    }

    public String getUrl() {
        return url;
    }

    private int splitIndex(String url) {
        String[] separated = url.split("/");
        return Integer.parseInt(separated[separated.length - 1]);
    }

    public String getImageLink() {
        return String.format(Locale.ROOT,
                "https://starwars-visualguide.com/assets/img/characters/%d.jpg",
                splitIndex(url));
    }
}
