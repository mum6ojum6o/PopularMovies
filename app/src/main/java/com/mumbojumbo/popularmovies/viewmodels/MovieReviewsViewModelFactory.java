package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieReviewsViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int movieId;
    private static final String TAG = "MovieReviewsViewModelFactory";
    MovieCommentsViewModel movieCommentsViewModel;
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) movieCommentsViewModel;
    }
    public MovieReviewsViewModelFactory(Application application, int movieId){
        Log.i(TAG,":movieId:"+movieId);
        movieCommentsViewModel = new MovieCommentsViewModel(application,movieId);
    }
}
