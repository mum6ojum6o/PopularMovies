package com.mumbojumbo.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mumbojumbo.popularmovies.adapters.MoviePostersAdapter;
import com.mumbojumbo.popularmovies.repositories.MovieRepository;
import com.mumbojumbo.popularmovies.room.entities.Movie;
import com.mumbojumbo.popularmovies.model.Result;
import com.mumbojumbo.popularmovies.retrofit.MovieResultsFromNetwork;
import com.mumbojumbo.popularmovies.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import retrofit2.*;

public class MainActivity extends AppCompatActivity
        implements MoviePostersAdapter.IGetMoreMovies{
    private int mSortOption = 1; //1 for popular movies and 0 for top rated movies
    private static final String RESTORE_SORT_OPTIONS_KEY = "SORT_OPTION";

    private static final String TAG="MainActivity";
    /*@BindView(R.id.rv_movie_posters)*/ RecyclerView mRecyclerView;
    private static final String url="";
    private List<Movie> mMovies;
    private Result mResult;
    private MoviePostersAdapter mMoviePostersAdapter;
    private MovieResultsFromNetwork mMovieResultsFromNetwork;
    private MovieViewModel mMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_movie_posters);
        this.mMovies = new ArrayList<Movie>();

        GridLayoutManager manager = new GridLayoutManager(MainActivity.this,4);
        mMoviePostersAdapter = new MoviePostersAdapter(mMovies,1,this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMoviePostersAdapter);
        if(savedInstanceState!=null){
            this.mSortOption = savedInstanceState.getInt(RESTORE_SORT_OPTIONS_KEY,1);
        }
        setupViewModel();
    }

    @Override
    public void onResume(){
        super.onResume();

    }


    @Override
    public void getMoviesFromPage(int page) {
        mMovieViewModel.pullFromNetwork(page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        super.onOptionsItemSelected(menuItem);
        switch (menuItem.getItemId()){
            case R.id.menu_item_popularity:
                if(this.mSortOption !=1) {
                    this.mSortOption =1;
                    /*this.mMovies.clear();*/

                }
                break;
            case R.id.menu_item_ratings:
                if(this.mSortOption !=0) {
                    this.mSortOption = 0;
                    /*this.mMovies.clear();*/

                }
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(this.RESTORE_SORT_OPTIONS_KEY,this.mSortOption);
    }

    public void setupViewModel(){
         mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mMovieViewModel.getmCachedMovies().observe(this, new Observer<List<com.mumbojumbo.popularmovies.room.entities.Movie>>() {
            @Override
            public void onChanged(List<com.mumbojumbo.popularmovies.room.entities.Movie> movies) {
                Log.i(TAG,"OnChange:"+movies.toString());
                mMovies.addAll(movies);
                mMoviePostersAdapter.notifyDataSetChanged();
            }
        });
    }

}
