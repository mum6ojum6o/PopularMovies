package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.repositories.MovieRepository;

import java.util.List;

public class TopRatedMoviesViewModel extends AndroidViewModel {
    MovieRepository mMovieRepository;
    LiveData<List<Movie>> mTopRatedMovies;
    public TopRatedMoviesViewModel(@NonNull Application application) {
        super(application);
        mMovieRepository = new MovieRepository(application);
        mTopRatedMovies = mMovieRepository.getTopRatedMovies(1);
    }

    public LiveData<List<Movie>> getTopRatedMovies(){
        return this.mTopRatedMovies;
    }

    public void getMoreTopRatedMovies(int page){
        this.mMovieRepository.getTopRatedMovies(page);
    }
}
