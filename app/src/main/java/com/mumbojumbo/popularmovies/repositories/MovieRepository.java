package com.mumbojumbo.popularmovies.repositories;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mumbojumbo.popularmovies.model.Comment;
import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.model.Result;
import com.mumbojumbo.popularmovies.retrofit.IRetrofitService;
import com.mumbojumbo.popularmovies.room.AppDatabase;
//import com.mumbojumbo.popularmovies.room.entities.Movie;
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
    private MutableLiveData<List<Movie>> mTopRatedMovies;
    private MutableLiveData<List<Movie>> mPopularMovies;
    private IRetrofitService mRetroFitService;
    private static final String TAG="MovieRepository";
    private LiveData<List<Movie>> mFavorites;
    private MutableLiveData<List<Comment>> mComments;
    public MovieRepository(Context context){

        this.mDb= AppDatabase.getInstance(context);
    }
    public void saveMovie(Movie aMovie){
        AppExecutor executor = AppExecutor.getInstance();
        executor.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.mMovieDao().save(aMovie);
            }
        });
    }

    /*public LiveData<List<Movie>> loadAllMovies(){
        if(mMovies!=null){
           return mMovies;
        }
        this.mMovies = new MutableLiveData<>(new ArrayList<Movie>());
        this.mMovies = this.mDb.mMovieDao().getAllPopularMovies();
        pullFromNetwork(1);
        return this.mMovies;
    }*/

    public LiveData<List<Movie>> loadFavorites(){
        if(mFavorites!=null){
            return mFavorites;
        }
        mFavorites = new MutableLiveData<>(new ArrayList<Movie>());
        this.mFavorites = this.mDb.mMovieDao().getFavoriteMovies();
        return this.mFavorites;
    }

    public MutableLiveData<List<Movie>> getTopRatedMovies(int page){
        if(mTopRatedMovies == null){
            mTopRatedMovies = new MutableLiveData<>(new ArrayList<Movie>());
        }
        buildRetrofitInstance();
        if(mRetroFitService!=null){
            Log.i(TAG,"Getting Top Rated Movies for page:"+page);
            mRetroFitService.topRatedMovies(page).enqueue(new Callback<Result<Movie>>() {
                @Override
                public void onResponse(Call<Result<Movie>> call, Response<Result<Movie>> response) {
                    if(response.isSuccessful()){
                        if(response.body()!=null){
                            List<Movie> topRatedMovies = mTopRatedMovies.getValue();
                            topRatedMovies.addAll(response.body().getResults());
                            mTopRatedMovies.postValue(topRatedMovies);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Result<Movie>> call, Throwable t) {
                    Log.i(TAG,"Crickey!! Something Went wrong while pulling toprated movies from the network");
                }
            });
        }
        return mTopRatedMovies;
    }



    public MutableLiveData<List<Movie>> getPopularMovie(int page){
        if(mPopularMovies == null) {
            Log.i(TAG,"page:"+page+"mPopularMovies is not null");
            mPopularMovies = new MutableLiveData<>(new ArrayList<Movie>());
        }
        Log.i(TAG,"Pulling From Network:"+ page);
        buildRetrofitInstance();
        if(mRetroFitService!=null){
            mRetroFitService.popularMovies(page).enqueue(new Callback<Result<Movie>>() {
                @Override //OnResponse and onFailure methods run on the main thread!!!!!!!
                public void onResponse(Call<Result<Movie>> call, Response<Result<Movie>> response) {
                    Log.i(TAG,"Response receieved and was Successfull for page:"+page);
                    if(response.isSuccessful()){
                        Result result = response.body();
                        if(result!=null){
                            Log.i(TAG,"Response receieved and was Successfull");
                            List movies =
                                    result.getResults();

                            List<Movie> previousMovies = mPopularMovies.getValue();
                            previousMovies.addAll(movies);
                            mPopularMovies.postValue((List<Movie>)previousMovies);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Result<Movie>> call, Throwable t) {
                        Log.i(TAG,"Crickey!! Something Went wrong while pulling data from the network");
                }
            });
        }else{
            Log.i(TAG,"Unable to create Retrofit Instance");
        }
        return mPopularMovies;
    }

    public MutableLiveData<List<Comment>> getComments(final int movieId, final int page ){
        if(mComments==null){
            mComments = new MutableLiveData<>(new ArrayList<Comment>());
        }
        if(mRetroFitService==null)
            buildRetrofitInstance();

        mRetroFitService.getComments(movieId,page).enqueue(new Callback<Result<Comment>>() {
            @SuppressWarnings("uncheked")
            @Override
            public void onResponse(Call<Result<Comment>> call, Response<Result<Comment>> response) {
                if(response.isSuccessful()) {
                    mComments.setValue(response.body().getResults());

                }
            }

            @Override
            public void onFailure(Call<Result<Comment>> call, Throwable t) {
                Log.i(TAG,"Error while getting comments!");
            }
        });

        return mComments;
    }

    private void buildRetrofitInstance(){
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IRetrofitService.DOMAIN_URL)
                .build();
        this.mRetroFitService = retrofit.create(IRetrofitService.class);
    }

}
