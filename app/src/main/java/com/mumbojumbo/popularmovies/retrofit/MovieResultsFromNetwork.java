package com.mumbojumbo.popularmovies.retrofit;


import com.mumbojumbo.popularmovies.model.Result;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieResultsFromNetwork {
    public static final String MODEL_KEY="MODEL";
    public static final String IMAGE_BASE_URL="http://image.tmdb.org/t/p/w185/";
    public static final String IMAGE_BASE_BACKDROP_URL="http://image.tmdb.org/t/p/w1280/";
    IRetrofitService mRetroFitService;
    Callback<Result> mCallback;
    public MovieResultsFromNetwork(Callback<Result> callback){
        mCallback = callback;
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IRetrofitService.DOMAIN_URL)
                .build();
        mRetroFitService = retrofit.create(IRetrofitService.class);

    }
    public void getPopularMovies(int page){
        //mRetroFitService.popularMovies(page).enqueue(mCallback);
    }
    public void getTopRatedMovies(int page){
        //mRetroFitService.topRatedMovies(page).enqueue(mCallback);
    }
}
