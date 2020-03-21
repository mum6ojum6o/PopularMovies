package com.mumbojumbo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mumbojumbo.popularmovies.adapters.MovieReviewsAdapter;
import com.mumbojumbo.popularmovies.model.Comment;
import com.mumbojumbo.popularmovies.model.Movie;
//import com.mumbojumbo.popularmovies.room.entities.Movie;
import com.mumbojumbo.popularmovies.retrofit.MovieResultsFromNetwork;
import com.mumbojumbo.popularmovies.viewmodels.MovieCommentsViewModel;
import com.mumbojumbo.popularmovies.viewmodels.MovieReviewsViewModelFactory;
import com.mumbojumbo.popularmovies.viewmodels.MovieViewModel;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener, MovieReviewsAdapter.IMovieReviewsListener{
     ImageView mPoster;
     TextView mMovieTitle;
     TextView mSynopsis;
     TextView mRating;
     TextView mReleaseDate;
     TextView mReviews;
     RecyclerView mMovieMiscDetails;
     List<Comment> mReviewsList;
     MovieReviewsAdapter mMovieReviewAdapter;
   ImageView mFavorite;
    Movie mMovie;
    int mCurrentpage;
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
        mReviews = (TextView)findViewById(R.id.tv_reviews);
        mMovieMiscDetails = (RecyclerView)findViewById(R.id.rv_movie_misc);
        mMovieMiscDetails.setVisibility(View.INVISIBLE);

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
        mReviews.setOnClickListener(this);
        mMovieReviewAdapter = new MovieReviewsAdapter(mReviewsList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMovieMiscDetails.setLayoutManager(linearLayoutManager);
        mMovieMiscDetails.setAdapter(mMovieReviewAdapter);

    }
    private void populateUI(Bundle bundle){
        Movie movie=(Movie)(bundle.getParcelable(MovieResultsFromNetwork.MODEL_KEY));
        this.mMovie = movie;
        if(movie!=null) {
            mMovieTitle.setText(movie.getmOriginalTitle());
            mSynopsis.setText(movie.getmOverview());
            mRating.append(""+movie.getmPopularity());
            mReleaseDate.setText(movie.getmReleaseDate());
            mReviewsList = new ArrayList<Comment>();
            Glide.with(this)
                    .load(Uri.parse(MovieResultsFromNetwork.IMAGE_BASE_BACKDROP_URL+movie.getmBackdrop()))
                    .fitCenter()
                    //.placeholder(R.mipmap.ic_image_placedholder)
                    .error(R.mipmap.ic_image_placedholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPoster);
            /*if(mMovie.isFavorite()){
                mFavorite.setColorFilter(getResources().getColor(R.color.colorAccent));
            }*/
            setupViewModel();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_favorite:
                updateFavorite();
                break;
            case R.id.tv_reviews:
                showReviews();
        }
    }

    public void updateFavorite(){
        /*if(mMovie.isFavorite()){
            mMovie.setFavorite(false);
            mFavorite.clearColorFilter();

        }else{*/
        mMovie.setFavorite(true);
        mFavorite.setColorFilter(getResources().getColor(R.color.colorAccent));
        //}*/
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.updateMovie(mMovie);
    }

    /*public void launchReviews(){
        Intent intent = new Intent(MovieDetailActivity.this,MovieReviewsActivity.class);
        intent.putExtra(MovieReviewsActivity.MOVIEID_KEY,mMovie.getId());
        startActivity(intent);
    }*/


    public void showReviews(){
        if(mMovieMiscDetails.getVisibility()==View.INVISIBLE ) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                    RecyclerView.HORIZONTAL,false);
            mMovieMiscDetails.setLayoutManager(linearLayoutManager);
            mMovieMiscDetails.setAdapter(mMovieReviewAdapter);
            mMovieReviewAdapter.notifyDataSetChanged();
            mMovieMiscDetails.setVisibility(View.VISIBLE);
        }else{
            List<Comment> temp =new ArrayList<>(mReviewsList);
            mReviewsList.clear();
            mMovieReviewAdapter.notifyDataSetChanged();
            mMovieMiscDetails.setVisibility(View.INVISIBLE);
            mReviewsList.addAll(temp);
            temp.clear();
        }
    }
    public void setupViewModel(){
        MovieCommentsViewModel viewModel = new MovieReviewsViewModelFactory(this.getApplication(),mMovie.getId()).create(MovieCommentsViewModel.class);
        mCurrentpage = 1;
        viewModel.getComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                addRecentlyFetchedResponsesToComments(comments);
                updateViewsAssociatedWithReviews();
            }
        });
    }

    private void addRecentlyFetchedResponsesToComments(List<Comment> comments){
        for(Comment aComment :comments){
            if(!mReviewsList.contains(aComment)) mReviewsList.add(aComment);
        }
    }

    private void updateViewsAssociatedWithReviews(){
        StringBuilder sb = new StringBuilder();
        sb.append("Reviews(");sb.append(mReviewsList.size());sb.append(")");
        mReviews.setText(sb.toString());


        /*
        Ugly Code:
        this is needed because, if the recyclerview is being scrolled or is calculating while a
        notifyDataSetChanged call, an exception is thrown*/
        mMovieMiscDetails.post(new Runnable() {
            @Override
            public void run() {
                mMovieReviewAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void getMoreReviews() {
        MovieCommentsViewModel viewModel = new MovieReviewsViewModelFactory(this.getApplication(),mMovie.getId()).create(MovieCommentsViewModel.class);
        viewModel.getCommentsByPage(mMovie.getId(),++mCurrentpage).observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                addRecentlyFetchedResponsesToComments(comments);
                updateViewsAssociatedWithReviews();
            }
        });
    }
}
