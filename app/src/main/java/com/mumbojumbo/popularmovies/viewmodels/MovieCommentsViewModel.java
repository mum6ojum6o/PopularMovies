package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.mumbojumbo.popularmovies.model.Comment;
import com.mumbojumbo.popularmovies.model.Result;
import com.mumbojumbo.popularmovies.repositories.MovieRepository;

import java.util.List;

public class MovieCommentsViewModel extends AndroidViewModel {
    private static final String TAG = "CommentsViewModel";
    MutableLiveData<Result<Comment>> mComments;
    MovieRepository movieRepo;
    public MovieCommentsViewModel(@NonNull Application application, int movieId) {
        super(application);
        Log.i(TAG,"Fetching Comments for "+movieId);
        movieRepo = new MovieRepository(application);
        mComments = movieRepo.getComments(movieId,1);
    }

    public LiveData<Result<Comment>> getComments(){
        return mComments;
    }
    public LiveData<Result<Comment>> getCommentsByPage(int movieId, int page){
        return movieRepo.getComments(movieId,page);
    }
}
