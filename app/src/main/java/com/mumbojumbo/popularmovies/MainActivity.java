package com.mumbojumbo.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mumbojumbo.popularmovies.adapters.MoviePostersAdapter;
import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.model.Result;
import com.mumbojumbo.popularmovies.retrofit.MovieResultsFromNetwork;

import java.util.ArrayList;
import java.util.List;
import retrofit2.*;

public class MainActivity extends AppCompatActivity
        implements Callback<Result>,MoviePostersAdapter.IGetMoreMovies{
    private int mSortOption = 1; //1 for popular movies and 0 for top rated movies
    private static final String RESTORE_SORT_OPTIONS_KEY = "SORT_OPTION";

    private static final String TAG="MainActivity";
    /*@BindView(R.id.rv_movie_posters)*/ RecyclerView mRecyclerView;
    private static final String url="";
    private List<Movie> mMovies;
    private Result mResult;
    private MoviePostersAdapter mMoviePostersAdapter;
    private MovieResultsFromNetwork mMovieResultsFromNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        mRecyclerView = findViewById(R.id.rv_movie_posters);
        this.mMovies = new ArrayList<Movie>();
        this.mMovieResultsFromNetwork = new MovieResultsFromNetwork(this);
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this,4);
        mMoviePostersAdapter = new MoviePostersAdapter(mMovies,1,this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMoviePostersAdapter);
        if(savedInstanceState!=null){
            this.mSortOption = savedInstanceState.getInt(RESTORE_SORT_OPTIONS_KEY,1);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mSortOption ==1)
            this.mMovieResultsFromNetwork.getPopularMovies(1);
        else
            this.mMovieResultsFromNetwork.getTopRatedMovies(1);
    }

    @Override
    public void onResponse(Call<Result> call, Response<Result> response) {
        if(response.isSuccessful()) {
            mResult = response.body();
            mMovies.addAll(mResult.getResults());
            mMoviePostersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<Result> call, Throwable t) {
        Log.i(TAG,"Something went wrong!!");
    }

    @Override
    public void getMoviesFromPage(int page) {
        this.mMovieResultsFromNetwork.getPopularMovies(page);
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
                    this.mMovies.clear();
                    this.mMovieResultsFromNetwork.getPopularMovies(1);
                }
                break;
            case R.id.menu_item_ratings:
                if(this.mSortOption !=0) {
                    this.mSortOption = 0;
                    this.mMovies.clear();
                    this.mMovieResultsFromNetwork.getTopRatedMovies(1);
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



}
