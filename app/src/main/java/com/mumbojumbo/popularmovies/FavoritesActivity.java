package com.mumbojumbo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.mumbojumbo.popularmovies.adapters.FavoriteMoviesViewModel;
import com.mumbojumbo.popularmovies.adapters.MoviePostersAdapter;
import com.mumbojumbo.popularmovies.room.entities.Movie;
import com.mumbojumbo.popularmovies.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements MoviePostersAdapter.IGetMoreMovies{
    private RecyclerView mRecyclerView;
    private List<Movie> mFavoriteMovies;
    private MoviePostersAdapter mMoviePosterAdapter;
    private static final String TAG="FavoritesActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_favorites);
        setUpViewModel();
        mFavoriteMovies = new ArrayList<>();
        mMoviePosterAdapter = new MoviePostersAdapter(mFavoriteMovies,1,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mMoviePosterAdapter);
    }

    public void setUpViewModel(){
        FavoriteMoviesViewModel favoriteMoviesViewModel = new ViewModelProvider(this).get(FavoriteMoviesViewModel.class);
        favoriteMoviesViewModel.getFavoritesMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.i(TAG,"favorites updated!!");
                mFavoriteMovies.clear();
                mFavoriteMovies.addAll(movies);
                mMoviePosterAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void getMoviesFromPage(int page) {
        //do Nothing as we are fetching favorites which are cached.
    }
}
