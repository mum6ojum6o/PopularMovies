package com.mumbojumbo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mumbojumbo.popularmovies.room.entities.Movie;
import com.mumbojumbo.popularmovies.retrofit.MovieResultsFromNetwork;
import com.mumbojumbo.popularmovies.viewmodels.MovieViewModel;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener{
     ImageView mPoster;
     TextView mMovieTitle;
     TextView mSynopsis;
     TextView mRating;
     TextView mReleaseDate;
   ImageView mFavorite;
    Movie mMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mMovieTitle = (TextView)findViewById(R.id.tv_movie_title);
        mSynopsis = (TextView)findViewById(R.id.tv_synopsis);
        mRating = (TextView)findViewById(R.id.tv_user_rating);
        mReleaseDate = (TextView)findViewById(R.id.tv_release_date);
        mPoster = (ImageView)findViewById(R.id.iv_movie_poster_detail);
        mFavorite = (ImageView)findViewById(R.id.iv_favorite);

        Intent activityLauncherIntent = this.getIntent();
        if(activityLauncherIntent!=null){
            if(activityLauncherIntent.hasExtra("bundle")){
                Bundle b = activityLauncherIntent.getBundleExtra("bundle");
                if(b!=null){
                    populateUI(b);

                }
            }
        }
        mFavorite.setOnClickListener(this);
    }
    private void populateUI(Bundle bundle){
        Movie movie=(Movie)(bundle.getParcelable(MovieResultsFromNetwork.MODEL_KEY));
        this.mMovie = movie;
        if(movie!=null) {
            mMovieTitle.setText(movie.getMovieTitle());
            mSynopsis.setText(movie.getOverview());
            mRating.append(""+movie.getPopularity());
            mReleaseDate.setText(movie.getReleaseDate());
            Glide.with(this)
                    .load(Uri.parse(MovieResultsFromNetwork.IMAGE_BASE_URL+movie.getPoster()))
                    .placeholder(R.mipmap.ic_image_placedholder)
                    .error(R.mipmap.ic_image_placedholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPoster);
            if(mMovie.isFavorite()){
                mFavorite.setColorFilter(getResources().getColor(R.color.colorAccent));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_favorite:
                updateFavorite();
        }
    }

    public void updateFavorite(){
        if(mMovie.isFavorite()){
            mMovie.setFavorite(false);
            mFavorite.clearColorFilter();

        }else{
            mMovie.setFavorite(true);
            mFavorite.setColorFilter(getResources().getColor(R.color.colorAccent));
        }
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.updateMovie(mMovie);
    }
}
