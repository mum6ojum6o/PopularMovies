package com.mumbojumbo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.model.Result;
import com.mumbojumbo.popularmovies.retrofit.MovieResults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.*;

public class MainActivity extends AppCompatActivity implements Callback<Result> {
    private static final String TAG="MainActivity";
    @BindView(R.id.rv_movie_posters) RecyclerView mRecyclerView;
    private static final String url="";
    private List<Movie> mMovies;
    private Result mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mMovies = new ArrayList<Movie>();
        new MovieResults(1,this);
    }

    @Override
    public void onResponse(Call<Result> call, Response<Result> response) {
        mResult = response.body();
    }

    @Override
    public void onFailure(Call<Result> call, Throwable t) {
        Log.i(TAG,"Somethign went wrong!!");
    }

}
