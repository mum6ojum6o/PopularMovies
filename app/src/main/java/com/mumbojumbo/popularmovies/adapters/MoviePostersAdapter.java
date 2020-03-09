package com.mumbojumbo.popularmovies.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mumbojumbo.popularmovies.R;
import com.mumbojumbo.popularmovies.room.entities.Movie;
import com.mumbojumbo.popularmovies.viewholders.MoviePosterViewHolder;

import java.util.List;

public class MoviePostersAdapter extends RecyclerView.Adapter<MoviePosterViewHolder> {
    List<Movie> mMovies;
    int mPagesLoaded;
    private IGetMoreMovies moreMoviesFetcher;
    public MoviePostersAdapter(List<Movie> mMovies,
                               int pagesLoaded,
                               IGetMoreMovies movieFetcher) {
       this.mMovies = mMovies;
       this.mPagesLoaded = pagesLoaded;
       this.moreMoviesFetcher = movieFetcher;
       //this.mPosterClickListener = moviePosterClickListener;
    }
    @NonNull
    @Override
    public MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =   LayoutInflater.from(parent.getContext());
        View layout = layoutInflater.inflate(R.layout.movie_list_item,parent,false);
        return new MoviePosterViewHolder(layout);
    }

    public void resetPages(){
        mPagesLoaded = 1;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePosterViewHolder holder, int position) {
        if(mMovies.get(position)!=null)
        holder.bind(mMovies.get(position));
        else
            Glide
                    .with(holder.itemView.getContext())
                    .clear(holder.mImageView);
        if(position>=mMovies.size()-5){
            if(moreMoviesFetcher!=null){
                mPagesLoaded++;
                moreMoviesFetcher.getMoviesFromPage(mPagesLoaded);
            }
        }
    }

    @Override
    public int getItemCount() {
        if(mMovies!=null)
            return mMovies.size();
        return 0;
    }
    public interface IGetMoreMovies{
        void getMoviesFromPage(int page);
    }


    public interface IMoviePosterClickListener{
        void onClick();
    }

}
