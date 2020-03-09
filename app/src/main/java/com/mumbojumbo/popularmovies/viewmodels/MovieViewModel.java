package com.mumbojumbo.popularmovies.viewmodels;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.mumbojumbo.popularmovies.repositories.MovieRepository;
import com.mumbojumbo.popularmovies.room.entities.Movie;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    LiveData<List<Movie>> mCachedMovies;
    public static final String TAG="MovieViewModel";
    public MovieViewModel(Application application){
        super(application);
        Log.i(TAG,"Loading from the cache");
        mCachedMovies = new MovieRepository(application).loadAllMovies();
    }
    public LiveData<List<Movie>> getmCachedMovies(){
        return this.mCachedMovies;
    }
    public void pullFromNetwork(int page){
        new MovieRepository(getApplication()).pullFromNetwork(page);
    }

}
