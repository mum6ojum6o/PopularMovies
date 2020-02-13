package com.mumbojumbo.popularmovies.retrofit;


import com.mumbojumbo.popularmovies.model.Result;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieResults {
    IRetrofitService mRetroFitService;
    Callback<Result> mCallback;
    public MovieResults(int page,Callback<Result> callback){
        mCallback = callback;
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IRetrofitService.DOMAIN_URL)
                .build();
        mRetroFitService = retrofit.create(IRetrofitService.class);
        mRetroFitService.popularMovies(page).enqueue(mCallback);
    }
}
