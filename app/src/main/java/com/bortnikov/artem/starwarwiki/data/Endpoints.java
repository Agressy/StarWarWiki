package com.bortnikov.artem.starwarwiki.data;

import com.bortnikov.artem.starwarwiki.data.model.retrofit.Example;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoints {

    @GET("people/")
    Flowable<Example> getPeopleResponse();

    @GET("people/")
    Flowable<Example> getSearchResponse(@Query("search") String search);


}
