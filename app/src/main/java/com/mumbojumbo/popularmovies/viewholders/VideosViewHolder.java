package com.mumbojumbo.popularmovies.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mumbojumbo.popularmovies.R;
import com.mumbojumbo.popularmovies.adapters.VideoAdapter;

import org.w3c.dom.Text;

public class VideosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mVideoName, mVideoType;
    VideoAdapter.IMovieVideoClickListener iMovieVideoClickListener;
    private String key;
    public VideosViewHolder(@NonNull View itemView, VideoAdapter.IMovieVideoClickListener iMovieVideoClickListener) {
        super(itemView);
        mVideoName = (TextView)itemView.findViewById(R.id.tv_video_name);
        mVideoType = (TextView)itemView.findViewById(R.id.tv_video_type);
        this.iMovieVideoClickListener = iMovieVideoClickListener;
        itemView.setOnClickListener(this);
    }
    public void bind(String videoName, String videoType, String key){
        this.key = key;
        mVideoName.setText(videoName);
        mVideoType.setText(videoType);
    }

    @Override
    public void onClick(View v) {
        iMovieVideoClickListener.onVideoItemClick((String)mVideoName.getText(),(String)mVideoType.getText(),this.key);
    }
}
