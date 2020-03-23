package com.mumbojumbo.popularmovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mumbojumbo.popularmovies.R;
import com.mumbojumbo.popularmovies.model.Comment;
import com.mumbojumbo.popularmovies.viewholders.MovieReviewsViewHolder;

import java.util.List;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsViewHolder> {
    List<Comment> mReviews;
    IMovieReviewsListener movieReviewsListener;
    public MovieReviewsAdapter(List<Comment> mReviews,IMovieReviewsListener movieReviewsListener) {
        this.mReviews = mReviews;
        this.movieReviewsListener = movieReviewsListener;
    }

    @NonNull
    @Override
    public MovieReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewHolderLayout = inflater.inflate(R.layout.comment_list_item,parent,false);
        return new MovieReviewsViewHolder(viewHolderLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewsViewHolder holder, int position) {
        holder.populateUI(mReviews.get(position).getmAuthor(),mReviews.get(position).getmContent());
        if(position == mReviews.size()-2){
            movieReviewsListener.getMoreReviews();
        }
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public interface IMovieReviewsListener{
        public void getMoreReviews();
    }

    public void setReview(List<Comment> comments){
        this.mReviews = comments;
    }
}
