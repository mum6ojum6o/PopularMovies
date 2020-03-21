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
    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        MovieRepository movieRepo = new MovieRepository(application);
        mCachedFavorites = movieRepo.loadFavorites();
    }
    public LiveData<List<Movie>> getFavoritesMovies(){
        Log.i(TAG,"fetching Favorites!!");
        return this.mCachedFavorites;
    }
}
