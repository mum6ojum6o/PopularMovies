package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mumbojumbo.popularmovies.model.Videos;
import com.mumbojumbo.popularmovies.repositories.MovieRepository;

import java.util.List;

public class VideosViewModel extends AndroidViewModel {
    LiveData<List<Videos>> mVideos;
    MovieRepository mMovieRepository;
    public VideosViewModel(@NonNull Application application, int movieId) {
        super(application);
        mMovieRepository = new MovieRepository(application);
        mVideos = mMovieRepository.getVideos(movieId);
    }

    public LiveData<List<Videos>> getVideos(){
        return this.mVideos;
    }

}
