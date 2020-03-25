package com.mumbojumbo.popularmovies.retrofit;

import com.mumbojumbo.popularmovies.BuildConfig;
import com.mumbojumbo.popularmovies.model.Comment;
import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.model.Result;
import com.mumbojumbo.popularmovies.model.Videos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRetrofitService {
    String DOMAIN_URL="https://api.themoviedb.org/";
    @GET("3/movie/popular?api_key="+BuildConfig.API_KEY)
    Call<Result<Movie>> popularMovies(@Query("page") int page);

    @GET("3/movie/top_rated?api_key="+BuildConfig.API_KEY)
    Call<Result<Movie>>topRatedMovies(@Query("page")int page);

    @GET("3/movie/{movieId}/reviews?api_key="+BuildConfig.API_KEY)
    Call<Result<Comment>> getComments(@Path("movieId")int movieId,@Query("page")int page);

    @GET("3/movie/{movieId}/videos?api_key="+BuildConfig.API_KEY)
    Call<Result<Videos>> getVideos(@Path("movieId")int movieId);
}
