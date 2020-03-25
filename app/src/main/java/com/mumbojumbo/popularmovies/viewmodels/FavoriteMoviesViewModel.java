package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.repositories.MovieRepository;

import java.util.List;

public class FavoriteMoviesViewModel extends AndroidViewModel {
    private static final String TAG = FavoriteMoviesViewModel.class.getSimpleName() ;
    LiveData<List<Movie>> mCachedFavorites;
    MovieRepository mMovieRepo;
    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        mMovieRepo = new MovieRepository(application);
        mCachedFavorites = mMovieRepo.loadFavorites();
    }
    public FavoriteMoviesViewModel(@NonNull Application application,int movieId) {
        super(application);
        mMovieRepo = new MovieRepository(application);
        mCachedFavorites = mMovieRepo.loadFavorites();
    }
    public LiveData<List<Movie>> getFavoritesMovies(){
        Log.i(TAG,"fetching Favorites!!");
        return this.mCachedFavorites;
    }
    public void updateMovie(Movie aMovie){
        mMovieRepo.updateMovie(aMovie);
    }
    public void insertMovie(Movie aMovie){
        mMovieRepo.insertMovie(aMovie);
    }
    public LiveData<Movie> getAFavorite(int movieId){
        return mMovieRepo.getAFavorite(movieId);
    }
}
