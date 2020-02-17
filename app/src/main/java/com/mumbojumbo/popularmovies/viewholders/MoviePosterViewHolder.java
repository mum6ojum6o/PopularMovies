package com.mumbojumbo.popularmovies.viewholders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mumbojumbo.popularmovies.MovieDetailActivity;
import com.mumbojumbo.popularmovies.R;
import com.mumbojumbo.popularmovies.adapters.MoviePostersAdapter;
import com.mumbojumbo.popularmovies.model.Movie;
import com.mumbojumbo.popularmovies.retrofit.MovieResultsFromNetwork;


public class MoviePosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView mImageView;
    private Movie mData;
     public MoviePosterViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView= itemView.findViewById(R.id.iv_image_poster);
        itemView.setOnClickListener(this);
    }
    public void setImage(String uri){
        String poster_path = "http://image.tmdb.org/t/p/w185/"+uri;
        Glide.with(itemView.getContext())
            .load(Uri.parse(poster_path))
                .centerCrop()
                .placeholder(R.mipmap.ic_image_placedholder)
                .error(R.mipmap.ic_image_placedholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }
    public void bind(Movie data){
        this.mData = data;
        setImage(mData.getmPoster());
    }

    @Override
    public void onClick(View v) {
         Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
         Bundle bundle = new Bundle();
         bundle.putParcelable(MovieResultsFromNetwork.MODEL_KEY,mData);
         intent.putExtra("bundle",bundle);
        v.getContext().startActivity(intent);
    }
}
