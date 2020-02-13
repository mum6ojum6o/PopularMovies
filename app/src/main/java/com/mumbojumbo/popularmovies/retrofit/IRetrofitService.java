package com.mumbojumbo.popularmovies.retrofit;

import com.mumbojumbo.popularmovies.BuildConfig;
import com.mumbojumbo.popularmovies.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitService {
    final String DOMAIN_URL="https://api.themoviedb.org/";
    @GET("3/movie/popular?api_key="+BuildConfig.API_KEY)
    public Call<Result> popularMovies(@Query("page")int page);
}
