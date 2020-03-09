package com.mumbojumbo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.retrofit.MovieResultsFromNetwork;

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
        if(movie!=null) {
            mMovieTitle.setText(movie.getmOriginalTitle());
            mSynopsis.setText(movie.getmOverview());
            mRating.append(""+movie.getmPopularity());
            mReleaseDate.setText(movie.getmReleaseDate());
            Glide.with(this)
                    .load(Uri.parse(MovieResultsFromNetwork.IMAGE_BASE_URL+movie.getmPoster()))
                    .placeholder(R.mipmap.ic_image_placedholder)
                    .error(R.mipmap.ic_image_placedholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPoster);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_favorite:

        }
    }
}
