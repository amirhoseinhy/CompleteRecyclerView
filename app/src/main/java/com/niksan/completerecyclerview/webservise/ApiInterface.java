package com.niksan.completerecyclerview.webservise;

import com.niksan.completerecyclerview.model.Alphabet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/recycler_retrofit_loadimage/json2")
    Call<List<Alphabet>> getAlphabets();
}
