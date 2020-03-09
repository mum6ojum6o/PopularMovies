package com.mumbojumbo.popularmovies.repositories;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mumbojumbo.popularmovies.model.Result;
import com.mumbojumbo.popularmovies.retrofit.IRetrofitService;
import com.mumbojumbo.popularmovies.room.AppDatabase;
import com.mumbojumbo.popularmovies.room.entities.Movie;
import com.mumbojumbo.popularmovies.utils.AppExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private AppDatabase mDb;
    public LiveData<List<Movie>> mMovies;
    private IRetrofitService mRetroFitService;
    private static final String TAG="MovieRepository";

    public MovieRepository(Context context){
        this.mDb= AppDatabase.getInstance(context);
    }
    public void saveMovie(Movie aMovie){
        this.mDb.mMovieDao().save(aMovie);
    }

    public LiveData<List<Movie>> loadAllMovies(){
        if(mMovies!=null){
           return mMovies;
        }
        this.mMovies = new MutableLiveData<>(new ArrayList<Movie>());
        this.mMovies = this.mDb.mMovieDao().getAllPopularMovies();
        pullFromNetwork(1);
        return this.mMovies;
    }


    public void pullFromNetwork(int page){

        AppExecutor executor = AppExecutor.getInstance();
        buildRetrofitInstance();
        if(mRetroFitService!=null){
            mRetroFitService.popularMovies(page).enqueue(new Callback<Result>() {
                @Override //OnResponse and onFailure methods run on the main thread!!!!!!!
                public void onResponse(Call<Result> call, Response<Result> response) {

                    if(response.isSuccessful()){
                        Result result = response.body();
                        if(result!=null){
                            List<com.mumbojumbo.popularmovies.model.Movie> movies =
                                    result.getResults();
                            List<Movie> cachedMovies = new ArrayList<>();
                            for(com.mumbojumbo.popularmovies.model.Movie aMovie: movies){
                                cachedMovies.add(new Movie(aMovie.getId(),aMovie.getmOriginalTitle(),
                                        aMovie.getmOverview(),aMovie.getmPopularity(),aMovie.getmPoster(),
                                        aMovie.getmReleaseDate(),false,result.getmPage(),aMovie.getmVoteCount()));
                            }
                            executor.diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.mMovieDao().saveAll(cachedMovies);
                                }
                            });

                        }
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                        Log.i(TAG,"Crickey!! Something Went wrong while pulling data from the network");
                }
            });
        }else{
            Log.i(TAG,"Unable to create Retrofit Instance");
        }
    }

    private void buildRetrofitInstance(){
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IRetrofitService.DOMAIN_URL)
                .build();
        this.mRetroFitService = retrofit.create(IRetrofitService.class);
    }

}
