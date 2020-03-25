package com.mumbojumbo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.viewmodels.FavoriteMoviesViewModel;
import com.mumbojumbo.popularmovies.adapters.MoviePostersAdapter;

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

        mMoviePosterAdapter = new MoviePostersAdapter(new ArrayList<>(),1,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mMoviePosterAdapter);
        getSupportActionBar().setTitle(R.string.favorite);
        setUpViewModel();
    }

    public void setUpViewModel(){
        FavoriteMoviesViewModel favoriteMoviesViewModel = new ViewModelProvider(this).get(FavoriteMoviesViewModel.class);
        favoriteMoviesViewModel.getFavoritesMovies().observe(this, (List<Movie> movies) -> {
            /*@Override
            public void onChanged(List<Movie> movies) {*/
            Log.i(TAG, "favorites updated!!");
            mMoviePosterAdapter.setMovies(movies);
            mMoviePosterAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void getMoviesFromPage(int page) {
        //do Nothing as we are fetching favorites which are cached.
    }
}
