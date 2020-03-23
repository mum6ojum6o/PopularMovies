package com.mumbojumbo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mumbojumbo.popularmovies.adapters.MovieReviewsAdapter;
import com.mumbojumbo.popularmovies.adapters.VideoAdapter;
import com.mumbojumbo.popularmovies.model.Comment;
import com.mumbojumbo.popularmovies.model.Movie;
//import com.mumbojumbo.popularmovies.room.entities.Movie;
import com.mumbojumbo.popularmovies.model.Videos;
import com.mumbojumbo.popularmovies.retrofit.MovieResultsFromNetwork;
import com.mumbojumbo.popularmovies.viewmodels.FavoriteMoviesViewModel;
import com.mumbojumbo.popularmovies.viewmodels.MovieCommentsViewModel;
import com.mumbojumbo.popularmovies.viewmodels.MovieReviewsViewModelFactory;
import com.mumbojumbo.popularmovies.viewmodels.MovieViewModel;
import com.mumbojumbo.popularmovies.viewmodels.VideosViewModel;
import com.mumbojumbo.popularmovies.viewmodels.VideosViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener, MovieReviewsAdapter.IMovieReviewsListener, VideoAdapter.IMovieVideoClickListener{
    private static final String TAG = "MovieDetailActivity";
    ImageView mPoster;
     TextView mMovieTitle;
     TextView mSynopsis;
     TextView mRating;
     TextView mReleaseDate;
     TextView mReviews;
     TextView mVideos;
     RecyclerView mMovieMiscDetails;
     List<Comment> mReviewsList;
     List<Videos> mVideoList;

     MovieReviewsAdapter mMovieReviewAdapter;
     FavoriteMoviesViewModel favoriteMoviesViewModel;
     VideoAdapter mVideoAdapter;
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
        mVideos = (TextView)findViewById(R.id.tv_trailers);
        mMovieMiscDetails = (RecyclerView)findViewById(R.id.rv_movie_misc);
        mMovieMiscDetails.setVisibility(View.INVISIBLE);
        mMovieReviewAdapter = new MovieReviewsAdapter(new ArrayList<Comment>(),this);
        mVideoAdapter = new VideoAdapter(new ArrayList<Videos>(), this);
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
        mVideos.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMovieMiscDetails.setLayoutManager(linearLayoutManager);
        //


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
                break;
            case R.id.tv_trailers:
                showVideos();
                break;
        }
    }

    public void updateFavorite(){
        if(mMovie.getTableId()!=0){
            if(mMovie.isFavorite()) {
                mMovie.setFavorite(false);
                mFavorite.clearColorFilter();
            }else{
                mMovie.setFavorite(true);
                mFavorite.setColorFilter(getResources().getColor(R.color.colorAccent));
            }
            favoriteMoviesViewModel.updateMovie(mMovie);

        }else{
            mMovie.setFavorite(true);
            mFavorite.setColorFilter(getResources().getColor(R.color.colorAccent));
            favoriteMoviesViewModel.insertMovie(mMovie);
        }
    }

    public void showVideos(){
        if(mMovieMiscDetails.getVisibility()==View.INVISIBLE ) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                    RecyclerView.HORIZONTAL,false);
            mMovieMiscDetails.setLayoutManager(linearLayoutManager);
            mMovieMiscDetails.setAdapter(mVideoAdapter);
            mVideoAdapter.setmVideos(mVideoList);
            mVideoAdapter.notifyDataSetChanged();
            mMovieMiscDetails.setVisibility(View.VISIBLE);
        }else{
            mMovieMiscDetails.setVisibility(View.INVISIBLE);
        }
    }


    public void showReviews(){
        if(mMovieMiscDetails.getVisibility()==View.INVISIBLE) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                    RecyclerView.HORIZONTAL,false);
            mMovieMiscDetails.setLayoutManager(linearLayoutManager);
            mMovieMiscDetails.setAdapter(mMovieReviewAdapter);
            mMovieReviewAdapter.setReview(mReviewsList);
            mMovieReviewAdapter.notifyDataSetChanged();
            mMovieMiscDetails.setVisibility(View.VISIBLE);
        }else{

            mMovieReviewAdapter.setReview(new ArrayList<>());
            mMovieReviewAdapter.notifyDataSetChanged();
            mMovieMiscDetails.setVisibility(View.INVISIBLE);

        }
    }
    public void setupViewModel(){

        setupMovieCommentsViewModel();
        setupVideoViewModel();
        setUpFavoritesViewModel();

    }

    private void setupVideoViewModel(){
        VideosViewModel videoViewModel = new VideosViewModelFactory(this.getApplication(),mMovie.getMovieId()).create(VideosViewModel.class);
        videoViewModel.getVideos().observe(this,(List<Videos> videos)->{
            mVideoAdapter.setmVideos(videos);
            mVideos.setText("Trailers ("+videos.size()+")");
            mVideoList = videos;
            mMovieMiscDetails.setAdapter(mVideoAdapter);
            mVideoAdapter.notifyDataSetChanged();
        });
    }

    private void setupMovieCommentsViewModel(){
        MovieCommentsViewModel viewModel = new MovieReviewsViewModelFactory(this.getApplication(),mMovie.getMovieId()).create(MovieCommentsViewModel.class);
        mCurrentpage = 1;
        viewModel.getComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                updateViewsAssociatedWithReviews(comments);
            }
        });
    }

    private void setUpFavoritesViewModel(){
        favoriteMoviesViewModel = new ViewModelProvider(this).get(FavoriteMoviesViewModel.class);
        favoriteMoviesViewModel.getAFavorite(mMovie.getMovieId()).observe(this,(Movie aMovie)->{
            if(aMovie!= null){
                Log.i(TAG,"Favorites onChanged: isFavorite:"+aMovie.isFavorite());
                mMovie.setFavorite(aMovie.isFavorite());
                mMovie.setTableId(aMovie.getTableId());
                if(aMovie.isFavorite()) {
                    mFavorite.setColorFilter(getResources().getColor(R.color.colorAccent));
                }
            }
        });
    }

    private void updateViewsAssociatedWithReviews(List<Comment> comments){
        StringBuilder sb = new StringBuilder();
        sb.append("Reviews(");sb.append(comments.size());sb.append(")");
        mReviews.setText(sb.toString());
        mMovieReviewAdapter.setReview(comments);
        mReviewsList = comments;
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
        MovieCommentsViewModel viewModel = new MovieReviewsViewModelFactory(this.getApplication(),mMovie.getMovieId())
                .create(MovieCommentsViewModel.class);
        viewModel.getCommentsByPage(mMovie.getMovieId(),++mCurrentpage).observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {

                updateViewsAssociatedWithReviews(comments);
            }
        });
    }

    @Override
    public void onVideoItemClick(String videoName, String videoType, String key) {
        //Toast.makeText(this,"Clicked on Video", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("vnd.youtube:" + key));
        if(packageAvailable(intent))
            startActivity(intent);
        else {
            intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + key));
            if(packageAvailable(intent))
                startActivity(intent);
        }
    }

    private boolean packageAvailable(Intent intent){
        return getPackageManager().resolveActivity(intent,0)!=null;
    }
}
