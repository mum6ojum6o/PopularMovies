package com.mumbojumbo.popularmovies;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.mumbojumbo.popularmovies.adapters.MoviePostersAdapter;
import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.viewmodels.MovieViewModel;
import com.mumbojumbo.popularmovies.viewmodels.TopRatedMoviesViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MoviePostersAdapter.IGetMoreMovies{
    private int mSortOption = 1; //1 for popular movies and 0 for top rated movies
    private static final String RESTORE_SORT_OPTIONS_KEY = "SORT_OPTION";

    private static final String TAG="MainActivity";
    /*@BindView(R.id.rv_movie_posters)*/ RecyclerView mRecyclerView;
    private static final String url="";
    private List<Movie> mMovies;
    private MoviePostersAdapter mMoviePostersAdapter;
    private MovieViewModel mMovieViewModel;
    private TopRatedMoviesViewModel mTopRatedMovieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"in onCreate()");
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
        if(this.mSortOption==1)
            mMovieViewModel.pullFromNetwork(page);
        else
            mTopRatedMovieModel.getMoreTopRatedMovies(page);
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
                    getSupportActionBar().setTitle(R.string.app_name);
                    mMoviePostersAdapter.resetPages();
                    setupViewModel();
                    fetchResultsBasedOnPreferences();
                 }

                break;
            case R.id.menu_item_ratings:
                if(this.mSortOption !=0) {
                    this.mSortOption = 0;
                    getSupportActionBar().setTitle(R.string.top_rated);
                    mMoviePostersAdapter.resetPages();
                    setupViewModel();
                    fetchResultsBasedOnPreferences();

                }

                break;
            case R.id.toolbar_icon_favorite:
                    startActivity(new Intent(MainActivity.this,FavoritesActivity.class));
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(this.RESTORE_SORT_OPTIONS_KEY,this.mSortOption);
    }

    private void setupViewModel(){
        if(this.mSortOption==1) {
            if(mMovieViewModel==null)
                mMovieViewModel = new ViewModelProvider(this)
                        .get(MovieViewModel.class);
        }
        else {
            if(this.mTopRatedMovieModel==null) {
                mTopRatedMovieModel = new ViewModelProvider(this)
                        .get(TopRatedMoviesViewModel.class);
            }
        }

        fetchResultsBasedOnPreferences();
    }

    private void fetchPopularMovies(){
        mMovieViewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.i(TAG,"OnChange:"+movies.size());
                mMoviePostersAdapter.setMovies(movies);
                mMoviePostersAdapter.notifyDataSetChanged();
            }
        });
    }

    private void fetchTopRatedMovies(){
        mTopRatedMovieModel.getTopRatedMovies().observe(this, (List<Movie> movie) -> {
           mMoviePostersAdapter.setMovies(movie);
           mMoviePostersAdapter.notifyDataSetChanged();
        });
    }
    private void fetchResultsBasedOnPreferences(){
        if(this.mSortOption==1){
            fetchPopularMovies();
        }else{
            fetchTopRatedMovies();
        }
    }

}
