package com.mumbojumbo.popularmovies.retrofit;

import com.mumbojumbo.popularmovies.BuildConfig;
import com.mumbojumbo.popularmovies.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitService {
    String DOMAIN_URL="https://api.themoviedb.org/";
    @GET("3/movie/popular?api_key="+BuildConfig.API_KEY)
    Call<Result> popularMovies(@Query("page") int page);

    @GET("3/movie/top_rated?api_key="+BuildConfig.API_KEY)
    Call<Result>topRatedMovies(@Query("page")int page);
}
