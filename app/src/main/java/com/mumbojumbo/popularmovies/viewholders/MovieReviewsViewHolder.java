package com.mumbojumbo.popularmovies.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mumbojumbo.popularmovies.R;


public class MovieReviewsViewHolder extends RecyclerView.ViewHolder {
    public TextView mAuthor;
    public TextView mContent;
    public MovieReviewsViewHolder(@NonNull View itemView) {
        super(itemView);

        mAuthor = (TextView)itemView.findViewById(R.id.tv_author);
        mContent = (TextView)itemView.findViewById(R.id.tv_review);

    }

    public void populateUI(String authorName, String reviewContent){
        mAuthor.setText(authorName);
        mContent.setText(reviewContent);
    }

}
