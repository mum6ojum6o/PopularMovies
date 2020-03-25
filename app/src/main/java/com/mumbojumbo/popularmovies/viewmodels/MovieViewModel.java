package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    LiveData<List<Movie>> mMovies;
    MovieRepository mMovieRepository;
    public static final String TAG="MovieViewModel";
    public MovieViewModel(Application application){
        super(application);
        Log.i(TAG,"Loading from the cache");
        mMovieRepository = new MovieRepository(application);
        mMovies = mMovieRepository.getPopularMovie(1);

    }
    public LiveData<List<Movie>> getmCachedMovies(){
        return this.mMovies;
    }
    public void pullFromNetwork(int page){
        Log.i(TAG,"pull From Network page:"+page);
        mMovieRepository.getPopularMovie(page);
    }

    public LiveData<List<Movie>> getPopularMovies(){
        return this.mMovies;
    }
    public void insertMovie(Movie aMovie){
        mMovieRepository.insertMovie(aMovie);
    }
    public void updateMovie(Movie aMovie){
        mMovieRepository.updateMovie(aMovie);
    }


}
