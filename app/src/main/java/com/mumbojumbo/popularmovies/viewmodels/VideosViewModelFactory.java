package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class VideosViewModelFactory implements ViewModelProvider.Factory {
    private static final String TAG = "VideosViewModelFactory";
    VideosViewModel mVideosViewModel;
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)mVideosViewModel;
    }

    public VideosViewModelFactory(Application mApplication, int movieId) {
        mVideosViewModel = new VideosViewModel(mApplication,movieId);


    }
}
